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
import com.example.handsomelibrary.model.ListFollowDocBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.inquiry.activity.DoctorInfoActivity;
import com.hjy.wisdommedical.ui.personal.adapter.AttentionDoctorListAdapter;
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
 * 关注医生 Fragment
 * Created by Stefan on 2018/7/9 9:39.
 */
public class AttentionDoctorFragment extends BaseFragment {

    @BindView(R.id.rv_docList)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    private AttentionDoctorListAdapter mAdapter;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_doctor_list;
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
        getListFollowDoc();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new AttentionDoctorListAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            ListFollowDocBean listFollowDocBean = mAdapter.getData().get(position);
            Bundle bundle = new Bundle();
            SpfUtils.saveIntToSpf(Constant.docId, listFollowDocBean.getAppDocEntityVO().getId());
            SpfUtils.saveStrToSpf(Constant.docNo, listFollowDocBean.getAppDocEntityVO().getDocNo());
            JumpUtils.jump((AppCompatActivity) getActivity(), DoctorInfoActivity.class, bundle);
        });
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.iv_isFollow:
                    getUnFollowDoc(mAdapter.getData().get(position).getDocId(), mAdapter.getData().get(position).getCusId(), position);
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
                getListFollowDoc();
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
    public void attendtion(AttentionEvent attentionEvent){
        if(attentionEvent.mark){
            mRefreshLayout.autoRefresh();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    //
//    @Override
//    public void onResume() {
//        super.onResume();
//        mRefreshLayout.autoRefresh();
//    }

    /**
     * 加载数据
     */
    private void getListFollowDoc() {
        mKProgressHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .getListFollowDoc(SpfUtils.getSpfSaveInt(Constant.userId))
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<List<ListFollowDocBean>>() {
                    @Override
                    protected void onSuccess(List<ListFollowDocBean> listConsultDocBeans) {
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
     * 取消关注醫生
     */
    private void getUnFollowDoc(int docId, int cusId, int position) {
        mKProgressHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        Map<String, Object> prams = new HashMap<>();
        prams.put("cusId", cusId);
        prams.put("docId", docId);
        RxHttpUtils.createApi(ApiService.class)
                .getUnFollowDoc(prams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<FollowDocBean>() {
                    @Override
                    protected void onSuccess(FollowDocBean followDocBean) {
                        mAdapter.getData().remove(position);
                        mAdapter.notifyDataSetChanged();
                        mKProgressHUD.dismiss();
                        T.showShort(getString(R.string.cancel_attention));
                        EventBus.getDefault().post(new InquiryEvent(true));
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                        T.showShort(errorMsg + "," + getString(R.string.cancel_attention_failure));
                    }
                });
    }
}
