package com.hjy.wisdommedical.ui.shopping.classify.adapter;

import android.support.annotation.Nullable;
import android.widget.RadioButton;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.ListClassBean;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by Stefan on 2018/10/25.
 */
public class ClassifyLeftAdapter extends BaseQuickAdapter<ListClassBean.ListPtClassBean, BaseViewHolder> {
    int checkPosition = 0;

    public int getCheckPosition() {
        return checkPosition;
    }

    public void setCheckPosition(int checkPosition) {
        this.checkPosition = checkPosition;
    }

    public ClassifyLeftAdapter(@Nullable List<ListClassBean.ListPtClassBean> data) {
        super(R.layout.item_classify_left, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListClassBean.ListPtClassBean item) {
        RadioButton rb_left = helper.getView(R.id.rb_left);
        helper.addOnClickListener(R.id.rb_left);
        rb_left.setText(item.getCfName());
        if (helper.getLayoutPosition() == getCheckPosition()) {
            rb_left.setChecked(true);
            rb_left.setTextColor(mContext.getResources().getColor(R.color.mainColor));
        } else {
            rb_left.setChecked(false);
            rb_left.setTextColor(mContext.getResources().getColor(R.color.txtSuperColor));
        }


//        if (position == checkPosition) {
//            holder.department_left.setChecked(true);
//            holder.department_left.setTextColor(Color.BLACK);
//        } else {
//            holder.department_left.setChecked(false);
//            holder.department_left.setTextColor(getResources().getColor(R.color.app_text_default_color));
//        }
    }
}
