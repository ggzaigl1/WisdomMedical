package com.hjy.wisdommedical.ui.shopping.home.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.model.DetailMoreBean;
import com.example.handsomelibrary.model.ProductInfoBean;
import com.example.handsomelibrary.utils.GlideRoundTransform;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * @author Stefan
 * @date 2018/10/16
 */
public class MoreDetailAdapter extends BaseQuickAdapter<DetailMoreBean.RowsBean, BaseViewHolder> {

    public MoreDetailAdapter(@Nullable List<DetailMoreBean.RowsBean> data) {
        super(R.layout.item_assess, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailMoreBean.RowsBean item) {
        if (item.getPhotoUrl() != null) {
            Glide.with(mContext)
                    .load(ApiService.BASE_PIC_URL + item.getPhotoUrl())
                    .transform(new CenterCrop(mContext), new GlideRoundTransform(mContext, 50))
                    .crossFade(500) //标准的淡入淡出动画
                    .into((ImageView) helper.getView(R.id.iv_pic));
        } else {
            Glide.with(mContext)
                    .load(R.mipmap.icon_man)
                    .transform(new CenterCrop(mContext), new GlideRoundTransform(mContext, 50))
                    .crossFade(500) //标准的淡入淡出动画
                    .into((ImageView) helper.getView(R.id.iv_pic));
        }

        helper.setText(R.id.tv_name, item.getNickname())
                .setText(R.id.tv_context, item.getEvaContent());
    }
}
