package cn.stormbirds.stimlib.im.handler;

import android.util.Log;

import cn.stormbirds.stimlib.bean.AppMessage;
import cn.stormbirds.stimlib.bean.GroupMessage;
import cn.stormbirds.stimlib.event.Events;
import cn.stormbirds.stimlib.event.IMEventCenter;


/**
  * Copyright (c) 小宝 @ 2019
  *
  * @ Package Name:    cn.stormbirds.stimlib.im.handler
  * @ Author：         stormbirds
  * @ Email：          xbaojun@gmail.com
  * @ Created At：     2019/5/13 11:22
  * @ Description：    群聊消息具体实现
  *
  */
public class GroupChatMessageHandler extends AbstractMessageHandler {

    private static final String TAG = GroupChatMessageHandler.class.getSimpleName();

    @Override
    protected void action(AppMessage message) {
        Log.d(TAG, "收到群聊消息，message=" + message);

        GroupMessage msg = new GroupMessage();
        msg.setMsgId(message.getHead().getMsgId());
        msg.setMsgType(message.getHead().getMsgType());
        msg.setMsgContentType(message.getHead().getMsgContentType());
        msg.setFromId(message.getHead().getFromId());
        msg.setToId(message.getHead().getToId());
        msg.setTimestamp(message.getHead().getTimestamp());
        msg.setExtend(message.getHead().getExtend());
        msg.setContent(message.getBody());


        IMEventCenter.dispatchEvent(Events.CHAT_GROUP_MESSAGE, 0, 0, msg);
    }
}
