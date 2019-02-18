package com.hjy.wisdommedical.ui.inquiry.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.ListToAppBean;
import com.hedgehog.ratingbar.RatingBar;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * 在线问诊 Adapter
 * Created by Stefan on 2018/6/29.
 */
public class InquiryAdapter extends BaseQuickAdapter<ListToAppBean.RowsBean, BaseViewHolder> {
    private RatingBar ratingBar;

    public InquiryAdapter(@Nullable List<ListToAppBean.RowsBean> data) {
        super(R.layout.inquiry_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListToAppBean.RowsBean item) {
        helper.addOnClickListener(R.id.tv_it);
        helper.addOnClickListener(R.id.tv_vd);
        helper.setText(R.id.tv_docName, item.getDocName());//医生姓名
        helper.setText(R.id.tv_titleName, item.getTitleName());//职称
        helper.setText(R.id.tv_hospitalName, item.getHospicalName());//医院名字
        helper.setText(R.id.tv_departmentName, item.getDepartmentName());//科室名字
        helper.setText(R.id.tv_specialty, item.getSpecialty());//病情
        helper.setText(R.id.tv_it, "￥" + 0.00);//图文价格
        helper.setText(R.id.tv_vd, "￥" + 0.00);//视频价格
        ratingBar = helper.getView(R.id.RatingBar);
        setRatingBar();
        ratingBar.setStar((float) item.getScore());//分数
        helper.setText(R.id.tv_diagnoseCount, item.getDiagnoseCount() + "名患者");//患者数量

    }

    private void setRatingBar() {
        ratingBar.setStarEmptyDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_light));
        ratingBar.setStarFillDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_fill));
        ratingBar.setStarHalfDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_half));
    }
}
