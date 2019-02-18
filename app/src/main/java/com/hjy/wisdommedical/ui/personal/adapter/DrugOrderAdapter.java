package com.hjy.wisdommedical.ui.personal.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by QKun on 2018/7/10.
 */

public class DrugOrderAdapter extends BaseQuickAdapter<Object,BaseViewHolder> {

    public DrugOrderAdapter(@Nullable List<Object> data) {
        super(R.layout.item_drug_order,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }
}
