package cn.stormbirds.stimdemo.im;

/**
  * Copyright (c) 小宝 @ 2019
  *
  * @ Package Name:    cn.stormbirds.stimlib.im
  * @ Author：         stormbirds
  * @ Email：          xbaojun@gmail.com
  * @ Created At：     2019/5/13 11:33
  * @ Description：    消息类型
  *
  */
public enum MessageType {

    /*
     * 握手消息
     */
    HANDSHAKE(1001),

    /*
     * 心跳消息
     */
    HEARTBEAT(1002),

    /*
     * 客户端提交的消息接收状态报告
     */
    CLIENT_MSG_RECEIVED_STATUS_REPORT(1009),

    /*
     * 服务端返回的消息发送状态报告
     */
    SERVER_MSG_SENT_STATUS_REPORT(1010),

    /**
     * 单聊消息
     */
    SINGLE_CHAT(2001),

    /**
     * 群聊消息
     */
    GROUP_CHAT(3001);

    private int msgType;

    MessageType(int msgType) {
        this.msgType = msgType;
    }

    public int getMsgType() {
        return this.msgType;
    }

    public enum MessageContentType {

        /**
         * 文本消息
         */
        TEXT(101),

        /**
         * 图片消息
         */
        IMAGE(102),

        /**
         * 语音消息
         */
        VOICE(103),

        /**
         * 视频消息
         */
        VIDEO(104);

        private int msgContentType;

        MessageContentType(int msgContentType) {
            this.msgContentType = msgContentType;
        }

        public int getMsgContentType() {
            return this.msgContentType;
        }
    }
}
