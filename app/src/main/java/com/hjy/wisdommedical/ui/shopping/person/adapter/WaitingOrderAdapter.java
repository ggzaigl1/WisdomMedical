package com.hjy.wisdommedical.ui.shopping.person.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.DrugOrderBean;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.adapter.ListItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 2018/11/1.
 * 待付款订单
 */
public class WaitingOrderAdapter extends BaseQuickAdapter<DrugOrderBean.RowsBean, BaseViewHolder> {

    public WaitingOrderAdapter(@Nullable List<DrugOrderBean.RowsBean> data) {
        super(R.layout.item_store_waiting_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugOrderBean.RowsBean item) {
        int orderStatus = item.getOrderStatus();
        TextView tv_cancel = helper.getView(R.id.tv_cancel);
        TextView tv_pay = helper.getView(R.id.tv_pay);

        RecyclerView recyclerView = helper.getView(R.id.recycler_order);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.addItemDecoration(new ListItemDecoration.Builder().setDraw(true).create(mContext.getApplicationContext()));
        OrderAdapter mAdapter = new OrderAdapter(new ArrayList<>());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(item.getListOrderDetails());
//        helper.setText(R.id.tv_order, "订单编号" + " " + item.getOrderNo());
        helper.setText(R.id.tv_allPrice, item.getOrderPay() + "");
        helper.addOnClickListener(R.id.tv_cancel);
        helper.addOnClickListener(R.id.tv_pay);

        /**
         * 1;待付款
         * 2;待发货
         * 3;待收货
         * 4;待评论
         * 5;完成
         * 6;待退款
         * 7;已退款
         * 8;订单超时
         */
        if (orderStatus == 1) {
//            tv_delStatus.setText("待付款");
            tv_cancel.setText("取消订单");
            tv_pay.setText("去付款");
        }
    }
}
