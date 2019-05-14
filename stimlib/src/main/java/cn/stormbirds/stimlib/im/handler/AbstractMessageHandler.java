package cn.stormbirds.stimlib.im.handler;


import cn.stormbirds.stimlib.bean.AppMessage;

/**
  * Copyright (c) 小宝 @ 2019
  *
  * @ Package Name:    cn.stormbirds.stimlib.im.handler
  * @ Author：         stormbirds
  * @ Email：          xbaojun@gmail.com
  * @ Created At：     2019/5/13 11:21
  * @ Description：    抽象的MessageHandler
  *
  */
public abstract class AbstractMessageHandler implements IMessageHandler {

    @Override
    public void execute(AppMessage message) {
        action(message);
    }

    protected abstract void action(AppMessage message);
}
