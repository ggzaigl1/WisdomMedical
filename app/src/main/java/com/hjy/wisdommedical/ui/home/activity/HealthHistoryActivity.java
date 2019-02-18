package com.hjy.wisdommedical.ui.home.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.HealthHistoryBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.home.adapter.HealthHistoryAdapter;
import com.hjy.wisdommedical.ui.home.adapter.IllnessHistoryAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by QKun on 2018/9/10.
 * 健康历史
 */
public class HealthHistoryActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTextView;
    @BindView(R.id.bt_commit)
    Button mCommit;
    @BindView(R.id.edit_family_history)
    EditText edit_family_history;
    @BindView(R.id.edit_illness_history)
    EditText edit_illness_history;
    @BindView(R.id.rlv_family_history)
    RecyclerView mRecyclerView_Family_History;
    @BindView(R.id.rlv_illness_history)
    RecyclerView mRecyclerView_illness_history;

    HealthHistoryAdapter mHealthHistoryAdapter;
    IllnessHistoryAdapter mIllnessHistoryAdapter;

    private List<String> mStrings = new ArrayList();
    private int mId;

    @Override
    protected int getContentView() {
        return R.layout.activity_health_history;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mId = getIntent().getIntExtra("Id", 0);
        mTextView.setText(R.string.health_history);
        setDummyData();
        initHead();
        initView();
        //获取详情
        GetInfoHistory(mId);

    }

    @OnClick({R.id.iv_back, R.id.bt_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.bt_commit:
                if (TextUtils.isEmpty(edit_family_history.getText().toString().trim()) && TextUtils.isEmpty(edit_illness_history.getText().toString().trim())) {
                    T.showShort(getString(R.string.family_history_empty));
                    return;
                }
                getUpdateHistory(mId, edit_family_history.getText().toString().trim(), edit_illness_history.getText().toString().trim());
                break;
        }
    }

    /**
     * 上面的item
     */
    private void initHead() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        mRecyclerView_Family_History.setLayoutManager(manager);
        mRecyclerView_Family_History.setHasFixedSize(true);
        mHealthHistoryAdapter = new HealthHistoryAdapter(mStrings);
        mRecyclerView_Family_History.setAdapter(mHealthHistoryAdapter);
        mHealthHistoryAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.tv_item_image_text:
                    String diseaseName = mHealthHistoryAdapter.getData().get(position);
                    edit_family_history.setText(diseaseName);
                    break;
            }
        });
    }

    /**
     * 下面的item
     */
    private void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局
        mRecyclerView_illness_history.setLayoutManager(manager);
        mRecyclerView_illness_history.setHasFixedSize(true);
        mIllnessHistoryAdapter = new IllnessHistoryAdapter(mStrings);
        mRecyclerView_illness_history.setAdapter(mIllnessHistoryAdapter);
        mIllnessHistoryAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.tv_item_image_text:
                    String diseaseName = mIllnessHistoryAdapter.getData().get(position);
                    edit_illness_history.setText(diseaseName);
                    break;
            }
        });
    }

    /**
     * 获取数据详情
     *
     * @param visitMemberId
     */
    @SuppressLint("CheckResult")
    private void GetInfoHistory(int visitMemberId) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .infoHistory(visitMemberId)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BaseBean<HealthHistoryBean>>() {
                    @Override
                    protected void onSuccess(BaseBean<HealthHistoryBean> historyBeanBaseBean) {
                        if (null != historyBeanBaseBean.getRows().getFamilyHistory() && null != historyBeanBaseBean.getRows().getOwnHistory()) {
                            mCommit.setText(getString(R.string.modification));
                            edit_family_history.setText(historyBeanBaseBean.getRows().getFamilyHistory());
                            edit_illness_history.setText(historyBeanBaseBean.getRows().getOwnHistory());
                        } else { //这里第一次进入
                            mCommit.setText(getString(R.string.save));
                            edit_family_history.setText("");
                            edit_illness_history.setText("");
                        }
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                    }
                });
    }

    /**
     * 健康史 修改
     *physical_examination_data
     * @param visitMemberId
     * @param familyHistory
     * @param ownHistory
     */
    private void getUpdateHistory(int visitMemberId, String familyHistory, String ownHistory) {
        mKProgressHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .updateHistory(visitMemberId, familyHistory, ownHistory)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BaseBean<HealthHistoryBean>>() {
                    @Override
                    protected void onSuccess(BaseBean<HealthHistoryBean> stringBaseBean) {
                        T.showShort(getString(R.string.succeed));
                        mKProgressHUD.dismiss();
                        JumpUtils.exitActivity(mContext);
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                    }
                });

    }

    private void setDummyData() {
        mStrings.add(getString(R.string.nothing));
        mStrings.add(getString(R.string.high_blood_pressure));
        mStrings.add(getString(R.string.heart_disease));
    }
}