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
import com.hjy.wisdommedical.hx.chat.ChatActivity;
import com.hjy.wisdommedical.net.RetrofitManager;
import com.hjy.wisdommedical.ui.inquiry.activity.CommentActivity;
import com.hjy.wisdommedical.ui.inquiry.adapter.ImageTextListAdapter;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.utils.EaseCommonUtils;
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
 * 我的咨询 图文咨询 fragment
 * Created by Stefan on 2018/7/3.
 */
public class ImageTextFragment extends BaseFragment {

    @BindView(R.id.rv_it)
    RecyclerView mRecyclerView;
    @BindView(R.id.Ll_hand)
    LinearLayout Ll_hand;

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    int mPageNo = 1;
    private ImageTextListAdapter mAdapter;
    protected PermissionChecker permissionChecker;
    protected static final String[] PERMISSIONS = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO

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
        mRefreshLayout.autoRefresh();
    }

    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new ImageTextListAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.tv_evaluate:
                    ListConsultBean bean = (ListConsultBean) adapter.getData().get(position);
                    if (bean.getOrderStatus() == 2) {
                        //未支付 or 未回复
                        getRemoveOrder(mAdapter.getData().get(position).getId(), position,"");
                    } else if (bean.getOrderStatus() == 4) {
                        //已完成
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("bean", bean);
                        JumpUtils.jump(mContext, CommentActivity.class, bundle);
                    } else if (bean.getOrderStatus() == 5) {
                        //已回复
                        Bundle bundle = new Bundle();
                        SpfUtils.saveStrToSpf(Constant.docUsername, mAdapter.getData().get(position).getAppDocEntityVO().getDocUsername()); // 医生id
                        bundle.putString(EaseConstant.EXTRA_USER_ID, mAdapter.getData().get(position).getAppDocEntityVO().getDocUsername());//医生id
                        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
                        JumpUtils.jump(mContext, ChatActivity.class, bundle);
                    }
                    break;
                case R.id.tv_delete:
                    getRemoveOrder(mAdapter.getData().get(position).getId(), position,mAdapter.getData().get(position).getAppDocEntityVO().getDocUsername());
                    break;
                case R.id.Ll_Chat:
                    if (permissionChecker.isLackPermissions(PERMISSIONS)) {
                        new MaterialDialog.Builder(mContext).title(R.string.require_acquisition)
                                .content(R.string.default_always_message)
                                .positiveText(R.string.next)
                                .onPositive((dialog, which) -> onPermission()).show();
                    } else {
                        ListConsultBean listConsultBean = mAdapter.getData().get(position);
                        //进入诊室
                        EMUtils.loginEM(mContext, SpfUtils.getSpfSaveStr(Constant.username), "123456");
                        SpfUtils.saveStrToSpf(Constant.docUsername, listConsultBean.getAppDocEntityVO().getDocUsername());
                        Bundle bundle = new Bundle();
                        bundle.putString(EaseConstant.EXTRA_USER_ID, listConsultBean.getChatGroupId());
                        bundle.putString(EaseConstant.EXTRA_USER_NAME, listConsultBean.getAppDocEntityVO().getDocUsername());
                        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_GROUP);
                        JumpUtils.jump(mContext, ChatActivity.class, bundle);
                    }
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
        objectMap.put("serviceId", 1);
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
    private void getRemoveOrder(int id, int position,String DocUsername) {
        if (mAdapter.getData().get(position).getOrderStatus() == 2) {
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

                            //删除和某个user会话，如果需要保留聊天记录，传false
                            EMClient.getInstance().chatManager().deleteConversation(DocUsername, true);
                            mKProgressHUD.dismiss();
                        }

                        @Override
                        protected void onError(String errorMsg) {
                            T.showShort(getString(R.string.delete_failure));
                            mKProgressHUD.dismiss();
                        }
                    });
        }
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