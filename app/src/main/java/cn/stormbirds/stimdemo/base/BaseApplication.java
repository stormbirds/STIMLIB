package cn.stormbirds.stimdemo.base;

import java.util.UUID;

import cn.stormbirds.stimlib.STIMApp;
import cn.stormbirds.stimlib.im.IMClientBootstrap;


/**
 * Copyright (c) 小宝 @2019
 *
 * @ Package Name:    cn.stormbirds.stimdemo.base
 * @ Author：         stormbirds
 * @ Email：          xbaojun@gmail.com
 * @ Created At：     2019/5/14 11:45
 * @ Description：
 */


public class BaseApplication extends STIMApp {

//    @Override
//    public void addApplications(AppConfig appConfig) {
//        appConfig.add(new STIMApp());
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        String userId = UUID.randomUUID().toString();
        String token = "Bearer " + userId;
        String hosts = "[{\"host\":\"192.168.6.198\", \"port\":8855}]";
        IMClientBootstrap.getInstance().init(userId, token, hosts, 0);
    }
}
