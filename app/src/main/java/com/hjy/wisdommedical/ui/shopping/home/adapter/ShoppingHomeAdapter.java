package com.hjy.wisdommedical.ui.shopping.home.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.model.MallHomeBeanSection;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * @author QKun
 * @date 2018/10/12
 */
public class ShoppingHomeAdapter extends BaseSectionQuickAdapter<MallHomeBeanSection, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.tv_mall_home_buy
     *
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public ShoppingHomeAdapter(List<MallHomeBeanSection> data) {
        super(R.layout.mall_home_recycle_item, R.layout.mall_home_header_item, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MallHomeBeanSection item) {
        Glide.with(mContext).load(ApiService.BASE_PIC_URL + item.header).into((ImageView) helper.getView(R.id.iv_mall_home_cfPic));
        helper.addOnClickListener(R.id.iv_mall_home_cfPic);
    }

    @Override
    protected void convert(BaseViewHolder helper, MallHomeBeanSection item) {
        if (item.t.getPdSmallPic().contains(",")) {
            String[] split = item.t.getPdSmallPic().split(",");
            Glide.with(mContext).load(ApiService.BASE_PIC_URL + split[0]).into((ImageView) helper.getView(R.id.iv_mall_home_pdSmallPic));
        } else {
            Glide.with(mContext).load(ApiService.BASE_PIC_URL + item.t.getPdSmallPic()).into((ImageView) helper.getView(R.id.iv_mall_home_pdSmallPic));
        }
        helper.setText(R.id.tv_mall_home_pdSign, item.t.getPdName());
        helper.setText(R.id.tv_mall_home_pdCom, item.t.getPdCom().trim());
        helper.setText(R.id.tv_mall_home_price, "￥" + item.t.getPriceNow());

        helper.addOnClickListener(R.id.tv_mall_home_buy);
    }
}
