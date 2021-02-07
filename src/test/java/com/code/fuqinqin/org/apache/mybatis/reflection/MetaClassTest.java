package com.code.fuqinqin.org.apache.mybatis.reflection;

import com.alibaba.fastjson.JSON;
import com.code.fuqinqin.org.apache.mybatis.reflection.model.Student;
import lombok.Data;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>MetaClass UT</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/1 14:55
 */
public class MetaClassTest {
    private MetaClass metaClass = MetaClass.forClass(Student.class, new DefaultReflectorFactory());

    private List list1 = new ArrayList() {
        {
            add("bar");
        }
    };
    private List list2 = Arrays.asList("bar");

    @Test
    public void listTest() {
        System.out.println(JSON.toJSONString(list1));
        System.out.println(JSON.toJSONString(list2));
    }

    @Test
    public void getGetterTypeTest() {
        System.out.println("name.type = " + metaClass.getGetterType("name"));
        System.out.println("age.type = " + metaClass.getGetterType("age"));
        System.out.println("age.birthday = " + metaClass.getGetterType("birthday"));
        System.out.println("age.clazz = " + metaClass.getGetterType("clazz"));
    }

    @Test
    public void getSetterTypeTest() {
        System.out.println("name.type = " + metaClass.getSetterType("name"));
        System.out.println("age.type = " + metaClass.getSetterType("age"));
        System.out.println("age.birthday = " + metaClass.getSetterType("birthday"));
        System.out.println("age.clazz = " + metaClass.getSetterType("clazz"));
    }

    @Test
    public void hasGetterTest() {
        System.out.println("hasGetter-name: " + metaClass.hasGetter("name"));
        System.out.println("hasGetter-age: " + metaClass.hasGetter("age"));
        System.out.println("hasGetter-birthday: " + metaClass.hasGetter("birthday"));
        System.out.println("hasGetter-clazz: " + metaClass.hasGetter("clazz"));
        System.out.println("hasGetter-hobby: " + metaClass.hasGetter("hobby"));
        System.out.println("hasGetter-hobby.category: " + metaClass.hasGetter("hobby.category"));
        System.out.println("hasGetter-hobby.name: " + metaClass.hasGetter("hobby.name"));
        System.out.println("hasGetter-list1: " + metaClass.hasGetter("list1"));
        System.out.println("hasGetter-list1[0]: " + metaClass.hasGetter("list1[0]"));
        System.out.println("hasGetter-list2: " + metaClass.hasGetter("list2"));
        System.out.println("hasGetter-list2[0]: " + metaClass.hasGetter("list2[0]"));
        System.out.println("hasGetter-list3: " + metaClass.hasGetter("list3"));
        System.out.println("hasGetter-list3[0]: " + metaClass.hasGetter("list3[0]"));
        System.out.println("hasGetter-list4: " + metaClass.hasGetter("list4"));
        System.out.println("hasGetter-list4[0]: " + metaClass.hasGetter("list4[0]"));
        System.out.println("hasGetter-list5: " + metaClass.hasGetter("list5"));
    }

    @Test
    public void getGetterNames() {
        System.out.println("getGetterNames: " + JSON.toJSONString(metaClass.getGetterNames()));
    }

    @Test
    public void findPropertyTest() {
        System.out.println("findProperty: " + metaClass.findProperty("hobby"));
    }

    @Test
    public void getSetterTypeTest2() {
        MetaClass metaClass = MetaClass.forClass(Clazz.class, new DefaultReflectorFactory());
        System.out.println("Student.name setterType: " + metaClass.getSetterType("student.name"));
        System.out.println("Student.bookList setterType: " + metaClass.getSetterType("student.bookList"));
        // 会报错
//        System.out.println("Student.book.name setterType: " + metaClass.getSetterType("student.bookList[0].name"));
    }

    @Data
    private static class Book {
        private String name;
    }

    @Data
    private static class Student {
        private String name;
        private List<Book> bookList;
    }

    @Data
    private static class Clazz {
        private String name;
        private MetaClassTest.Student student;
    }
}
