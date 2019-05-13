package cn.stormbirds.stimlib.event;

/**
  * Copyright (c) 小宝 @ 2019
  *
  * @ Package Name:    cn.stormbirds.stimlib.event
  * @ Author：         stormbirds
  * @ Email：          xbaojun@gmail.com
  * @ Created At：     2019/5/13 11:19
  * @ Description：    事件模型
  *
  */
public class CEvent implements PoolableObject {

    /**
     * 主题
     */
    public String topic;

    /**
     * 消息类型
     */
    public int msgCode;

    /**
     * 预留参数
     */
    public int resultCode;

    /**
     * 回调返回数据
     */
    public Object obj;

    public CEvent() {}

    public CEvent(String topic, int msgCode, int resultCode, Object obj) {
        this.topic = topic;
        this.msgCode = msgCode;
        this.resultCode = resultCode;
        this.obj = obj;
    }

    @Override
    public void reset() {
        topic = null;
        msgCode = 0;
        resultCode = 0;
        obj = null;
    }
}
