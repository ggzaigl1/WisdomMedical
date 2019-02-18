package com.hjy.wisdommedical.ui.personal.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.ListFollowDocBean;
import com.hedgehog.ratingbar.RatingBar;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * 问诊 关注 医生列表Adapter
 * Created by Stefan on 2018/7/9.
 */
public class AttentionDoctorListAdapter extends BaseQuickAdapter<ListFollowDocBean, BaseViewHolder> {

    private RatingBar ratingBar;

    public AttentionDoctorListAdapter(@Nullable List<ListFollowDocBean> data) {
        super(R.layout.item_doctor_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListFollowDocBean item) {
        helper.setText(R.id.tv_docName, item.getAppDocEntityVO().getDocName())//医生名字
                .setText(R.id.tv_hospicalName, item.getAppDocEntityVO().getHospicalName())
                .setText(R.id.tv_departmentName, item.getAppDocEntityVO().getDepartmentName());
        ImageView isFollow = helper.getView(R.id.iv_isFollow);
        if (item.getAppDocEntityVO().getIsFollow() == 1) {
            isFollow.setBackgroundResource(R.mipmap.icon_followed);
        } else {
            isFollow.setBackgroundResource(R.mipmap.icon_follow);
        }
        ratingBar = helper.getView(R.id.RatingBar);
        setRatingBar();
        ratingBar.setStar((float) item.getAppDocEntityVO().getScore());
        helper.addOnClickListener(R.id.iv_isFollow);
    }

    private void setRatingBar() {
        ratingBar.setStarEmptyDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_light));
        ratingBar.setStarFillDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_fill));
        ratingBar.setStarHalfDrawable(mContext.getResources().getDrawable(R.mipmap.icon_star_half));
    }
}
