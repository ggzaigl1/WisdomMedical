package com.hjy.wisdommedical.ui.shopping.person;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.hjy.wisdommedical.ui.shopping.person.activity.DrugOrderActivity;
import com.hjy.wisdommedical.ui.shopping.person.activity.MyCollectionActivity;
import com.hjy.wisdommedical.ui.shopping.person.activity.WaitingPaymentActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by QKun on 2018/10/11.
 * 健康商城-我的
 */
public class ShoppingMyFragment extends BaseFragment {

    public static final String TAG = "ShoppingMy";

    @BindView(R.id.tv_payment)
    TextView tv_payment;
    @BindView(R.id.tv_drugOrder)
    TextView tv_drugOrder;
    @BindView(R.id.tv_myCollection)
    TextView tv_myCollection;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.iv_imgHeader)
    ImageView iv_imgHeader;

    public static ShoppingMyFragment newInstance(String parames) {
        Bundle bundle = new Bundle();
        bundle.putString(TAG, parames);
        ShoppingMyFragment fragment = new ShoppingMyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_shopping_person;
    }

    @Override
    protected void initData() {
        getUserInfo();
    }

    @OnClick({R.id.tv_payment, R.id.tv_drugOrder, R.id.tv_myCollection})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_payment:
                JumpUtils.jump(mContext, WaitingPaymentActivity.class, null);
                break;
            case R.id.tv_drugOrder:
                JumpUtils.jump(mContext, DrugOrderActivity.class, null);
                break;
            case R.id.tv_myCollection:
                JumpUtils.jump(mContext, MyCollectionActivity.class, null);
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
                                        .into(iv_imgHeader);
                            } else {
                                Glide.with(mContext)
                                        .load(R.mipmap.icon_doctor)
                                        .transform(new CenterCrop(mContext), new GlideRoundTransform(mContext, 50))
                                        .crossFade(500) //标准的淡入淡出动画
                                        .into(iv_imgHeader);
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
