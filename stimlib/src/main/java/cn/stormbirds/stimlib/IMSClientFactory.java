package cn.stormbirds.stimlib;

import cn.stormbirds.stimlib.api.IMSClientInterface;
import cn.stormbirds.stimlib.netty.NettyTcpClient;

public class IMSClientFactory {

    public static IMSClientInterface getIMSClient() {
        return NettyTcpClient.getInstance();
    }
}