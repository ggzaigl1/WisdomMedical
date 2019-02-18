package com.hjy.wisdommedical.ui.inquiry.activity;

import android.app.AlertDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.util.ArrayMap;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.interceptor.Transformer;
import com.example.handsomelibrary.model.ListConsultBean;
import com.example.handsomelibrary.retrofit.RxHttpUtils;
import com.example.handsomelibrary.retrofit.observer.CommonObserver;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.hx.single.CallManager;
import com.hjy.wisdommedical.widget.WaveView;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCmdMessageBody;
import com.hyphenate.chat.EMMessage;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 進入診室
 * Created by 初夏小溪 on 2018/9/7 0007.
 */
public class WaitingDoctorActivity extends BaseActivity {

    @BindView(R.id.wave_view)
    WaveView mWaveView;
    @BindView(R.id.tv_docName)
    TextView tv_docName;

    //是否 等待视频问诊
    boolean isVideoMsg;
    private ListConsultBean mListConsultBean;


    @Override
    protected int getContentView() {
        return R.layout.hx_wait_call_voice_act;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        CallManager.getInstance().setCallState(CallManager.CallState.CONNECTED);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        isVideoMsg = bundle.getBoolean("isVideoMsg");
        mListConsultBean = (ListConsultBean) bundle.getSerializable("listConsultBean");
        tv_docName.setText(mListConsultBean.getAppDocEntityVO().getDocName());

        mWaveView.setDuration(5000);
        mWaveView.setStyle(Paint.Style.FILL_AND_STROKE);
        mWaveView.setColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
        mWaveView.setInterpolator(new LinearOutSlowInInterpolator());
        mWaveView.start();
        EMMessage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (CallManager.getInstance().getCallState() == CallManager.CallState.DISCONNECTED) {
            GetUpdateStatus(mListConsultBean.getId(), 8);
        }
    }

    private void EMMessage() {
        String action = "com.hjy.wisdommedical.ui.inquiry.activity";//action可以自定义
        EMCmdMessageBody cmdBody = new EMCmdMessageBody(action);
        EMMessage cmdMsg = EMMessage.createSendMessage(EMMessage.Type.CMD);
        //支持单聊和群聊，默认单聊，如果是群聊添加下面这行
//        cmdMsg.setChatType(EMMessage.ChatType.GroupChat);
        cmdMsg.addBody(cmdBody);
        String hxid = mListConsultBean.getAppDocEntityVO().getDocUsername();
        cmdMsg.setTo(hxid);//发送透传消息给对应的医生
        cmdMsg.setAttribute("username", mListConsultBean.getVisitMemberName());//医生端接收患者姓名
        cmdMsg.setAttribute("hxId", hxid);//向医生端发生 自己的环信id 用于通知栏 直接点击发送视频或语音问诊
        cmdMsg.setAttribute("isVideoMsg", isVideoMsg);//向医生端发送是否 视频问诊
        EMClient.getInstance().chatManager().sendMessage(cmdMsg);
    }

    @OnClick({R.id.bt_wait})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_wait:
                new AlertDialog.Builder(mContext)
                        .setTitle(getString(R.string.system_title))
                        .setMessage(getString(R.string.exit_waiting))
                        .setCancelable(true)
                        .setPositiveButton(R.string.ok, (dialog, which) -> GetUpdateStatus(mListConsultBean.getId(), 8))
                        .setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss()).show();
                break;
        }
    }

    /**
     * 修改订单状态
     *
     * @param orderId
     */
    private void GetUpdateStatus(Integer orderId, int status) {
        mKProgressHUD = KProgressHUD.create(mContext).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(2).setDimAmount(0.5f).show();
        ArrayMap<String, Object> objectMap = new ArrayMap<>();
        objectMap.put("id", orderId);
        objectMap.put("status", status);
        RxHttpUtils.createApi(ApiService.class)
                .GetUpdateStatus(objectMap)
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<String>() {
                    @Override
                    protected void onSuccess(String s) {
                        mKProgressHUD.dismiss();
                        JumpUtils.exitActivity(mContext);
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        mKProgressHUD.dismiss();
                        T.showShort(errorMsg);
                    }
                });
    }


    //重写onKeyDown方法,对按键(不一定是返回按键)监听
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            new AlertDialog.Builder(mContext)
                    .setTitle(getString(R.string.system_title))
                    .setMessage(getString(R.string.exit_waiting))
                    .setCancelable(true)
                    .setPositiveButton(R.string.ok, (dialog, which) -> GetUpdateStatus(mListConsultBean.getId(), 8))
                    .setNegativeButton(R.string.cancel, (dialog, which) -> dialog.dismiss()).show();
        }
        return false;
    }
}
