package com.hjy.wisdommedical.hx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.L;
import com.example.handsomelibrary.utils.T;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * 环信请求工具类
 * Created by fangs on 2018/9/4 10:49.
 */
public class EMUtils {

    private static KProgressHUD mKProgressHUD;

    /**
     * 环信 登录
     *
     * @param userName
     * @param password
     */
    public static void loginEM(AppCompatActivity act, String userName, String password) {
        mKProgressHUD = KProgressHUD.create(act).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        EMClient.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                mKProgressHUD.dismiss();
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                L.d("main", "登录聊天服务器成功！");
//                        JumpUtils.jump(act, SessionActivity.class, null);
//                        登录聊天界面
//                        Bundle bundle = new Bundle();
//                        bundle.putString(EaseConstant.EXTRA_USER_ID, docName);
//                        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
//                        JumpUtils.jump(act, ChatActivity.class, bundle);
            }

            @Override
            public void onProgress(int progress, String status) {
                mKProgressHUD.dismiss();
            }

            @Override
            public void onError(int code, String message) {
                T.showLong("登录聊天服务器失败！");
                L.d("main", "登录聊天服务器失败！");
                mKProgressHUD.dismiss();
            }
        });
    }

    /**
     * 环信 登录
     *
     * @param userName
     * @param password
     */
    public static void loginEM(AppCompatActivity act, String userName, String password, Class toact, Bundle bundle) {
        mKProgressHUD = KProgressHUD.create(act).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        EMClient.getInstance().login(userName, password, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                mKProgressHUD.dismiss();
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                L.d("main", "登录聊天服务器成功！");
                JumpUtils.jump(act, toact, bundle);
//                        登录聊天界面
//                        Bundle bundle = new Bundle();
//                        bundle.putString(EaseConstant.EXTRA_USER_ID, docName);
//                        bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
//                        JumpUtils.jump(act, ChatActivity.class, bundle);
            }

            @Override
            public void onProgress(int progress, String status) {
                mKProgressHUD.dismiss();
            }

            @Override
            public void onError(int code, String message) {
                T.showLong("登录聊天服务器失败！");
                L.d("main", "登录聊天服务器失败！");
                mKProgressHUD.dismiss();
            }
        });
    }

    /**
     * 环信 退出登录
     */
    public static void outLoginEM(AppCompatActivity act) {
        mKProgressHUD = KProgressHUD.create(act).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        EMClient.getInstance().logout(true, new EMCallBack() {
            @Override
            public void onSuccess() {
                mKProgressHUD.dismiss();
                L.d("main", "退出登录成功！");
            }

            @Override
            public void onError(int i, String s) {
                mKProgressHUD.dismiss();
                L.d("main", "退出登录失败！");
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
