package com.hjy.wisdommedical.ui.home.activity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.ChooseUserBean;
import com.example.handsomelibrary.utils.JumpUtils;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.record.HealthAssessActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by QKun on 2018/9/3 0003.
 * 健康档案
 */
public class HealthRecordsActivity extends BaseActivity {

    @BindView(R.id.tv_Name)
    TextView tv_Name;
    @BindView(R.id.tv_Age)
    TextView tv_Age;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.Rl_bg)
    RelativeLayout mRelativeLayout_Bg;
    @BindView(R.id.health_record_head)
    AppCompatImageView mHealthRecordHead;
    ChooseUserBean.RowsBean chooseUseManagementBean;

    @Override
    protected int getContentView() {
        return R.layout.activity_health_records;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.health_records);
        tv_title.setTextColor(Color.WHITE);
        mRelativeLayout_Bg.setBackgroundColor(Color.parseColor("#7C96F7"));
        chooseUseManagementBean = (ChooseUserBean.RowsBean) getIntent().getSerializableExtra("chooseUseManagementBean");
        int gender = chooseUseManagementBean.getGender();
        switch (gender){
            case 0:
                mHealthRecordHead.setImageResource(R.mipmap.icon_woman);
                break;
            case 1:
                mHealthRecordHead.setImageResource(R.mipmap.icon_man);
                break;

        }
        setHeaderData();
    }

    @SuppressLint({"SetTextI18n", "SimpleDateFormat"})
    private void setHeaderData() {
        tv_Name.setText(chooseUseManagementBean.getMemberName());//患者姓名
        tv_Age.setText((Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date())) - Integer.parseInt(chooseUseManagementBean.getBirthday().substring(0, 4))) + " 岁");
    }

    @OnClick({R.id.iv_back, R.id.ll_basics_record, R.id.ll_health_history, R.id.ll_physical_data,
            R.id.ll_mentality_health, R.id.ll_health_assessment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.ll_basics_record: //基础档案
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("mChooseUseManagementBean", chooseUseManagementBean);
                JumpUtils.jump(mContext, BasicsRecordActivity.class, bundle1);
                break;
            case R.id.ll_health_history: //健康史
                Bundle bundle2 = new Bundle();
                bundle2.putInt("Id", chooseUseManagementBean.getId());
                JumpUtils.jump(mContext, HealthHistoryActivity.class, bundle2);
                break;
            case R.id.ll_physical_data: //体检数据
                Bundle bundle3 = new Bundle();
                bundle3.putInt("Id", chooseUseManagementBean.getId());
                JumpUtils.jump(mContext, PhyExamDataActivity.class, bundle3);
                break;
            case R.id.ll_mentality_health: //心理健康
                Bundle bundle4 = new Bundle();
                bundle4.putInt("Id", chooseUseManagementBean.getId());
                JumpUtils.jump(mContext, MentalHealthActivity.class, bundle4);
                break;
            case R.id.ll_health_assessment://健康评估
                JumpUtils.jump(mContext, HealthAssessActivity.class, null);
                break;
        }
    }
}
