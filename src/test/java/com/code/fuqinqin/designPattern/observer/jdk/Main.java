package com.code.fuqinqin.designPattern.observer.jdk;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/1/13 16:58
 */
public class Main {
    public static void main(String[] args) {
//        new Main().singleThread();
        new Main().multiThread();
    }

    /**
     * 单线程
     */
    private void singleThread() {
        TestObservable observable = new TestObservable();

        AObserver aObserver = new AObserver();
        BObserver bObserver = new BObserver();
        CObserver cObserver = new CObserver();

        observable.addObserver(aObserver);
        observable.addObserver(bObserver);
        observable.addObserver(cObserver);

        observable.setState(2);
    }

    /**
     * 多线程
     *
     * JDK自带的观察者模式是线程不安全的，需要在setChanged的方法上加对象级同步锁
     * */
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100));
    private void multiThread(){
        TestObservable observable = new TestObservable();

        AObserver aObserver = new AObserver();
        BObserver bObserver = new BObserver();
        CObserver cObserver = new CObserver();

        observable.addObserver(aObserver);
        observable.addObserver(bObserver);
        observable.addObserver(cObserver);

        for (int i = 0; i < 10; i++) {
            final int index = i;
            executor.execute(() -> observable.setState(index));
        }
    }
}
