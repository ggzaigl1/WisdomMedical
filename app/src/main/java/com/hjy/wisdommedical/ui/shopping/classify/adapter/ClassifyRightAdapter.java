package com.hjy.wisdommedical.ui.shopping.classify.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.ListClassBean;
import com.example.handsomelibrary.model.listSnClassBean;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by Stefan on 2018/10/25.
 */
public class ClassifyRightAdapter extends BaseQuickAdapter<listSnClassBean, BaseViewHolder> {

    public ClassifyRightAdapter(@Nullable List<listSnClassBean> data) {
        super(R.layout.item_classify_right, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, listSnClassBean item) {
        TextView tv_DrugClassify = helper.getView(R.id.tv_DrugClassify);
        tv_DrugClassify.setText(item.getCfName());
    }
}
