package com.hjy.wisdommedical.ui.registration;

import android.os.Bundle;

import com.example.handsomelibrary.base.BaseFragment;
import com.hjy.wisdommedical.R;

/**
 * Created by QKun on 2018/6/27.
 * 预约挂号
 */
public class RegistrationFragment extends BaseFragment {

    private static final String REGISTRATION_FRAGMENT = "registration";


    public static RegistrationFragment newInstance(String params) {
        Bundle bundle = new Bundle();
        bundle.putString(REGISTRATION_FRAGMENT, params);
        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_registration;
    }

    @Override
    protected void initData() {

    }

}
