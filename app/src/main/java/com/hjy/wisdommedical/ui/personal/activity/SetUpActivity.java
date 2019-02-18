package com.hjy.wisdommedical.ui.personal.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.MainActivity;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.hx.EMUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置 Activity
 * Created by Stefan on 2018/7/9 10:37.
 */

public class SetUpActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.st_btn)
    Switch st_btn;

    @Override
    protected int getContentView() {
        return R.layout.activity_set_up;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        tv_title.setText(R.string.set_up);
        st_btn.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                T.showShort(getString(R.string.open_push));
            } else {
                T.showShort(getString(R.string.close_push));
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.Ll_ModifyPs, R.id.Ll_feedBack, R.id.tv_exit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.Ll_ModifyPs:
                JumpUtils.jump(mContext, ModifyPsActivity.class, null);
                break;
            case R.id.Ll_feedBack:
                JumpUtils.jump(mContext, FeedBackActivity.class, null);
                break;
            case R.id.tv_exit:
                new AlertDialog.Builder(mContext)
                        .setTitle(getString(R.string.system_title))
                        .setMessage(getString(R.string.is_exit))
                        .setCancelable(true)
                        .setPositiveButton(R.string.ok, (dialog, which) -> {
                            EMUtils.outLoginEM(mContext);
                            Bundle bundle = new Bundle();
                            bundle.putBoolean("exit", true);
                            JumpUtils.jump(mContext, MainActivity.class, bundle);
                            finish();
                        }).setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss()).show();
                break;
        }
    }
}
