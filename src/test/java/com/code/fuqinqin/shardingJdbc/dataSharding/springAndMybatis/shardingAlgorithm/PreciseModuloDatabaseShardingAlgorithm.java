package com.code.fuqinqin.shardingJdbc.dataSharding.springAndMybatis.shardingAlgorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * 数据库分片算法
 * @author fuqinqin
 * @date 2020-04-06
 * */
public class PreciseModuloDatabaseShardingAlgorithm implements PreciseShardingAlgorithm<String> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreciseModuloDatabaseShardingAlgorithm.class);

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        collection.forEach(col -> LOGGER.info("#77235 col = {}", col));
        String columnName = preciseShardingValue.getColumnName();
        String logicTableName = preciseShardingValue.getLogicTableName();
        String value = preciseShardingValue.getValue();
        LOGGER.info("#66235 columnName={}, logicTableName={}, value={}", columnName, logicTableName, value);
        return null;
    }
}
