package com.hjy.wisdommedical.ui.inquiry.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.handsomelibrary.model.ListConsultBean;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.util.TimeUtils;

import java.util.List;

/**
 * Created by Stefan on 2018/7/3.
 * 我的咨詢 语音 视频
 */
public class VideoAdviceAdapter extends BaseQuickAdapter<ListConsultBean, BaseViewHolder> {

    public VideoAdviceAdapter(@Nullable List<ListConsultBean> data) {
        super(R.layout.item_image_television_list, data);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder helper, ListConsultBean item) {

        TextView status = helper.getView(R.id.tv_status);//订单状态
        TextView tv_delete = helper.getView(R.id.tv_delete);//删除或取消
        TextView evaluate = helper.getView(R.id.tv_evaluate);//进入诊室或者去评价
        helper.setText(R.id.tv_Name, item.getVisitMemberName());
        helper.setText(R.id.tv_DocName, item.getAppDocEntityVO().getDocName());
        helper.setText(R.id.tv_DisName, item.getConsultContent());
        String reserveTime = item.getReserveTime();//时间段标记1，2.....
        String date = item.getReserveDate();//年月日
        String time_d = Constant.time[Integer.parseInt(reserveTime)];//时间段
        TextView tv_Date = helper.getView(R.id.tv_Date);
        tv_Date.setText(date + " " + time_d);
        helper.setText(R.id.tv_Total, item.getServicePrice());
        helper.addOnClickListener(R.id.tv_delete);
        String startTime_d = time_d.substring(0, 5);
        String endTime_d = time_d.substring(6, 11);
        long l = System.currentTimeMillis();//当前时间时间戳
        String currentTime = TimeUtils.Long2DataString(l, "yyyy-MM-dd HH:mm");

        long t = TimeUtils.timeString2long(date + " " + startTime_d, "yyyy-MM-dd HH:mm");//年月日时间戳头
        long w = TimeUtils.timeString2long(date + " " + endTime_d, "yyyy-MM-dd HH:mm");//年月日时间戳头
        long c = TimeUtils.timeString2long(currentTime, "yyyy-MM-dd HH:mm");//当前时间时间戳

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
            evaluate.setVisibility(View.GONE);
        } else if (item.getOrderStatus() == 3) {
            status.setText(R.string.yi_pay);
            tv_delete.setVisibility(View.GONE);
            evaluate.setVisibility(View.GONE);
        } else if (item.getOrderStatus() == 4) {
            status.setText(R.string.yi_completed);
            tv_delete.setText(R.string.delete_order);
            evaluate.setVisibility(View.GONE);
        } else if (item.getOrderStatus() == 5) {
            status.setText(R.string.have_reply);
            evaluate.setText(R.string.check_the_record);
            tv_delete.setVisibility(View.GONE);
            evaluate.setVisibility(View.GONE);
        } else if (item.getOrderStatus() == 6) {
            status.setText(R.string.no_clinical_visit);
            tv_delete.setText(R.string.cancel_order);
            evaluate.setText(R.string.enter_the_office);
            if (c < t) {//当前时间小于头 ->时间未到 不可以约
                evaluate.setBackground(mContext.getResources().getDrawable(R.drawable.shape_evaluation_bg_no));
                evaluate.setFocusable(false);
            } else if (c > w) {//当前时间大于尾 ->时间已过不可预约
                evaluate.setBackground(mContext.getResources().getDrawable(R.drawable.shape_evaluation_bg_no));
                evaluate.setFocusable(false);
            } else {
                evaluate.setBackground(mContext.getResources().getDrawable(R.drawable.shape_evaluation_bg));
                evaluate.setFocusable(true);
                helper.addOnClickListener(R.id.tv_evaluate);
            }
        } else if (item.getOrderStatus() == 7) {
            status.setText(R.string.no_clinical_visit);
            tv_delete.setText(R.string.cancel_order);
            evaluate.setText(R.string.enter_the_office);
            evaluate.setVisibility(View.VISIBLE);
            if (c < t) {//当前时间小于头 ->时间未到 不可以约
                evaluate.setBackground(mContext.getResources().getDrawable(R.drawable.shape_evaluation_bg_no));
                evaluate.setFocusable(false);
            } else if (c > w) {//当前时间大于尾 ->时间已过不可预约
                evaluate.setBackground(mContext.getResources().getDrawable(R.drawable.shape_evaluation_bg_no));
                evaluate.setFocusable(false);
            } else {
                evaluate.setBackground(mContext.getResources().getDrawable(R.drawable.shape_evaluation_bg));
                evaluate.setFocusable(true);
                helper.addOnClickListener(R.id.tv_evaluate);
            }
        } else if (item.getOrderStatus() == 8) {
            status.setText(R.string.yes_clinical_visit);
            tv_delete.setText(R.string.delete_order);
            evaluate.setText(R.string.to_evaluate);
            evaluate.setBackground(mContext.getResources().getDrawable(R.drawable.shape_evaluation_bg));
            evaluate.setFocusable(true);
            helper.addOnClickListener(R.id.tv_evaluate);
        }
    }
}
