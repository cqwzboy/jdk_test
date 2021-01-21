package com.code.fuqinqin.designPattern.builder.xiaomi;

import com.code.fuqinqin.designPattern.builder.IPhone;

/**
 * <p>小米手机</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/21 10:20
 */
public class XiaomiPhone implements IPhone {
    @Override
    public String getName() {
        return "我是小米手机";
    }
}
