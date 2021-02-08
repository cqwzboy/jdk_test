package com.code.fuqinqin.jdk.reflect.type;

import lombok.Data;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/8 11:17
 */
public class GenericArrayTypeTest {

    @Test
    public void test() {
        Stream.of(School.class.getDeclaredFields()).forEach(field -> {
            if (field.getGenericType() instanceof Class) {
                System.out.println("Filed[" + field.getName() + "] instanceof Class");
            }
            if (field.getGenericType() instanceof ParameterizedType) {
                System.out.println("Filed[" + field.getName() + "] instanceof ParameterizedType");
            }
            if (field.getGenericType() instanceof TypeVariable) {
                System.out.println("Filed[" + field.getName() + "] instanceof TypeVariable");
            }
            if (field.getGenericType() instanceof GenericArrayType) {
                System.out.println("Filed[" + field.getName() + "] instanceof GenericArrayType");
            }
        });
    }

    @Test
    public void test2() throws NoSuchFieldException {
        Field field = School.class.getDeclaredField("field4");
        GenericArrayType type = (GenericArrayType) field.getGenericType();
        Type genericComponentType = type.getGenericComponentType();
        System.out.println("genericComponentType.getTypeName(): " + genericComponentType.getTypeName());
    }

    @Data
    public static class Person {
        private String name;
    }

    @Data
    public static class School<T extends Person> {
        private T field1;
        private List<T> field2;
        private T[] field3;
        private List<T>[] field4;
    }

}
