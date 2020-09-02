package com.code.fuqinqin.netty.timer;

import java.util.concurrent.ThreadFactory;

/**
 * 名称线程工厂
 *
 * @author fuqinqin3
 * @date 2020-09-02
 * */
public class NameThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("netty-hashedWheelTimer");
        return thread;
    }
}
