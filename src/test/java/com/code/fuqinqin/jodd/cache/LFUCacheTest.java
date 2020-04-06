package com.code.fuqinqin.jodd.cache;

import jodd.cache.LFUCache;
import org.junit.Test;

/**
 * JoddCache之LFU策略缓存
 * @author fuqinqin
 * @date 2019-09-18
 * */
public class LFUCacheTest extends CacheTest {

    @Test
    public void pruneCacheTest(){
        LFUCache<String, String> cache = new LFUCache<>(3);// ①

        cache.put("a", "value-a");// ②
        cache.put("b", "value-b");
        cache.put("c", "value-c");

        cache.get("a");// ③
        cache.get("a");// 命中a两次
        cache.get("c");// 命中c一次

        cache.put("d", "value-d");// ④
        showCache(cache);
    }

}
