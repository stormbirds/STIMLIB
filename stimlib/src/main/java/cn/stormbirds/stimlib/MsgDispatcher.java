package cn.stormbirds.stimlib;

import cn.stormbirds.stimlib.listener.OnEventListener;
import cn.stormbirds.stimlib.protobuf.MessageProtobuf;

public class MsgDispatcher {

    private OnEventListener mOnEventListener;

    public MsgDispatcher() {

    }

    public void setOnEventListener(OnEventListener listener) {
        this.mOnEventListener = listener;
    }

    /**
     * 接收消息，并通过OnEventListener转发消息到应用层
     * @param msg
     */
    public void receivedMsg(MessageProtobuf.Msg msg) {
        if(mOnEventListener == null) {
            return;
        }

        mOnEventListener.dispatchMsg(msg);
    }
}