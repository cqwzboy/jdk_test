package com.code.fuqinqin.jdk.thread.reentrantlock;

import org.junit.Test;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>ReentrantLock测试</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/24 11:24
 */
public class ReentrantLockTest {

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Test
    public void test() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int threadCount = 10000000;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            executorService.execute(() -> {
                count();
//                countByLock();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        stopWatch.stop();
        System.out.println("[主线程] count = " + count + ", 耗时：" + stopWatch.getTotalTimeMillis());
    }

    private ReentrantLock lock = new ReentrantLock();
    private int count = 0;
    private void countByLock() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
    private void count() {
        count++;
    }

}
