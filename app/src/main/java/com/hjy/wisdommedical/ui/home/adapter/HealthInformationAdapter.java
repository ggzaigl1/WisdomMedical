package com.hjy.wisdommedical.ui.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.HealthInformationBean;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/8/14 0014.
 * 健康资讯  adapter
 */

public class HealthInformationAdapter extends BaseQuickAdapter<HealthInformationBean.RowsBean, BaseViewHolder> {


    public HealthInformationAdapter(@Nullable List<HealthInformationBean.RowsBean> data) {
        super(R.layout.item_health_information, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HealthInformationBean.RowsBean item) {
        helper.setText(R.id.tv_title, item.getCategory())
                .setText(R.id.tv_context, item.getTitle());
        helper.addOnClickListener(R.id.Ll_health_item_onclick);
//        ImgLoadUtils.loadCircularBead(mContext, ApiService.BASE_PIC_URL + item.getPhotoUrl(), helper.getView(R.id.iv_pic), 50);
    }

}
