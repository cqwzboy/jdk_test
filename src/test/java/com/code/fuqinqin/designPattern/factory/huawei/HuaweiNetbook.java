package com.code.fuqinqin.designPattern.factory.huawei;

/**
 * <p>华为电脑</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/21 9:47
 */
public class HuaweiNetbook extends HuaweiProduct {
    private static final String TYPE = "NETBOOK";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void show() {
        System.out.println("华为笔记本");
    }
}
