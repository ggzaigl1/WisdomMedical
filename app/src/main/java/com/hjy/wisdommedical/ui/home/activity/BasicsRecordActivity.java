package com.hjy.wisdommedical.ui.home.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.ChooseUseManagementBean;
import com.example.handsomelibrary.model.ChooseUserBean;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by QKun on 2018/9/10.
 * 基础档案
 */
public class BasicsRecordActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTextView;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_sex)
    TextView mTvSex;
    @BindView(R.id.tv_IdNum)
    TextView mTvIdNum;
    @BindView(R.id.tv_date)
    TextView mTvDate;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.tv_site)
    TextView mTvSite;
    @BindView(R.id.tv_IsMarriage)
    TextView mTvIsMarriage;
    ChooseUserBean.RowsBean mChooseUseManagementBean;

    @Override
    protected int getContentView() {
        return R.layout.activity_basic_record;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData(Bundle savedInstanceState) {
        mTextView.setText(R.string.basic_file);
        mChooseUseManagementBean = (ChooseUserBean.RowsBean) getIntent().getSerializableExtra("mChooseUseManagementBean");
        if (null != mChooseUseManagementBean) {
            mTvName.setText(mChooseUseManagementBean.getMemberName());
            switch (mChooseUseManagementBean.getGender()) {
                case 0:
                    mTvSex.setText(getString(R.string.woman));
                    break;
                case 1:
                    mTvSex.setText(getString(R.string.man));
                    break;
            }
            mTvIdNum.setText(mChooseUseManagementBean.getIdNumber());
            mTvDate.setText(mChooseUseManagementBean.getBirthday());
            mTvLocation.setText(mChooseUseManagementBean.getProvince() + mChooseUseManagementBean.getCity() + mChooseUseManagementBean.getDistrict());
            mTvSite.setText(mChooseUseManagementBean.getStreet());
            if(mChooseUseManagementBean.getIsMarried()!=null&&mChooseUseManagementBean.getIsMarried().equals("0")){
                mTvIsMarriage.setText("已婚");
            }else if(mChooseUseManagementBean.getIsMarried()!=null&&mChooseUseManagementBean.getIsMarried().equals("1")){
                mTvIsMarriage.setText("未婚");
            }

        }
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
        }
    }
}
