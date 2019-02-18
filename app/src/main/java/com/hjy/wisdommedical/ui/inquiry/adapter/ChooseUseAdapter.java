package com.hjy.wisdommedical.ui.inquiry.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.ChooseUseManagementBean;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/7/3 0003.
 * 选择就诊人 adapter
 */
public class ChooseUseAdapter extends BaseQuickAdapter<ChooseUseManagementBean, BaseViewHolder> {

    public ChooseUseAdapter(@Nullable List<ChooseUseManagementBean> data) {
        super(R.layout.item_choose_user, data);
        for (int i = 0; i < data.size(); i++) {
            if (i == 0) {
                data.get(0).setSelected(true);
            }
        }
    }

    @Override
    protected void convert(BaseViewHolder helper, ChooseUseManagementBean item) {
        int type = item.getGender();
        String idType = "";
        switch (type) {
            case 0:
                idType = "女";
                break;
            case 1:
                idType = "男";
                break;
        }
        helper.setText(R.id.tv_name, item.getMemberName())
                .setText(R.id.tv_sex, idType)
                .setText(R.id.tv_bry, item.getBirthday())
                .setText(R.id.tv_phone, item.getMobile())
                .setText(R.id.tv_id, item.getIdNumber());

        if (item.getIsDefault() == 1) {
            helper.setImageDrawable(R.id.choose_user_list_item_image, mContext.getResources().getDrawable(R.drawable.svg_dispatching_on));
        } else {
            helper.setImageDrawable(R.id.choose_user_list_item_image, mContext.getResources().getDrawable(R.drawable.svg_dispatching_off));
        }

        helper.addOnClickListener(R.id.iv_choose_user_delete);
        helper.addOnClickListener(R.id.Ll_ManagementDefault);
    }

}
