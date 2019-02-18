package com.hjy.wisdommedical.ui.inquiry.activity;

import android.content.Intent;
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
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.ChooseUseManagementBean;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.SpfUtils;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.net.RetrofitManager;
import com.hjy.wisdommedical.ui.inquiry.adapter.ChooseUseAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/7/3 0003.
 * 选择就诊人
 */

public class ChooseUseActivity extends BaseActivity {

    @BindView(R.id.rv_choose_user)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.Ll_hand)
    LinearLayout Ll_hand;
    ChooseUseAdapter mAdapter;


    @Override
    protected int getContentView() {
        return R.layout.activity_choose_user;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        init();
        setDummyData();
        tv_title.setText(R.string.select_patient);
    }

    private void init() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ChooseUseAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        View EmptyView = LayoutInflater.from(mContext).inflate(R.layout.activity_user_error, Ll_hand, false);
        mAdapter.setEmptyView(EmptyView);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.iv_choose_user_delete:
                        //家庭成员ID
//                        new MaterialDialog.Builder(mContext).title(R.string.system_title)
//                                .content(R.string.is_delete).positiveColor(ContextCompat.getColor(mContext, R.color.mainColor))
//                                .positiveText(R.string.ok).onPositive((dialog, which) -> removeHealthManagement(mAdapter.getData().get(position).getId(), position))
//                                .negativeText(R.string.cancel).negativeColor(ContextCompat.getColor(mContext, R.color.mainColor))
//                                .onNegative((dialog, which) -> dialog.dismiss()).show();
//                        break;
                    case R.id.Ll_ManagementDefault:
                        GetDefault(position, mAdapter.getData().get(position).getId());
                        break;
                }
            }
        });
    }

    /**
     * 设置默认家庭成员
     *
     * @param id
     */
    private void GetDefault(int position, int id) {
        ArrayMap<String, Object> param = new ArrayMap<>();
        param.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        param.put("id", id);
        RetrofitManager.createApi(ApiService.class)
                .SethealthManagementDefault(param)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<Object>() {
                    @Override
                    protected void onSuccess(Object Object) {
                        //返回到图文 视频 音频界面
                        Intent intent = getIntent();
                        intent.putExtra("name", mAdapter.getData().get(position).getMemberName());
                        intent.putExtra("visitMemberId", mAdapter.getData().get(position).getId());
                        setResult(Constant.SELECT_USER, intent);
                        finish();
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                    }
                });
    }

    @OnClick({R.id.iv_back})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
        }
    }


    /**
     * 获取就诊人列表
     */
    private void setDummyData() {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> map = new ArrayMap<>();
        map.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        RetrofitManager.createApi(ApiService.class)
                .listVisitMembers(map)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<List<ChooseUseManagementBean>>() {
                    @Override
                    protected void onSuccess(List<ChooseUseManagementBean> chooseUseManagementBeans) {
                        mKProgressHUD.dismiss();
                        if (null != chooseUseManagementBeans) {
                            mAdapter.setNewData(chooseUseManagementBeans);
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                    }
                });
    }

//    /**
//     * 删除就诊人
//     *
//     * @param id
//     */
//    private void removeHealthManagement(int id, int position) {
//        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
//        RetrofitManager.createApi(ApiService.class)
//                .removeManagementSave(id, SpfUtils.getSpfSaveInt(Constant.userId))
//                .compose(Transformer.switchSchedulers())
//                .subscribe(new CommonObserver<Object>() {
//                    @Override
//                    protected void onSuccess(Object o) {
//                        T.showShort("删除成功！");
//                        mAdapter.getData().remove(position);
//                        mAdapter.notifyDataSetChanged();
//                        mKProgressHUD.dismiss();
//                    }
//
//                    @Override
//                    protected void onError(String errorMsg) {
//                        mKProgressHUD.dismiss();
//                    }
//                });
//    }

}
