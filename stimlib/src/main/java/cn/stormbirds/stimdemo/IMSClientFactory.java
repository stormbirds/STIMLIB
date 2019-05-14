package cn.stormbirds.stimdemo;

import cn.stormbirds.stimdemo.api.IMSClientInterface;
import cn.stormbirds.stimdemo.netty.NettyTcpClient;

public class IMSClientFactory {

    public static IMSClientInterface getIMSClient() {
        return NettyTcpClient.getInstance();
    }
}