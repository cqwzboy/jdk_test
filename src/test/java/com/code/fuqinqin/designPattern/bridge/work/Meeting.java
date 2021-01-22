package com.code.fuqinqin.designPattern.bridge.work;

/**
 * <p>会议工作</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/22 10:02
 */
public class Meeting implements IWork {
    @Override
    public String name() {
        return "meeting";
    }

    @Override
    public void process() {
        System.out.println("== 会议工作 ==");
    }
}
