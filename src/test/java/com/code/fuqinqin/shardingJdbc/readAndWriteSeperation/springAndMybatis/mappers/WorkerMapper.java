package com.code.fuqinqin.shardingJdbc.readAndWriteSeperation.springAndMybatis.mappers;

import com.code.fuqinqin.shardingJdbc.readAndWriteSeperation.springAndMybatis.entity.Worker;
import com.code.fuqinqin.shardingJdbc.readAndWriteSeperation.springAndMybatis.entity.WorkerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WorkerMapper {
    long countByExample(WorkerExample example);

    int deleteByExample(WorkerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Worker record);

    int insertSelective(Worker record);

    List<Worker> selectByExample(WorkerExample example);

    Worker selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Worker record, @Param("example") WorkerExample example);

    int updateByExample(@Param("record") Worker record, @Param("example") WorkerExample example);

    int updateByPrimaryKeySelective(Worker record);

    int updateByPrimaryKey(Worker record);
}