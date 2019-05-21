package cn.stormbirds.stimlib;

import android.util.Log;

import cn.stormbirds.stimlib.netty.NettyTcpClient;
import cn.stormbirds.stimlib.protobuf.MessageProtobuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HeartbeatRespHandler extends ChannelInboundHandlerAdapter {

    private static final String TAG = HeartbeatRespHandler.class.getSimpleName();
    private NettyTcpClient imsClient;

    public HeartbeatRespHandler(NettyTcpClient imsClient) {
        this.imsClient = imsClient;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageProtobuf.Msg heartbeatRespMsg = (MessageProtobuf.Msg) msg;
        if (heartbeatRespMsg == null || heartbeatRespMsg.getHead() == null) {
            return;
        }

        MessageProtobuf.Msg heartbeatMsg = imsClient.getHeartbeatMsg();
        if (heartbeatMsg == null || heartbeatMsg.getHead() == null) {
            return;
        }

        int heartbeatMsgType = heartbeatMsg.getHead().getMsgType();
        if (heartbeatMsgType == heartbeatRespMsg.getHead().getMsgType()) {
            Log.v(TAG,"收到服务端心跳响应消息，message=" + heartbeatRespMsg);
        } else {
            // 消息透传
            ctx.fireChannelRead(msg);
        }
    }
}