package com.code.fuqinqin.mybatis;

import com.code.fuqinqin.mybatis.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * 启动器
 *
 * @author fuqinqin
 * @date 2020-04-09
 * */
@Slf4j
public class TestRunner {
    @Test
    public void test(){
        String path = "mybatis/SqlMapConfig.xml";
        InputStream inputStream = TestRunner.class.getClassLoader().getResourceAsStream(path);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        String statement = "ns1.selectById";
        Person person = session.selectOne(statement, 1L);
        log.info("#8843 person={}", person);
    }
}
