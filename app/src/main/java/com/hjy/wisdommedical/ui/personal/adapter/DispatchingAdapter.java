package com.hjy.wisdommedical.ui.personal.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.AddressToListBean;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/7/10 0010.
 * 配送地址 adapter
 */

public class DispatchingAdapter extends BaseQuickAdapter<AddressToListBean.RowsBean, BaseViewHolder> {

    public DispatchingAdapter(@Nullable List<AddressToListBean.RowsBean> data) {
        super(R.layout.item_dispatching, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressToListBean.RowsBean item) {
        helper.setText(R.id.tv_name, item.getConsigneeName()).setText(R.id.tv_phone, item.getMobile())
                .setText(R.id.tv_address, item.getCountry() + item.getProvince() + item.getCity() + item.getDistrict() + item.getStreet());

        if (item.getIsDefault() == 1) {
            helper.setImageDrawable(R.id.choose_user_list_item_image, mContext.getResources().getDrawable(R.drawable.svg_dispatching_on));
        } else {
            helper.setImageDrawable(R.id.choose_user_list_item_image, mContext.getResources().getDrawable(R.drawable.svg_dispatching_off));
        }

        helper.addOnClickListener(R.id.iv_choose_user_delete);
        helper.addOnClickListener(R.id.iv_choose_user_compile);
        helper.addOnClickListener(R.id.Ll_Default);
    }

}
