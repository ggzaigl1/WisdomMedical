package com.hjy.wisdommedical.ui.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.ArrayMap;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.RegisterBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.util.Validator;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 初夏小溪 on 2018/7/6 0006.
 * 注册
 */

public class RegisterUserActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;

    @BindView(R.id.et_name)
    EditText et_name;
    @BindView(R.id.et_email)
    EditText et_email;

    @BindView(R.id.et_new_psd)
    EditText et_new_psd;
    @BindView(R.id.iv_new_pad)
    ImageView iv_new_pad;

    @BindView(R.id.et_new_sure_psd)
    EditText et_new_sure_psd;
    @BindView(R.id.iv_new_sure_psd)
    ImageView iv_new_sure_psd;
    private Boolean showPassword = true;

    @Override
    protected int getContentView() {
        return R.layout.activity_register_user;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.register);
    }

    @OnClick({R.id.iv_back, R.id.iv_new_pad, R.id.iv_new_sure_psd, R.id.btnRegister})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.iv_new_pad:
                if (showPassword) {// 显示密码
                    iv_new_pad.setImageDrawable(getResources().getDrawable(R.mipmap.login_password_on));
                    et_new_psd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_new_psd.setSelection(et_new_psd.getText().toString().length());
                    showPassword = !showPassword;
                } else {// 隐藏密码
                    iv_new_pad.setImageDrawable(getResources().getDrawable(R.mipmap.login_password_off));
                    et_new_psd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_new_psd.setSelection(et_new_psd.getText().toString().length());
                    showPassword = !showPassword;
                }
                break;
            case R.id.iv_new_sure_psd:
                if (showPassword) {// 显示密码
                    iv_new_sure_psd.setImageDrawable(getResources().getDrawable(R.mipmap.login_password_on));
                    et_new_sure_psd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_new_sure_psd.setSelection(et_new_sure_psd.getText().toString().length());
                    showPassword = !showPassword;
                } else {// 隐藏密码
                    iv_new_sure_psd.setImageDrawable(getResources().getDrawable(R.mipmap.login_password_off));
                    et_new_sure_psd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_new_sure_psd.setSelection(et_new_sure_psd.getText().toString().length());
                    showPassword = !showPassword;
                }
                break;
            case R.id.btnRegister:
                String name = et_name.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String pass = et_new_psd.getText().toString().trim();
                String surePass = et_new_sure_psd.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    T.showLong(getString(R.string.username_empty));
                    return;
                } else if (TextUtils.isEmpty(email)) {
                    T.showLong(getString(R.string.email_empty));
                    return;
                } else if (!Validator.isEmail(email)) {
                    T.showLong(getString(R.string.username_not));
                    return;
                } else if (TextUtils.isEmpty(pass) || TextUtils.isEmpty(surePass)) {
                    T.showLong(getString(R.string.pad_or_confirmationPas_empty));
                    return;
                } else if (!pass.equals(surePass)) {
                    T.showLong(getString(R.string.passwords_or_confirmation_not_match));
                    return;
                } else if (et_new_psd.getText().length() < 6) {
                    T.showShort(getString(R.string.new_psd_empty_len));
                    return;
                } else if (et_new_sure_psd.getText().toString().length() < 6) {
                    T.showShort(getString(R.string.sure_psd_empty_len));
                    return;
                }
                register(name, pass, email);
                break;
        }
    }

    /**
     * 注册 regSource 注册渠道
     * regSource 参数说明 患者端：1  医生端：2
     *
     * @param name
     * @param pass
     * @param email
     */
    @SuppressLint("CheckResult")
    private void register(String name, String pass, String email) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, String> parame = new ArrayMap<>();
        parame.put("username", name);
        parame.put("password", pass);
        parame.put("email", email);
        parame.put("regSource", "1");
        RxHttpUtils.createApi(ApiService.class)
                .register(parame)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RegisterBean>() {
                    @Override
                    public void accept(RegisterBean bean) throws Exception {
                        mKProgressHUD.dismiss();
                        if (bean.getCode() == 500) {
                            T.showShort(bean.getMsg());
                        } else {
                            T.showLong(getString(R.string.register_successful));
                            JumpUtils.exitActivity(RegisterUserActivity.this);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mKProgressHUD.dismiss();
                        T.showShort("网络连接异常");
                    }
                });
    }

    /**
     * 点击空白位置 隐藏软键盘
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
}
