package com.hjy.wisdommedical;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.home.HomeFragment;
import com.hjy.wisdommedical.ui.inquiry.fragment.InquiryFragment;
import com.hjy.wisdommedical.ui.login.LoginActivity;
import com.hjy.wisdommedical.ui.personal.fragment.PersonalFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    @BindView(R.id.fl_fragment)
    FrameLayout fl_fragment;

    private List<BaseFragment> mFragments = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initBottomNavigation();

        initFragment();

        setDefaultFragment();
    }

    private void setDefaultFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fl_fragment, mFragments.get(0));
        ft.commit();
    }

    private void initFragment() {
        mFragments.add(HomeFragment.newInstance(""));
//        mFragments.add(RegistrationFragment.newInstance(""));
        mFragments.add(InquiryFragment.newInstance(""));
        mFragments.add(PersonalFragment.newInstance(""));
    }

    private void initBottomNavigation() {
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.svg_home, getString(R.string.home))
                        .setActiveColor(R.color.colorPrimary))
//                .addItem(new BottomNavigationItem(R.drawable.svg_registration, getString(R.string.registration))
//                        .setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.svg_online_inquiry, getString(R.string.online_inquiry))
                        .setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.svg_personal, getString(R.string.personal))
                        .setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .initialise();

        mBottomNavigationBar.setTabSelectedListener(this);
    }


    @Override
    public void onTabSelected(int position) {
        if (mFragments != null) {
            if (position <= mFragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment currentFragment = fm.findFragmentById(R.id.fl_fragment);
                BaseFragment nextFragment = mFragments.get(position);
                if (nextFragment.isAdded()) {
                    ft.hide(currentFragment).show(nextFragment);
                } else {
                    ft.hide(currentFragment).add(R.id.fl_fragment, nextFragment);
                    if (nextFragment.isHidden()) {
                        ft.show(nextFragment);
                    }
                }
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabUnselected(int position) {
        if (mFragments != null) {
            if (position < mFragments.size()) {
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Fragment nextFragment = mFragments.get(position);
                ft.hide(nextFragment);
                ft.commitAllowingStateLoss();
            }
        }
    }

    @Override
    public void onTabReselected(int position) {

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle extras = intent.getExtras();
        if (null != extras) {
            boolean exit = extras.getBoolean("exit", false);
            if (exit) {
                this.finish();
                JumpUtils.jump(mContext, LoginActivity.class, null);
                SpfUtils.remove(Constant.token);
            }
        }
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
