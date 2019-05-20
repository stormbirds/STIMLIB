package cn.stormbirds.stimlib;

import cn.stormbirds.stimlib.api.IMClientInterface;
import cn.stormbirds.stimlib.netty.NettyTcpClient;

public class IMClientFactory {

    public static IMClientInterface getIMSClient() {
        return NettyTcpClient.getInstance();
    }
}