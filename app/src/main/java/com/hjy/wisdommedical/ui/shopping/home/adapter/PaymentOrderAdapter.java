package com.hjy.wisdommedical.ui.shopping.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.model.ShopingCartBean;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by Stefan on 2018/10/24.
 * 支付订单
 */
public class PaymentOrderAdapter extends BaseQuickAdapter<ShopingCartBean.RowsBean, BaseViewHolder> {

    public PaymentOrderAdapter(@Nullable List<ShopingCartBean.RowsBean> data) {
        super(R.layout.item_payment_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopingCartBean.RowsBean item) {
        helper.setText(R.id.tv_count, item.getProduct().getPdName())
                .setText(R.id.tv_price, "￥" + item.getProduct().getPriceNow())
                .setText(R.id.tv_num, "×" + item.getAmount());
        Glide.with(mContext).load(ApiService.BASE_PIC_URL + item.getProduct().getPdSmallPic()).into((ImageView) helper.getView(R.id.iv_img));
    }
}
