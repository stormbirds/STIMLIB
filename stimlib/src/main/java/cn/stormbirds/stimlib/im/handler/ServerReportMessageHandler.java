package cn.stormbirds.stimlib.im.handler;

import android.util.Log;

import cn.stormbirds.stimlib.bean.AppMessage;

/**
  * Copyright (c) 小宝 @ 2019
  *
  * @ Package Name:    cn.stormbirds.stimlib.im.handler
  * @ Author：         stormbirds
  * @ Email：          xbaojun@gmail.com
  * @ Created At：     2019/5/13 11:24
  * @ Description：    服务端返回的消息发送状态报告
  *
  */
public class ServerReportMessageHandler extends AbstractMessageHandler {

    private static final String TAG = ServerReportMessageHandler.class.getSimpleName();

    @Override
    protected void action(AppMessage message) {
        Log.v(TAG, "收到消息状态报告，message=" + message);
    }
}
