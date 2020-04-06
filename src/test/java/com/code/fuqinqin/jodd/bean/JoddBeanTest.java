package com.code.fuqinqin.jodd.bean;

import com.code.fuqinqin.jodd.JoddTest;
import jodd.bean.BeanUtil;
import lombok.Data;
import lombok.ToString;
import org.junit.Before;
import org.junit.Test;

/**
 * 瑞士军刀jodd-bean模块测试
 * @author fuqinqin3
 * @date 2019-09-16
 * */
public class JoddBeanTest extends JoddTest {
    BeanUtil pojo;
    BeanUtil silent;
    BeanUtil declared;
    BeanUtil forced;

    @Before
    public void init(){
        pojo = BeanUtil.pojo;
        silent = BeanUtil.silent;
        declared = BeanUtil.declared;
        forced = BeanUtil.forced;
    }

    @Test
    public void pojo(){
        Person person = new Person();
        LOGGER.info("person hasName = {}", pojo.hasProperty(person, "name"));   // 是否存在牧歌属性
        LOGGER.info("extractThisReference = {}", pojo.extractThisReference("1q.name.eqe[sf"));
        pojo.setProperty(person, "name", "zhangsan");
        LOGGER.info("getProperty = {}", (String)pojo.getProperty(person, "name"));
    }

    @Test
    public void silent(){
        Person person = new Person();
        silent.setProperty(person, "name", "zhangsan");
        showPerson(person);
    }

    @Test
    public void declared(){
        Person person = new Person();
        declared.setProperty(person, "name", "zhangsan");
        showPerson(person);
    }

    @Test
    public void forced(){
        Person person = new Person();
        forced.setProperty(person, "name", "zhangsan");
        showPerson(person);
    }

    private void showPerson(Person person){
        System.out.println(person);
    }

    @Data
    @ToString
    public static class Person {
        private Long no;
        private String name;
        private String address;
    }
}
