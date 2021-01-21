package com.code.fuqinqin.designPattern.builder;

/**
 * <p>手机生产流程规范</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/21 10:11
 */
public interface PhoneBuilder {
    /**
     * 市场分析
     * */
    void marketAnalysis();
    /**
     * 市场调研
     * */
    void marketResearch();
    /**
     * 工业设计
     * */
    void design();
    /**
     * 硬件生产
     * */
    void hardwareProduct();
    /**
     * 软件开发
     * */
    void softwareDevelop();
    /**
     * 产品测试
     * */
    void productTest();
    /**
     * 上市
     * */
    IPhone listed();
}
