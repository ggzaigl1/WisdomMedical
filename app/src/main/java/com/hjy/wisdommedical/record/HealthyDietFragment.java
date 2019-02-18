package com.hjy.wisdommedical.record;

import android.os.Bundle;

import com.example.handsomelibrary.base.BaseFragment;
import com.hjy.wisdommedical.R;

/**
 * Created by QKun on 2018/9/18.
 */
public class HealthyDietFragment extends BaseFragment {
    private static final String HEALTHY_DIET_FRAGMENT = "HealthyDiet";

    public static HealthyDietFragment newInstance(String params) {
        Bundle bundle = new Bundle();
        bundle.putString(HEALTHY_DIET_FRAGMENT, params);
        HealthyDietFragment fragment = new HealthyDietFragment();
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
