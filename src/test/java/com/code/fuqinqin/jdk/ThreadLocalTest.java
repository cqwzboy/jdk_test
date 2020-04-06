package com.code.fuqinqin.jdk;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author fuqinqin
 * @date 2019-11-12
 * */
public class ThreadLocalTest {
    private static final ThreadLocal<String> LOCAL = new InheritableThreadLocal<>();
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(1,
            1,
            3,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(8),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void test(){
        LOCAL.set("Min thread set");

        for (int i = 0; i < 2; i++) {// 第二个线程排队
            final int index = i;
            EXECUTOR.execute(() -> {
                System.out.println(Thread.currentThread().getName() + index + " - execute..." + System.currentTimeMillis());
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + index + " - " + System.currentTimeMillis() + "-" + LOCAL.get());
            });
        }

        try {
            synchronized (ThreadLocalTest.class){
                ThreadLocalTest.class.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
