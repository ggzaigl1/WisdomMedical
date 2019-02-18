package com.hjy.wisdommedical.ui.shopping.person.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.DrugOrderBean;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.shopping.home.GoodsDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 2018/11/1.
 * 订单 待付款
 */
public class AllOrderAdapter extends BaseQuickAdapter<DrugOrderBean.RowsBean, BaseViewHolder> {

    private List<DrugOrderBean.RowsBean> mBeans;

    public AllOrderAdapter(@Nullable List<DrugOrderBean.RowsBean> data) {
        super(R.layout.item_store_all_order, data);
        this.mBeans = data;
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugOrderBean.RowsBean item) {
        int orderStatus = item.getOrderStatus();
        TextView tv_delStatus = helper.getView(R.id.tv_delStatus);
        TextView tv_left = helper.getView(R.id.tv_left);
        TextView tv_right = helper.getView(R.id.tv_right);

        RecyclerView recyclerView = helper.getView(R.id.recycler_order);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        OrderAdapter mAdapter = new OrderAdapter(new ArrayList<>());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setNewData(item.getListOrderDetails());
        helper.setText(R.id.tv_order, "订单编号" + " " + item.getOrderNo())
                .setText(R.id.tv_allPrice, "共"
                        + item.getListOrderDetails().size()
                        + "件商品 合计："
                        + item.getOrderPay()
                        + "元"
                        + "（含运费" + " " + item.getLogisticsPay() + "元)");
        helper.addOnClickListener(R.id.tv_right)
                .addOnClickListener(R.id.tv_left);
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.Ll_child:
                    Bundle bundle = new Bundle();
                    bundle.putInt("id", mAdapter.getData().get(position).getGoodsId());//商品id
                    bundle.putInt("priceNow", mAdapter.getData().get(position).getGoodsPrice());
                    bundle.putString("pdSmallPic", mAdapter.getData().get(position).getPdSmallPic());
                    bundle.putString("pdCom", mAdapter.getData().get(position).getPdName());
                    bundle.putInt("Amount", mAdapter.getData().get(position).getGoodsCount());
                    JumpUtils.jump((AppCompatActivity) mContext, GoodsDetailActivity.class, bundle);
                    break;
                default:
                    break;
            }
        });

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

        switch (orderStatus) {
            case 1:
                tv_left.setText("取消订单");
                tv_right.setText("去付款");
                tv_left.setVisibility(View.VISIBLE);
                tv_right.setVisibility(View.VISIBLE);
                tv_delStatus.setText("待付款");
                break;
            case 2:
                tv_left.setText("取消订单");
                tv_left.setVisibility(View.VISIBLE);
                tv_right.setVisibility(View.GONE);
                tv_delStatus.setText("待发货");
                break;
            case 3:
                tv_left.setText("查看物流");
                tv_right.setText("确认收货");
                tv_left.setVisibility(View.VISIBLE);
                tv_right.setVisibility(View.VISIBLE);
                tv_delStatus.setText("待收货");
                break;
            case 4:
                tv_right.setText("去评价");
                tv_right.setVisibility(View.VISIBLE);
                tv_left.setVisibility(View.GONE);
                tv_delStatus.setText("待评论");
                break;
            case 5:
                tv_left.setVisibility(View.GONE);
                tv_right.setVisibility(View.GONE);
                tv_delStatus.setText("完成");
                break;
            case 6:
                tv_left.setText("去评价");
                tv_left.setVisibility(View.VISIBLE);
                tv_right.setVisibility(View.GONE);
                tv_delStatus.setText("待退款");
                break;
            case 7:
                tv_left.setText("去评价");
                tv_left.setVisibility(View.VISIBLE);
                tv_right.setVisibility(View.GONE);
                tv_delStatus.setText("已退款");
                break;
            case 8:
                tv_left.setVisibility(View.GONE);
                tv_right.setVisibility(View.GONE);
                tv_delStatus.setText("订单超时");
                break;
            default:
                tv_left.setVisibility(View.GONE);
                tv_right.setVisibility(View.GONE);
                tv_delStatus.setText("订单超时");
                break;
        }
    }
}
