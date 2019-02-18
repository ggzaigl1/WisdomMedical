package com.hjy.wisdommedical.ui.shopping.person.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.model.OrderDetailsListBean;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by 初夏小溪
 * 订单详情
 * data :2018/12/11 0011 10:01
 */
public class OrderDetailAdapter extends BaseQuickAdapter<OrderDetailsListBean.OrderDetailsBean, BaseViewHolder> {

    public OrderDetailAdapter(@Nullable List<OrderDetailsListBean.OrderDetailsBean> data) {
        super(R.layout.item_store_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderDetailsListBean.OrderDetailsBean item) {
        helper.setText(R.id.tv_count, item.getPdName())
                .setText(R.id.tv_price, item.getGoodsPrice() + "")
                .setText(R.id.tv_goodsCount, "×" + item.getGoodsCount());
        Glide.with(mContext).load(ApiService.BASE_PIC_URL + item.getPdSmallPic()).into((ImageView) helper.getView(R.id.iv_img));
    }
}
