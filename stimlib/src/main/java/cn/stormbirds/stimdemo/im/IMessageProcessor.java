package cn.stormbirds.stimdemo.im;

import cn.stormbirds.stimdemo.bean.AppMessage;
import cn.stormbirds.stimdemo.bean.BaseMessage;
import cn.stormbirds.stimdemo.bean.ContentMessage;

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
