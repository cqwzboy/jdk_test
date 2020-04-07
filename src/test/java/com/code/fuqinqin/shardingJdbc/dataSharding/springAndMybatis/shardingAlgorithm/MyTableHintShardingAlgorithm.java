package com.code.fuqinqin.shardingJdbc.dataSharding.springAndMybatis.shardingAlgorithm;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * 表的强制路由分片算法
 * @author fuqinqin
 * @date 2020-04-07
 * */
@Slf4j
public class MyTableHintShardingAlgorithm implements HintShardingAlgorithm<String> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<String> hintShardingValue) {
        Collection<String> values = hintShardingValue.getValues();
        Iterator<String> iterator = values.iterator();
        while (iterator.hasNext()){
            String data = iterator.next();
            String tableIndex = data.split("\\|")[1];
            Iterator<String> tableIterator = collection.iterator();
            while (tableIterator.hasNext()) {
                String orgTable = tableIterator.next();
                if(orgTable.substring(orgTable.lastIndexOf("_")+1).equals(tableIndex)){
                    log.info("#1231 table命中：{}", orgTable);
                    return Arrays.asList(orgTable);
                }
            }
        }
        log.info("#12312 table未命中");
        return collection;
    }
}
