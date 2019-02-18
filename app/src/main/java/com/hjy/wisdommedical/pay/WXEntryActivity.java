package com.hjy.wisdommedical.pay;

import android.os.Bundle;

import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.L;
import com.example.handsomelibrary.utils.T;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by 初夏小溪 on 2018/9/4 0004.
 * 微信支付
 */
public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler {


    // 申请成功签名的APPID
    public static final String wxID = "wxfa3a683021f65771";
    // IWXAPI 是第三方app和微信通信的openapi接口
    private IWXAPI wxApi;

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //微信分享需要
        wxApi = WXAPIFactory.createWXAPI(mContext, wxID);
        wxApi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        L.e("WXPayEntryActivity回调微信支付的结果errCode = " + resp.errCode);
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int errCode = resp.errCode;
            if (errCode == -1) {/*支付失败*/
                T.showShort("支付失败");
            } else if (errCode == 0) {/*支付成功*/
                T.showShort("支付成功");
            } else if (errCode == -2) {/*取消支付*/
                T.showShort("取消支付");
            }
            finish();
        }
    }


}
