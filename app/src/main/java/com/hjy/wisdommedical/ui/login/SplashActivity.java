package com.hjy.wisdommedical.ui.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.MainActivity;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by QKun on 2018/7/13.
 *
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    /**
     * 延迟200 毫秒 隐藏 加载图片控件
     */
    @SuppressLint("CheckResult")
    private void init() {
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
//                        检测token 选择进入主界面 or 登录界面
                    String token = SpfUtils.getSpfSaveStr(Constant.token);
                    if (token.isEmpty()) {
                        startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        RxHttpUtils.createApi(ApiService.class)
                                .checkToken(token)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(tokenBean -> {
                                    if (tokenBean != null) {
                                        if (tokenBean.getCode() == 0) {//成功
                                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                            SplashActivity.this.finish();
                                            SplashActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                            finish();
                                        } else {
                                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                                            SplashActivity.this.finish();
                                            SplashActivity.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                            finish();
                                        }
                                    }
                                }, throwable -> T.showShort(throwable.getMessage()));
                    }
                });
    }
}
