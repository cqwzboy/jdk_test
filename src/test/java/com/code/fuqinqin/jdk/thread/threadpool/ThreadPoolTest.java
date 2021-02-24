package com.code.fuqinqin.jdk.thread.threadpool;

import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>线程池测试类</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/24 10:40
 */
public class ThreadPoolTest {

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
            5,
            30,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(10),
            new NameThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    @Test
    public void test() {
        for (int i = 0; i < 20; i++) {
            final int tmp = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " ---- " + tmp);
            });
        }
        System.out.println("主线程执行完毕。。。");
    }

    private static class NameThreadFactory implements ThreadFactory {
        private AtomicInteger no = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("ThreadPoolTest[" + no.getAndIncrement() + "]");
            thread.setDaemon(false);
            return thread;
        }
    }

}
