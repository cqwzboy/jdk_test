package com.code.fuqinqin.concurrent.futrue_callable;

import java.util.concurrent.*;

/**
 * Future和Callable应用
 *
 * @author fuqinqin
 * */
public class FutrueCallableDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(2000);
                System.out.println("子线程业务处理完毕。。。");
                return 1 + 2;
            }
        });
        service.shutdown();

        System.out.println("执行主线程业务。。。");
        Thread.sleep(1000);

        /*System.out.println("（前）是否已经取消："+future.isCancelled());
        System.out.println("[取消] "+future.cancel(false));
        System.out.println("（后）是否已经取消："+future.isCancelled());
        System.out.println("子线程是否执行完毕："+future.isDone());*/

        Integer result = future.get();
        System.out.println("子线程执行结果："+result);
    }

}
