package com.hjy.wisdommedical.ui.inquiry.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.ChooseUserBean;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/7/3 0003.
 * 个人资料 选择就诊人 adapter
 */
public class ChooseUseManagementAdapter extends BaseQuickAdapter<ChooseUserBean.RowsBean, BaseViewHolder> {

    public ChooseUseManagementAdapter(@Nullable List<ChooseUserBean.RowsBean> data) {
        super(R.layout.item_choose_management_user, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ChooseUserBean.RowsBean item) {
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
        helper.addOnClickListener(R.id.iv_choose_user_delete);
        helper.addOnClickListener(R.id.iv_choose_user_compile);
    }
}
