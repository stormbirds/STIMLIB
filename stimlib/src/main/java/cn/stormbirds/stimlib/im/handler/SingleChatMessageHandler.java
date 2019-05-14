package cn.stormbirds.stimlib.im.handler;

import android.util.Log;

import cn.stormbirds.stimlib.bean.AppMessage;
import cn.stormbirds.stimlib.bean.SingleMessage;
import cn.stormbirds.stimlib.event.IMEventCenter;
import cn.stormbirds.stimlib.event.Events;


/**
  * Copyright (c) 小宝 @ 2019
  *
  * @ Package Name:    cn.stormbirds.stimlib.im.handler
  * @ Author：         stormbirds
  * @ Email：          xbaojun@gmail.com
  * @ Created At：     2019/5/13 11:24
  * @ Description：    单聊消息处理实现
  *
  */
public class SingleChatMessageHandler extends AbstractMessageHandler {

    private static final String TAG = SingleChatMessageHandler.class.getSimpleName();

    @Override
    protected void action(AppMessage message) {
        Log.d(TAG, "收到单聊消息，message=" + message);

        SingleMessage msg = new SingleMessage();
        msg.setMsgId(message.getHead().getMsgId());
        msg.setMsgType(message.getHead().getMsgType());
        msg.setMsgContentType(message.getHead().getMsgContentType());
        msg.setFromId(message.getHead().getFromId());
        msg.setToId(message.getHead().getToId());
        msg.setTimestamp(message.getHead().getTimestamp());
        msg.setExtend(message.getHead().getExtend());
        msg.setContent(message.getBody());


        IMEventCenter.dispatchEvent(Events.CHAT_SINGLE_MESSAGE, 0, 0, msg);
    }
}
