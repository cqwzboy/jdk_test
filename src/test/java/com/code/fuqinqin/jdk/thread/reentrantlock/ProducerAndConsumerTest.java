package com.code.fuqinqin.jdk.thread.reentrantlock;

import lombok.SneakyThrows;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>基于ReentrantLock的生产/消费模型</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/25 10:07
 */
public class ProducerAndConsumerTest {

    private volatile List<Integer> list = new ArrayList<>();
    private static final Integer COUNT = 10;

    private ReentrantLock lock = new ReentrantLock();
    private Condition emptyCondition = lock.newCondition();
    private Condition fullCondition = lock.newCondition();

    private static final AtomicInteger PRODUCER_AWAIT_COUNT = new AtomicInteger();
    private static final AtomicInteger CONSUMER_COUNT = new AtomicInteger();

    @Test
    public void test() throws InterruptedException {
        DataProducer dataProducer = new DataProducer();
        DataConsumer dataConsumer = new DataConsumer();

        new Thread(dataProducer).start();
        new Thread(dataProducer).start();
        new Thread(dataProducer).start();
        new Thread(dataProducer).start();

        new Thread(dataConsumer).start();
        new Thread(dataConsumer).start();

        while (true) {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("PRODUCER_AWAIT_COUNT = " + PRODUCER_AWAIT_COUNT.get() + ", CONSUMER_COUNT = " + CONSUMER_COUNT.get() + ", CONSUMER_RUN_COUNT = " + CONSUMER_RUN_COUNT.get());
            System.out.println("list.size() = " + list.size());
        }
    }

    /**
     * 数据生产者
     *
     * @author fuqinqin3
     */
    class DataProducer implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            String track;
            for (int i = 0; i < 10; i++) {
                track = UUID.randomUUID().toString().replaceAll("-", "");
                lock.lockInterruptibly();
                try {
                    if (list.size() == COUNT) {
                        System.out.println("Thread[" + Thread.currentThread().getName() + "] list满了, track = " + track);
                        emptyCondition.signalAll();
                        PRODUCER_AWAIT_COUNT.getAndIncrement();
                        fullCondition.await();
                    }
                    if (list.size() < COUNT) {
                        list.add(Random.random());
                        emptyCondition.signalAll();
                        System.out.println("Thread[" + Thread.currentThread().getName() + "] 生产数据, track = " + track);
                    }
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    /**
     * 数据消费者
     *
     * @author fuqinqin3
     */
    private static final AtomicInteger CONSUMER_RUN_COUNT = new AtomicInteger();

    class DataConsumer implements Runnable {
        @SneakyThrows
        @Override
        public void run() {
            lock.lockInterruptibly();
            CONSUMER_RUN_COUNT.getAndIncrement();
            try {
                if (list.size() == 0) {
                    fullCondition.signalAll();
                    emptyCondition.await();
                }
                if (list.size() > 0) {
                    list.remove(0);
                    System.out.println("Thread[" + Thread.currentThread().getName() + "] 消费一次");
                    CONSUMER_COUNT.getAndIncrement();
                    fullCondition.signalAll();
                } else {
                    System.out.println("=============================================");
                }
            } finally {
                emptyCondition.signalAll();
                lock.unlock();
                run();
            }
        }
    }

    static class Random {
        public static int random() {
            return ((int) (Math.random() * 10)) + 1;
        }
    }

}
