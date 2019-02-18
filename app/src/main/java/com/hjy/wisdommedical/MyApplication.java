package com.hjy.wisdommedical;

import android.app.ActivityManager;
import android.content.Context;
import android.content.IntentFilter;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.example.handsomelibrary.api.ApiService;
import com.example.handsomelibrary.application.BaseApplication;
import com.example.handsomelibrary.utils.L;
import com.example.handsomelibrary.utils.SpfUtils;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.hx.single.CallManager;
import com.hjy.wisdommedical.hx.single.CallReceiver;
import com.hjy.wisdommedical.widget.GlideImagePickerLoader;
import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.hyphenate.easeui.utils.EaseCommonUtils;
import com.hyphenate.easeui.utils.EaseUserUtils;
import com.hyphenate.exceptions.HyphenateException;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;

import java.util.Iterator;
import java.util.List;

/**
 * Created by lz on 2016/4/16.
 * 项目的 Application类，做一些项目的初始化操作，比如sdk的初始化等
 */
public class MyApplication extends BaseApplication {

    // 上下文菜单
    private Context mContext;
    // 记录是否已经初始化
    private boolean isInit = false;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        initImagePicker();

        // 初始化环信SDK
        initHyphenate();

        //Android 7.0 系统解决拍照的问题 exposed beyond app through ClipData.Item.getUri()
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

    /**
     * 分割 Dex 支持
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImagePickerLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(true);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
//        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
    }

    private CallReceiver callReceiver;
    /**
     * 初始化环信sdk，并做一些注册监听的操作
     */
    private void initHyphenate() {
        // 获取当前进程 id 并取得进程名
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        /**
         * 如果app启用了远程的service，此application:onCreate会被调用2次
         * 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
         * 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回
         */
        if (processAppName == null || !processAppName.equalsIgnoreCase(mContext.getPackageName())) {
            // 则此application的onCreate 是被service 调用的，直接返回
            return;
        }

// 初始化sdk的一些配置
//        EMOptions options = new EMOptions();
        //        options.enableDNSConfig(false);
        //        options.setIMServer("im1.easemob.cm");
        //        options.setImPort(443);
        //        options.setRestServer("a1.easemob.com:80");
        //        options.setAppKey("easemob-demo#chatdemoui");
        //        options.setAppKey("easemob-demo#chatuidemo");
        //        options.setAppKey("hx-ps#api4vip6");
        //        options.setAppKey("cx-dev#cxstudy");
//        options.setAutoLogin(true);
        // 设置小米推送 appID 和 appKey
        //        options.setMipushConfig("2882303761517573806", "5981757315806");
        // 设置消息是否按照服务器时间排序
//        options.setSortMessageByServerTime(false);

        //初始化 EaseUI
        EaseUI.getInstance().init(mContext, null);
        EaseUI.getInstance().setUserProfileProvider(new EaseUI.EaseUserProfileProvider(){
            @Override
            public EaseUser getUser(String username) {
                L.e("hxUserId", username);
                return getUserInfo(username);
            }
        });
        // 开启 debug 模式
//        EMClient.getInstance().setDebugMode(true);

        // 设置通话广播监听器
        IntentFilter callFilter = new IntentFilter(EMClient.getInstance()
                .callManager()
                .getIncomingCallBroadcastAction());
        if (callReceiver == null) {
            callReceiver = new CallReceiver();
        }
        //注册通话广播接收者
        mContext.registerReceiver(callReceiver, callFilter);

        CallManager.getInstance().setExternalInputData(false);
        // 通话管理类的初始化
        CallManager.getInstance().init(mContext);

        setConnectionListener();
    }

    /**
     * 设置连接监听
     */
    private void setConnectionListener() {
        EMConnectionListener connectionListener = new EMConnectionListener() {
            @Override
            public void onConnected() {

            }

            @Override
            public void onDisconnected(int i) {
                String str = "" + i;
                switch (i) {
                    case EMError.USER_REMOVED:
                        str = "账户被移除";
                        break;
                    case EMError.USER_LOGIN_ANOTHER_DEVICE:
                        str = "其他设备登录";
                        break;
                    case EMError.USER_KICKED_BY_OTHER_DEVICE:
                        str = "其他设备强制下线";
                        break;
                    case EMError.USER_KICKED_BY_CHANGE_PASSWORD:
                        str = "密码修改";
                        break;
                    case EMError.SERVER_SERVICE_RESTRICTED:
                        str = "被后台限制";
                        break;
                }
                Log.i("onDisconnected %s", str);
            }
        };
        EMClient.getInstance().addConnectionListener(connectionListener);
    }

    /**
     * 根据Pid获取当前进程的名字，一般就是当前app的包名
     *
     * @param pid 进程的id
     * @return 返回进程的名字
     */
    private String getAppName(int pid) {
        String processName = null;
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator i = list.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pid) {
                    // 根据进程的信息获取当前进程的名字
                    processName = info.processName;
                    // 返回当前进程名
                    return processName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 没有匹配的项，返回为null
        return processName;
    }

    private EaseUser getUserInfo(String username) {
        EaseUser user = null;
        //如果用户是本人，就设置自己的头像
        if (username.equals(EMClient.getInstance().getCurrentUser())) {
            user = new EaseUser(username);
            user.setAvatar(ApiService.BASE_PIC_URL + SpfUtils.getSpfSaveStr(Constant.userHeadImg));
            user.setNick(SpfUtils.getSpfSaveStr(Constant.userNickName));
            return user;
        } else {
            //收到别人的消息，设置别人的头像
            EMConversation conversation = EMClient.getInstance().chatManager()
                    .getConversation(EaseUserUtils.toChatId, EaseCommonUtils.getConversationType(EaseUserUtils.chatType), true);
            List<EMMessage> messages = conversation.getAllMessages();
            int i = 0;
            if (null == messages || (i = messages.size()) == 0) return null;

            for (--i; i > -1 ; i--){
                EMMessage message = messages.get(i);

                if (username.equals(message.getUserName())){
                    user = new EaseUser(username);
                    try {
                        user.setAvatar(message.getStringAttribute("hxChartHeadImg"));
                        user.setNick(message.getStringAttribute("userName"));
                        break;
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
            }

            return user;
        }
    }
}