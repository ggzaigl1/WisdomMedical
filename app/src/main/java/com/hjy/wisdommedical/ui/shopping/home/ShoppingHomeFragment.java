package com.hjy.wisdommedical.ui.shopping.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.model.BannerBean;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.MallHomeBean;
import com.example.handsomelibrary.model.MallHomeBeanSection;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.shopping.ShoppingMainActivity;
import com.hjy.wisdommedical.ui.shopping.home.adapter.ShoppingHomeAdapter;
import com.hjy.wisdommedical.widget.NetworkImageHolderView;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author QKun
 * @date 2018/10/11
 * 健康商城-首页
 */
public class ShoppingHomeFragment extends BaseFragment {

    public static final String TAG = "ShoppingHome";
    @BindView(R.id.home_recycle)
    RecyclerView mHomeRecycler;

    List<String> bannerBeans = new ArrayList<>();
    private ConvenientBanner mBannerView;
    private ShoppingHomeAdapter mAdapter;

    public static ShoppingHomeFragment newInstance(String parames) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, parames);
        ShoppingHomeFragment fragment = new ShoppingHomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_shopping_home;
    }

    @Override
    protected void initData() {
        initRecycle();
        getBanner();
        getMallHomeList();
    }

    @SuppressLint("CheckResult")
    private void getMallHomeList() {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .getMallHomeList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listBaseBean -> {
                    mKProgressHUD.dismiss();
                    List<MallHomeBeanSection> sections = new ArrayList<>();
                    for (MallHomeBean mallHomeBean : listBaseBean.getRows()) {
                        if (mallHomeBean.getClassfication() != null && !TextUtils.isEmpty(mallHomeBean.getClassfication().getCfPic())) {
                            sections.add(new MallHomeBeanSection(true, mallHomeBean.getClassfication().getCfPic()));
                            for (MallHomeBean.ProductBean productBean : mallHomeBean.getProduct()) {
                                sections.add(new MallHomeBeanSection(productBean));
                            }
                        }
                    }
                    mAdapter.setNewData(sections);
                }, throwable -> {
                    mKProgressHUD.dismiss();
                    T.showShort("网络连接异常");
                });
    }

    @Override
    @OnClick({R.id.et_search_heat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_search_heat:
                JumpUtils.jump(mContext, SearchActivity.class, null);
                break;
        }
    }

    @SuppressLint("CheckResult")
    private void getBanner() {
        RxHttpUtils.createApi(ApiService.class)
                .getBanner(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listBaseBean -> {
                    for (BannerBean bannerBean : listBaseBean.getRows()) {
                        bannerBeans.add(ApiService.BASE_PIC_URL + bannerBean.getImgUrl());
                    }
                    bannerView(bannerBeans);
                }, throwable -> T.showShort("网络连接异常"));
    }

    private void initRecycle() {
        mHomeRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mHomeRecycler.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        mAdapter = new ShoppingHomeAdapter(new ArrayList<>());
        View home_recycle_header = LayoutInflater.from(mContext).inflate(R.layout.home_recycle_header, (ViewGroup) mHomeRecycler.getParent(), false);
        mBannerView = home_recycle_header.findViewById(R.id.convenientBanner);
        mAdapter.addHeaderView(home_recycle_header);
        mHomeRecycler.setAdapter(mAdapter);
        //跳转详情
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            MallHomeBeanSection mallHomeBeanSection = mAdapter.getData().get(position);
            if (!mallHomeBeanSection.isHeader) {
                MallHomeBean.ProductBean productBean = mAdapter.getData().get(position).t;
                Bundle bundle = new Bundle();
                bundle.putInt("id", productBean.getId());//商品id
                bundle.putInt("priceNow", productBean.getPriceNow());
                bundle.putString("pdSmallPic", productBean.getPdSmallPic());
                bundle.putString("pdCom", productBean.getPdCom());
                JumpUtils.jump(mContext, GoodsDetailActivity.class, bundle);
            }
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                //购买
                case R.id.tv_mall_home_buy:
                    MallHomeBeanSection mallHomeBeanSection = mAdapter.getData().get(position);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", mallHomeBeanSection.t.getId());
                    bundle.putInt("priceNow", mallHomeBeanSection.t.getPriceNow());
                    bundle.putString("pdSmallPic", mallHomeBeanSection.t.getPdSmallPic());
                    bundle.putString("pdCom", mallHomeBeanSection.t.getPdCom());
                    JumpUtils.jump((AppCompatActivity) getActivity(), PaymentOrderActivity.class, bundle);
                    break;
                //分类
//                case R.id.iv_mall_home_cfPic:
//                    ((ShoppingMainActivity) getActivity()).jumpFragmentOne();
//                    break;
                default:
                    break;
            }
        });
    }

    private void bannerView(List<String> bannerBeans) {
        mBannerView.setPages(() -> new NetworkImageHolderView(), bannerBeans)
                .startTurning(2000)
                .setPointViewVisible(true)
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                //设置手动影响（设置了该项无法手动切换）
                .setManualPageable(true);
//                .setcurrentitem(0);

    }

}
