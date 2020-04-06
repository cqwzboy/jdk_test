package com.code.fuqinqin.jodd.cache.mock;

import java.util.HashMap;
import java.util.Iterator;

public class LFUCache<K,V> extends AbstractCacheMap<K,V> {

    public LFUCache(final int maxSize) {
        this(maxSize, 0);
    }
    public LFUCache(final int maxSize, final long timeout) {
        this.cacheSize = maxSize;
        this.timeout = timeout;
        cacheMap = new HashMap<>(maxSize + 1);// 1.
    }

    /**
     * Prunes expired and, if cache is still full, the LFU element(s) from the cache.
     * On LFU removal, access count is normalized to value which had removed object.
     * Returns the number of removed objects.
     */
    @Override
    protected int pruneCache() {
        int count = 0;
        CacheObject<K,V> comin = null;

        // remove expired items and find cached object with minimal access count
        Iterator<CacheObject<K,V>> values = cacheMap.values().iterator();
        while (values.hasNext()) {
            CacheObject<K,V> co = values.next();
            if (co.isExpired()) {                               // 1.
                values.remove();
                onRemove(co.key, co.cachedObject);
                count++;
                continue;
            }

            if (comin == null) {                               // 2.
                comin = co;
            } else {
                if (co.accessCount < comin.accessCount) {
                    comin = co;
                }
            }
        }

        if (!isFull()) {                                      // 3.
            return count;
        }

        // decrease access count to all cached objects
        if (comin != null) {                                  // 4.
            long minAccessCount = comin.accessCount;

            values = cacheMap.values().iterator();
            while (values.hasNext()) {
                CacheObject<K, V> co = values.next();
                co.accessCount -= minAccessCount;
                if (co.accessCount <= 0) {
                    values.remove();
                    onRemove(co.key, co.cachedObject);
                    count++;
                }
            }
        }
        return count;
    }

}