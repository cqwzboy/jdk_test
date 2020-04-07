package com.code.fuqinqin.shardingJdbc.dataSharding.springAndMybatis.shardingAlgorithm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * 数据库强行路由分片算法
 * @author fuqinqin
 * @date 2020-04-07
 * */
@Slf4j
public class MyDatabaseHintShardingAlgorithm implements HintShardingAlgorithm<String> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<String> hintShardingValue) {
        Collection<String> values = hintShardingValue.getValues();
        Iterator<String> iterator = values.iterator();
        while (iterator.hasNext()){
            String data = iterator.next();
            String databaseIndex = data.split("\\|")[0];
            Iterator<String> databaseIterator = collection.iterator();
            while (databaseIterator.hasNext()) {
                String orgDatabase = databaseIterator.next();
                if(orgDatabase.substring(orgDatabase.lastIndexOf("_")+1).equals(databaseIndex)){
                    log.info("#885 database命中：{}", orgDatabase);
                    return Arrays.asList(orgDatabase);
                }
            }
        }
        log.info("#8823 database未命中");
        return null;
    }
}
