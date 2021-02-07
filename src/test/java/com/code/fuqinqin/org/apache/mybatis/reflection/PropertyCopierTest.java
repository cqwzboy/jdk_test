package com.code.fuqinqin.org.apache.mybatis.reflection;

import lombok.*;
import org.apache.ibatis.reflection.property.PropertyCopier;
import org.junit.Test;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/7 15:05
 */
public class PropertyCopierTest {

    @Test
    public void sameClassTest() {
        Student source = new Student();
        source.setId(1);
        source.setName("张三");
        source.setClazz("1");
        Student target = new Student();
        System.out.println("target = " + target);
        PropertyCopier.copyBeanProperties(Student.class, source, target);
        System.out.println("target = " + target);
    }

    @Test
    public void differentClassTest() {
        Student source = new Student();
        source.setId(1);
        source.setName("张三");
        source.setClazz("1");
        Worker target = new Worker();
        System.out.println("target = " + target);
        PropertyCopier.copyBeanProperties(Student.class, source, target);
        System.out.println("target = " + target);
    }

    @Data
    private static class Person {
        public Integer id;
        private String name;
    }

    @Data
    @ToString(callSuper = true)
    private static class Student extends Person {
        private String clazz;
    }

    @Data
    @ToString(callSuper = true)
    private static class Worker extends Person {
        private String level;
    }

}
