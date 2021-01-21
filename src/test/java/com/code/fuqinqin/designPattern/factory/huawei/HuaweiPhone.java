package com.code.fuqinqin.designPattern.factory.huawei;

/**
 * <p>华为手机</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/21 9:46
 */
public class HuaweiPhone extends HuaweiProduct {
    private static final String TYPE = "PHONE";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void show() {
        System.out.println("华为手机");
    }
}
