package com.hjy.wisdommedical.ui.personal.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.GetAddressInfoBean;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.net.RetrofitManager;
import com.hjy.wisdommedical.ui.inquiry.activity.NewUserEditActivity;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.smarttop.library.bean.City;
import com.smarttop.library.bean.County;
import com.smarttop.library.bean.Province;
import com.smarttop.library.bean.Street;
import com.smarttop.library.utils.LogUtil;
import com.smarttop.library.widget.AddressSelector;
import com.smarttop.library.widget.BottomDialog;
import com.smarttop.library.widget.OnAddressSelectedListener;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 初夏小溪 on 2018/7/10 0010.
 * 添加配送地址
 */

public class AddDispatchingActivity extends BaseActivity implements OnAddressSelectedListener, AddressSelector.OnDialogCloseListener {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.tv_city)
    TextView mTextView_city;
    @BindView(R.id.tv_add_user_name)
    TextView mTextView_add_user_name;
    @BindView(R.id.tv_add_user_phone)
    TextView tv_add_user_phone;
    @BindView(R.id.tv_add_user_postcode)
    TextView tv_add_user_postcode;
    @BindView(R.id.tv_location)
    TextView tv_location;
    private String mTrim;
    private BottomDialog dialog;
    private String mCity;
    private String mCounty;
    private String mStreets;
    private String mProvinces;
    private int mType;
    //配送地址id
    private int mId;

    @Override
    protected int getContentView() {
        return R.layout.activity_add_dispatching;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.add_dispatching_location);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText(R.string.save);
        mType = getIntent().getIntExtra("type", 0);
        mId = getIntent().getIntExtra("id", 0);
        if (mType == 1) {
            tv_title.setText(R.string.update_dispatching_location);
            tv_right.setVisibility(View.VISIBLE);
            tv_right.setText(R.string.save);
            GetAddressInfo(mId);
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_right, R.id.Ll_city, R.id.Ll_name, R.id.Ll_phone, R.id.Ll_address, R.id.Ll_postcode})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
            //確定
            case R.id.tv_right:
                GetSave();
//                mContext.finish();
                break;
            //所在城市
            case R.id.Ll_city:
                GetCity();
                break;
            case R.id.Ll_name:
                GetUser(Constant.ADDRESS_USER);
                break;
            case R.id.Ll_phone:
                GetUser(Constant.ADDRESS_PHONT);
                break;
            case R.id.Ll_address:
                GetUser(Constant.ADDRESS_REGION);
                break;
            case R.id.Ll_postcode:
                GetUser(Constant.ADDRESS_POSTCODE);
                break;
        }
    }

    private void GetUser(int type) {
        String add_user_name = mTextView_add_user_name.getText().toString();
        String user_phone = tv_add_user_phone.getText().toString().trim();
        String user_postcode = tv_add_user_postcode.getText().toString().trim();
        String location = tv_location.getText().toString();
        if (type == Constant.ADDRESS_USER) {
            Intent intent = new Intent(this, NewUserEditActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("add_user_name",add_user_name);//收货人姓名
            startActivityForResult(intent, 1);
        } else if (type == Constant.ADDRESS_PHONT) {
            Intent intent = new Intent(this, NewUserEditActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("user_phone",user_phone);//手机
            startActivityForResult(intent, 1);
        } else if (type == Constant.ADDRESS_REGION) {
            Intent intent = new Intent(this, NewUserEditActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("location",location);//地址
            startActivityForResult(intent, 1);
        } else if (type == Constant.ADDRESS_POSTCODE) {
            Intent intent = new Intent(this, NewUserEditActivity.class);
            intent.putExtra("type", type);
            intent.putExtra("user_postcode",user_postcode);//邮编
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case Constant.ADDRESS_USER:
                mTrim = data.getExtras().getString("trim");
                mTextView_add_user_name.setText(mTrim);
                break;
            case Constant.ADDRESS_PHONT:
                mTrim = data.getExtras().getString("trim");
                tv_add_user_phone.setText(mTrim);
                break;
            case Constant.ADDRESS_REGION:
                mTrim = data.getExtras().getString("trim");
                tv_location.setText(mTrim);
                break;
            case Constant.ADDRESS_POSTCODE:
                mTrim = data.getExtras().getString("trim");
                tv_add_user_postcode.setText(mTrim);
                break;
        }
    }


    /**
     * 收货地址回显数据
     *
     * @param id
     */
    private void GetAddressInfo(int id) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RetrofitManager.createApi(ApiService.class)
                .addressInfo(id)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<GetAddressInfoBean>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    protected void onSuccess(GetAddressInfoBean rowsBean) {
                        mKProgressHUD.dismiss();
                        if (null != rowsBean) {
                            mTextView_add_user_name.setText(rowsBean.getConsigneeName());//收货人姓名
                            tv_add_user_phone.setText(rowsBean.getMobile() + "");//手机号码
                            tv_add_user_postcode.setText(rowsBean.getPostcode());//邮政编码
                            mProvinces = rowsBean.getProvince();
                            mCity = rowsBean.getCity();
                            mCounty = rowsBean.getDistrict();
                            mTextView_city.setText(mProvinces + mCity + mCounty);
                            tv_location.setText(rowsBean.getStreet());
                        }
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                        T.showShort(errorMsg);
                    }
                });
    }

    /**
     * 网络请求
     */
    private void GetSave() {
        if (mTextView_add_user_name.getText().equals("")) {
            T.showShort(R.string.user_address_null);
            return;
        } else if (tv_add_user_phone.getText().equals("")) {
            T.showShort(getString(R.string.phone_null));
            return;
        } else if ((TextUtils.isEmpty(mProvinces)) || (TextUtils.isEmpty(mCity)) || (TextUtils.isEmpty(mCounty))) {
            T.showShort(getString(R.string.select_your_region));
            return;
        } else if (TextUtils.isEmpty(tv_location.getText().toString())) {
            T.showShort(getString(R.string.detailed_address_null));
            return;
        }
        if (mType != 1) { //代表是新增的
            mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
            ArrayMap<String, Object> param = new ArrayMap<>();
            param.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
            param.put("consigneeName", mTextView_add_user_name.getText().toString().trim());
            param.put("mobile", tv_add_user_phone.getText().toString().trim());
            param.put("country", "中国");
            param.put("province", mProvinces);//省
            param.put("city", mCity);//市区
            param.put("district", mCounty + mStreets); //区 or 县
            param.put("street", tv_location.getText().toString().trim()); //详细地址
            param.put("postcode", tv_add_user_postcode.getText().toString().trim());
            param.put("token", SpfUtils.getSpfSaveStr(Constant.token));
            RetrofitManager.createApi(ApiService.class)
                    .address(param)
                    .compose(Transformer.switchSchedulers())
                    .subscribe(new CommonObserver<Object>() {
                        @Override
                        protected void onSuccess(Object Object) {
                            mKProgressHUD.dismiss();
                            T.showShort(getString(R.string.complete));
                            JumpUtils.exitActivity(mContext);
                        }

                        @Override
                        protected void onError(String errorMsg) {
                            mKProgressHUD.dismiss();
                            T.showShort(errorMsg);
                        }
                    });
        } else { //编辑当前地址  实现更新接口的
            mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
            ArrayMap<String, Object> param = new ArrayMap<>();
            param.put("cusId", SpfUtils.getSpfSaveInt(Constant.userId));
            param.put("id", mId);
            param.put("consigneeName", mTextView_add_user_name.getText().toString().trim());
            param.put("mobile", tv_add_user_phone.getText().toString().trim());
            param.put("country", "中国");
            param.put("province", mProvinces);
            param.put("city", mCity);
            param.put("district", mCounty + mStreets);
            param.put("street", tv_location.getText().toString().trim());
            param.put("postcode", tv_add_user_postcode.getText().toString().trim());
            param.put("token", SpfUtils.getSpfSaveStr(Constant.token));

            RetrofitManager.createApi(ApiService.class)
                    .addressUpdate(param)
                    .compose(Transformer.switchSchedulers())
                    .subscribe(new CommonObserver<Object>() {
                        @Override
                        protected void onSuccess(Object Object) {
                            mKProgressHUD.dismiss();
                            T.showShort(getString(R.string.complete));
                            JumpUtils.exitActivity(mContext);
                        }

                        @Override
                        protected void onError(String errorMsg) {
                            mKProgressHUD.dismiss();
                            T.showShort(errorMsg);
                        }
                    });
        }

    }

    //所在城市 选择
    private void GetCity() {
        if (dialog != null) {
            dialog.show();
        } else {
            dialog = new BottomDialog(this);
            dialog.setOnAddressSelectedListener(this);
            dialog.setDialogDismisListener(this);
            dialog.setTextSize(15);//设置字体的大小
            dialog.setIndicatorBackgroundColor(R.color.dispatching);//设置指示器的颜色
            dialog.setTextSelectedColor(R.color.txtSuperColor);//设置字体获得焦点的颜色
            dialog.setTextUnSelectedColor(R.color.dispatching);//设置字体没有获得焦点的颜色
//            dialog.setDisplaySelectorArea("31",1,"2704",1,"2711",0,"15582",1);//设置已选中的地区
            dialog.show();
        }
    }

    @Override
    public void dialogclose() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onAddressSelected(Province province, City city, County county, Street street) {
        String provinceCode = (province == null ? "" : province.code);
        String cityCode = (city == null ? "" : city.code);
        String countyCode = (county == null ? "" : county.code);
        String streetCode = (street == null ? "" : street.code);
        LogUtil.d("数据", "省份id=" + provinceCode);
        LogUtil.d("数据", "城市id=" + cityCode);
        LogUtil.d("数据", "乡镇id=" + countyCode);
        LogUtil.d("数据", "街道id=" + streetCode);
        mProvinces = province == null ? "" : province.name;
        mCity = city == null ? "" : city.name;
        mCounty = county == null ? "" : county.name;
        mStreets = street == null ? "" : street.name;
//        String context = (province == null ? "" : province.name) + (city == null ? "" : city.name) + (county == null ? "" : county.name) + (street == null ? "" : street.name);
        mTextView_city.setText(mProvinces + mCity + mCounty + mStreets);
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
