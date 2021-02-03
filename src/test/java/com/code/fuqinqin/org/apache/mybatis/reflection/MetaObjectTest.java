package com.code.fuqinqin.org.apache.mybatis.reflection;

import com.code.fuqinqin.org.apache.mybatis.reflection.model.Student;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.junit.Test;

import java.util.Date;

/**
 * <p>
 * MetaObject UT
 * 具备两部分能力：
 * ① Object级别的set/get操作
 * ② Class级别的操作，与MetaClass对等
 * </p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/1 15:37
 */
public class MetaObjectTest {
    private Student student = new Student();
    private MetaObject metaObject = SystemMetaObject.forObject(student);

    @Test
    public void setAndGetTest() {
        metaObject.setValue("name", "张三");
        System.out.println("name: " + metaObject.getValue("name"));

        metaObject.setValue("age", 23);
        System.out.println("age: " + metaObject.getValue("age"));

        metaObject.setValue("birthday", new Date());
        System.out.println("birthday: " + metaObject.getValue("birthday"));

        metaObject.setValue("clazz", 2);
        System.out.println("clazz: " + metaObject.getValue("clazz"));

        metaObject.setValue("hobby.category", "运动类");
        System.out.println("hobby.category: " + metaObject.getValue("hobby.category"));

        metaObject.setValue("hobby.name", "篮球");
        System.out.println("hobby.name: " + metaObject.getValue("hobby.name"));

        metaObject.setValue("map.key1", "value1");
        System.out.println("map.key1: " + metaObject.getValue("map.key1"));

        metaObject.setValue("map[key2]", "value2");
        System.out.println("map[key2]: " + metaObject.getValue("map[key2]"));

        metaObject.setValue("list1[0]", "list1");
        System.out.println("list1[0]: " + metaObject.getValue("list1[0]"));
    }
}
