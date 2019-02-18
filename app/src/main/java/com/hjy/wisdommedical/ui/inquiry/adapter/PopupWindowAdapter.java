package com.hjy.wisdommedical.ui.inquiry.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.DeptListBean;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by Stefan on 2018/6/29.
 */
public class PopupWindowAdapter extends BaseQuickAdapter<DeptListBean, BaseViewHolder> {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PopupWindowAdapter(@Nullable List<DeptListBean> data) {
        super(R.layout.item_popu, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DeptListBean item) {
        helper.setText(R.id.tv_dept, item.getDepartmentName())
                .addOnClickListener(R.id.tv_dept);
        id = item.getId();
    }
}
