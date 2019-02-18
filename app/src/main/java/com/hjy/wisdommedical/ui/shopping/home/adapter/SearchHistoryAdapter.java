package com.hjy.wisdommedical.ui.shopping.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by Stefan on 2018/10/19.
 */
public class SearchHistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public SearchHistoryAdapter(@Nullable List<String> data) {
        super(R.layout.item_search_history, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_Name,item).addOnClickListener(R.id.tv_Name);
        helper.addOnClickListener(R.id.ll_delete);
    }
}
