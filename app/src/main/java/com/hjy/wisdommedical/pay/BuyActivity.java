package com.hjy.wisdommedical.pay;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.T;
import com.hjy.wisdommedical.R;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
/**
 * Created by 初夏小溪 on 2018/9/4 0004.
 * 付款页面
 */
public class BuyActivity extends BaseActivity {

    @BindView(R.id.rb_ali)
    RadioButton mRb_Ali;
    @BindView(R.id.rb_wx)
    RadioButton mRb_Wx;

    private static final int SDK_PAY_FLAG = 1;

    @Override
    protected int getContentView() {
        return R.layout.activity_buy;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        moveTaskToBack(true);
    }

    /**
     * 支付宝 请求接口数据 返回对应的订单号
     *
     * @param aLipayGoldBean
     */
    public void GetToAliPay(final String aLipayGoldBean) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(BuyActivity.this);
                Map<String, String> result = alipay.payV2("", true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 唤起微信充值界面 调接口
     */
    private void GetToWX(){
        IWXAPI wxApi = WXAPIFactory.createWXAPI(this, "wxfa3a683021f65771");
        if (!wxApi.isWXAppInstalled()) {
            T.showShort("未安装微信");
            return;
        }
        PayReq req = new PayReq();
//        req.appId = wechatGoldBean.getAppid();
//        req.partnerId = wechatGoldBean.getPartnerid();
//        req.prepayId = wechatGoldBean.getPrepayid();
//        req.nonceStr = wechatGoldBean.getNoncestr();
//        req.timeStamp = wechatGoldBean.getTimestamp();
//        req.packageValue = "Sign=WXPay";
//        req.sign = wechatGoldBean.getSign();

//        IWXAPI api = WXAPIFactory.createWXAPI(mContext, wechatGoldBean.getAppid());
//        api.sendReq(req);
    }
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };



    @OnClick({R.id.iv_back, R.id.bt_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                JumpUtils.exitActivity(mContext);
                break;
            case R.id.bt_commit:
                if (mRb_Ali.isChecked()){
                    GetToAliPay("");
                }else if (mRb_Wx.isChecked()){
                    GetToWX();
                }
                break;
        }
    }
}
