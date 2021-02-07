package com.code.fuqinqin.jdk.reflect.type;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * <p>常规点测，不好归类</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/7 15:59
 */
public class NormalTest {

    @Test
    public void fieldTypeTest() {
        Field[] fields = Person.class.getDeclaredFields();
        Arrays.asList(fields).forEach(field -> {
            System.out.println("----------------------- " + field.getName() + " ---------------------------");
            System.out.println("field.getClass(): " + field.getClass().getName());
            System.out.println("field.getType(): " + field.getType().getName());
            Type genericType = field.getGenericType();
            System.out.println("field.getGenericType(): " + genericType.getTypeName());
            System.out.println("field.getGenericType() instanceof ParameterizedType:" + (genericType instanceof ParameterizedType));
            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                System.out.println("parameterizedType.getRawType(): " + parameterizedType.getRawType().getTypeName());
                System.out.println("parameterizedType.getActualTypeArguments(): " + JSON.toJSONString(parameterizedType.getActualTypeArguments()));
                System.out.println("parameterizedType.getOwnerType(): " + (parameterizedType.getOwnerType() == null ? null : parameterizedType.getOwnerType().getTypeName()));
            }
        });
    }

    @Test
    public void methodTypeTest() {
        Method[] methods = Person.class.getDeclaredMethods();
        Stream.of(methods).filter(method -> "query".equals(method.getName()))
                .forEach(method -> {
                    System.out.println("----------------------- " + method.getName() + " ---------------------------");
                    System.out.println("method.getGenericParameterTypes(): " + JSON.toJSONString(method.getGenericParameterTypes()));
                    System.out.println("method.getGenericReturnType(): " + JSON.toJSONString(method.getGenericReturnType()));
                });
    }

    @Data
    public static class Person {
        private Integer id;
        private String name;
        private List<String> hobbies;

        public Map<String, Person> query(List<Integer> idList) {
            return new HashMap<>();
        }
    }

}
