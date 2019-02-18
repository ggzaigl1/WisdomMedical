package com.hjy.wisdommedical.ui.personal.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.FollowDocBean;
import com.example.handsomelibrary.model.ListConsultDocBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.inquiry.activity.DoctorInfoActivity;
import com.hjy.wisdommedical.ui.personal.adapter.InquiryDoctorListAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 问诊医生 Fragment
 * Created by Stefan on 2018/7/9 9:30.
 */
public class InquiryDoctorFragment extends BaseFragment {

    @BindView(R.id.rv_docList)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;

    private InquiryDoctorListAdapter mAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_doctor_list;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new InquiryDoctorListAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            ListConsultDocBean appDocEntityVOBean = mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            SpfUtils.saveIntToSpf(Constant.docId, appDocEntityVOBean.getId());
            SpfUtils.saveStrToSpf(Constant.docNo, appDocEntityVOBean.getDocNo());
            JumpUtils.jump((AppCompatActivity) getActivity(), DoctorInfoActivity.class, bundle);
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.iv_isFollow:
                    if (mAdapter.getData().get(position).getIsFollow() == 1) {
                        //已关注
                        getUnFollowDoc(position, mAdapter.getData().get(position).getId());
                    } else {
                        getFollowDoc(position, mAdapter.getData().get(position).getId());
                    }
                    EventBus.getDefault().post(new AttentionEvent(true));
                    break;
            }
        });
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_advice_error, null, false);
        mAdapter.setEmptyView(view);

        //刷新
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(mContext));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(mContext));
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                getListConsultDoc();
                mRefreshLayout.finishRefresh();
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void attendtion(InquiryEvent inquiryEvent) {
        if (inquiryEvent.mark) {
            mRefreshLayout.autoRefresh();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getListConsultDoc();
    }

    /**
     * 加载数据
     */
    private void getListConsultDoc() {
        mKProgressHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .getListConsultDoc(SpfUtils.getSpfSaveInt(Constant.userId))
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<List<ListConsultDocBean>>() {
                    @Override
                    protected void onSuccess(List<ListConsultDocBean> listConsultDocBeans) {
                        if (listConsultDocBeans != null) {
                            mAdapter.setNewData(listConsultDocBeans);
                            mKProgressHUD.dismiss();
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                    }
                });
    }

    /**
     * 取消关注
     */
    private void getUnFollowDoc(int position, int docId) {
        mKProgressHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        Map<String, Object> prams = new HashMap<>();
        prams.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        prams.put("docId", docId);
        RxHttpUtils.createApi(ApiService.class)
                .getUnFollowDoc(prams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<FollowDocBean>() {
                    @Override
                    protected void onSuccess(FollowDocBean followDocBean) {
                        ListConsultDocBean bean = mAdapter.getData().get(position);
                        bean.setIsFollow(0);
                        mAdapter.notifyItemChanged(position);
                        mKProgressHUD.dismiss();
                        T.showShort(getString(R.string.cancel_attention));
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                        T.showShort(errorMsg + "," + getString(R.string.cancel_attention_failure));
                    }
                });
    }

    /**
     * 关注医生
     */
    private void getFollowDoc(int position, int docId) {
        mKProgressHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        Map<String, Object> prams = new HashMap<>();
        prams.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        prams.put("docId", docId);
        RxHttpUtils.createApi(ApiService.class)
                .getFollowDoc(prams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<FollowDocBean>() {
                    @Override
                    protected void onSuccess(FollowDocBean followDocBean) {
                        ListConsultDocBean bean = mAdapter.getData().get(position);
                        bean.setIsFollow(1);
                        mAdapter.notifyItemChanged(position);
                        mKProgressHUD.dismiss();
                        T.showShort(R.string.attention_succeed);
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        T.showShort(errorMsg + "," + getString(R.string.attention_failure));
                        mKProgressHUD.dismiss();
                    }
                });
    }
}
