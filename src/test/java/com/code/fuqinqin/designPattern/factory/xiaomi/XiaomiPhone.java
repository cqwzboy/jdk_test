package com.code.fuqinqin.designPattern.factory.xiaomi;

/**
 * <p>小米手机</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/21 9:55
 */
public class XiaomiPhone extends XiaomiProduct {
    private static final String TYPE = "PHONE";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void show() {
        System.out.println("小米手机");
    }
}
