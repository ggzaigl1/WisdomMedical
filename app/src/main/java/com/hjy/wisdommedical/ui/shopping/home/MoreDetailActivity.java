package com.hjy.wisdommedical.ui.shopping.home;

import android.annotation.SuppressLint;
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
import com.example.handsomelibrary.model.DetailMoreBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.adapter.ListItemDecoration;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.shopping.home.adapter.GoodsDetailAdapter;
import com.hjy.wisdommedical.ui.shopping.home.adapter.MoreDetailAdapter;
import com.hjy.wisdommedical.ui.shopping.person.adapter.AllOrderAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 初夏小溪
 * data :2018/12/19 0019 15:48
 * 更多评价
 */
public class MoreDetailActivity extends BaseActivity {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.Ll_hand)
    LinearLayout Ll_hand;
    @BindView(R.id.tv_title)
    TextView tv_title;

    int mPageNo = 1;
    MoreDetailAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_more_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.more_detail);
        int goodsId = getIntent().getIntExtra("goodsId", -1);
        getListComment(goodsId);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new MoreDetailAdapter(new ArrayList<>());
        mRecyclerView.addItemDecoration(new ListItemDecoration.Builder().setDraw(true).create(mContext.getApplicationContext()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.activity_advice_error, Ll_hand, false));

    }

    /**
     * 商品评价列表
     *
     * @param goodsId
     */
    @SuppressLint({"CheckResult", "SetTextI18n"})
    private void getListComment(int goodsId) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> objectMap = new ArrayMap<>();
        objectMap.put("goodsId", goodsId);
        objectMap.put("pageNumber", mPageNo);
        objectMap.put("pageSize", 20);
        RxHttpUtils.createApi(ApiService.class)
                .getListComment(objectMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BaseBean<DetailMoreBean>>() {
                    @Override
                    protected void onSuccess(BaseBean<DetailMoreBean> detailMoreBeanBaseBean) {
                        if (null != detailMoreBeanBaseBean) {
                            mKProgressHUD.dismiss();
                            mAdapter.setNewData(detailMoreBeanBaseBean.getRows().getRows());
                        } else {
                            mKProgressHUD.dismiss();
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
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
