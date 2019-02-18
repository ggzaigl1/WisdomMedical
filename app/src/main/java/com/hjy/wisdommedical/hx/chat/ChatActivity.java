package com.hjy.wisdommedical.hx.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.JumpUtils;
import com.example.handsomelibrary.utils.SpfUtils;
import com.hjy.wisdommedical.R;
import com.hjy.wisdommedical.constant.Constant;
import com.hjy.wisdommedical.hx.single.CallManager;
import com.hjy.wisdommedical.hx.single.VideoCallActivity;
import com.hjy.wisdommedical.hx.single.VoiceCallActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.easeui.EaseConstant;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 聊天 activity
 * Created by fangs on 2018/9/3.
 */
public class ChatActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tv_title;

    protected int chatType;
    String toChatId = "";
    String toChatUsername = "";
    private EaseChatFragment chatFragment;

    @Override
    protected int getContentView() {
        return R.layout.chat_act;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //user or group id
        Bundle bundle = getIntent().getExtras();
        chatType = getIntent().getIntExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        toChatId = bundle.getString(EaseConstant.EXTRA_USER_ID, SpfUtils.getSpfSaveStr(Constant.docUsername));
        toChatUsername = getIntent().getStringExtra(EaseConstant.EXTRA_USER_NAME);

        tv_title.setText(toChatUsername);

        Bundle fmBundle = new Bundle();
        fmBundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, chatType);
        fmBundle.putString(EaseConstant.EXTRA_USER_ID, toChatId);

        chatFragment = new EaseChatFragment();
        //set arguments
        chatFragment.setArguments(fmBundle);
        getSupportFragmentManager().beginTransaction()
                .add(com.hjy.chat.R.id.container, chatFragment)
                .commit();
    }

    private void initTitle() {
        if (chatType == EaseConstant.CHATTYPE_SINGLE) {
            // set title
               tv_title.setText(toChatUsername);
        } else {
            if (chatType == EaseConstant.CHATTYPE_GROUP) {
                //group chat
                EMGroup group = EMClient.getInstance().groupManager().getGroup(toChatUsername);
                if (group != null) tv_title.setText(group.getGroupName());
            }
        }
    }

//    @Override // 删除聊天记录按钮
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_schedule, menu);
//        View menuLayout = menu.findItem(R.id.menuSchedule).getActionView();
//        menuLayout.setOnClickListener(this);
//
//        AppCompatImageView imgMenu = menuLayout.findViewById(R.id.imgMenu);
//        int svgIcon = chatType == EaseConstant.CHATTYPE_SINGLE ? R.drawable.svg_delete : R.drawable.svg_group;
//        imgMenu.setImageResource(svgIcon);
//        return true;
//    }

    @OnClick({R.id.iv_back})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back://菜单按钮
                JumpUtils.exitActivity(mContext);
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        // enter to chat activity when click notification bar, here make sure only one chat activiy
        Bundle bundle = intent.getExtras();
        Intent intent1 = getIntent();
        if (null != bundle) {
            intent1 = intent1.putExtras(bundle);
            super.onNewIntent(intent1);
            setIntent(intent1);//intent传值 接收不到问题，关键在这句

            String username = bundle.getString(EaseConstant.EXTRA_USER_ID, "");
            if (toChatId.equals(username))
                super.onNewIntent(intent);
            else {
                finish();
                startActivity(intent);
            }
        } else {
            super.onNewIntent(intent);
        }
    }

    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
    }

//    public String getToChatUsername(){
//        return toChatUsername;
//    }

    /**
     * 视频呼叫
     */
    public void callVideo() {
        checkContacts();
        Intent intent = new Intent(this, VideoCallActivity.class);
        CallManager.getInstance().setChatId(toChatId);
        CallManager.getInstance().setInComingCall(false);
        CallManager.getInstance().setCallType(CallManager.CallType.VIDEO);
        startActivity(intent);
    }

    /**
     * 语音呼叫
     */
    public void callVoice() {
        checkContacts();
        Intent intent = new Intent(this, VoiceCallActivity.class);
        CallManager.getInstance().setChatId(toChatId);
        CallManager.getInstance().setInComingCall(false);
        CallManager.getInstance().setCallType(CallManager.CallType.VOICE);
        startActivity(intent);
    }

    private void checkContacts() {
        if (!toChatId.isEmpty()) {
//            VMSPUtil.put("toUsername", toChatUsername);
        }
    }
}
