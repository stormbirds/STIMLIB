package cn.stormbirds.stimdemo;

import android.app.Application;

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
public class STIMApp extends Application {

    private static STIMApp instance;

    public static STIMApp sharedInstance() {
        if (instance == null) {
            throw new IllegalStateException("STIMApp not init...");
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
