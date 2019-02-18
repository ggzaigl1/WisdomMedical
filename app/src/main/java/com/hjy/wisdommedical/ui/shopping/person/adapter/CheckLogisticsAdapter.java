package com.hjy.wisdommedical.ui.shopping.person.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by Stefan on 2018/11/7.
 */
public class CheckLogisticsAdapter extends BaseQuickAdapter<LogisticsBean, BaseViewHolder> {
    public CheckLogisticsAdapter(@Nullable List<LogisticsBean> data) {
        super(R.layout.item_check_logistics, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LogisticsBean item) {
        helper.setText(R.id.tv_place, item.getPlace());
        ImageView iv_group = helper.getView(R.id.iv_group);
        if (helper.getLayoutPosition() == 0) {
            iv_group.setImageResource(R.mipmap.group_2x);
        }else {
            iv_group.setImageResource(R.mipmap.group_n_2x);
        }
    }
}
