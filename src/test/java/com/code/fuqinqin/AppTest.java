package com.code.fuqinqin;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,
            9,
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(991),
            new ThreadPoolExecutor.AbortPolicy());

    @Test
    public void test() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    System.out.println(Thread.currentThread().getName());
                    System.out.println(Thread.currentThread().getThreadGroup().activeCount());
                }
            });
        }

        while (true){
            /*System.out.println("executor.getActiveCount() = " + executor.getActiveCount());
            System.out.println("executor.getCompletedTaskCount() = " + executor.getCompletedTaskCount());
            System.out.println("executor.getCorePoolSize() = " + executor.getCorePoolSize());
            System.out.println("executor.getLargestPoolSize() = " + executor.getLargestPoolSize());
            System.out.println("executor.getMaximumPoolSize() = " + executor.getMaximumPoolSize());
            System.out.println("executor.getPoolSize() = " + executor.getPoolSize());
            System.out.println("executor.getTaskCount() = " + executor.getTaskCount());
            System.out.println("-----------------------------------------------------");*/
            Thread.sleep(3000);
        }
    }
}
