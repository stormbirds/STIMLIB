package cn.stormbirds.stimdemo.bean;

import cn.stormbirds.stimdemo.utils.StringUtil;

/**
 * Copyright (c) 小宝 @2019
 *
 * @ Package Name:    cn.stormbirds.stimlib.bean
 * @ Author：         stormbirds
 * @ Email：          xbaojun@gmail.com
 * @ Created At：     2019/5/13 16:26
 * @ Description：
 */


public class GroupMessage extends ContentMessage implements Cloneable  {
    @Override
    public int hashCode() {
        try {
            return this.msgId.hashCode();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!(obj instanceof GroupMessage)) {
            return false;
        }

        return StringUtil.equals(this.msgId, ((GroupMessage) obj).getMsgId());
    }
}
