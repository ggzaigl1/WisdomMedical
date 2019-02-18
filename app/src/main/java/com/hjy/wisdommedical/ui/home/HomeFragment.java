package com.hjy.wisdommedical.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.BannerBean;
import com.example.handsomelibrary.model.BaseBean;
import com.example.handsomelibrary.model.HomeItemBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.adapter.ListItemDecoration;
import com.hjy.wisdommedical.ui.home.activity.HealthInformationActivity;
import com.hjy.wisdommedical.ui.home.activity.HomeSearchActivity;
import com.hjy.wisdommedical.ui.home.adapter.HotDoctorAdapter;
import com.hjy.wisdommedical.ui.inquiry.activity.ChooseUseManagementActivity;
import com.hjy.wisdommedical.ui.shopping.ShoppingMainActivity;
import com.hjy.wisdommedical.widget.NetworkImageHolderView;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by QKun on 2018/6/27.
 * 首页
 */
public class HomeFragment extends BaseFragment {

    private static final String HOME_FRAGMENT = "home";

    @BindView(R.id.homeRv)
    RecyclerView homeRv;
    HotDoctorAdapter contentAdapter;

    View headView;
    EditText edit_search;
    ConvenientBanner bannerView;

    public static HomeFragment newInstance(String params) {
        Bundle bundle = new Bundle();
        bundle.putString(HOME_FRAGMENT, params);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        setBanner();
        initRvAdapter();
        hotDoc();

        //搜索
        edit_search.setOnClickListener(v -> JumpUtils.jump(mContext, HomeSearchActivity.class, null));
    }

    private void initRvAdapter() {
        contentAdapter = new HotDoctorAdapter(getContext(), new ArrayList<>());
        contentAdapter.addHeaderView(headView);
        View infoView = LayoutInflater.from(getActivity()).inflate(R.layout.item_homefm_info, null);
        infoView.setOnClickListener(this);
        contentAdapter.addHeaderView(infoView);

        homeRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeRv.addItemDecoration(new ListItemDecoration.Builder().setDraw(true).create(mContext.getApplicationContext()));
        homeRv.setAdapter(contentAdapter);
    }

    @SuppressLint({"ResourceAsColor", "CheckResult"})
    private void setBanner() {
        headView = LayoutInflater.from(getActivity()).inflate(R.layout.item_homefm_banner, null);
        bannerView = headView.findViewById(R.id.convenientBanner);
        edit_search = headView.findViewById(R.id.edit_search);
        headView.findViewById(R.id.imgMsg).setOnClickListener(this);
        headView.findViewById(R.id.tvFunBtn1).setOnClickListener(this);
        headView.findViewById(R.id.tvFunBtn2).setOnClickListener(this);
        headView.findViewById(R.id.tvFunBtn3).setOnClickListener(this);
        headView.findViewById(R.id.tvFunBtn4).setOnClickListener(this);

        List<String> bannerBeans = new ArrayList<>();
        RxHttpUtils.createApi(ApiService.class)
                .getBanner(1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(baseBeanO -> {
                    for (BannerBean bannerBean : baseBeanO.getRows()) {
                        bannerBeans.add(ApiService.BASE_PIC_URL + bannerBean.getImgUrl());
                    }
                    bannerView.setPages(() -> new NetworkImageHolderView(), bannerBeans)
                            .startTurning(2000)
                            .setPointViewVisible(true)
                            //设置指示器的方向
                            .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_RIGHT)
                            //设置手动影响（设置了该项无法手动切换）
                            .setManualPageable(true);
                }, throwable -> T.showShort("网络连接异常"));
    }

    private void hotDoc() {
        mKProgressHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Integer> params = new ArrayMap<>();
        params.put("pageNumber", 1);
        params.put("pageSize", 3);
        RxHttpUtils.createApi(ApiService.class)
                .getHotDoc(params)
                .throttleFirst(2, TimeUnit.SECONDS)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<HomeItemBean>() {
                    @Override
                    protected void onSuccess(HomeItemBean homeItemBean) {
                        contentAdapter.addData(homeItemBean.getRows());
                        contentAdapter.notifyDataSetChanged();
                        mKProgressHUD.dismiss();
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        T.showLong(errorMsg);
                        mKProgressHUD.dismiss();
                    }
                });
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.imgMsg:
                Toast.makeText(mContext, "功能开发中..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvFunBtn1:
                Bundle bundle = new Bundle();
                bundle.putInt("type", 1);
                JumpUtils.jump(mContext, ChooseUseManagementActivity.class, bundle);
                break;
            case R.id.tvFunBtn2:
                Toast.makeText(mContext, "功能开发中..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvFunBtn3:
                Toast.makeText(mContext, "功能开发中..", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvFunBtn4:
                //Toast.makeText(mContext, "功能开发中..", Toast.LENGTH_SHORT).show();
                JumpUtils.jump(mContext, ShoppingMainActivity.class, null);
                break;
            case R.id.Cl_home_health://健康资讯
                JumpUtils.jump(mContext, HealthInformationActivity.class, null);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != bannerView) {
            bannerView.startTurning(2000);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (null != bannerView) {
            bannerView.stopTurning();
        }
    }
}
