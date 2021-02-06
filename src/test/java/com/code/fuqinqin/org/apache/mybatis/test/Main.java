package com.code.fuqinqin.org.apache.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * <p></p>
 *
 * @author fuqinqin
 * @version 1.0
 * @date 2021/2/6 18:01
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String resource = "mybatis/test/mybatis-config.xml";
        InputStream inputStream = null;
        SqlSession sqlSession = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sessionFactory.openSession();
            // 方式一
            /*PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);
            List<PersonEntity> personEntities = personMapper.queryAll();*/
            // 方式二
            List<PersonEntity> personEntities = sqlSession.selectList("com.code.fuqinqin.org.apache.mybatis.test.PersonMapper.queryAll");
            personEntities.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }
}
