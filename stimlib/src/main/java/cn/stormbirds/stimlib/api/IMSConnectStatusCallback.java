package cn.stormbirds.stimlib.api;


/**
 *
 * @Package:        cn.stormbirds.imlib.api
 * @ClassName:      IMSConnectStatusCallback
 * @Description:    java类作用描述
 * @Author:         StormBirds
 * @Email           xbaojun@gmail.com
 * @CreateDate:     19-5-12 下午7:37
 * @Version:        1.0
 */
public interface IMSConnectStatusCallback {

    /**
     * ims连接中
     */
    void onConnecting();

    /**
     * ims连接成功
     */
    void onConnected();

    /**
     * ims连接失败
     */
    void onConnectFailed();
}