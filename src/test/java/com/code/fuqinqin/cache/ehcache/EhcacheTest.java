package com.code.fuqinqin.cache.ehcache;

import org.badger.common.lang.util.DateUtil;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.File;
import java.util.Arrays;

/**
 * @author fuqinqin3
 * @date 2020-10-30
 * */
public class EhcacheTest {
    private CacheManager cacheManager;
    private Cache<String, Person> personCache;
    private PersistentCacheManager persistentCacheManager;
    private Cache<String, Person> personPersistenceCache;

    @Before
    public void init(){
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
        personCache = cacheManager.createCache("personCache",
                CacheConfigurationBuilder.newCacheConfigurationBuilder(String.class, Person.class, ResourcePoolsBuilder.heap(3)));

        persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(new File("F://ehcache-data")))
                .withCache("persistencePersonCache",
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                String.class,
                                Person.class,
                                ResourcePoolsBuilder.newResourcePoolsBuilder()
                                        .heap(10, EntryUnit.ENTRIES)
                                        .offheap(1, MemoryUnit.MB)
                                        .disk(20, MemoryUnit.MB, true)))
                .build(true);
        personPersistenceCache = persistentCacheManager.getCache("persistencePersonCache", String.class, Person.class);
    }

    @Test
    public void test(){
        personCache.put("张三", new Person(1, "张三", DateUtil.parse("1991-11-06 12:00:00"), Arrays.asList("篮球", "足球")));
        personCache.put("李四", new Person(2, "李四", DateUtil.parse("1992-11-06 12:00:00"), Arrays.asList("排球", "乒乓")));
        personCache.put("王五", new Person(3, "王五", DateUtil.parse("1993-11-06 12:00:00"), Arrays.asList("骑行")));
        System.out.println(personCache.get("张三"));
        personCache.put("赵六", new Person(4, "赵六", DateUtil.parse("1994-11-06 12:00:00"), Arrays.asList("棋牌", "赌博")));
        System.out.println(personCache.get("张三"));
        System.out.println(personCache.get("李四"));
        System.out.println(personCache.get("王五"));
        System.out.println(personCache.get("赵六"));
    }

    @Test
    public void diskTest(){
        personPersistenceCache.put("zhangsan", new Person(1, "张三", DateUtil.parse("1991-11-06 12:00:00"), Arrays.asList("篮球", "足球")));
    }

    @Test
    public void showDiskData(){
        System.out.println(personPersistenceCache.get("zhangsan"));
    }

    @After
    public void after(){
        cacheManager.close();
    }
}
