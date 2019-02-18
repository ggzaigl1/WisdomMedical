package com.hjy.wisdommedical.ui.home.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.handsomelibrary.model.HomeItemBean;
import com.example.handsomelibrary.utils.GlideCircleTransform;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.adapter.RvCommonAdapter;
import com.hjy.wisdommedical.adapter.ViewHolder;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.inquiry.activity.DoctorInfoActivity;
import com.hjy.wisdommedical.util.TintUtils;

import java.util.List;

/**
 * 热门医生 adapter
 * Created by fangs on 2018/6/27.
 */
public class HotDoctorAdapter extends RvCommonAdapter<HomeItemBean.RowsBean> {

    public HotDoctorAdapter(Context context, List<HomeItemBean.RowsBean> datas) {
        super(context, R.layout.item_homefm_hotdoctor, datas);
    }

    @Override
    public void convert(ViewHolder holder, HomeItemBean.RowsBean home, int position) {
        if (position == 0) {
            TextView tvHotDoctor = holder.getView(R.id.tvHotDoctor);
            tvHotDoctor.setVisibility(View.VISIBLE);
        }

        AppCompatImageView imgDoctor = holder.getView(R.id.imgDoctor);
        Glide.with(mContext).load(home.getDocPhotoUrl())
                .fallback(R.mipmap.icon_doctor)
                .placeholder(R.mipmap.icon_doctor)
                .error(R.mipmap.icon_doctor)
                .transform(new GlideCircleTransform(mContext))
                .skipMemoryCache(true)//不自动缓存到内存
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgDoctor);

        holder.setText(R.id.tvDoctorName, home.getDocName());
        holder.setText(R.id.tvDoctorTitle, home.getTitleName());

        Drawable drawable = TintUtils.getDrawable(R.drawable.svg_address, 1);
        TextView tvDoctorAddress = holder.getView(R.id.tvDoctorAddress);
        TintUtils.setTxtIconLocal(tvDoctorAddress, drawable, 0);
        tvDoctorAddress.setText(home.getHospicalName());

        holder.setText(R.id.tvDoctorMajor, mContext.getString(R.string.good_at) + ":" + home.getSpecialty());
        holder.setOnClickListener(R.id.layoutDoctor, v -> {
            Bundle bundle = new Bundle();
            SpfUtils.saveIntToSpf(Constant.docId, home.getId());
            SpfUtils.saveStrToSpf(Constant.docNo, home.getDocNo());
            JumpUtils.jump((AppCompatActivity) mContext, DoctorInfoActivity.class, bundle);
        });
    }
}
