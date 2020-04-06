package com.code.fuqinqin.jodd.cache.mock;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class TimedCache<K, V> extends AbstractCacheMap<K, V> {

    public TimedCache(final long timeout) {
        this.cacheSize = 0;
        this.timeout = timeout;
        cacheMap = new HashMap<>();
    }

    @Override
    protected int pruneCache() {
        int count = 0;
        Iterator<CacheObject<K,V>> values = cacheMap.values().iterator();
        while (values.hasNext()) {
            CacheObject co = values.next();
            if (co.isExpired()) {
                values.remove();
                count++;
            }
        }
        return count;
    }

    protected Timer pruneTimer;
    public void schedulePrune(final long delay) {// 1.
        if (pruneTimer != null) {
            pruneTimer.cancel();
        }
        pruneTimer = new Timer();
        pruneTimer.schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        prune();
                    }
                }, delay, delay
        );
    }
    public void cancelPruneSchedule() {// 2.
        if (pruneTimer != null) {
            pruneTimer.cancel();
            pruneTimer = null;
        }
    }

}