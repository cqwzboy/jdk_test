package com.code.fuqinqin.jodd.cache;

import jodd.cache.FIFOCache;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * FIFO缓存测试
 * @author fuqinqin3
 * @date 2019-09-17
 * */
public class FIFOCacheTest extends CacheTest {

    @Test
    public void cacheTest() throws InterruptedException {
        // 设置容量为1，默认过期时长为2秒的FIFOCache
        FIFOCache<String, String> cache = new FIFOCache<>(1, 2000);//①

        cache.put("name", "zhangsan");// 默认2秒过期时间
        LOGGER.info("1. name = {}", cache.get("name"));// 未过期
        TimeUnit.SECONDS.sleep(1);//②
        LOGGER.info("2. name = {}", cache.get("name"));// 未过期
        TimeUnit.SECONDS.sleep(3);//③
        LOGGER.info("3. name = {}", cache.get("name"));// 已过期

        cache.put("age", "3", 5000);//④ 指定过期时间5秒
        LOGGER.info("4. age = {}", cache.get("age"));// 未过期
        TimeUnit.SECONDS.sleep(4);//⑤
        LOGGER.info("5. age = {}", cache.get("age"));// 未过期
        TimeUnit.SECONDS.sleep(6);//⑥
        LOGGER.info("6. age = {}", cache.get("age"));// 已过期
    }

    @Test
    public void pruneCacheTest(){
        FIFOCache<String, String> cache = new FIFOCache<>(1);// ①

        cache.put("name", "zhangsan");// ②
        LOGGER.info("1. name = {}", cache.get("name"));

        cache.put("age", "3");// ③
        LOGGER.info("2. name = {}", cache.get("name"));// ④
        LOGGER.info("3. age = {}", cache.get("age"));
    }
}
