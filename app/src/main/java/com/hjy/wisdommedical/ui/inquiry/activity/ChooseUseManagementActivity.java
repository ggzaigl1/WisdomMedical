package com.hjy.wisdommedical.ui.inquiry.activity;

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

import com.afollestad.materialdialogs.MaterialDialog;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.ChooseUserBean;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.net.RetrofitManager;
import com.hjy.wisdommedical.ui.home.activity.HealthRecordsActivity;
import com.hjy.wisdommedical.ui.inquiry.adapter.ChooseUseManagementAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/7/3 0003.
 * 个人资料 就诊人管理
 */

public class ChooseUseManagementActivity extends BaseActivity {

    @BindView(R.id.rv_choose_user)
    RecyclerView mRecyclerView;
    @BindView(R.id.Ll_hand)
    LinearLayout Ll_hand;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_right)
    ImageView iv_right;
    ChooseUseManagementAdapter mAdapter;
    int mPageNo = 1;
    private int type = 1; //1:编辑进入的  0：新增进入的
    int classNameType;

    @Override
    protected int getContentView() {
        return R.layout.activity_choose_user_management;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initView();
        initRefresh();
        tv_title.setText(R.string.select_patient_management);
        iv_right.setBackground(ContextCompat.getDrawable(mContext, R.mipmap.icon_choose_user_plus));
        iv_right.setVisibility(View.VISIBLE);
        classNameType = getIntent().getIntExtra("type", 0);//首页传值HomeFragment

    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ChooseUseManagementAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
        View EmptyView = LayoutInflater.from(mContext).inflate(R.layout.activity_error, Ll_hand, false);
        mAdapter.setEmptyView(EmptyView);
        //item 条目点击事情
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Bundle bundle = new Bundle();
                ChooseUserBean.RowsBean chooseUseManagementBean = mAdapter.getData().get(position);
                bundle.putSerializable("chooseUseManagementBean", chooseUseManagementBean);
                if (classNameType == 1) {
                    //健康档案
                    JumpUtils.jump(mContext, HealthRecordsActivity.class, bundle);
                    finish();
                }
            }
        });
        //item 子控件点击事件
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.iv_choose_user_delete:
                    //删除家庭成员ID
                    new MaterialDialog.Builder(mContext).title(R.string.system_title)
                            .content(R.string.is_delete).positiveColor(ContextCompat.getColor(mContext, R.color.mainColor))
                            .positiveText(R.string.ok).onPositive((dialog, which) -> removeHealthManagement(mAdapter.getData().get(position).getId(), position))
                            .negativeText(R.string.cancel).negativeColor(ContextCompat.getColor(mContext, R.color.mainColor))
                            .onNegative((dialog, which) -> dialog.dismiss()).show();
                    break;
                //编辑
                case R.id.iv_choose_user_compile:
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", mAdapter.getData().get(position).getId());
                    bundle.putInt("type", type);
                    JumpUtils.jump(mContext, AddUseActivity.class, bundle);
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
                JumpUtils.jump(mContext, AddUseActivity.class, bundle);
                break;
        }
    }

    /**
     * 获取就诊人列表
     *
     * @param mPageNo
     */
    private void setDummyData(int mPageNo) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> map = new ArrayMap<>();
        map.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
        map.put("pageNumber", mPageNo);
        map.put("pageSize", 20);
        RetrofitManager.createApi(ApiService.class)
                .HealthManagementListToApp(map)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<ChooseUserBean>() {
                    @Override
                    protected void onSuccess(ChooseUserBean chooseUserBean) {
                        mKProgressHUD.dismiss();
                        if (null != chooseUserBean) {
                            if (mRefreshLayout.isRefreshing()) {
                                mAdapter.setNewData(chooseUserBean.getRows());
                                mRefreshLayout.finishRefresh();
                            } else if (mRefreshLayout.isLoading()) {
                                mAdapter.getData().addAll(chooseUserBean.getRows());
                                mRefreshLayout.finishLoadMore();
                                mAdapter.notifyDataSetChanged();
                            } else {
                                mAdapter.setNewData(chooseUserBean.getRows());
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
     * 删除就诊人
     *
     * @param id
     */
    private void removeHealthManagement(int id, int position) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RetrofitManager.createApi(ApiService.class)
                .removeManagementSave(id, SpfUtils.getSpfSaveInt(Constant.userId))
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<Object>() {
                    @Override
                    protected void onSuccess(Object o) {
                        T.showShort(getString(R.string.delete_succeed));
                        mKProgressHUD.dismiss();
                        mAdapter.getData().remove(position);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void onError(String errorMsg) {
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
                setDummyData(mPageNo);
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                mPageNo = 1;
                setDummyData(mPageNo);
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

    @Override
    protected void onResume() {
        super.onResume();
        setDummyData(mPageNo);
    }
}
