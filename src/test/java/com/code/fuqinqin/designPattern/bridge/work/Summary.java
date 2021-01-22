package com.code.fuqinqin.designPattern.bridge.work;

/**
 * <p>总结汇报</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/22 10:04
 */
public class Summary implements IWork {
    @Override
    public String name() {
        return "summary";
    }

    @Override
    public void process() {
        System.out.println("== 总结汇报 ==");
    }
}
