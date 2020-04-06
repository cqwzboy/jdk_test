package com.code.fuqinqin.concurrent.threadpoolexecutor;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Testor {
    @Test
    public void test(){
        ThreadPoolExecutorManager.Callback callback = new ThreadPoolExecutorManager.Callback() {
            @Override
            public ThreadPoolExecutor call() {
                return new ThreadPoolExecutor(1, 10, 3, TimeUnit.SECONDS,
                        new LinkedBlockingDeque<>());
            }
        };

        final ThreadPoolExecutorManager manager = new ThreadPoolExecutorManager(3, 10, 2, TimeUnit.SECONDS,
                callback);

        int count = 100;
        final AtomicInteger num = new AtomicInteger(0);
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            final int finalI = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    ThreadPoolExecutor threadPoolExecutor = manager.get();
                    if(threadPoolExecutor == null){
                        num.incrementAndGet();
                        System.out.println("====================== "+finalI+" null =====================");
                    }
                    if(finalI ==88){
                        sleep(5000);
                        System.out.println("-------------------------- 1 ----------------------------");
                        Map<Integer, PoolManager.Node<ThreadPoolExecutor>> cacheMap = manager.allMembers();
                        for (Integer hashCode : cacheMap.keySet()) {
                            PoolManager.Node<ThreadPoolExecutor> node = cacheMap.get(hashCode);
                            System.out.println(hashCode + " = {inUsed="+node.getInUsed().get()+"}");
                        }
                    }else{
                        sleep(10);
                    }
                    System.out.println("release "+manager.release(threadPoolExecutor));
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("-------------------------- 2 ----------------------------");
        Map<Integer, PoolManager.Node<ThreadPoolExecutor>> cacheMap = manager.allMembers();
        for (Integer hashCode : cacheMap.keySet()) {
            PoolManager.Node<ThreadPoolExecutor> node = cacheMap.get(hashCode);
            System.out.println(hashCode + " = {inUsed="+node.getInUsed().get()+"}");
        }

        System.out.println("为null的数量为："+num.get());

        while (true) {
            sleep(100);
        }

//        System.out.println("-- main down --");
    }

    private void sleep(long timeout){
        try {
               Thread.sleep(timeout);
        } catch (InterruptedException e) {
               e.printStackTrace();
        }
    }
}
