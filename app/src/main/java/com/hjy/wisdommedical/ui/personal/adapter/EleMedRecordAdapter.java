package com.hjy.wisdommedical.ui.personal.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.personal.activity.TextBean;

import java.util.List;

/**
 * Created by Stefan on 2018/9/7.
 */
public class EleMedRecordAdapter extends BaseQuickAdapter<TextBean, BaseViewHolder> {

    public boolean mark;

    public EleMedRecordAdapter(@Nullable List<TextBean> data) {
        super(R.layout.item_ele_record, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TextBean item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_type, item.getType());
        helper.setText(R.id.tv_date, item.getDate());
        helper.setText(R.id.tv_info, item.getInfo());
        setF(helper,item);
    }

    private void setF(BaseViewHolder helper,TextBean item) {
        LinearLayout ll_aa = helper.getView(R.id.ll_aa);
        LinearLayout ll_bb = helper.getView(R.id.ll_bb);
        View v_line = helper.getView(R.id.v_line);
        CheckBox ckBox = helper.getView(R.id.ckBox);
        helper.addOnClickListener(R.id.ckBox);

        if(item.isFlag()){
            ckBox.setChecked(true);
        }else {
            ckBox.setChecked(false);
        }
//        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
//        int height = dm.heightPixels;
//        int width = dm.widthPixels;
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) v_line.getLayoutParams();
        if (mark) {
            ckBox.setVisibility(View.VISIBLE);
            ll_aa.setBackgroundColor(mContext.getResources().getColor(R.color.item_bg));
            ll_bb.setBackgroundColor(mContext.getResources().getColor(R.color.item_bg));
            v_line.setBackgroundColor(mContext.getResources().getColor(R.color.white));
            params.height = 16;
            v_line.setLayoutParams(params);

        } else {
            ckBox.setVisibility(View.GONE);
        }

    }

//    @Override
//    public void onBindViewHolder(BaseViewHolder helper, int position, List<Object> payloads) {
//        if (payloads.isEmpty()) {
//            onBindViewHolder(helper, position);
//        } else {
//            setF(helper);
//        }
//    }
}
