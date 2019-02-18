package com.hjy.wisdommedical.ui.inquiry.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.ListConsultBean;
import com.hjy.wisdommedical.R;

import java.util.List;

/**
 * Created by Stefan on 2018/7/3.
 * 图文咨询 adapter
 */
public class ImageTextListAdapter extends BaseQuickAdapter<ListConsultBean, BaseViewHolder> {

    public ImageTextListAdapter(@Nullable List<ListConsultBean> data) {
        super(R.layout.item_image_text_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListConsultBean item) {

        TextView status = helper.getView(R.id.tv_status);
        TextView tv_delete = helper.getView(R.id.tv_delete);
        TextView evaluate = helper.getView(R.id.tv_evaluate);
        helper.setText(R.id.tv_Name, item.getVisitMemberName());
        helper.setText(R.id.tv_DocName, item.getAppDocEntityVO().getDocName());
        helper.setText(R.id.tv_DisName, item.getConsultContent());
        helper.setText(R.id.tv_Total, item.getServicePrice());
        helper.addOnClickListener(R.id.tv_evaluate);
        helper.addOnClickListener(R.id.tv_delete);
        helper.addOnClickListener(R.id.Ll_Chat);

        if (null != item.getIsEvaluation()) {
            if (item.getIsEvaluation() == 1) {
                evaluate.setVisibility(View.GONE);
            } else {
                evaluate.setVisibility(View.VISIBLE);
            }
        }

        if (item.getOrderStatus() == 0) {
            status.setText(R.string.yi_delete);
            tv_delete.setVisibility(View.GONE);
            evaluate.setVisibility(View.GONE);
        } else if (item.getOrderStatus() == 1) {
            status.setText(R.string.yi_cancel);
            tv_delete.setVisibility(View.GONE);
            evaluate.setVisibility(View.GONE);
        } else if (item.getOrderStatus() == 2) {
            status.setText(R.string.not_pay);
            tv_delete.setText(R.string.cancel_order);
            evaluate.setVisibility(View.GONE);
        } else if (item.getOrderStatus() == 3) {
            status.setText(R.string.yi_pay);
            tv_delete.setVisibility(View.GONE);
            evaluate.setVisibility(View.GONE);
        } else if (item.getOrderStatus() == 4) {
            status.setText(R.string.yi_completed);
            evaluate.setText(R.string.to_evaluate);
            tv_delete.setText(R.string.delete_order);
        } else if (item.getOrderStatus() == 5) {
            status.setText(R.string.have_reply);
            evaluate.setText(R.string.check_the_record);
        } else if (item.getOrderStatus() == 6) {
            status.setText(R.string.no_clinical_visit);
            tv_delete.setText(R.string.cancel_order);
            evaluate.setVisibility(View.GONE);
        }
    }
}


