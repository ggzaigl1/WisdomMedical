package com.hjy.wisdommedical.record;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.home.HomeFragment;
import com.hjy.wisdommedical.ui.inquiry.fragment.InquiryFragment;
import com.hjy.wisdommedical.ui.personal.fragment.PersonalFragment;
import com.hjy.wisdommedical.widget.ArcView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 健康评估 activity
 * Created by fangs on 2018/9/17 20:27.
 */
public class HealthAssessActivity extends BaseActivity {

    @BindView(R.id.arcTitle)
    ArcView mArcTitle;
    @BindView(R.id.iv_back)
    AppCompatImageView mIvBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.btn_healthy_diet)
    Button mBtnHealthyDiet;
    @BindView(R.id.btn_exercise_advice)
    Button mBtnExerciseAdvice;
    @BindView(R.id.btn_mental_health)
    Button mBtnMentalHealth;
    @BindView(R.id.btn_abnormal_advice)
    Button mBtnAbnormalAdvice;

    private FragmentManager mFm;
    private FragmentTransaction mFt;
    private HealthyDietFragment mHealthyDietFragment;
    private ExerciseAdviceFragment mExerciseAdviceFragment;
    private MentalHealthFragment mMentalHealthFragment;
    private AbnormalAdviceFragment mAbnormalAdviceFragment;


    @Override
    protected int getContentView() {
        return R.layout.health_assess_act;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mFm = getSupportFragmentManager();
        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentTransaction ft = mFm.beginTransaction();
         mHealthyDietFragment = HealthyDietFragment.newInstance("");
        ft.replace(R.id.fragmentGroup, mHealthyDietFragment);
        ft.commit();
        mBtnHealthyDiet.setBackgroundResource(R.drawable.shape_health_record_select_btn_bg);
        mBtnHealthyDiet.setTextColor(getResources().getColor(R.color.white));
    }

    @OnClick({R.id.btn_healthy_diet, R.id.btn_exercise_advice, R.id.btn_mental_health, R.id.btn_abnormal_advice})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        mFt = mFm.beginTransaction();
        cleanStatue();

        switch (view.getId()) {
            case R.id.btn_healthy_diet:
                if (mHealthyDietFragment == null) {
                    mHealthyDietFragment = HealthyDietFragment.newInstance("");
                    mFt.add(R.id.fragmentGroup, mHealthyDietFragment);
                }
                mFt.show(mHealthyDietFragment);

                mBtnHealthyDiet.setBackgroundResource(R.drawable.shape_health_record_select_btn_bg);
                mBtnHealthyDiet.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.btn_exercise_advice:
                if (mExerciseAdviceFragment == null) {
                    mExerciseAdviceFragment = ExerciseAdviceFragment.newInstance("");
                    mFt.add(R.id.fragmentGroup, mExerciseAdviceFragment);
                }
                mFt.show(mExerciseAdviceFragment);
                mBtnExerciseAdvice.setBackgroundResource(R.drawable.shape_health_record_select_btn_bg);
                mBtnExerciseAdvice.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.btn_mental_health:
                if (mMentalHealthFragment == null) {
                    mMentalHealthFragment = MentalHealthFragment.newInstance("");
                    mFt.add(R.id.fragmentGroup, mMentalHealthFragment);
                }
                mFt.show(mMentalHealthFragment);
                mBtnMentalHealth.setBackgroundResource(R.drawable.shape_health_record_select_btn_bg);
                mBtnMentalHealth.setTextColor(getResources().getColor(R.color.white));
                break;
            case R.id.btn_abnormal_advice:
                if (mAbnormalAdviceFragment == null) {
                    mAbnormalAdviceFragment = AbnormalAdviceFragment.newInstance("");
                    mFt.add(R.id.fragmentGroup, mAbnormalAdviceFragment);
                }
                mFt.show(mAbnormalAdviceFragment);
                mBtnAbnormalAdvice.setBackgroundResource(R.drawable.shape_health_record_select_btn_bg);
                mBtnAbnormalAdvice.setTextColor(getResources().getColor(R.color.white));
                break;
        }
        mFt.commit();
    }

    private void cleanStatue() {
        if (mHealthyDietFragment != null) {
            mFt.hide(mHealthyDietFragment);
        }
        if (mExerciseAdviceFragment != null) {
            mFt.hide(mExerciseAdviceFragment);
        }
        if (mMentalHealthFragment != null) {
            mFt.hide(mMentalHealthFragment);
        }
        if (mAbnormalAdviceFragment != null) {
            mFt.hide(mAbnormalAdviceFragment);
        }

        mBtnHealthyDiet.setBackgroundResource(R.drawable.shape_health_record_btn_bg);
        mBtnHealthyDiet.setTextColor(getResources().getColor(R.color.health_record_btn));

        mBtnExerciseAdvice.setBackgroundResource(R.drawable.shape_health_record_btn_bg);
        mBtnExerciseAdvice.setTextColor(getResources().getColor(R.color.health_record_btn));

        mBtnMentalHealth.setBackgroundResource(R.drawable.shape_health_record_btn_bg);
        mBtnMentalHealth.setTextColor(getResources().getColor(R.color.health_record_btn));

        mBtnAbnormalAdvice.setBackgroundResource(R.drawable.shape_health_record_btn_bg);
        mBtnAbnormalAdvice.setTextColor(getResources().getColor(R.color.health_record_btn));
    }
}
