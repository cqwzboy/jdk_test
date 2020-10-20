package com.code.fuqinqin.jdk.thread.futrue;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * Futrue/Callable 测试类
 *
 * @author fuqinqin3
 * @date 2020-0-10-20
 * */
public class FutureTest {
    ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
            1,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    @Test
    public void test() throws InterruptedException, ExecutionException {
        long startTime = System.currentTimeMillis();
        Callable<String> callable = () -> {
            System.out.println("异步执行 begin....");
            Thread.sleep(5000);
            System.out.println("异步执行 end....");
            return "Hello";
        };
        FutureTask<String> futureTask = new FutureTask<>(callable);
        executor.execute(futureTask);
        System.out.println("主线程执行 begin...");
        Thread.sleep(2000);
        System.out.println("主线程执行 end...");
        if(!futureTask.isDone()){
            System.out.println("异步任务暂未执行完成");
        }
        String result = futureTask.get();
        System.out.println(result + " World, 耗时=" + (System.currentTimeMillis() - startTime));
    }

}
