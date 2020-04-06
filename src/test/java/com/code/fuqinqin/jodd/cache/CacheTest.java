package com.code.fuqinqin.jodd.cache;

import com.code.fuqinqin.jodd.JoddTest;
import jodd.cache.Cache;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class CacheTest extends JoddTest {
    protected <K, V> void showCache(Cache<K, V> cache){
        showCache(cache, null);
    }

    protected <K, V> void showCache(Cache<K, V> cache, String no){
        Map<K, V> snapshot = cache.snapshot();
        if(MapUtils.isEmpty(snapshot)){
            LOGGER.info("showTime: {} ==> {}", "null", "null");
            return;
        }
        snapshot.forEach((K, V) -> {
            if(StringUtils.isBlank(no)){
                LOGGER.info("showTime: {} ==> {}", K, V);
            }else {
                LOGGER.info("showTime: [{}] {} ==> {}", no, K, V);
            }
        });
    }
}
