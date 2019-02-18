package com.hjy.wisdommedical.hx.chat;

import android.os.Bundle;

import com.example.handsomelibrary.base.BaseActivity;
import com.example.handsomelibrary.utils.JumpUtils;
import com.hjy.wisdommedical.R;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;

/**
 * 回话列表 activity
 * Created by fangs on 2018/9/4 10:04.
 */
public class SessionActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return com.hjy.chat.R.layout.chat_act;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        EaseConversationListFragment sessionListFragment = new EaseConversationListFragment();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, sessionListFragment)
                .commit();

        sessionListFragment.setConversationListItemClickListener(conversation -> {
            Bundle bundle = new Bundle();
            bundle.putString(EaseConstant.EXTRA_USER_ID, conversation.conversationId());
            bundle.putInt(EaseConstant.EXTRA_CHAT_TYPE, SessionActivity.this.getChatType(conversation.getType()));

            JumpUtils.jump(SessionActivity.this, ChatActivity.class, bundle);
        });
    }

    /**
     * 比对枚举 返回 聊天类型
     * @param chatType
     * @return
     */
    private int getChatType(EMConversation.EMConversationType chatType){
        if (chatType == EMConversation.EMConversationType.Chat) {
            return EaseConstant.CHATTYPE_SINGLE;
        } else if (chatType == EMConversation.EMConversationType.GroupChat) {
            return EaseConstant.CHATTYPE_GROUP;
        } else if (chatType == EMConversation.EMConversationType.ChatRoom) {
            return EaseConstant.CHATTYPE_CHATROOM;
        }

        return EaseConstant.CHATTYPE_SINGLE;
    }


}
