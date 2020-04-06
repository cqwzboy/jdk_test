package com.code.fuqinqin.shardingJdbc.readAndWriteSeperation;

import com.code.fuqinqin.shardingJdbc.readAndWriteSeperation.springAndMybatis.entity.Worker;
import com.code.fuqinqin.shardingJdbc.readAndWriteSeperation.springAndMybatis.mappers.WorkerMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 主从读写分离
 * @author fuqinqin
 * @date 2020-04-06
 * */
public class ReadWriteSeperatorTest {
    private ApplicationContext ctx;
    private WorkerMapper workerMapper;

    @Before
    public void init(){
        this.ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.workerMapper = this.workerMapper = ctx.getBean(WorkerMapper.class);
    }

    @Test
    public void test(){
//        insert();
        select();
    }

    /**
     * 新增
     * */
    private void insert(){
        Worker worker = new Worker();
        worker.setName("zhangsan");
        worker.setMoney(10000313L);
        int rownum = workerMapper.insert(worker);
        System.out.println("插入行数："+rownum);
    }

    /**
     * 查询
     * */
    private void select(){
        Long id = 3L;
        try{
            // 强制主库路由
//            HintManager.getInstance().setMasterRouteOnly();
            Worker worker = workerMapper.selectByPrimaryKey(id);
            System.out.println("从库查询结果："+worker.getId()+" - "+worker.getName()+" - "+worker.getMoney());
        }finally {
            // 清除缓存
//            HintManager.getInstance().close();
        }
    }
}
