package cn.stormbirds.stimlib;

import android.support.multidex.MultiDexApplication;

/**
  * <p>Copyright (c) 小宝 @ 2019</p>
  *
  * <p>@ Package Name:    cn.stormbirds.stimlib</p>
  * <p>@ Author：         stormbirds</p>
  * <p>@ Email：          xbaojun@gmail.com</p>
  * <p>@ Created At：     2019/5/13 11:53</p>
  * <p>@ Description：    im服务启动类</p>
  *
  */
public class NettyChatApp extends MultiDexApplication {

    private static NettyChatApp instance;

    public static NettyChatApp sharedInstance() {
        if (instance == null) {
            throw new IllegalStateException("app not init...");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
