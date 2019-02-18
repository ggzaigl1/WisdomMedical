package com.hjy.wisdommedical.ui.shopping.person.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.JumpUtils;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.shopping.person.fragment.PendDeliveryFragment;
import com.hjy.wisdommedical.ui.shopping.person.fragment.ToBeEvaluateFragment;
import com.hjy.wisdommedical.ui.shopping.person.fragment.WaitCollectFragment;
import com.hjy.wisdommedical.ui.shopping.person.fragment.WholeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 我的订单-商城
 * Created by Stefan on 2018/11/1 15:10.
 */

public class DrugOrderActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.tv_title)
    TextView tv_title;

    private List<Fragment> mFragments;

    @Override
    protected int getContentView() {
        return R.layout.activity_drug_order;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText("我的订单");
        mFragments = new ArrayList<>();
        mFragments.add(new WholeFragment());
        mFragments.add(new PendDeliveryFragment());
        mFragments.add(new WaitCollectFragment());
        mFragments.add(new ToBeEvaluateFragment());

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments == null ? 0 : mFragments.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return getResources().getStringArray(R.array.my_order)[position];
            }
        });
        mViewPager.setOffscreenPageLimit(-1);
        mViewPager.addOnPageChangeListener(this);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (positionOffset == 0 || positionOffsetPixels == 0) {
            mViewPager.setCurrentItem(position);
        }
    }

    @Override
    public void onPageSelected(int position) {
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
