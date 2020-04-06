package com.code.fuqinqin.jodd.cache;

import jodd.cache.LRUCache;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * LRU缓存测试
 * @author fuqinqin3
 * @date 2019-09-17
 * */
public class LRUCacheTest extends CacheTest {

    @Test
    public void cacheTest() throws InterruptedException {
        LRUCache cache = new LRUCache(2, 2000);     // 过期时间是毫秒
        cache.put("name", "zhangsan");
        LOGGER.info("1.name = {}", cache.get("name"));
        TimeUnit.SECONDS.sleep(1);
        LOGGER.info("2.name = {}", cache.get("name"));
        TimeUnit.SECONDS.sleep(3);
        LOGGER.info("3.name = {}", cache.get("name"));
    }

    @Test
    public void pruneCacheTest(){
        LRUCache cache = new LRUCache(3);// 创建容量为3，永不过期的缓存
        cache.put("a", "value-a");  // ①
        cache.put("b", "value-b");
        cache.put("c", "value-c");

        cache.get("a");// a命中  // ②
        cache.get("b");// b命中

        LOGGER.info("------{}------", "c被淘汰");
        cache.put("d", "value-d");// ③
        showCache(cache);// 显示缓存列表，c被淘汰

        LOGGER.info("------{}------", "a被淘汰");
        cache.put("e", "value-e");// ④
        showCache(cache);// 显示缓存列表，a被淘汰
    }

}
