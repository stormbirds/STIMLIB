package cn.stormbirds.stimlib.event;

import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
  * Copyright (c) 小宝 @ 2019
  *
  * @ Package Name:    cn.stormbirds.stimlib.event
  * @ Author：         stormbirds
  * @ Email：          xbaojun@gmail.com
  * @ Created At：     2019/5/13 11:19
  * @ Description：    事件中心服务（实现类似系统广播的功能）
  *
  */
public class IMEventCenter {

    /**
     * 事件中心服务名称
     */
    public static final String TAG = "EventCenter";

    /**
     * 监听器列表，支持一对多存储
     */
    private static final HashMap<String, Object> mListenerMap = new HashMap<String, Object>();

    /**
     * 监听器列表锁
     */
    private static final Object mListenerLock = new Object();

    /**
     * 事件对象池
     */
    private static final CEvenObjPool mPool = new CEvenObjPool(5);

    /**
     * 注册/注销监听器
     *
     * @param toBind
     * @param listener 监听器
     * @param topics 主题（一个服务可以发布多个主题事件）
     */
    public static void onBindEvent(boolean toBind, IM_EventListener listener, String[] topics) {
        if(toBind) {
            registerEventListener(listener, topics);
        }else {
            unregisterEventListener(listener, topics);
        }
    }

    /**
     * 注册监听器
     * @param listener
     *                  监听器
     * @param topic
     *                  主题
     */
    public static void registerEventListener(IM_EventListener listener, String topic) {
        registerEventListener(listener, new String[]{ topic });
    }

    /**
     * 注册监听器
     * @param listener
     *                  监听器
     * @param topics
     *                  主题（一个服务可以发布多个主题事件）
     */
    public static void registerEventListener(IM_EventListener listener, String[] topics) {
        if(null == listener || null == topics) {
            return;
        }

        synchronized (mListenerLock) {
            for(String topic : topics) {
                if(TextUtils.isEmpty(topic)) {
                    continue;
                }
                Object obj = mListenerMap.get(topic);
                if(null == obj) {
                    // 还没有监听器，直接放到Map集合
                    mListenerMap.put(topic, listener);
                }else if(obj instanceof IM_EventListener) {
                    // 有一个监听器
                    IM_EventListener oldListener = (IM_EventListener) obj;
                    if(listener == oldListener) {
                        // 去重
                        continue;
                    }
                    LinkedList<IM_EventListener> list = new LinkedList<IM_EventListener>();
                    list.add(oldListener);
                    list.add(listener);
                    mListenerMap.put(topic, list);
                }else if(obj instanceof List) {
                    // 有多个监听器
                    LinkedList<IM_EventListener> listenerList = (LinkedList<IM_EventListener>) obj;
                    if(listenerList.indexOf(listener) >= 0) {
                        // 去重
                        continue;
                    }
                    listenerList.add(listener);
                }
            }
        }
    }

    /**
     * 注销监听器
     * @param listener
     *                  监听器
     * @param topic
     *                  注销对该主题的监听
     */
    public static void unregisterEventListener(IM_EventListener listener, String topic) {
        unregisterEventListener(listener, new String[]{topic});
    }

    /**
     * 注销监听器
     * @param listener
     *                  监听器
     * @param topics
     *                  注销对该主题（一个服务可以发布多个主题事件）的监听
     */
    public static void unregisterEventListener(IM_EventListener listener, String[] topics) {
        if(null == listener || null == topics) {
            return;
        }
        synchronized (mListenerLock) {
            for(String topic : topics) {
                if(TextUtils.isEmpty(topic)) {
                    continue;
                }
                Object obj = mListenerMap.get(topic);
                if(null == obj) {
                    continue;
                }else if(obj instanceof IM_EventListener) {
                    // 有一个监听器
                    if(obj == listener) {
                        mListenerMap.remove(topic);
                    }
                }else if(obj instanceof List) {
                    // 有多个监听器
                    LinkedList<IM_EventListener> list = (LinkedList<IM_EventListener>) obj;
                    list.remove(listener);
                }
            }
        }
    }

    /**
     * 同步分发事件
     *
     * @param topic
     *              主题
     * @param msgCode
     *              消息类型
     * @param resultCode
     *              预留参数
     * @param obj
     *              回调返回数据
     *
     */
    public static void dispatchEvent(String topic, int msgCode, int resultCode, Object obj) {
        if(!TextUtils.isEmpty(topic)) {
            CEvent event = mPool.get();
            event.topic = topic;
            event.msgCode = msgCode;
            event.resultCode = resultCode;
            event.obj = obj;
            dispatchEvent(event);
        }
    }

    public static void dispatchEvent(CEvent event) {
        if(mListenerMap.size() == 0) {// 没有监听器，直接跳出代码，无需执行以下代码
            return;
        }

        if(null != event && !TextUtils.isEmpty(event.topic)) {
            String topic = event.topic;
            // 通知事件监听器处理事件
            IM_EventListener listener = null;
            LinkedList<IM_EventListener> listenerList = null;

            synchronized (mListenerLock) {
                Log.v(TAG, "dispatchEvent | topic = " + topic + " msgCode = " + event.msgCode);
                Object obj = mListenerMap.get(topic);
                if(null != obj) {
                    if(obj instanceof IM_EventListener) {
                        listener = (IM_EventListener) obj;
                    }else if(obj instanceof List) {
                        listenerList = (LinkedList<IM_EventListener>) ((LinkedList<IM_EventListener>) obj).clone();
                    }
                }
            }

            if(null != listener) {
                listener.onEvent(topic, event.msgCode, event.resultCode, event.obj);
            }else if(null != listenerList) {
                for(IM_EventListener l : listenerList) {
                    l.onEvent(topic, event.msgCode, event.resultCode, event.obj);
                }
            }

            mPool.returnObj(event);
        }
    }
}
