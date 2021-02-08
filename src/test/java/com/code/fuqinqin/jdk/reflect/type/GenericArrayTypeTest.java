package com.code.fuqinqin.jdk.reflect.type;

import lombok.Data;
import org.apache.ibatis.reflection.TypeParameterResolver;
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

    @Test
    public void test3() throws NoSuchMethodException {
        Method method = School.class.getDeclaredMethod("method1");

        Type returnType = method.getGenericReturnType();
        showMethod(method.getName(), returnType);

        Type type = TypeParameterResolver.resolveReturnType(method, School.class);
        showMethod(method.getName(), type);
    }

    private void showMethod(String methodName, Type returnType) {
        if (returnType instanceof Class) {
            System.out.println("method[" + methodName + "] instanceof Class");
        }
        if (returnType instanceof ParameterizedType) {
            System.out.println("method[" + methodName + "] instanceof ParameterizedType");
        }
        if (returnType instanceof TypeVariable) {
            System.out.println("method[" + methodName + "] instanceof TypeVariable");
        }
        if (returnType instanceof GenericArrayType) {
            System.out.println("method[" + methodName + "] instanceof GenericArrayType");
        }
        if (returnType instanceof WildcardType) {
            System.out.println("method[" + methodName + "] instanceof WildcardType");
        }
    }

    @Test
    public void test4() throws NoSuchFieldException, NoSuchMethodException {
        Field field = School.class.getDeclaredField("field2");
        System.out.println("field.getGenericType(): " + field.getGenericType().getTypeName());
        System.out.println("field.getDeclaringClass(): " + field.getDeclaringClass().getName());

        Method method = School.class.getDeclaredMethod("method1");
        System.out.println("method.getDeclaringClass(): " + method.getDeclaringClass().getName());
    }

    @Data
    public static class Person {
        private String name;
    }

    @Data
    public static class Animal<T> {

    }

    @Data
    public static class School<T extends Person> {
        private T field1;
        private List<T> field2;
        private T[] field3;
        private List<T>[] field4;
        private String[] field5;

        public <R extends Animal<?>> R method1() {
            return null;
        }
    }

    @Data
    public static class School1 extends School<Person> {

    }

}
