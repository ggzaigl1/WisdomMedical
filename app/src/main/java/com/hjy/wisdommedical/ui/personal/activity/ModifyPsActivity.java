package com.hjy.wisdommedical.ui.personal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.net.RetrofitManager;
import com.hjy.wisdommedical.ui.login.LoginActivity;
import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.handsomelibrary.utils.T.showShort;

/**
 * 设置-修改密码 Activity
 * Created by Stefan on 2018/7/9 14:04.
 */

public class ModifyPsActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_old_eye)
    ImageView iv_old_eye;
    @BindView(R.id.iv_new_eye)
    ImageView iv_new_eye;
    @BindView(R.id.iv_sureNew_eye)
    ImageView iv_sureNew_eye;
    @BindView(R.id.et_OldPs)
    EditText et_OldPs;
    @BindView(R.id.et_NewPs)
    EditText et_NewPs;
    @BindView(R.id.et_sure_newPs)
    EditText et_sure_newPs;

    boolean flagA = true;
    boolean flagB = true;
    boolean flagC = true;

    @Override
    protected int getContentView() {
        return R.layout.activity_modify_ps;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.modify_password);
        setInitialState();
    }

    private void setInitialState() {
//        et_OldPs.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        et_NewPs.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//        et_sure_newPs.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        iv_old_eye.setBackgroundResource(R.mipmap.login_password_off);
        iv_new_eye.setBackgroundResource(R.mipmap.login_password_off);
        iv_sureNew_eye.setBackgroundResource(R.mipmap.login_password_off);
    }

    @OnClick({R.id.iv_back, R.id.iv_old_eye, R.id.iv_new_eye, R.id.iv_sureNew_eye, R.id.bt_modify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.iv_old_eye:
                if (flagA) {
                    iv_old_eye.setBackgroundResource(R.mipmap.login_password_on);
                    //选中显示密码
                    et_OldPs.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    //把光标设置在文字结尾
                    et_OldPs.setSelection(et_OldPs.getText().length());
                    flagA = !flagA;
                } else {
                    iv_old_eye.setBackgroundResource(R.mipmap.login_password_off);
                    //隐藏密码
                    et_OldPs.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //把光标设置在文字结尾
                    et_OldPs.setSelection(et_OldPs.getText().length());
                    flagA = !flagA;
                }
                break;
            case R.id.iv_new_eye:
                if (flagB) {
                    iv_new_eye.setBackgroundResource(R.mipmap.login_password_on);
                    et_NewPs.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_NewPs.setSelection(et_NewPs.getText().length());
                    flagB = !flagB;
                } else {
                    iv_new_eye.setBackgroundResource(R.mipmap.login_password_off);
                    et_NewPs.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_NewPs.setSelection(et_NewPs.getText().length());
                    flagB = !flagB;
                }

                break;
            case R.id.iv_sureNew_eye:
                if (flagC) {
                    iv_sureNew_eye.setBackgroundResource(R.mipmap.login_password_on);
                    et_sure_newPs.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    et_sure_newPs.setSelection(et_sure_newPs.getText().length());
                    flagC = !flagC;
                } else {
                    iv_sureNew_eye.setBackgroundResource(R.mipmap.login_password_off);
                    et_sure_newPs.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    et_sure_newPs.setSelection(et_sure_newPs.getText().length());
                    flagC = !flagC;
                }
                break;
            case R.id.bt_modify:
                GetSave();
                break;
        }
    }

    /**
     * 网络请求
     */
    private void GetSave() {
        if (et_OldPs.getText().toString().equals("")) {
            showShort("旧密码不能为空");
            return;
        } else if (et_NewPs.getText().toString().equals("")) {
            showShort("新密码不能为空");
            return;
        } else if (et_sure_newPs.getText().toString().equals("")) {
            showShort("新密码不能为空");
            return;
        } else if (!et_NewPs.getText().toString().equals(et_sure_newPs.getText().toString())) {
            showShort("两次新密码不一致");
            return;
        }
        mKProgressHUD = KProgressHUD.create(this).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        RetrofitManager.createApi(ApiService.class)
                .updatePwd(SpfUtils.getSpfSaveStr(Constant.username), et_OldPs.getText().toString().trim(), et_sure_newPs.getText().toString().trim())
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<Object>() {
                    @Override
                    protected void onSuccess(Object Object) {
                        T.showLong(getString(R.string.update_password));
                        Intent intent = new Intent(ModifyPsActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                        T.showShort(errorMsg);
                    }
                });
    }
}
