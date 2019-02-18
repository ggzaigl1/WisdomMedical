package com.hjy.wisdommedical.ui.shopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.base.BaseFragment;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.ui.shopping.classify.ShoppingClassifyFragment;
import com.hjy.wisdommedical.ui.shopping.home.ShoppingHomeFragment;
import com.hjy.wisdommedical.ui.shopping.person.ShoppingMyFragment;
import com.hjy.wisdommedical.ui.shopping.trolley.ShoppingTrolleyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author QKun
 * @date 2018/10/11
 */
public class ShoppingMainActivity extends BaseActivity {

    @BindView(R.id.shopping_bottom_navigation)
    BottomNavigationBar mShoppingBottomNavigation;

    private List<BaseFragment> mFragments = new ArrayList<>();

    @Override
    protected int getContentView() {
        return R.layout.activity_shopping;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        initFragment();
        defaultFragment();
        initBottomNavigation();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //商品详情 点击购物车 跳转到 商城 购物车
//        boolean mBooleanExtra = intent.getBooleanExtra("ShoppingMainActivity", false);
//        if (mBooleanExtra) {
//            mShoppingBottomNavigation.selectTab(2);
//        }
    }

    private void initFragment() {
        mFragments.add(ShoppingHomeFragment.newInstance(""));
        mFragments.add(ShoppingClassifyFragment.newInstance(""));
        mFragments.add(ShoppingTrolleyFragment.newInstance(""));
        mFragments.add(ShoppingMyFragment.newInstance(""));
    }

    private void defaultFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.replace(R.id.fl_shopping_content, mFragments.get(0));
        ft.commit();
    }

    private void initBottomNavigation() {
        mShoppingBottomNavigation.setMode(BottomNavigationBar.MODE_FIXED);
        mShoppingBottomNavigation.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mShoppingBottomNavigation.setActiveColor(R.color.colorPrimary)
                .addItem(new BottomNavigationItem(R.drawable.svg_home, R.string.shopping_home))
                .addItem(new BottomNavigationItem(R.drawable.svg_shopping_classify, R.string.shopping_classify))
                .addItem(new BottomNavigationItem(R.drawable.svg_shopping_trolley, R.string.shopping_trolley))
                .addItem(new BottomNavigationItem(R.drawable.svg_shopping_person, R.string.shopping_person))
                .setFirstSelectedPosition(0)
                .initialise();

        mShoppingBottomNavigation.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                if (mFragments != null) {
                    if (position <= mFragments.size()) {
                        FragmentManager fm = getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        Fragment currentFragment = fm.findFragmentById(R.id.fl_shopping_content);
                        BaseFragment nextFragment = mFragments.get(position);
                        if (nextFragment.isAdded()) {
                            ft.hide(currentFragment).show(nextFragment);
                        } else {
                            ft.hide(currentFragment).add(R.id.fl_shopping_content, nextFragment);
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
        });
    }

    //仅适用于 第一个Fragment 点击分类标题使用
    public void jumpFragmentOne() {
        mShoppingBottomNavigation.selectTab(1);
    }
}
