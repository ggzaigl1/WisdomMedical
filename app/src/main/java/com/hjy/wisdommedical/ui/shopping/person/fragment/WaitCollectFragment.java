package com.hjy.wisdommedical.ui.shopping.person.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.DrugOrderBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.adapter.ListItemDecoration;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.shopping.person.activity.CheckLogisticsActivity;
import com.hjy.wisdommedical.ui.shopping.person.activity.OrderDetailActivity;
import com.hjy.wisdommedical.ui.shopping.person.adapter.AllOrderAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 待收货-个人中心订单
 * Created by Stefan on 2018/11/1.
 */
public class WaitCollectFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.Ll_hand)
    LinearLayout Ll_hand;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    AllOrderAdapter mAdapter;
    int mPageNo = 1;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_whole;
    }

    @Override
    protected void initData() {
        initRefresh();
        mRefreshLayout.autoRefresh();
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new AllOrderAdapter(new ArrayList<>());
        mRecyclerView.addItemDecoration(new ListItemDecoration.Builder().setDraw(true).create(mContext.getApplicationContext()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setEmptyView(LayoutInflater.from(getActivity()).inflate(R.layout.activity_advice_error, Ll_hand, false));

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.tv_left:
                    JumpUtils.jump(mContext, CheckLogisticsActivity.class, null);
                    break;
                case R.id.tv_right:
                    if (mAdapter.getData().get(position).getOrderStatus() == 3) {
                        new AlertDialog.Builder(mContext)
                                .setTitle(getString(R.string.system_title))
                                .setMessage(getString(R.string.is_cargo))
                                .setCancelable(true)
                                .setPositiveButton(R.string.ok, (dialog, which) -> {
                                    getConfirmReceipt(mAdapter.getData().get(position).getId());
                                }).setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss()).show();
                    }
                    break;
            }
        });
        mAdapter.setOnItemClickListener((adapter, view1, position) -> {
            DrugOrderBean.RowsBean rowsBean = mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("rowsBean", rowsBean);
            JumpUtils.jump(mContext, OrderDetailActivity.class, bundle);
        });
    }


    /**
     * 确认收货
     *
     * @param orderId
     */
    private void getConfirmReceipt(int orderId) {
        mKProgressHUD = KProgressHUD.create(getActivity()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .getConfirmReceipt(orderId)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BaseBean<Object>>() {
                    @Override
                    protected void onSuccess(BaseBean<Object> objectBaseBean) {
                        mKProgressHUD.dismiss();
                        mRefreshLayout.autoRefresh();
                        T.showShort("确认收货成功");
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                        T.showShort("确认收货失败");
                    }
                });
    }

    /**
     * 我的订单
     */
    @SuppressLint("CheckResult")
    private void getConfirmOrder(int mPageNo) {
        ArrayMap<String, Object> objectMap = new ArrayMap<>();
        objectMap.put("inputStatus", 3);
        objectMap.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        objectMap.put("pageNumber", mPageNo);
        objectMap.put("pageSize", 20);
        RxHttpUtils.createApi(ApiService.class)
                .getListOrder(objectMap)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BaseBean<DrugOrderBean>>() {
                    @Override
                    protected void onSuccess(BaseBean<DrugOrderBean> drugOrderBeanBaseBean) {
                        if (null != drugOrderBeanBaseBean.getRows().getRows()) {
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(drugOrderBeanBaseBean.getRows().getRows());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(drugOrderBeanBaseBean.getRows().getRows());
                                mRefreshLayout.finishLoadMore();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.setNewData(drugOrderBeanBaseBean.getRows().getRows());
                            }
                        } else {
                            if (mRefreshLayout.isRefreshing()) {
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mRefreshLayout.finishLoadMore();
                            }
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
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
                getConfirmOrder(mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 1;
                getConfirmOrder(mPageNo);
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
