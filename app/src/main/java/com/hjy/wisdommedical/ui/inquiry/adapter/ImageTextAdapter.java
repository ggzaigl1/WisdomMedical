package com.hjy.wisdommedical.ui.inquiry.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.DiseaseBean;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/7/2 0002.
 * list
 */
public class ImageTextAdapter extends BaseQuickAdapter<DiseaseBean.ListCommonDiseaseBean, BaseViewHolder> {

    public ImageTextAdapter(@Nullable List<DiseaseBean.ListCommonDiseaseBean> data) {
        super(R.layout.item_image_text, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, DiseaseBean.ListCommonDiseaseBean item) {
        helper.setText(R.id.tv_item_image_text, item.getDiseaseName());
        helper.addOnClickListener(R.id.tv_item_image_text);

        switch (item.getId()) {
            case 1:
                helper.setBackgroundColor(R.id.tv_item_image_text, mContext.getResources().getColor(R.color.main_bg));
                break;
            case 2:
                helper.setBackgroundColor(R.id.tv_item_image_text, mContext.getResources().getColor(R.color.yellow));
                break;
            case 3:
                helper.setBackgroundColor(R.id.tv_item_image_text, mContext.getResources().getColor(R.color.azure));
                break;
            case 4:
                helper.setBackgroundColor(R.id.tv_item_image_text, mContext.getResources().getColor(R.color.red));
                break;
        }
    }
}
