package com.hjy.wisdommedical.ui.inquiry.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.DoctorInfoBean;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by Stefan on 2018/6/29.
 * 医生详情患者标签
 */
public class EvaluationDoctorInfoTagAdapter extends BaseQuickAdapter<DoctorInfoBean.ListEvaluationTagDocBean, BaseViewHolder> {

    public EvaluationDoctorInfoTagAdapter(@Nullable List<DoctorInfoBean.ListEvaluationTagDocBean> data) {
        super(R.layout.item_comment_text, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DoctorInfoBean.ListEvaluationTagDocBean item) {

        helper.setText(R.id.tv_item_text, item.getTagName() + "(" + item.getEvaluationCount() + ")");
        helper.addOnClickListener(R.id.tv_item_text);
        if (item.isIselector()) {
            helper.setBackgroundRes(R.id.tv_item_text, R.drawable.shape_comment_bg_yes)
                    .setTextColor(R.id.tv_item_text, ContextCompat.getColor(mContext, R.color.white));
        } else {
            helper.setBackgroundRes(R.id.tv_item_text, R.drawable.shape_comment_bg_no)
                    .setTextColor(R.id.tv_item_text, ContextCompat.getColor(mContext, R.color.black));
        }
    }

}
