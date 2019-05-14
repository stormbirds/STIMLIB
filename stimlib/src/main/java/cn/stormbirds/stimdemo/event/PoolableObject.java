package cn.stormbirds.stimdemo.event;

/**
  * Copyright (c) 小宝 @ 2019
  *
  * @ Package Name:    cn.stormbirds.stimlib.event
  * @ Author：         stormbirds
  * @ Email：          xbaojun@gmail.com
  * @ Created At：     2019/5/13 11:21
  * @ Description：    对象池中的对象要求实现PoolableObject接口
  *
  */
public interface PoolableObject {

    /**
     * 恢复到默认状态
     */
    void reset();
}
