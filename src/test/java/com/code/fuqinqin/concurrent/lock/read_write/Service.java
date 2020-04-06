package com.code.fuqinqin.concurrent.lock.read_write;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Service {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock;

    public void read(){
        readLock = lock.readLock();
        readLock.lock();
        try {
            System.out.println("读取 当前线程："+Thread.currentThread().getName()+", 当前时间："+System.currentTimeMillis());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }

    private ReentrantReadWriteLock.WriteLock writeLock;

    public void write(){
        writeLock = lock.writeLock();
        writeLock.lock();
        try {
            System.out.println("写入 当前线程："+Thread.currentThread().getName()+", 当前时间："+System.currentTimeMillis());
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }
}
