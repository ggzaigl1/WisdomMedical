package com.hjy.wisdommedical.ui.shopping.person.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.DrugOrderBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.shopping.person.adapter.WaitingOrderAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 初夏小溪
 * 待付款-个人中心订单
 * data :2018/12/5 0005 17:03
 */
public class WaitingPaymentActivity extends BaseActivity {

    @BindView(R.id.rv_pend)
    RecyclerView mRecyclerView;
    @BindView(R.id.Ll_hand)
    LinearLayout Ll_hand;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_title)
    TextView tv_title;

    WaitingOrderAdapter mAdapter;
    int mPageNo = 1;

    @Override
    protected int getContentView() {
        return R.layout.activity_waiting_pay;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText("待付款");
        initRefresh();
        getConfirmOrder(mPageNo);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new WaitingOrderAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.activity_advice_error, Ll_hand, false));
        mAdapter.setOnItemClickListener((adapter, view1, position) -> {
            DrugOrderBean.RowsBean rowsBean = mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            bundle.putSerializable("rowsBean", rowsBean);
            JumpUtils.jump(mContext, OrderDetailActivity.class, bundle);
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.tv_cancel:
                    T.showShort(tv_title.getText().toString() + position);
                    break;
                case R.id.tv_pay:
                    T.showShort(tv_title.getText().toString() + position);
                    break;
            }
        });
    }

    /**
     * 我的订单
     */
    @SuppressLint("CheckResult")
    private void getConfirmOrder(int mPageNo) {
        ArrayMap<String, Object> objectMap = new ArrayMap<>();
        objectMap.put("inputStatus", 1);
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
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(this));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(this));
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

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
        }
    }
}
