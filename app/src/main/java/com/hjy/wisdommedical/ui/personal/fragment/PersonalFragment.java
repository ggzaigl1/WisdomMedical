package com.hjy.wisdommedical.ui.personal.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseFragment;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.UserInfo;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.GlideRoundTransform;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.ui.inquiry.activity.ChooseUseManagementActivity;
import com.hjy.wisdommedical.ui.inquiry.activity.MyAdviceActivity;
import com.hjy.wisdommedical.ui.personal.activity.DispatchingActivity;
import com.hjy.wisdommedical.ui.personal.activity.MyDoctorActivity;
import com.hjy.wisdommedical.ui.personal.activity.PersonInfoActivity;
import com.hjy.wisdommedical.ui.personal.activity.SetUpActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 个人中心 Fragment
 * Created by Stefan on 2018/7/6 9:19.
 */
public class PersonalFragment extends BaseFragment {

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.iv_header)
    ImageView iv_header;

    private static final String PERSONAL_FRAGMENT = "personal";


    public static PersonalFragment newInstance(String params) {
        Bundle bundle = new Bundle();
        bundle.putString(PERSONAL_FRAGMENT, params);
        PersonalFragment fragment = new PersonalFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_personal;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        getUserInfo();
    }

    @OnClick({R.id.MyAdvice, R.id.tv_perInfo, R.id.tv_myDoctor, R.id.tv_setUp, R.id.tv_dispatching, R.id.drug_orders, R.id.tv_choose,R.id.tv_package,R.id.tv_show
    ,R.id.tv_social})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.MyAdvice:
                JumpUtils.jump(mContext, MyAdviceActivity.class, null);
                break;
            case R.id.tv_perInfo:
                JumpUtils.jump(mContext, PersonInfoActivity.class, null);
                break;
            case R.id.tv_myDoctor:
                JumpUtils.jump(mContext, MyDoctorActivity.class, null);
                break;
            case R.id.tv_setUp:
                JumpUtils.jump(mContext, SetUpActivity.class, null);
                break;
            /*配送地址*/
            case R.id.tv_dispatching:
                JumpUtils.jump(mContext, DispatchingActivity.class, null);
                break;
            case R.id.drug_orders: //药品订单
                Toast.makeText(mContext, "功能开发中..", Toast.LENGTH_SHORT).show();
//                JumpUtils.jump(mContext, DrugOrdersActivity.class, null);
                break;
            case R.id.tv_package: //我的套餐
                Toast.makeText(mContext, "功能开发中..", Toast.LENGTH_SHORT).show();
//                JumpUtils.jump(mContext, DrugOrdersActivity.class, null);
                break;
            case R.id.tv_show: //预约展示
                Toast.makeText(mContext, "功能开发中..", Toast.LENGTH_SHORT).show();
//                JumpUtils.jump(mContext, DrugOrdersActivity.class, null);
                break;
            case R.id.tv_social: //健康社交
                Toast.makeText(mContext, "功能开发中..", Toast.LENGTH_SHORT).show();
//                JumpUtils.jump(mContext, DrugOrdersActivity.class, null);
                break;
            case R.id.tv_choose: //就诊人管理
                JumpUtils.jump(mContext, ChooseUseManagementActivity.class, null);
                break;
        }
    }


    /**
     * 查看个人资料
     */
    private void getUserInfo() {
        RxHttpUtils.createApi(ApiService.class)
                .getUserInfo(SpfUtils.getSpfSaveInt(Constant.userId))
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<UserInfo>() {
                    @Override
                    protected void onSuccess(UserInfo userInfo) {
                        if (userInfo != null) {
                            if (!TextUtils.isEmpty(userInfo.getNickname())) {
                                tv_name.setText(userInfo.getNickname());
                            }
                            if (!TextUtils.isEmpty(userInfo.getPhotoUrl())) {
                                Glide.with(mContext)
                                        .load(ApiService.BASE_PIC_URL + userInfo.getPhotoUrl())
                                        .placeholder(R.mipmap.icon_doctor)
                                        .error(R.mipmap.icon_doctor)
                                        .transform(new CenterCrop(mContext), new GlideRoundTransform(mContext, 50))
                                        .crossFade(500) //标准的淡入淡出动画
                                        .into(iv_header);
                            } else {
                                Glide.with(mContext)
                                        .load(R.mipmap.icon_doctor)
                                        .transform(new CenterCrop(mContext), new GlideRoundTransform(mContext, 50))
                                        .crossFade(500) //标准的淡入淡出动画
                                        .into(iv_header);
                            }
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        T.showLong(errorMsg);
                    }
                });
    }
}
