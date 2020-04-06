package com.code.fuqinqin.jodd.cache.mock;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends AbstractCacheMap<K, V> {

    public LRUCache(final int cacheSize) {
        this(cacheSize, 0);
    }
    public LRUCache(final int cacheSize, final long timeout) {
        this.cacheSize = cacheSize;
        this.timeout = timeout;
        cacheMap = new LinkedHashMap<K, CacheObject<K,V>>(cacheSize + 1,
                1.0f,
                true) {// 1.
            @Override
            protected boolean removeEldestEntry(final Map.Entry eldest) {// 2.
                return LRUCache.this.removeEldestEntry(size());
            }
        };
    }

    /**
     * 当缓存当前数量大于cacheSize时，触发清理队首（最久）元素
     */
    protected boolean removeEldestEntry(final int currentSize) {
        if (cacheSize == 0) {
            return false;
        }
        return currentSize > cacheSize;
    }

    // ---------------------------------------------------------------- prune

    /**
     * Prune only expired objects, <code>LinkedHashMap</code> will take care of LRU if needed.
     */
    @Override
    protected int pruneCache() {
        if (!isPruneExpiredActive()) {
            return 0;
        }
        int count = 0;
        Iterator<CacheObject<K,V>> values = cacheMap.values().iterator();
        while (values.hasNext()) {
            CacheObject<K,V> co = values.next();
            if (co.isExpired()) {
                values.remove();
                onRemove(co.key, co.cachedObject);
                count++;
            }
        }
        return count;
    }
}
