package com.hjy.wisdommedical.ui.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.ListToAppBean;
import com.hedgehog.ratingbar.RatingBar;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/9/1 0001.
 * 首页搜索adapter
 */
public class HomeSearchAdapter extends BaseQuickAdapter<ListToAppBean.RowsBean, BaseViewHolder> {

    private RatingBar ratingBar;

    public HomeSearchAdapter(@Nullable List<ListToAppBean.RowsBean> data) {
        super(R.layout.inquiry_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper,ListToAppBean.RowsBean item) {
        helper.addOnClickListener(R.id.tv_it);
        helper.addOnClickListener(R.id.tv_vd);
        helper.setText(R.id.tv_docName, item.getDocName());//医生姓名
        helper.setText(R.id.tv_titleName, item.getTitleName());//职称
        helper.setText(R.id.tv_hospitalName, item.getHospicalName());//医院名字
        helper.setText(R.id.tv_departmentName, item.getDepartmentName());//科室名字
        helper.setText(R.id.tv_specialty, item.getSpecialty());//病情
        helper.setText(R.id.tv_it, "￥" + item.getListDocService().get(0).getServicePrice());//图文价格
        helper.setText(R.id.tv_vd, "￥" + item.getListDocService().get(1).getServicePrice());//视频价格
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
