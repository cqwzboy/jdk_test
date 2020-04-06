package com.code.fuqinqin.concurrent.threadpoolexecutor;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public interface PoolManager<T> {
    void setCoreSize(int coreSize);
    int getCoreSize();
    void setMaxSize(int maxSize);
    int getMaxSize();
    void setKeepAliveTime(long keepAliveTime);
    long getKeepAliveTime();
    void setTimeUnit(TimeUnit timeUnit);
    TimeUnit getTimeUnit();

    boolean hasIdle();

    T get();
    T tryGet();
    T get(long timeout, TimeUnit timeUnit) throws InterruptedException;

    boolean release(T t);

    @Data
    @AllArgsConstructor
    class Node<T> {
        private T resource;
        private long initTime;      // nano time
        private int referTimes;
        private volatile AtomicBoolean inUsed = new AtomicBoolean(false);
    }
}
