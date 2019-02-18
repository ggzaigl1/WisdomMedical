package com.hjy.wisdommedical.record;

import android.os.Bundle;

import com.example.handsomelibrary.base.BaseFragment;
import com.hjy.wisdommedical.R;

/**
 * Created by QKun on 2018/9/18.
 */
public class AbnormalAdviceFragment extends BaseFragment {
    private static final String ABNORMAL_ADVICE_FRAGMENT = "AbnormalAdvice";

    public static AbnormalAdviceFragment newInstance(String params) {
        Bundle bundle = new Bundle();
        bundle.putString(ABNORMAL_ADVICE_FRAGMENT, params);
        AbnormalAdviceFragment fragment = new AbnormalAdviceFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_health_diet;
    }

    @Override
    protected void initData() {

    }
}
