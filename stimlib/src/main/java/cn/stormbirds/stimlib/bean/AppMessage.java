package cn.stormbirds.stimlib.bean;

/**
  * Copyright (c) 小宝 @ 2019
  *
  * @ Package Name:    cn.stormbirds.stimlib.bean
  * @ Author：         stormbirds
  * @ Email：          xbaojun@gmail.com
  * @ Created At：     2019/5/13 11:15
  * @ Description：    App消息，用于把protobuf消息转换成app可用的消息类型
  *
  */
public class AppMessage {

    private Head head;  // 消息头
    private String body;// 消息体

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "AppMessage{" +
                "head=" + head +
                ", body='" + body + '\'' +
                '}';
    }
}
