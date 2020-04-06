package com.code.fuqinqin.jodd.cache.mock;

import jodd.cache.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.StampedLock;

public abstract class AbstractCacheMap<K,V> implements Cache<K,V> {

    class CacheObject<K2, V2> {
        CacheObject(final K2 key, final V2 object, final long ttl) {
            this.key = key;
            this.cachedObject = object;
            this.ttl = ttl;
            this.lastAccess = System.currentTimeMillis();
        }

        final K2 key;                       // 键
        final V2 cachedObject;              // 原始值
        long lastAccess;                    // 最近被访问命中时间
        long accessCount;                   // 命中次数
        long ttl;                           // 剩余过期时间

        boolean isExpired() {               // 判断元素是否已经过期
            if (ttl == 0) {
                return false;
            }
            return lastAccess + ttl < System.currentTimeMillis();
        }

        V2 getObject() {                    // 获取原始值，且命中数加1，重置最近访问时间
            lastAccess = System.currentTimeMillis();
            accessCount++;
            return cachedObject;
        }
    }

    protected Map<K, CacheObject<K, V>> cacheMap;

    // ---------------------------------------------------------------- properties

    protected int cacheSize;      // max cache size, 0 = no limit

    @Override
    public int limit() {
        return cacheSize;
    }

    protected long timeout;     // default timeout, 0 = no timeout

    @Override
    public long timeout() {
        return timeout;
    }

    protected boolean existCustomTimeout;

    protected boolean isPruneExpiredActive() {
        return (timeout != 0) || existCustomTimeout;
    }


    // ---------------------------------------------------------------- put

    @Override
    public void put(final K key, final V object) {
        put(key, object, timeout);
    }


    private final StampedLock lock = new StampedLock();// 1.
    @Override
    public void put(final K key, final V object, final long timeout) {
        Objects.requireNonNull(object);

        final long stamp = lock.writeLock();// 2.

        try {
            CacheObject<K, V> co = new CacheObject<>(key, object, timeout);// 3.
            if (timeout != 0) {
                existCustomTimeout = true;// 4.
            }
            if (isReallyFull(key)) {// 5.
                pruneCache();// 6.
            }
            cacheMap.put(key, co);// 7.
        } finally {
            lock.unlockWrite(stamp);
        }
    }


    // ---------------------------------------------------------------- get



    public int getHitCount() {
        return hitCount;
    }

    public int getMissCount() {
        return missCount;
    }

    protected int hitCount;                                                 // 1.
    protected int missCount;
    @Override
    public V get(final K key) {
        long stamp = lock.readLock();
        try {
            CacheObject<K, V> co = cacheMap.get(key);
            if (co == null) {
                missCount++;
                return null;
            }
            if (co.isExpired()) {                                         // 2.
                final long newStamp = lock.tryConvertToWriteLock(stamp);
                if (newStamp != 0L) {
                    stamp = newStamp;
                    // lock is upgraded to write lock
                } else {
                    // manually upgrade lock to write lock
                    lock.unlockRead(stamp);
                    stamp = lock.writeLock();
                }
                CacheObject<K, V> removedCo = cacheMap.remove(key);     // 3.
                if (removedCo != null) {
                    onRemove(removedCo.key, removedCo.cachedObject);    // 4.
                }
                missCount++;
                return null;
            }

            hitCount++;
            return co.getObject();
        } finally {
            lock.unlock(stamp);
        }
    }

    // ---------------------------------------------------------------- prune

    protected abstract int pruneCache();

    @Override
    public final int prune() {
        final long stamp = lock.writeLock();
        try {
            return pruneCache();
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    // ---------------------------------------------------------------- common

    @Override
    public boolean isFull() {
        if (cacheSize == 0) {
            return false;
        }
        return cacheMap.size() >= cacheSize;
    }

    protected boolean isReallyFull(final K key) {
        if (cacheSize == 0) {
            return false;
        }
        if (cacheMap.size() >= cacheSize) {
            return !cacheMap.containsKey(key);
        } else {
            return false;
        }
    }

    @Override
    public V remove(final K key) {
        V removedValue = null;
        final long stamp = lock.writeLock();
        try {
            CacheObject<K, V> co = cacheMap.remove(key);

            if (co != null) {
                onRemove(co.key, co.cachedObject);
                removedValue = co.cachedObject;
            }
        } finally {
            lock.unlockWrite(stamp);
        }
        return removedValue;
    }

    @Override
    public void clear() {
        final long stamp = lock.writeLock();
        try {
            cacheMap.clear();
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    @Override
    public int size() {
        return cacheMap.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Map<K, V> snapshot() {
        final long stamp = lock.writeLock();
        try {
            Map<K, V> map = new HashMap<>(cacheMap.size());
            cacheMap.forEach((key, cacheValue) -> map.put(key, cacheValue.getObject()));
            return map;
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    // ---------------------------------------------------------------- protected

    protected void onRemove(final K key, final V cachedObject) {
    }
}
