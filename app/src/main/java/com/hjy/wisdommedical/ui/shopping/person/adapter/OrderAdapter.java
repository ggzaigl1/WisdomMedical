package com.hjy.wisdommedical.ui.shopping.person.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.model.DrugOrderBean;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by Stefan on 2018/11/1.
 * 订单 待付款 二级条目
 */
public class OrderAdapter extends BaseQuickAdapter<DrugOrderBean.RowsBean.ListOrderDetailsBean, BaseViewHolder> {

    public OrderAdapter(@Nullable List<DrugOrderBean.RowsBean.ListOrderDetailsBean> data) {
        super(R.layout.item_store_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrugOrderBean.RowsBean.ListOrderDetailsBean item) {
        helper.setText(R.id.tv_count, item.getPdName())
                .setText(R.id.tv_price, item.getGoodsPrice() + "")
                .setText(R.id.tv_goodsCount, "×" + item.getGoodsCount());
        Glide.with(mContext).load(ApiService.BASE_PIC_URL + item.getPdSmallPic()).into((ImageView) helper.getView(R.id.iv_img));
        helper.addOnClickListener(R.id.Ll_child);
    }
}
