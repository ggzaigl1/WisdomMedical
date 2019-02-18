package com.hjy.wisdommedical.ui.shopping.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bumptech.glide.Glide;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.ProductInfoBean;
import com.example.handsomelibrary.model.ShopingCartBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.home.activity.LocalImageGoodsListHolderView;
import com.hjy.wisdommedical.ui.shopping.ShoppingMainActivity;
import com.hjy.wisdommedical.ui.shopping.ShoppingTrolleyActivity;
import com.hjy.wisdommedical.ui.shopping.home.adapter.GoodsDetailAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 商品详情
 * Created by Stefan on 2018/10/16 15:10.
 */
public class GoodsDetailActivity extends BaseActivity {

    @BindView(R.id.tvCartNum)
    TextView tvCartNum;
    @BindView(R.id.rv_evaList)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_buyNow)
    TextView tv_buyNow;
    @BindView(R.id.Rl_relationship)
    RelativeLayout Rl_relationship;
    @BindView(R.id.iv_collect)
    ImageView mIvCollect;
    List<String> bannerBeans;
    ConvenientBanner bannerView;
    GoodsDetailAdapter mAdapter;
    View headerView;
    View footerView;

    private int mId;
    private int mAmount;
    private int mPriceNow;
    private int mIsCollect;
    private int mTotal;
    private String mPdSmallPic;
    private String mPdCom;

    @Override
    protected int getContentView() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        mId = getIntent().getIntExtra("id", -1);//商品id
        mPriceNow = getIntent().getIntExtra("priceNow", -1);//价格
        mAmount = getIntent().getIntExtra("Amount", -1);//数量
        mPdSmallPic = getIntent().getStringExtra("pdSmallPic");//图片
        mPdCom = getIntent().getStringExtra("pdCom");

        headerView = LayoutInflater.from(mContext).inflate(R.layout.goods_detail_banner, Rl_relationship, false);
        footerView = LayoutInflater.from(mContext).inflate(R.layout.goods_detail_footer, Rl_relationship, false);

        listComment(mId);
        setRecycler();
    }

    private void setRecycler() {
        mAdapter = new GoodsDetailAdapter(new ArrayList<>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.addHeaderView(headerView);
        mAdapter.addFooterView(footerView);
    }

    @Override
    @OnClick({R.id.iv_back, R.id.lookCart, R.id.tv_addShopCart, R.id.tv_buyNow, R.id.iv_collect})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.tv_buyNow:
                Bundle bundle = new Bundle();
                bundle.putInt("id", mId);
                bundle.putInt("priceNow", mPriceNow);
                bundle.putString("pdSmallPic", mPdSmallPic);
                bundle.putString("pdCom", mPdCom);
                if (mAmount == -1) {
                    bundle.putInt("Amount", 1);
                } else {
                    bundle.putInt("Amount", mAmount);
                }
                JumpUtils.jump(this, PaymentOrderActivity.class, bundle);
                break;
            case R.id.tv_addShopCart://添加商品到购物车
                addCart(SpfUtils.getSpfSaveInt(Constant.userId), mId);
                break;
            case R.id.lookCart:////商品详情 点击购物车 跳转到 商城 购物车
//                Bundle bundle1 = new Bundle();
//                bundle1.putBoolean("ShoppingMainActivity", true);
//                JumpUtils.jump(mContext, ShoppingMainActivity.class, bundle1);
                JumpUtils.jump(mContext, ShoppingTrolleyActivity.class, null);
                break;
            case R.id.iv_collect:
                if (mIsCollect == 1) {
                    getCancleCollect(SpfUtils.getSpfSaveInt(Constant.userId), mId);
                } else {
                    getCollect(SpfUtils.getSpfSaveInt(Constant.userId), mId);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        getListCart();
    }

    /**
     * 商品加入购物车
     *
     * @param cusId
     * @param productId
     */
    @SuppressLint({"CheckResult", "SetTextI18n"})
    private void addCart(long cusId, long productId) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .addCart(cusId, productId)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Consumer<Object>) loginBean -> {
                    mKProgressHUD.dismiss();
//                    if (mTotal == 0) {
//                        tvCartNum.setVisibility(View.VISIBLE);
//                        tvCartNum.setText(mTotal + 1 + "");
                    T.showShort("商品已加入购物车");
//                    } else {
//                        tvCartNum.setText(mTotal + 1 + "");
//                    }
                }, throwable -> {
                    mKProgressHUD.dismiss();
                    T.showShort("网络连接异常");
                });
    }

    /**
     * 查看购物车
     */
    @SuppressLint("CheckResult")
    private void getListCart() {
        ArrayMap<String, Object> objectMap = new ArrayMap<>();
        objectMap.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        RxHttpUtils.createApi(ApiService.class)
                .listCart(objectMap)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BaseBean<ShopingCartBean>>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    protected void onSuccess(BaseBean<ShopingCartBean> shopingCartBeanBaseBean) {
                        mTotal = shopingCartBeanBaseBean.getRows().getTotal();
                        if (shopingCartBeanBaseBean.getRows().getTotal() != 0) {
                            tvCartNum.setVisibility(View.VISIBLE);
                            tvCartNum.setText(shopingCartBeanBaseBean.getRows().getTotal() + "");
                        } else {
                            tvCartNum.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        T.showShort(errorMsg);
                    }
                });
    }

    /**
     * 收藏
     *
     * @param cusId
     * @param productId
     */
    @SuppressLint("CheckResult")
    private void getCollect(long cusId, long productId) {
        RxHttpUtils.createApi(ApiService.class)
                .getCollect(cusId, productId)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object loginBean) throws Exception {
                        listComment(mId);
                        T.showShort("收藏成功");
                    }
                }, throwable -> {
                    T.showShort("网络连接异常");
                });
    }

    /**
     * 取消收藏
     *
     * @param cusId
     * @param productId
     */
    @SuppressLint("CheckResult")
    private void getCancleCollect(long cusId, long productId) {
        RxHttpUtils.createApi(ApiService.class)
                .getCancleCollect(cusId, productId)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object loginBean) throws Exception {
                        listComment(mId);
                        T.showShort("取消收藏成功");
                    }
                }, throwable -> {
                    T.showShort("网络连接异常");
                });
    }

    /**
     * 商品评价
     *
     * @param productId
     */
    @SuppressLint({"CheckResult", "SetTextI18n"})
    private void listComment(int productId) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .getProductInfo(productId, SpfUtils.getSpfSaveInt(Constant.userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BaseBean<ProductInfoBean>>() {
                    @Override
                    protected void onSuccess(BaseBean<ProductInfoBean> productInfoBeanBaseBean) {
                        if (productInfoBeanBaseBean.getRows().getProduct() != null) {
                            mKProgressHUD.dismiss();
                            mIsCollect = productInfoBeanBaseBean.getRows().getIsCollect();
                            if (mIsCollect == 1) {
                                mIvCollect.setImageResource(R.drawable.svg_heart_collect);
                            } else {
                                mIvCollect.setImageResource(R.drawable.svg_heart);
                            }
                            mAdapter.setNewData(productInfoBeanBaseBean.getRows().getListEvaluation());
                            TextView tvPrice = headerView.findViewById(R.id.tv_priceNow);
                            TextView tvPdCom = headerView.findViewById(R.id.tv_pdCom);
                            TextView tv_evaluation = headerView.findViewById(R.id.tv_evaluation);
                            tvPrice.setText("￥" + productInfoBeanBaseBean.getRows().getProduct().getPriceNow());
                            tvPdCom.setText(productInfoBeanBaseBean.getRows().getProduct().getPdCom());
                            bannerView = headerView.findViewById(R.id.convenientBanner);
                            String pdBigPic = productInfoBeanBaseBean.getRows().getProduct().getPdBigPic();
                            bannerBeans = new ArrayList<>();
                            if (!TextUtils.isEmpty(pdBigPic)) {
                                if (pdBigPic.contains(",")) {
                                    //banner 截取 循环添加
                                    String[] split = pdBigPic.split(",");
                                    Collections.addAll(bannerBeans, split);
                                    //商品详情 大图 暂时不知道咋搞 先截取再说
//                                String pic = pdBigPic.substring(0, pdBigPic.indexOf(","));
                                    String pic = pdBigPic.substring(pdBigPic.lastIndexOf("/group"));
                                    Glide.with(mContext).load(ApiService.BASE_PIC_URL + pic).into((ImageView) footerView.findViewById(R.id.iv_pdSmallPic));
                                } else {
                                    Glide.with(mContext).load(ApiService.BASE_PIC_URL + productInfoBeanBaseBean.getRows().getProduct().getPdBigPic()).into((ImageView) footerView.findViewById(R.id.iv_pdSmallPic));
                                    bannerBeans.add(productInfoBeanBaseBean.getRows().getProduct().getPdBigPic());
                                }
                            }

                            //更多评价
                            tv_evaluation.setOnClickListener(v -> {
                                Bundle bundle = new Bundle();
                                bundle.putInt("goodsId", mId);
                                JumpUtils.jump(mContext, MoreDetailActivity.class, bundle);
                            });

                            bannerView.setPages(LocalImageGoodsListHolderView::new, bannerBeans)
                                    //设置指示器是否可见
                                    //.setPointViewVisible(true)
                                    //设置自动切换（同时设置了切换时间间隔）
                                    .startTurning(2000)
                                    //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,不需要圆点指示器可用不设
                                    .setPageIndicator(new int[]{R.drawable.shape_banner_indicator1, R.drawable.shape_banner_indicator2})
                                    //设置指示器的方向（左、中、右）
                                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                                    //设置点击监听事件
                                    .setOnItemClickListener(position -> {
                                        // todo 点击 轮播图跳转到指定界面
                                        String s = bannerBeans.get(position);
                                    })
                                    //设置手动影响（设置了该项无法手动切换）
                                    .setManualPageable(true);
                        } else {
                            T.showShort("可能服务器出了点小差~");
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                        T.showShort("error");
                    }
                });
    }

}
