package com.hjy.wisdommedical.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.example.handsomelibrary.base.BaseActivity;
import com.hjy.wisdommedical.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by QKun on 2018/7/6.
 * 手机登陆
 */
public class LoginByPhoneActivity extends BaseActivity {

    @BindView(R.id.access_verification)
    Button mAccessVerification;

    private Disposable mDisposable;

    @Override
    protected int getContentView() {
        return R.layout.activity_login_phone;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.login_phone_verification, R.id.access_verification})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_phone_verification:
                startActivity(new Intent(LoginByPhoneActivity.this, LoginActivity.class));
                finish();
                break;
            case R.id.access_verification: //点击获取验证码
                if (mAccessVerification.getText().equals(getString(R.string.verification_code))) {
                    // TODO: 2018/7/13 请求数据

                    //倒计时完毕置为可点击状态
                    mAccessVerification.setEnabled(false);
                    int testCount = 60;
                    mDisposable = Flowable.intervalRange(0, testCount, 0, 1, TimeUnit.SECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnNext(new Consumer<Long>() {
                                @Override
                                public void accept(Long aLong) throws Exception {
                                    long mL = 59 - aLong;
                                    mAccessVerification.setBackground(LoginByPhoneActivity.this.getResources().getDrawable(R.drawable.shape_login_access_verification_selected));
                                    mAccessVerification.setText(mL + "s");
                                }
                            })
                            .doOnComplete(new Action() {
                                @Override
                                public void run() throws Exception {
                                    //倒计时完毕置为可点击状态
                                    mAccessVerification.setEnabled(true);
                                    mAccessVerification.setBackground(LoginByPhoneActivity.this.getResources().getDrawable(R.drawable.shape_login_access_verification));
                                    mAccessVerification.setText(getString(R.string.verification_code));
                                }
                            })
                            .subscribe();
                }
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
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
