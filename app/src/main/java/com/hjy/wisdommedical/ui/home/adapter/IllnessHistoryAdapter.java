package com.hjy.wisdommedical.ui.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by 初夏小溪 on 2018/9/11 0011.
 * 健康历史 疾病史
 */
public class IllnessHistoryAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public IllnessHistoryAdapter(@Nullable List<String> data) {
        super(R.layout.item_image_text, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (helper.getLayoutPosition() == 0) {
            helper.setText(R.id.tv_item_image_text, "无");
        } else if (helper.getLayoutPosition() == 1) {
            helper.setText(R.id.tv_item_image_text, "高血压");
        } else if (helper.getLayoutPosition() == 2) {
            helper.setText(R.id.tv_item_image_text, "心脏病");
        }

        helper.addOnClickListener(R.id.tv_item_image_text);
    }
}
