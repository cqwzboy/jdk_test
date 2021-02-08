package com.code.fuqinqin.jdk.reflect.type;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>Field</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/8 17:53
 */
public class FieldTest {

    @Test
    public void test() throws NoSuchFieldException {
        Field field = Person.class.getDeclaredField("data");

        System.out.println("field.getName(): " + field.getName());

        System.out.println("field.getType(): " + JSON.toJSONString(field.getType()));
        System.out.println("field.getGenericType(): " + JSON.toJSONString(field.getGenericType()));
        System.out.println("field.getDeclaringClass(): " + JSON.toJSONString(field.getDeclaringClass()));
    }

    @Data
    public static class Person {
        private List<String> data;
    }

}
