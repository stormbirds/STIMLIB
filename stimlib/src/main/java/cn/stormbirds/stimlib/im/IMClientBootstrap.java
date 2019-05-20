package cn.stormbirds.stimlib.im;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.UUID;
import java.util.Vector;

import cn.stormbirds.stimlib.IMClientFactory;
import cn.stormbirds.stimlib.api.IMClientInterface;
import cn.stormbirds.stimlib.protobuf.MessageProtobuf;


/**
  * Copyright (c) 小宝 @ 2019
  *
  * @ Package Name:    cn.stormbirds.stimlib.im
  * @ Author：         stormbirds
  * @ Email：          xbaojun@gmail.com
  * @ Created At：     2019/5/13 11:25
  * @ Description：    应用层的imsClient客户端启动器
  *
  */
public class IMClientBootstrap {

    private static final String TAG = IMClientBootstrap.class.getName();

    private static final IMClientBootstrap INSTANCE = new IMClientBootstrap();
    private IMClientInterface imsClient;
    private boolean isActive;
    private static String SERVERURLLIST = "";

    private IMClientBootstrap() {

    }

    public static IMClientBootstrap getInstance() {
        return INSTANCE;
    }

    public synchronized void init(String userId, String token, String hosts, int appStatus) {
        if (!isActive()) {
            Vector<String> serverUrlList = convertHosts(hosts);
            if (serverUrlList == null || serverUrlList.size() == 0) {
                Log.i(TAG, "初始化启动器错误 IMClientBootstrap，服务器地址不能为null");
                return;
            }

            SERVERURLLIST=hosts;

            isActive = true;
            Log.i(TAG, "初始化启动器 IMClientBootstrap 成功, servers=" + hosts);
            if (null != imsClient) {
                imsClient.close();
            }
            imsClient = IMClientFactory.getIMSClient();
            updateAppStatus(appStatus);
            imsClient.login(userId,token);
            imsClient.init(serverUrlList, new IMEventListener(userId,token), new IMConnectStatusListener());
        }
    }

    public boolean isActive() {
        return isActive;
    }

    /**
     * 发送消息
     *
     * @param msg
     */
    public void sendMessage(MessageProtobuf.Msg msg) {
        if (isActive) {
            imsClient.sendMsg(msg);
        }
    }

    private Vector<String> convertHosts(String hosts) {
        if (hosts != null && hosts.length() > 0) {
            JSONArray hostArray = JSONArray.parseArray(hosts);
            if (null != hostArray && hostArray.size() > 0) {
                Vector<String> serverUrlList = new Vector<String>();
                JSONObject host;
                for (int i = 0; i < hostArray.size(); i++) {
                    host = JSON.parseObject(hostArray.get(i).toString());
                    serverUrlList.add(host.getString("host") + " "
                            + host.getInteger("port"));
                }
                return serverUrlList;
            }
        }
        return null;
    }

    public void updateAppStatus(int appStatus) {
        if (imsClient == null) {
            return;
        }

        imsClient.setAppStatus(appStatus);
    }

    public void login(String userId, String token){
        if(imsClient!=null){
            imsClient.login(userId,token);
        }else
        init(userId,token,SERVERURLLIST,0);
    }
}
