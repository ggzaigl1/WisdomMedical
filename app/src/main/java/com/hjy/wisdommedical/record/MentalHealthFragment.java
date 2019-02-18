package com.hjy.wisdommedical.record;

import android.os.Bundle;

import com.example.handsomelibrary.base.BaseFragment;
import com.hjy.wisdommedical.R;

/**
 * Created by QKun on 2018/9/18.
 */
public class MentalHealthFragment extends BaseFragment {
    private static final String MENTAL_HEALTH_FRAGMENT = "MentalHealth";

    public static MentalHealthFragment newInstance(String params) {
        Bundle bundle = new Bundle();
        bundle.putString(MENTAL_HEALTH_FRAGMENT, params);
        MentalHealthFragment fragment = new MentalHealthFragment();
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
