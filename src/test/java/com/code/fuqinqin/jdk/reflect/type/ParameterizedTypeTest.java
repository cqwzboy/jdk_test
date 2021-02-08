package com.code.fuqinqin.jdk.reflect.type;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * <p>参数化类型</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/7 15:49
 */
public class ParameterizedTypeTest {
    private int count = 1;

    @Test
    public void fieldTest() {
        Field[] fields = Person.class.getDeclaredFields();
        Stream.of(fields).forEach(field -> {
            System.out.println("----------------------- " + count++ + ".field.getName(): " + field.getName() + " ---------------------------");
            System.out.println("field.getClass(): " + field.getClass().getName());
            System.out.println("field.getType(): " + field.getType().getName());
            Type genericType = field.getGenericType();
            System.out.println("field.getGenericType(): " + genericType.getTypeName());
            System.out.println("field.getGenericType() instanceof ParameterizedType: " + (genericType instanceof ParameterizedType));
            if (genericType instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericType;
                System.out.println("parameterizedType.getRawType(): " + parameterizedType.getRawType().getTypeName());
                System.out.println("parameterizedType.getActualTypeArguments(): " + JSON.toJSONString(parameterizedType.getActualTypeArguments()));
                System.out.println("parameterizedType.getOwnerType(): " + (parameterizedType.getOwnerType() == null ? null : parameterizedType.getOwnerType().getTypeName()));
            }
        });
    }

    @Test
    public void methodTest() {
        Method[] methods = Person.class.getDeclaredMethods();
        Stream.of(methods).filter(method -> "query".equals(method.getName()))
                .forEach(method -> {
                    System.out.println("----------------------- method.getName(): " + method.getName() + " ---------------------------");
                    System.out.println("method.getGenericParameterTypes(): " + JSON.toJSONString(method.getGenericParameterTypes()));
                    System.out.println("method.getGenericReturnType(): " + JSON.toJSONString(method.getGenericReturnType()));
                });
    }

    @Data
    public static class Person {
        private String name;
        private List<String> hobbyList;
        private Set<String> organSet;
        private Map<String, Integer> organCountMap;
        private Dream<String> dream;
        private Dream.DreamDetail<String> dreamDetail;

        public Map<String, Person> query(List<String> nameList) {
            return null;
        }
    }

    @Data
    public static class Dream<T> {
        @Data
        public static class DreamDetail<T> {
            private T name;
        }
    }

}
