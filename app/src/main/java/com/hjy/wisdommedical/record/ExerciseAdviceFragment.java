package com.hjy.wisdommedical.record;

import android.os.Bundle;

import com.example.handsomelibrary.base.BaseFragment;
import com.hjy.wisdommedical.R;

/**
 * Created by QKun on 2018/9/18.
 */
public class ExerciseAdviceFragment extends BaseFragment {
    private static final String EXERCISE_ADVICE_FRAGMENT = "ExerciseAdvice";

    public static ExerciseAdviceFragment newInstance(String params) {
        Bundle bundle = new Bundle();
        bundle.putString(EXERCISE_ADVICE_FRAGMENT, params);
        ExerciseAdviceFragment fragment = new ExerciseAdviceFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_exercise_advice;
    }

    @Override
    protected void initData() {

    }
}
