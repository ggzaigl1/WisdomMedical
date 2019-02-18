package com.hjy.wisdommedical.ui.shopping.trolley;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.ShopingCartBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.ResourceUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.shopping.home.GoodsDetailActivity;
import com.hjy.wisdommedical.ui.shopping.home.PaymentOrderActivity;
import com.hjy.wisdommedical.util.TransfmtUtils;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 健康商城  --> 购物车
 * Created by QKun on 2018/10/11.
 */
public class ShoppingTrolleyFragment extends BaseFragment {

    public static final String TAG = "ShoppingTrolley";

    @BindView(R.id.shoppingTrolleyList)
    RecyclerView mShoppingTrolleyList;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tvPrice)
    TextView tvPrice;
    @BindView(R.id.checkAll)
    CheckBox checkAll;
    @BindView(R.id.btnDelete)
    Button btnDelete;//删除按钮
    @BindView(R.id.btnSettleAccounts)
    Button btnSettleAccounts;//结算按钮
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    int mPageNo = 1;
    String sumPrice = "0.00";//结算总价 String类型
    ShoppingTrolleyAdapter mAdapter;

    public static ShoppingTrolleyFragment newInstance(String parames) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, parames);
        ShoppingTrolleyFragment fragment = new ShoppingTrolleyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_shopping_trolley;
    }

    @Override
    protected void initData() {
        tvTitle.setText(R.string.shopping_trolley);
        tvRight.setText(R.string.shopping_manage);
        tvPrice.setText(ResourceUtils.getReplaceStr(R.string.shoppingSumAmount, sumPrice));
        initRv();
        initRefresh();
//        deleteCartGoods();
//        getConfirmOrder();
    }

    private void initRv() {
        mAdapter = new ShoppingTrolleyAdapter(getContext(), new ArrayList<>());
        mAdapter.setModifyItemAmountListener((position, isAdd) -> {
            if (mAdapter.isItemChecked(position)) {
                List<ShopingCartBean.RowsBean> datas = mAdapter.getmDatas();
                ShopingCartBean.RowsBean shopingCartBean = datas.get(position);
                double product = shopingCartBean.getProduct().getPriceNow();//商品单价

                double sumAmount = isAdd ? product : -product;
                settlement(sumAmount);
            }
        });
        mAdapter.setChangeItemListener(position -> {
            if (mAdapter.getIsAllSelect()) {//如果当前点击的 多选框选中后，是全选状态
                checkAll.setChecked(true);//设置 界面 全选按钮为选中状态
                allChecked();
            } else {
                checkAll.setChecked(false);
                checkAll.setText("全选");

                List<ShopingCartBean.RowsBean> datas = mAdapter.getmDatas();
                ShopingCartBean.RowsBean shopingCartBean = datas.get(position);
                double product = shopingCartBean.getProduct().getPriceNow() * shopingCartBean.getAmount();//单价 * 数量（该商品的总价）

                double sumAmount = mAdapter.isItemChecked(position) ? product : -product;
                settlement(sumAmount);
            }
        });
        mAdapter.setItemClickListner(view -> {
            ShopingCartBean.RowsBean rowsBean = (ShopingCartBean.RowsBean) view.getTag();
            Bundle bundle = new Bundle();
            bundle.putInt("id", rowsBean.getProduct().getId());
            bundle.putInt("priceNow", rowsBean.getProduct().getPriceNow());
            bundle.putString("pdSmallPic", rowsBean.getProduct().getPdSmallPic());
            bundle.putString("pdCom", rowsBean.getProduct().getPdCom());
            JumpUtils.jump(mContext, GoodsDetailActivity.class, bundle);
        });

        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.trolley_recycle_header, (ViewGroup) mShoppingTrolleyList.getParent(), false);
        mAdapter.addHeaderView(headerView);
        mShoppingTrolleyList.setLayoutManager(new LinearLayoutManager(getContext()));
        mShoppingTrolleyList.setAdapter(mAdapter);
    }

    //结算 总价绘制到 界面(保留两位小数)
    private void settlement(double sumAmount) {
        sumPrice = TransfmtUtils.doubleToKeepBitDecimalPlaces(Double.parseDouble(sumPrice) + sumAmount, 2);
        tvPrice.setText(ResourceUtils.getReplaceStr(R.string.shoppingSumAmount, sumPrice));
    }

    @Override
    public void onResume() {
        super.onResume();
        mPageNo = 1;
        listCart(mPageNo);
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.btnSettleAccounts, R.id.checkAll, R.id.btnDelete})
    @Override
    public void onClick(View view) {
        List<ShopingCartBean.RowsBean> beanList = mAdapter.getmDatas();
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.btnSettleAccounts://结算
                List<ShopingCartBean.RowsBean> data = new ArrayList<>();
                for (int i = 0; i < beanList.size(); i++) {
                    if (mAdapter.isItemChecked(i)) {
                        data.add(beanList.get(i));
                    }
                }
                if (data.size() == 0) {
                    T.showShort(R.string.selectShopping);
                    return;
                }
                ShopingCartBean shopingCartBean = new ShopingCartBean();
                shopingCartBean.setRows(data);

                BaseBean<ShopingCartBean> baseBean = new BaseBean<>();
                baseBean.setRows(shopingCartBean);
                Bundle bundle = new Bundle();
                bundle.putInt("type", 3);
                bundle.putString("sumPrice", sumPrice);
                bundle.putSerializable("ShoppingCartArray", baseBean);
                JumpUtils.jump((AppCompatActivity) getActivity(), PaymentOrderActivity.class, bundle);
                break;
            case R.id.tv_right://管理
                if (tvRight.getText().toString().trim().equals(ResourceUtils.getStr(R.string.complete))) {
                    tvRight.setText(R.string.shopping_manage);
                    btnDelete.setVisibility(View.GONE);
                    btnSettleAccounts.setVisibility(View.VISIBLE);
                } else {
                    tvRight.setText(R.string.complete);
                    btnDelete.setVisibility(View.VISIBLE);
                    btnSettleAccounts.setVisibility(View.GONE);
                }
                break;
            case R.id.btnDelete://删除
                deleteCartGoods(beanList);
                break;
            case R.id.checkAll://底部 全选事件
                if (checkAll.isChecked()) {
                    allChecked();
                } else {
                    checkAll.setText("全选");
                    mAdapter.cleanChecked();
                    sumPrice = "0.00";
                }
                tvPrice.setText(ResourceUtils.getReplaceStr(R.string.shoppingSumAmount, sumPrice));
                mAdapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
    }

    private void allChecked() {
        checkAll.setText("取消");
        float sumAmount = 0f;
        List<ShopingCartBean.RowsBean> datas = mAdapter.getmDatas();
        for (int i = 0; i < mAdapter.getRealItemCount(); i++) {
            mAdapter.setItemChecked(i, true);
            ShopingCartBean.RowsBean shopingCartBean = datas.get(i);
            sumAmount += shopingCartBean.getProduct().getPriceNow() * shopingCartBean.getAmount();
        }
        sumPrice = TransfmtUtils.doubleToKeepBitDecimalPlaces(sumAmount, 2);
        tvPrice.setText(ResourceUtils.getReplaceStr(R.string.shoppingSumAmount, sumPrice));
    }

    /**
     * 查看购物车
     */
    @SuppressLint("CheckResult")
    private void listCart(int mPageNo) {
        ArrayMap<String, Object> objectMap = new ArrayMap<>();
        objectMap.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        objectMap.put("pageNumber", mPageNo);
        objectMap.put("pageSize", 20);
        RxHttpUtils.createApi(ApiService.class)
                .listCart(objectMap)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<ShopingCartBean>>() {
                    @Override
                    public void accept(BaseBean<ShopingCartBean> listBaseBean) throws Exception {
                        List<ShopingCartBean.RowsBean> listData = listBaseBean.getRows().getRows();
                        if (listData.isEmpty()) {
                            if (mRefreshLayout.isRefreshing()) {
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mRefreshLayout.finishLoadMore();
                            }
                        } else if (mRefreshLayout.isRefreshing()) {
                            mAdapter.setmDatas(listData);
                            mAdapter.notifyDataSetChanged();
                            mRefreshLayout.finishRefresh();
                        } else if (mRefreshLayout.isLoading()) {
                            mAdapter.setmDatas(listData);
                            mAdapter.notifyDataSetChanged();
                            mRefreshLayout.finishLoadMore();
                        } else {
                            mAdapter.setmDatas(listData);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        T.showShort("网络连接异常");
                    }
                });
    }


    /**
     * 删除购物车商品
     */
    @SuppressLint("CheckResult")
    private void deleteCartGoods(List<ShopingCartBean.RowsBean> datas) {
        List<Long> cartId = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            if (mAdapter.isItemChecked(i)) {
                ShopingCartBean.RowsBean shopingCartBean = datas.get(i);
                cartId.add((long) shopingCartBean.getId());
            }
        }

        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .deleteCartGoods(cartId)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<Object>>() {
                    @Override
                    public void accept(BaseBean<Object> listBaseBean) throws Exception {
                        mKProgressHUD.dismiss();

                        for (long id : cartId) {
                            for (int i = 0; i < datas.size(); i++) {
                                ShopingCartBean.RowsBean shopingCartBean = datas.get(i);
                                if (shopingCartBean.getId() == id) {
                                    mAdapter.removeData(i);
                                }
                            }
                        }

                        mAdapter.cleanChecked();
                        mAdapter.notifyDataSetChanged();

                        sumPrice = "0.00";
                        tvPrice.setText(ResourceUtils.getReplaceStr(R.string.shoppingSumAmount, sumPrice));
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mKProgressHUD.dismiss();
                        T.showShort("网络连接异常");
                    }
                });
    }

    /**
     * 分页加载数据
     */
    private void initRefresh() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                mPageNo += 1;
                listCart(mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 1;
                listCart(mPageNo);
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.finishRefresh();
        }
        if (mRefreshLayout.isLoading()) {
            mRefreshLayout.finishLoadMore();
        }
    }
}
