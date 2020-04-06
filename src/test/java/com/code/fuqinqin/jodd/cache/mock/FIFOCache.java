package com.code.fuqinqin.jodd.cache.mock;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class FIFOCache<K, V> extends AbstractCacheMap<K, V> {

    public FIFOCache(final int cacheSize) {
        this(cacheSize, 0);
    }
    public FIFOCache(final int cacheSize, final long timeout) {
        this.cacheSize = cacheSize;
        this.timeout = timeout;
        cacheMap = new LinkedHashMap<>(cacheSize + 1,       // 1.
                1.0f,                                          // 2.
                false);                                      // 3.
    }


    /**
     * 首先淘汰已过期的缓存，如果还是满载，再淘汰队首元素
     * */
    @Override
    protected int pruneCache() {
        int count = 0;
        CacheObject<K,V> first = null;
        Iterator<CacheObject<K,V>> values = cacheMap.values().iterator();
        while (values.hasNext()) {
            CacheObject<K,V> co = values.next();
            if (co.isExpired()) {                           // 1.
                values.remove();
                onRemove(co.key, co.cachedObject);
                count++;
            }
            if (first == null) {                            // 2.
                first = co;
            }
        }
        if (isFull()) {
            if (first != null) {                            // 3.
                cacheMap.remove(first.key);
                onRemove(first.key, first.cachedObject);
                count++;
            }
        }
        return count;
    }
}
