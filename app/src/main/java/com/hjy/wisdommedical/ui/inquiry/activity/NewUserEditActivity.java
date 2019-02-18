package com.hjy.wisdommedical.ui.inquiry.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.util.ScreenUtils;
import com.hjy.wisdommedical.util.Validator;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/7/11 0011.
 * 公共跳转编辑
 */

public class NewUserEditActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.id_editor_detail_name)
    EditText mEditText;
    private String mString;
    private int mType;

    @Override
    protected int getContentView() {
        return R.layout.activity_new_user_edit;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText(R.string.complete);
        mType = getIntent().getIntExtra("type", Constant.PERSON_NAME);
        if (mType == Constant.PERSON_NAME) {
            String mTextView_name = getIntent().getStringExtra("mTextView_Name");
            if (!TextUtils.isEmpty(mTextView_name)) {
                mEditText.setText(mTextView_name);
                mEditText.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)}); //最大输入长度
                mEditText.setSelection(mTextView_name.length());
            }
            tv_title.setText(getString(R.string.user_name));

        } else if (mType == Constant.CLASS_NUMBER) {
            String mTextView_no = getIntent().getStringExtra("mTextView_No");
            if(!TextUtils.isEmpty(mTextView_no)){
                mEditText.setText(mTextView_no);
                mEditText.setInputType(InputType.TYPE_CLASS_NUMBER);
                mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(18)}); //最大输入长度
                mEditText.setSelection(mTextView_no.length());
            }
            tv_title.setText(getString(R.string.id_card_number));

        } else if (mType == Constant.CLASS_PHONE) {
            tv_title.setText(getString(R.string.add_phone));
            String classPhone = getIntent().getStringExtra("classPhone");
            if(!TextUtils.isEmpty(classPhone)){
                mEditText.setText(classPhone);
                mEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)}); //最大输入长度
                mEditText.setSelection(classPhone.length());
            }
        } else if (mType == Constant.CLASS_ADDRESS) {
            String classAddress = getIntent().getStringExtra("classAddress");

            if(!TextUtils.isEmpty(classAddress)){
                mEditText.setText(classAddress);
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)}); //最大输入长度
                mEditText.setSelection(classAddress.length());
            }
            tv_title.setText(getString(R.string.add_location));

        } else if (mType == Constant.PHONE) {
            String mTextView_phone = getIntent().getStringExtra("mTextView_Phone");
            if(!TextUtils.isEmpty(mTextView_phone)){
                mEditText.setText(mTextView_phone);
                mEditText.setHint(getString(R.string.please_phone_number));
                mEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)}); //最大输入长度
                mEditText.setSelection(mTextView_phone.length());
            }
            tv_title.setText(getString(R.string.add_phone));

        } else if (mType == Constant.NICKNAME) {
            tv_title.setText(getString(R.string.nickname));
            mEditText.setHint(getString(R.string.please_nickname));
            mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
            mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)}); //最大输入长度
        } else if (mType == Constant.ADDRESS_USER) {
            tv_title.setText(getString(R.string.consignee));
            String add_user_name = getIntent().getStringExtra("add_user_name");
            if (add_user_name != null) {
                mEditText.setText(add_user_name);
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(10)}); //最大输入长度
                mEditText.setSelection(add_user_name.length());
            }
        } else if (mType == Constant.ADDRESS_PHONT) {
            tv_title.setText(getString(R.string.add_phone));
            String user_phone = getIntent().getStringExtra("user_phone");
            if (user_phone != null) {
                mEditText.setText(user_phone);
                mEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)}); //最大输入长度
                mEditText.setSelection(user_phone.length());
            }
        } else if (mType == Constant.ADDRESS_REGION) {
            tv_title.setText(getString(R.string.add_location));
            String location = getIntent().getStringExtra("location");
            if (location != null) {
                mEditText.setText(location);
                mEditText.setInputType(InputType.TYPE_CLASS_TEXT);
                mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(30)}); //最大输入长度
                mEditText.setSelection(location.length());
            }
        } else if (mType == Constant.ADDRESS_POSTCODE) {
            tv_title.setText(getString(R.string.add_postcode_loc));
            String user_postcode = getIntent().getStringExtra("user_postcode");
            if (user_postcode != null) {
                mEditText.setText(user_postcode);
                mEditText.setInputType(InputType.TYPE_CLASS_PHONE);
                mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)}); //最大输入长度
                mEditText.setSelection(user_postcode.length());
            }
        }

    }

    @OnClick({R.id.iv_back, R.id.tv_right})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            case R.id.tv_right:
                mString = mEditText.getText().toString().trim();
                if (mType == Constant.PERSON_NAME) {
                    Intent intent = getIntent();
                    intent.putExtra("trim", mString);
                    setResult(Constant.PERSON_NAME, intent);
                    finish();
                } else if (mType == Constant.CLASS_NUMBER) {
                    if (Validator.isIDCard(mString)) {
                        Intent intent = getIntent();
                        intent.putExtra("trim", mString);
                        setResult(Constant.CLASS_NUMBER, intent);
                        finish();
                    } else {
                        T.showShort(getString(R.string.please_enter_id_number));
                    }
                } else if (mType == Constant.PHONE) {
                    if (ScreenUtils.isMobileNO(mString)) {
                        Intent intent = getIntent();
                        intent.putExtra("trim", mString);
                        setResult(Constant.PHONE, intent);
                        finish();
                    } else {
                        T.showShort(getString(R.string.please_enter_phone_number));
                    }
                } else if (mType == Constant.CLASS_PHONE) {
                    if (ScreenUtils.isMobileNO(mString)) {
                        Intent intent = getIntent();
                        intent.putExtra("trim", mString);
                        setResult(Constant.CLASS_PHONE, intent);
                        finish();
                    } else {
                        T.showShort(getString(R.string.please_enter_phone_number));
                    }
                } else if (mType == Constant.CLASS_ADDRESS) {
                    Intent intent = getIntent();
                    intent.putExtra("trim", mString);
                    setResult(Constant.CLASS_ADDRESS, intent);
                    finish();
                } else if (mType == Constant.NICKNAME) {
                    Intent intent = getIntent();
                    intent.putExtra("trim", mString);
                    setResult(Constant.NICKNAME, intent);
                    finish();
                } else if (mType == Constant.ADDRESS_USER) {
                    Intent intent = getIntent();
                    intent.putExtra("trim", mString);
                    setResult(Constant.ADDRESS_USER, intent);
                    finish();
                } else if (mType == Constant.ADDRESS_PHONT) {
                    if (Validator.isMobile(mString)) {
                        Intent intent = getIntent();
                        intent.putExtra("trim", mString);
                        setResult(Constant.ADDRESS_PHONT, intent);
                        finish();
                    } else {
                        T.showShort(getString(R.string.please_enter_phone_number));
                    }
                } else if (mType == Constant.ADDRESS_REGION) {
                    Intent intent = getIntent();
                    intent.putExtra("trim", mString);
                    setResult(Constant.ADDRESS_REGION, intent);
                    finish();
                } else if (mType == Constant.ADDRESS_POSTCODE) {
                    Intent intent = getIntent();
                    intent.putExtra("trim", mString);
                    setResult(Constant.ADDRESS_POSTCODE, intent);
                    finish();
                }
                break;
        }
    }
}
