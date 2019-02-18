package com.hjy.wisdommedical.ui.home.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.HealthInformationBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.home.adapter.HealthInformationAdapter;
import com.hjy.wisdommedical.ui.web.WebViewActivity;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 初夏小溪 on 2018/8/14 0014.
 * 健康资讯
 */

public class HealthInformationActivity extends BaseActivity {

    @BindView(R.id.recycler_health_information)
    RecyclerView mRecyclerView;
    @BindView(R.id.Ll_health)
    LinearLayout Ll_health;
    @BindView(R.id.tv_title)
    TextView tv_title;

    HealthInformationAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_health_information;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.health_information);
        getListByDocIdToApp();
        initView();
    }


    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new HealthInformationAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setEmptyView(LayoutInflater.from(mContext).inflate(R.layout.activity_advice_error, Ll_health, false));
        mAdapter.setOnItemChildClickListener((adapter, view1, position) -> {
            switch (view1.getId()) {
                case R.id.Ll_health_item_onclick:
                    HealthInformationBean.RowsBean rowsBean = mAdapter.getData().get(position);
                    WebViewActivity.startWebActivity(mContext, rowsBean.getNewsUrl());// 详情
                    break;
            }
        });
    }

    /**
     * 健康资讯
     */
    private void getListByDocIdToApp() {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> prams = new ArrayMap<>();
        prams.put("pageNumber", 1);
        prams.put("pageSize ", 20);
        RxHttpUtils.createApi(ApiService.class)
                .healthNews(prams)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BaseBean<HealthInformationBean>>() {
                    @Override
                    protected void onSuccess(BaseBean<HealthInformationBean> listBaseBean) {
                        mAdapter.setNewData(listBaseBean.getRows().getRows());
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
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
