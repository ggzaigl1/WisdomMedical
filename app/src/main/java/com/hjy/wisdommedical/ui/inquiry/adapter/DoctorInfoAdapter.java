package com.hjy.wisdommedical.ui.inquiry.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.DoctorInfoBean;
import com.hedgehog.ratingbar.RatingBar;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by Stefan on 2018/6/29.
 * 医生详情患者评价
 */
public class DoctorInfoAdapter extends BaseQuickAdapter<DoctorInfoBean.ListEvaluationCusDocBean, BaseViewHolder> {

    private RatingBar ratingBar;

    public DoctorInfoAdapter(@Nullable List<DoctorInfoBean.ListEvaluationCusDocBean> data) {
        super(R.layout.item_evaluate, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorInfoBean.ListEvaluationCusDocBean item) {
        helper.setText(R.id.tv_time, item.getGmtCreate())
                .setText(R.id.tv_name, item.getUsername())
                .setText(R.id.tv_context, item.getEvaluationContent());
//        ImgLoadUtils.loadCircularBead(mContext, ApiService.BASE_PIC_URL + item.getPhotoUrl(), helper.getView(R.id.iv_pic), 50);
        ratingBar = helper.getView(R.id.RatingBar);
        setRatingBar();
        ratingBar.setStar((float) item.getSorce());
    }

    private void setRatingBar() {
        ratingBar.setStarEmptyDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_light));
        ratingBar.setStarFillDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_fill));
        ratingBar.setStarHalfDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_half));
    }
}
