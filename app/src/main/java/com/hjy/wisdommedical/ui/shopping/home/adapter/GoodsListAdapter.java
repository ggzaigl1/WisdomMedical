package com.hjy.wisdommedical.ui.shopping.home.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.model.BaseListBean;
import com.example.handsomelibrary.model.MallHomeBean;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by Stefan on 2018/10/16.
 * 商品列表和搜索详情列表
 */
public class GoodsListAdapter extends BaseQuickAdapter<BaseListBean.RowsBean, BaseViewHolder> {

    public GoodsListAdapter(@Nullable List<BaseListBean.RowsBean> data) {
        super(R.layout.item_goods_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseListBean.RowsBean item) {
        helper.setText(R.id.tv_pdCom, item.getPdCom())
                .setText(R.id.tv_name, item.getPdName())
                .setText(R.id.tv_pd_price, "￥" + item.getPriceNow());
        String pdSmallPic = item.getPdSmallPic();
        String smallPic;
        if (!TextUtils.isEmpty(pdSmallPic)) {
            if (pdSmallPic.contains(",")) {
                String[] split = pdSmallPic.split(",");
                smallPic = split[0];
            } else {
                smallPic = item.getPdSmallPic();
            }
            Glide.with(mContext).load(ApiService.BASE_PIC_URL + smallPic).into((ImageView) helper.getView(R.id.iv_pic));
        } else {
            Glide.with(mContext).load(R.mipmap.icon_placeholder).into((ImageView) helper.getView(R.id.iv_pic));
        }
    }
}
