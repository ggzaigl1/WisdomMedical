package com.hjy.wisdommedical.ui.shopping.person.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.DrugOrderBean;
import com.example.handsomelibrary.model.OrderDetailsListBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.adapter.ListItemDecoration;
import com.hjy.wisdommedical.ui.shopping.person.adapter.OrderDetailAdapter;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 订单详情
 * Created by Stefan on 2018/11/6 10:46.
 */

public class OrderDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_allPrice)
    TextView tv_allPrice;
    @BindView(R.id.tv_order_no)
    TextView tv_order_no;
    @BindView(R.id.tv_gmt)
    TextView tv_gmt;
    @BindView(R.id.tv_pay_gmt)
    TextView tv_pay_gmt;
    @BindView(R.id.order_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.Ll_hand)
    LinearLayout Ll_hand;

    OrderDetailAdapter mAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_order_detail;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        DrugOrderBean.RowsBean rowsBean = (DrugOrderBean.RowsBean) getIntent().getSerializableExtra("rowsBean");
        tv_title.setText("订单详情");
        if (rowsBean != null) {
            getOrderInfo(rowsBean.getId());
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new OrderDetailAdapter(new ArrayList<>());
        mRecyclerView.addItemDecoration(new ListItemDecoration.Builder().setDraw(true).create(mContext.getApplicationContext()));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setEmptyView(LayoutInflater.from(this).inflate(R.layout.activity_advice_error, Ll_hand, false));
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            default:
                break;
        }
    }

    /**
     * 订单详情
     */
    @SuppressLint("CheckResult")
    private void getOrderInfo(int id) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .getOrderInfo(id)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<BaseBean<OrderDetailsListBean>>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    protected void onSuccess(BaseBean<OrderDetailsListBean> orderDetailsListBeanBaseBean) {
                        mKProgressHUD.dismiss();
                        if (null != orderDetailsListBeanBaseBean) {
                            mAdapter.setNewData(orderDetailsListBeanBaseBean.getRows().getOrderDetails());
                            tv_name.setText(orderDetailsListBeanBaseBean.getRows().getOrder().getReceiptName() + " " + orderDetailsListBeanBaseBean.getRows().getOrder().getReceiptPhone());
                            tv_address.setText(orderDetailsListBeanBaseBean.getRows().getOrder().getReceiptAddress());
                            tv_allPrice.setText("共" + orderDetailsListBeanBaseBean.getRows().getOrderDetails().get(0).getGoodsCount()
                                    + "件商品 "
                                    + "合计：￥" + orderDetailsListBeanBaseBean.getRows().getOrder().getOrderPay()
                                    + "（含运费" + orderDetailsListBeanBaseBean.getRows().getOrder().getLogisticsPay() + "元）");
                            tv_order_no.setText(orderDetailsListBeanBaseBean.getRows().getOrder().getOrderNo());
                            tv_gmt.setText(orderDetailsListBeanBaseBean.getRows().getOrder().getGmtCreate());
                            tv_pay_gmt.setText(orderDetailsListBeanBaseBean.getRows().getOrder().getGmtCreate());
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                    }
                });
    }
}
