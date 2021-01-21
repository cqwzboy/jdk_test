package com.code.fuqinqin.designPattern.factory.xiaomi;

/**
 * <p>小米音箱</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/21 9:56
 */
public class XiaomiSpeaker extends XiaomiProduct {
    private static final String TYPE = "SPEAKER";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void show() {
        System.out.println("小米音箱");
    }
}
