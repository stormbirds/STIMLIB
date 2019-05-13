package cn.stormbirds.stimlib.im;

import cn.stormbirds.stimlib.bean.AppMessage;
import cn.stormbirds.stimlib.bean.BaseMessage;
import cn.stormbirds.stimlib.bean.ContentMessage;

/**
  * Copyright (c) 小宝 @ 2019
  *
  * @ Package Name:    cn.stormbirds.stimlib.im
  * @ Author：         stormbirds
  * @ Email：          xbaojun@gmail.com
  * @ Created At：     2019/5/13 11:25
  * @ Description：    消息处理器接口
  *
  */
public interface IMessageProcessor {

    void receiveMsg(AppMessage message);
    void sendMsg(AppMessage message);
    void sendMsg(ContentMessage message);
    void sendMsg(BaseMessage message);
}
