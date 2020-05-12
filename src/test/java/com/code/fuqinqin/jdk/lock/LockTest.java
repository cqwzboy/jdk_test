package com.code.fuqinqin.jdk.lock;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fuqinqin3
 * @date 2020-05-11
 * */
public class LockTest {
    private volatile int state = 0;

    @Test
    public void reentrantLockTest() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock(false);
        Condition condition = lock.newCondition();
        new Thread(() -> {
              try{
                  if(state == 0){
                      lock.lock();
                      try {
                          System.out.println("线程 ["+Thread.currentThread().getName()+"] 即将加锁");
                          condition.await();
                      } catch (InterruptedException e) {
                          e.printStackTrace();
                      }
                      System.out.println("线程 ["+Thread.currentThread().getName()+"] 已经解锁");
                  }
              }finally {
                  lock.unlock();
              }
        }).start();

        Thread.sleep(2000);

        lock.lock();
        try{
            condition.signalAll();
            System.out.println("主线程唤醒其他线程...");
        }finally {
            lock.unlock();
        }
    }

    @Test
    public void myselfMutexTest() throws InterruptedException {
        MyselfMutex mutex = new MyselfMutex();
        Condition condition = mutex.newCondition();
        new Thread(() -> {
            try{
                if(state == 0){
                    mutex.lock();
                    try {
                        System.out.println("[MyselfMutex] 线程 ["+Thread.currentThread().getName()+"] 即将加锁");
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("[MyselfMutex] 线程 ["+Thread.currentThread().getName()+"] 已经解锁");
                }
            }finally {
                mutex.unlock();
            }
        }).start();

        Thread.sleep(2000);

        mutex.lock();
        try{
            condition.signalAll();
            System.out.println("[MyselfMutex] 主线程唤醒其他线程...");
        }finally {
            mutex.unlock();
        }
    }
}
