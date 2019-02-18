package com.hjy.wisdommedical.ui.login;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatImageView;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.model.LoginBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.MainActivity;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by QKun on 2018/7/6.
 * 登录
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_title)
    TextView mLoginTitle;
    @BindView(R.id.login_username)
    AppCompatEditText mLoginUsername;
    @BindView(R.id.login_password)
    AppCompatEditText mLoginPassword;
    @BindView(R.id.show_or_hide_password)
    AppCompatImageView mShowOrHidePassword;
    private Boolean showPassword = true;

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //检测token  成功直接进入主页  这个步骤在启动页面完成
    }

    @OnClick({R.id.login_phone_verification, R.id.login_register, R.id.login_miss_password,
            R.id.show_or_hide_password, R.id.login_btn})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.login_phone_verification:
                //进入手机验证登录界面
                startActivity(new Intent(LoginActivity.this, LoginByPhoneActivity.class));
                break;
            case R.id.login_register:
                //注册
                startActivity(new Intent(LoginActivity.this, RegisterUserActivity.class));
                break;
            case R.id.login_miss_password:
                //忘记密码
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                break;
            case R.id.show_or_hide_password:
                if (showPassword) {// 显示密码
                    mShowOrHidePassword.setImageDrawable(getResources().getDrawable(R.mipmap.login_password_on));
                    mLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mLoginPassword.setSelection(mLoginPassword.getText().toString().length());
                    showPassword = !showPassword;
                } else {// 隐藏密码
                    mShowOrHidePassword.setImageDrawable(getResources().getDrawable(R.mipmap.login_password_off));
                    mLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mLoginPassword.setSelection(mLoginPassword.getText().toString().length());
                    showPassword = !showPassword;
                }
                break;
            case R.id.login_btn:  //登录
                if (mLoginUsername.getText().toString().trim().isEmpty() || mLoginPassword.getText().toString().trim().isEmpty()) {
                    T.showShort(getString(R.string.username_or_pad_empty));
                    return;
                }
                String username = mLoginUsername.getText().toString().trim();
                String password = mLoginPassword.getText().toString().trim();
                login(username, password);
                break;
        }
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     */
    @SuppressLint("CheckResult")
    private void login(String username, String password) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .login(username, password, 1)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<LoginBean>() {
                    @Override
                    public void accept(LoginBean loginBean) throws Exception {
                        if (loginBean != null) {
                            if (loginBean.getCode() == 501) {
                                //重新激活发送验证码
                                mKProgressHUD.dismiss();
                                new AlertDialog.Builder(mContext)
                                        .setTitle(LoginActivity.this.getString(R.string.system_title))
                                        .setMessage(LoginActivity.this.getString(R.string.login_notice))
                                        .setCancelable(true)
                                        .setPositiveButton(R.string.ok, (dialog, which) -> LoginActivity.this.GetSendEmailToAlive(username))
                                        .setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss()).show();
                            } else if (loginBean.getCode() == 0) {
                                String token = loginBean.getRows().getToken();
                                LoginBean.RowsBean.AppUserBean appUser = loginBean.getRows().getAppUser();
                                //保存token 和 账户 及 密码
                                int id = appUser.getId();
                                SpfUtils.saveIntToSpf(Constant.userId, id);
                                SpfUtils.saveStrToSpf(Constant.token, token);
                                SpfUtils.saveStrToSpf(Constant.username, username);
                                SpfUtils.saveStrToSpf(Constant.password, password);
                                SpfUtils.saveStrToSpf(Constant.userHeadImg, appUser.getPhotoUrl());
                                SpfUtils.saveStrToSpf(Constant.userNickName, appUser.getNickname());
                                mKProgressHUD.dismiss();
                                LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                LoginActivity.this.finish();
//                                EMUtils.loginEM(mContext, SpfUtils.getSpfSaveStr(Constant.username), "123456");
                            } else {
                                T.showLong(loginBean.getMsg());
                                mKProgressHUD.dismiss();
                            }
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
     * 重新发送激活邮件
     *
     * @param username
     */
    private void GetSendEmailToAlive(String username) {
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RxHttpUtils.createApi(ApiService.class)
                .sendEmailToAlive(username)
                .throttleFirst(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CommonObserver<Object>() {
                    @Override
                    protected void onSuccess(Object object) {
                        mKProgressHUD.dismiss();
                        T.showLong(getString(R.string.email_succeed));
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                        T.showLong(errorMsg);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
}
