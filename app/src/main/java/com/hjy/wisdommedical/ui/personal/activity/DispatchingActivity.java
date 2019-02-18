package com.hjy.wisdommedical.ui.personal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.AddressToListBean;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.net.RetrofitManager;
import com.hjy.wisdommedical.ui.personal.adapter.DispatchingAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/7/10 0010.
 * 配送地址
 */

public class DispatchingActivity extends BaseActivity {

    @BindView(R.id.rv_dispatching)
    RecyclerView mRecyclerView;
    @BindView(R.id.Ll_hand)
    LinearLayout Ll_hand;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_right)
    ImageView iv_right;
    DispatchingAdapter mAdapter;
    int mPageNo = 1;
    private int type = 1; //编辑item

    @Override
    protected int getContentView() {
        return R.layout.activity_dispatching;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initRefresh();
        init();
        tv_title.setText(R.string.dispatching_manage);
        iv_right.setBackground(ContextCompat.getDrawable(mContext, R.mipmap.icon_choose_user_plus));
        iv_right.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSave(mPageNo);
    }

    private void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new DispatchingAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        View emptyView = LayoutInflater.from(mContext).inflate(R.layout.activity_error, Ll_hand, false);
        mAdapter.setEmptyView(emptyView);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            Intent intent = getIntent();
            AddressToListBean.RowsBean rowsBean = mAdapter.getData().get(position);
            intent.putExtra("data", rowsBean);
            setResult(Constant.SHIPPING_ADDRESS, intent);
            finish();
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.iv_choose_user_delete:
                    getRemove(mAdapter.getData().get(position).getId(), position);
                    break;
                case R.id.iv_choose_user_compile:
                    AddressToListBean.RowsBean rowsBean = mAdapter.getData().get(position);
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", type);
                    bundle.putInt("id", rowsBean.getId());
                    JumpUtils.jump(mContext, AddDispatchingActivity.class, bundle);
                    break;
                case R.id.Ll_Default:
                    getDefault(mAdapter.getData().get(position).getCusId(), mAdapter.getData().get(position).getId());
                    break;
                default:
                    break;
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.iv_right})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.iv_right:
                Bundle bundle = new Bundle();
                bundle.putInt("type", 0);
                JumpUtils.jump(mContext, AddDispatchingActivity.class, bundle);
                break;
            default:
                break;
        }
    }

    /**
     * 获取配送地址条目
     */
    private void getSave(int mPageNo) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> objectMap = new ArrayMap<>();
        objectMap.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        objectMap.put("pageNumber", mPageNo);
        objectMap.put("pageSize", 20);
        RetrofitManager.createApi(ApiService.class)
                .listToApp(objectMap)
                .throttleFirst(2, TimeUnit.SECONDS)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<AddressToListBean>() {
                    @Override
                    protected void onSuccess(AddressToListBean addressToListBean) {
                        if (null != addressToListBean) {
                            mKProgressHUD.dismiss();
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(addressToListBean.getRows());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(addressToListBean.getRows());
                                mRefreshLayout.finishLoadMore();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.setNewData(addressToListBean.getRows());
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
                        mKProgressHUD.dismiss();
                    }
                });
    }

    /**
     * 设置默认配送地址
     *
     * @param id
     */
    private void getDefault(int cusId, int id) {
        ArrayMap<String, Object> param = new ArrayMap<>();
        param.put("cusId", cusId);
        param.put("id", id);
        RetrofitManager.createApi(ApiService.class)
                .setDefault(param)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<Object>() {
                    @Override
                    protected void onSuccess(Object Object) {
                        getSave(mPageNo);
                        T.showShort(R.string.set_default_dispatching);
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                        T.showShort(errorMsg);
                    }
                });
    }

    /**
     * 删除送货地址
     *
     * @param id
     * @param position
     */
    private void getRemove(int id, int position) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> objectMap = new ArrayMap<>(16);
        objectMap.put("id", id);
        RetrofitManager.createApi(ApiService.class)
                .getRemove(objectMap)
                .throttleFirst(2, TimeUnit.SECONDS)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<Object>() {
                    @Override
                    protected void onSuccess(Object o) {
                        mAdapter.getData().remove(position);
                        mAdapter.notifyDataSetChanged();
                        T.showShort(getString(R.string.delete_succeed));
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        T.showShort(R.string.delete_failure);
                        mKProgressHUD.dismiss();
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
                getSave(mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 1;
                getSave(mPageNo);
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
