package com.code.fuqinqin.concurrent.threadpoolexecutor;

import java.util.concurrent.TimeUnit;

public abstract class PoolManagerAdapter<T> implements PoolManager<T> {

    @Override
    public void setCoreSize(int coreSize) {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public int getCoreSize() {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public void setMaxSize(int maxSize) {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public int getMaxSize() {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public void setKeepAliveTime(long keepAliveTime) {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public long getKeepAliveTime() {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public void setTimeUnit(TimeUnit timeUnit) {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public TimeUnit getTimeUnit() {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public boolean hasIdle() {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public T get() {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public T tryGet() {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public T get(long timeout, TimeUnit timeUnit) throws InterruptedException {
        throw new UnsupportedOperationException("unsupported operation");
    }

    @Override
    public boolean release(T t) {
        throw new UnsupportedOperationException("unsupported operation");
    }
}
