package cn.stormbirds.stimlib.base;

import cn.stormbirds.stimlib.STIMApp;
import cn.stormbirds.stimlib.base.hookapp.AppConfig;
import cn.stormbirds.stimlib.base.hookapp.HookApplication;


/**
 * Copyright (c) 小宝 @2019
 *
 * @ Package Name:    cn.stormbirds.stimlib.base
 * @ Author：         stormbirds
 * @ Email：          xbaojun@gmail.com
 * @ Created At：     2019/5/14 11:45
 * @ Description：
 */


public class BaseApplication extends HookApplication {

    @Override
    public void addApplications(AppConfig appConfig) {
        appConfig.add(new STIMApp());
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
