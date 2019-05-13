package cn.stormbirds.stimlib.im.handler;


import cn.stormbirds.stimlib.bean.AppMessage;

/**
  * Copyright (c) 小宝 @ 2019
  *
  * @ Package Name:    cn.stormbirds.stimlib.im.handler
  * @ Author：         stormbirds
  * @ Email：          xbaojun@gmail.com
  * @ Created At：     2019/5/13 11:23
  * @ Description：    消息接口
  *
  */
public interface IMessageHandler {

    void execute(AppMessage message);
}
