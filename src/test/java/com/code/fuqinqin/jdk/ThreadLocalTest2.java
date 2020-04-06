package com.code.fuqinqin.jdk;

import org.junit.Test;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author fuqinqin
 * @date 2019-11-12
 * */
public class ThreadLocalTest2 {
    private static final ThreadLocal<String> LOCAL = new InheritableThreadLocal<>();
    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(1,
            1,
            3,
            TimeUnit.SECONDS,
            new SynchronousQueue<>(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void test(){
        EXECUTOR.execute(() -> {
            System.out.println(Thread.currentThread().getName() + " - 线程被主线程初始化");
        });

        new Thread(() -> {
            LOCAL.set("Min thread set");
            System.out.println(Thread.currentThread().getName() + " - execute..." + System.currentTimeMillis());
            EXECUTOR.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " - execute..." + System.currentTimeMillis());
                System.out.println(Thread.currentThread().getName() + " - " + System.currentTimeMillis() + "-" + LOCAL.get());
            });
            System.out.println(Thread.currentThread().getName() + " - " + System.currentTimeMillis() + "-" + LOCAL.get());
        }).start();

        try {
            synchronized (ThreadLocalTest.class){
                ThreadLocalTest.class.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void sleep(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
