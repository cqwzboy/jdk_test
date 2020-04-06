package com.code.fuqinqin.concurrent.threadpoolexecutor;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadPoolExecutorManager extends PoolManagerAdapter<ThreadPoolExecutor> {
    private volatile Map<Integer, Node<ThreadPoolExecutor>> cache = new ConcurrentHashMap<>();
    private Lock mainLock;
    private Condition condition;

    private volatile int coreSize;
    private volatile int maxSize;
    private volatile long keepAliveTime;
    private TimeUnit timeUnit;
    private Callback callback;

    ThreadPoolExecutorManager(int coreSize,
                              int maxSize,
                              long keepAliveTime,
                              TimeUnit timeUnit,
                              Callback callback){
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.callback = callback;
        for (int i = 0; i < coreSize; i++) {
            ThreadPoolExecutor poolExecutor = callback.call();
            cache.put(poolExecutor.hashCode(), new Node<>(poolExecutor,
                    TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()),
                    0,
                    new AtomicBoolean(false)));
        }

        this.mainLock = new ReentrantLock();
        this.condition = mainLock.newCondition();

        startClear();
    }

    private void startClear() {
        new Timer("PoolManagerTimer", true)
                .schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(memberSize() == 0){
                            return;
                        }

                        if(memberSize() <= ThreadPoolExecutorManager.this.coreSize){// 小于核心数量则不作超时控制
                            return;
                        }

                        Node<ThreadPoolExecutor> node;
                        Map<Integer, Node<ThreadPoolExecutor>> allMembers = allMembers();
                        for (Integer hashCode : allMembers.keySet()) {
                            node = cache.get(hashCode);
                            if(node.getInUsed().get()){
                                continue;
                            }
                            if((TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()) - node.getInitTime())
                                    >= getTimeUnit().toNanos(getKeepAliveTime())){
                                if(node.getInUsed().get()){//  recheck
                                    continue;
                                }
                                if(allMembers().size() <= ThreadPoolExecutorManager.this.coreSize){
                                    continue;
                                }
                                allMembers.remove(node.getResource().hashCode());
                                System.out.println(Thread.currentThread().getName()+" clear "+node.getResource().hashCode()+" success");
                            }
                        }
                    }
                }, 1000L, 1000L);
    }

    @Override
    public int getCoreSize() {
        return this.coreSize;
    }

    @Override
    public int getMaxSize() {
        return this.maxSize;
    }

    @Override
    public long getKeepAliveTime() {
        return this.keepAliveTime;
    }

    @Override
    public TimeUnit getTimeUnit() {
        return this.timeUnit;
    }

    @Override
    public boolean hasIdle() {
        if(cache.size() == 0){
            return false;
        }

        final Lock lock = mainLock;
        lock.lock();
        try{
            if(cache.size() == 0){
                return false;
            }
            for (Integer hashCode : cache.keySet()) {
                if(!cache.get(hashCode).getInUsed().get()){
                    return true;
                }
            }
            return false;
        } finally {
          lock.unlock();
        }
    }

    @Override
    public ThreadPoolExecutor get() {
        final Lock lock = mainLock;
        lock.lock();
        try{
            if(hasIdle()){
                System.out.println(Thread.currentThread().getName()+" get success...");
                return getFree();
            }

            if(cache.size() < this.maxSize){// 未达到maxsize，扩容
                resize();
                return get();
            }

            while (!hasIdle()) {
                condition.await();
            }

            System.out.println(Thread.currentThread().getName()+" get success...");
            return getFree();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            condition.signalAll();
            lock.unlock();
        }
        return null;
    }

    @Override
    public ThreadPoolExecutor tryGet() {
        final Lock lock = mainLock;
        lock.lock();
        try{
            if(cache.size() < this.maxSize){// 未达到maxsize，扩容
                resize();
                return tryGet();
            }

            if (!hasIdle()) {
                return null;
            }

            ThreadPoolExecutor idlePoolExecutor = getFree();
            if(idlePoolExecutor == null){
                return tryGet();
            }

            return idlePoolExecutor;
        } finally {
            condition.signalAll();
            lock.unlock();
        }
    }

    @Override
    public ThreadPoolExecutor get(long timeout, TimeUnit timeUnit) throws InterruptedException {
        long nanos = timeUnit.toNanos(timeout);
        final Lock lock = mainLock;
        lock.lock();
        try{
            if(hasIdle()){
                return getFree();
            }

            for (;;) {
                if(cache.size() < this.maxSize){// 未达到maxsize，扩容
                    resize();
                    return get(timeout, timeUnit);
                }

                if (hasIdle()) {
                    return getFree();
                }

                if (nanos <= 0) {
                    // 已超时
                    return null;
                }

                try {
                    nanos = condition.awaitNanos(nanos);
                } catch (InterruptedException ie) {
                    condition.signalAll();
                    throw ie;
                }

            }
        } finally {
            condition.signalAll();
            lock.unlock();
        }
    }

    @Override
    public boolean release(ThreadPoolExecutor threadPoolExecutor) {
        if(threadPoolExecutor == null){
            return true;
        }

        final Lock lock = mainLock;
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getName()+" release +++++++++");
            int hashCode = threadPoolExecutor.hashCode();
            if(cache.containsKey(hashCode)){
                for (Integer hash : cache.keySet()) {
                    if(hashCode == hash){
                        cache.get(hash).getInUsed().set(false);
                        cache.get(hash).setInitTime(TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()));
                        return true;
                    }
                }
            }
            return false;
        } finally {
            condition.signalAll();
            lock.unlock();
        }
    }

    public Map<Integer, Node<ThreadPoolExecutor>> allMembers() {
        return cache;
    }

    /**
     * 扩容
     * */
    private void resize(){
        System.out.println(Thread.currentThread().getName()+" resize......");
        ThreadPoolExecutor poolExecutor = callback.call();
        cache.put(poolExecutor.hashCode(), new Node<>(poolExecutor,
                TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()),
                0,
                new AtomicBoolean(false)));
    }

    /**
     * 获取一个未被占用的线程池
     * */
    private ThreadPoolExecutor getFree(){
        Node<ThreadPoolExecutor> node;
        for (Integer hashCode : cache.keySet()) {
            node = cache.get(hashCode);
            if(!node.getInUsed().get()){
                node.getInUsed().set(true);
                return node.getResource();
            }
        }
        return null;
    }

    private int memberSize(){
        return allMembers().size();
    }

    public interface Callback {
        ThreadPoolExecutor call();
    }
}
