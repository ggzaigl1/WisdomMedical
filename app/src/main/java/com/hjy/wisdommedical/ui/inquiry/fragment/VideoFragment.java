package com.hjy.wisdommedical.ui.inquiry.fragment;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.ListConsultBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.example.handsomelibrary.utils.permission.PermissionChecker;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.hx.EMUtils;
import com.hjy.wisdommedical.net.RetrofitManager;
import com.hjy.wisdommedical.ui.inquiry.activity.CommentActivity;
import com.hjy.wisdommedical.ui.inquiry.activity.WaitingDoctorActivity;
import com.hjy.wisdommedical.ui.inquiry.adapter.VideoAdviceAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;

/**
 * 我的咨询 视频咨询 fragment
 * Created by Stefan on 2018/7/3.
 */
public class VideoFragment extends BaseFragment {

    @BindView(R.id.rv_it)
    RecyclerView mRecyclerView;
    @BindView(R.id.Ll_hand)
    LinearLayout Ll_hand;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    int mPageNo = 1;
    private VideoAdviceAdapter mAdapter;
    protected PermissionChecker permissionChecker;
    protected static final String[] PERMISSIONS = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.RECORD_AUDIO,
    };

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_image_text;
    }

    @Override
    protected void initData() {
        permissionChecker = new PermissionChecker(getActivity());
        permissionChecker.setTitle(getString(R.string.check_info_title));
        permissionChecker.setMessage(getString(R.string.check_info_message));
        initRefresh();
        initView();
        getListConsult(mPageNo);

    }

    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new VideoAdviceAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener((BaseQuickAdapter adapter, View view, int position) -> {
            switch (view.getId()) {
                case R.id.tv_evaluate:
                    ListConsultBean bean = mAdapter.getData().get(position);
                    if (bean.getOrderStatus() == 2) {
                        getRemoveOrder(mAdapter.getData().get(position).getId(), position);
                    } else if (bean.getOrderStatus() == 4) {
                        //已完成
//                        Bundle bundle = new Bundle();
//                        bundle.putSerializable("bean", bean);
//                        JumpUtils.jump(mContext, CommentActivity.class, bundle);
                    } else if (bean.getOrderStatus() == 6) {
                        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
                            new MaterialDialog.Builder(mContext).title(R.string.require_acquisition)
                                    .content(R.string.default_always_message)
                                    .positiveText(R.string.next)
                                    .onPositive((dialog, which) -> onPermission()).show();
                        } else {
                            //进入诊室
                            GetUpdateStatus(mAdapter.getData().get(position).getId(), position);
                        }
                    } else if (bean.getOrderStatus() == 7) {
                        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
                            new MaterialDialog.Builder(mContext).title(R.string.require_acquisition)
                                    .content(R.string.default_always_message)
                                    .positiveText(R.string.next)
                                    .onPositive((dialog, which) -> onPermission()).show();
                        } else {
                            //进入诊室
                            GetUpdateStatus(mAdapter.getData().get(position).getId(), position);
                        }
                    } else if (bean.getOrderStatus() == 8) {
                        //已完成
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("bean", bean);
                        JumpUtils.jump(mContext, CommentActivity.class, bundle);
                    }
                    break;
                case R.id.tv_delete:
                    //取消订单
                    getRemoveOrder(mAdapter.getData().get(position).getId(), position);
                    break;
            }
        });
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.activity_advice_error, Ll_hand, false);
        mAdapter.setEmptyView(view);
    }

    /**
     * 数据加载
     */
    private void getListConsult(int mPageNo) {
        ArrayMap<String, Object> objectMap = new ArrayMap<>();
        objectMap.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        objectMap.put("serviceId", 2);
        objectMap.put("pageSize", 20);
        objectMap.put("pageNumber", mPageNo);
        RxHttpUtils.createApi(ApiService.class)
                .getListConsult(objectMap)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<List<ListConsultBean>>() {
                    @Override
                    protected void onSuccess(List<ListConsultBean> listConsultBeans) {
                        if (null != listConsultBeans) {
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(listConsultBeans);
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(listConsultBeans);
                                mRefreshLayout.finishLoadMore();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.setNewData(listConsultBeans);
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
                        T.showShort(errorMsg);
                    }
                });
    }

    /**
     * 删除订单
     *
     * @param id
     * @param position
     */
    private void getRemoveOrder(int id, int position) {
        if (mAdapter.getData().get(position).getOrderStatus() == 6) {
            mKProgressHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
            RetrofitManager.createApi(ApiService.class)
                    .removeOrder(id)
                    .throttleFirst(2, TimeUnit.SECONDS)
                    .compose(Transformer.switchSchedulers())
                    .subscribe(new CommonObserver<Object>() {
                        @Override
                        protected void onSuccess(Object o) {
                            mAdapter.getData().remove(position);
                            mAdapter.notifyDataSetChanged();
                            T.showShort(getString(R.string.cancel_succeed));
                            mKProgressHUD.dismiss();
                        }

                        @Override
                        protected void onError(String errorMsg) {
                            T.showShort(getString(R.string.cancel_failure));
                            mKProgressHUD.dismiss();
                        }
                    });
        } else {
            mKProgressHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
            RetrofitManager.createApi(ApiService.class)
                    .removeOrder(id)
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
    }

    /**
     * 修改订单状态
     *
     * @param orderId
     */
    private void GetUpdateStatus(Integer orderId, int position) {
        mKProgressHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> objectMap = new ArrayMap<>();
        objectMap.put("id", orderId);
        objectMap.put("status", 7);
        RxHttpUtils.createApi(ApiService.class)
                .GetUpdateStatus(objectMap)
                .throttleFirst(2, TimeUnit.SECONDS)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<String>() {
                    @Override
                    protected void onSuccess(String s) {
                        mKProgressHUD.dismiss();
                        ListConsultBean listConsultBean = mAdapter.getData().get(position);
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("isVideoMsg", true);
                        bundle.putSerializable("listConsultBean", listConsultBean);
                        EMUtils.loginEM(mContext, SpfUtils.getSpfSaveStr(Constant.username), "123456", WaitingDoctorActivity.class, bundle);
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                        T.showShort(errorMsg);
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
                getListConsult(mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 1;
                getListConsult(mPageNo);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mRefreshLayout.autoRefresh();
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

    /**
     * 请求权限返回结果
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionChecker.PERMISSION_REQUEST_CODE:
                //权限获取成功
                if (permissionChecker.hasAllPermissionsGranted(grantResults)) {
                } else {
                    //权限获取失败
                    permissionChecker.showDialog();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 检查权限
     */
    private void onPermission() {
        if (permissionChecker.isLackPermissions(PERMISSIONS)) {
            permissionChecker.requestPermissions();
        } else {

        }
    }
}
