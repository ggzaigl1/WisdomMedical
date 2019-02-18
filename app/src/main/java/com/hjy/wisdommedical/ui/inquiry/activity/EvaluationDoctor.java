package com.hjy.wisdommedical.ui.inquiry.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.DoctorInfoBean;
import com.example.handsomelibrary.model.EvaluationDoctorBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.inquiry.adapter.CommentAdapter;
import com.hjy.wisdommedical.ui.inquiry.adapter.EvaluationDoctorInfoAdapter;
import com.hjy.wisdommedical.ui.inquiry.adapter.EvaluationDoctorInfoTagAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 初夏小溪 on 2018/8/9 0009.
 * 医生详情 评价列表（分页）
 */

public class EvaluationDoctor extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_commentCount)
    TextView tv_commentCount;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.recycler_tag_view)
    RecyclerView mRecyclerView_Tag;
    @BindView(R.id.Ll_evaluation)
    LinearLayout mLinearLayout;
    private EvaluationDoctorInfoAdapter mAdapter;
    private EvaluationDoctorInfoTagAdapter mAdapterTag;

    private int totalEvaluationCount = 0;//统计tag全部
    private int mSelectedPos = 0; //定义单选默认值

    @Override
    protected int getContentView() {
        return R.layout.activity_evaluation_doctor;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.evaluations_doctor);
        initView();
        initTogView();
        getInfoToAppDoc();
        getListByDocIdToApp("全部");
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new EvaluationDoctorInfoAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        View view = LayoutInflater.from(mContext).inflate(R.layout.activity_advice_error, mLinearLayout, false);
        mAdapter.setEmptyView(view);
    }


    private void initTogView() {
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setAlignItems(AlignItems.CENTER);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        mRecyclerView_Tag.setLayoutManager(layoutManager);
        mRecyclerView_Tag.setHasFixedSize(true);
        mAdapterTag = new EvaluationDoctorInfoTagAdapter(new ArrayList<>());
        mRecyclerView_Tag.setAdapter(mAdapterTag);
        mAdapterTag.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.tv_item_text:
                    if (mSelectedPos != position) {
                        mAdapterTag.getData().get(mSelectedPos).setIselector(false);
                        mAdapterTag.notifyItemChanged(mSelectedPos);
                        mSelectedPos = position;
                        mAdapterTag.getData().get(mSelectedPos).setIselector(true);
                        String tagName = mAdapterTag.getData().get(mSelectedPos).getTagName();
                        mAdapterTag.notifyItemChanged(mSelectedPos);
                        getListByDocIdToApp(tagName);
                    }
                    break;
            }
        });
    }

    /**
     * 评价列表
     */
    private void getListByDocIdToApp(String tagName) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> prams = new ArrayMap<>();
        prams.put("pageNumber", 1);
        prams.put("pageSize ", 20);
        if (!tagName.equals("全部")) {
            prams.put("tagName", tagName);
        }
        prams.put("docNo", SpfUtils.getSpfSaveStr(Constant.docNo));
        RxHttpUtils.createApi(ApiService.class)
                .listByDocIdToApp(prams)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<EvaluationDoctorBean>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    protected void onSuccess(EvaluationDoctorBean evaluationDoctorBean) {
                        mAdapter.setNewData(evaluationDoctorBean.getRows());
                        tv_commentCount.setText(evaluationDoctorBean.getTotal() + getString(R.string.comments));
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        T.showShort(errorMsg);
                        mKProgressHUD.dismiss();
                    }
                });

    }

    /**
     * 医生评价tag
     */
    private void getInfoToAppDoc() {
        Map<String, Object> prams = new HashMap<>();
        prams.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        prams.put("id", SpfUtils.getSpfSaveInt(Constant.docId));
        RxHttpUtils.createApi(ApiService.class)
                .getInfoToAppDoc(prams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BaseBean<DoctorInfoBean>>() {
                    @Override
                    protected void onSuccess(BaseBean<DoctorInfoBean> doctorInfoBeanBaseBean) {
                        if (doctorInfoBeanBaseBean != null) {
                            List<DoctorInfoBean.ListEvaluationTagDocBean> listEvaluationTagDoc = doctorInfoBeanBaseBean.getRows().getListEvaluationTagDoc();
                            for (DoctorInfoBean.ListEvaluationTagDocBean listEvaluationTagDocBean : listEvaluationTagDoc) {
                                //遍历所有的Count
                                totalEvaluationCount += listEvaluationTagDocBean.getEvaluationCount();
                            }
                            listEvaluationTagDoc.add(0, new DoctorInfoBean.ListEvaluationTagDocBean(totalEvaluationCount, "全部", true));
                            mAdapterTag.setNewData(listEvaluationTagDoc);
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        T.showShort(errorMsg);
                    }
                });
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