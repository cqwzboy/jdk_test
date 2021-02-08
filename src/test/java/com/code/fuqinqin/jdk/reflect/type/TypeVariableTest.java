package com.code.fuqinqin.jdk.reflect.type;

import com.alibaba.fastjson.JSON;
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
 * @date 2021/2/7 17:18
 */
public class TypeVariableTest {

    @Test
    public void classTest() {
        TypeVariable<Class<Work>>[] typeVariables = Work.class.getTypeParameters();
        show(typeVariables);
    }

    @Test
    public void methodTest1() throws NoSuchMethodException {
        Method method = Work.class.getDeclaredMethod("queryById", String.class);
        TypeVariable<Method>[] typeVariables = method.getTypeParameters();
        show(typeVariables);
    }

    @Test
    public void methodTest2() {
        Method[] methods = Work.class.getDeclaredMethods();
        Stream.of(methods).filter(method -> "queryById".equals(method.getName()))
                .forEach(method -> {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    Stream.of(parameterTypes).forEach(parameterType -> {
                        System.out.println("----------------------- parameterType: " + parameterType.getName() + " ----------------------");
                        show(parameterType.getTypeParameters());
                    });
                });
    }

    @Test
    public void fieldTest() throws NoSuchFieldException {
        Field field = Work.class.getDeclaredField("person");
        System.out.println("field.getGenericType(): " + field.getGenericType().getTypeName());
        System.out.println("field.getType(): " + field.getType().getTypeName());
        Type genericType = field.getGenericType();
        System.out.println("genericType instanceof TypeVariable: " + (genericType instanceof TypeVariable));
        System.out.println("genericType instanceof TypeVariable: " + (genericType instanceof TypeVariable));
        System.out.println("genericType instanceof ParameterizedType: " + (genericType instanceof ParameterizedType));
        System.out.println("genericType instanceof ParameterizedType: " + (genericType instanceof ParameterizedType));
        if (genericType instanceof TypeVariable) {
            TypeVariable typeVariable = (TypeVariable) genericType;
            show(new TypeVariable[]{typeVariable});
        }
    }

    @Test
    public void test() throws NoSuchFieldException {
        Field field = Work.class.getDeclaredField("person");
        Type genericType = field.getGenericType();
        System.out.println("field.getGenericType(): " + genericType.getTypeName());
        System.out.println("field.getType(): " + field.getType().getTypeName());
        System.out.println("genericType instanceof TypeVariable: " + (genericType instanceof TypeVariable));
        System.out.println("genericType instanceof ParameterizedType: " + (genericType instanceof ParameterizedType));

        System.out.println("--------------------------------------------");

        Field field1 = Work.class.getDeclaredField("list1");
        genericType = field1.getGenericType();
        System.out.println("field1.getGenericType(): " + genericType.getTypeName());
        System.out.println("field1.getType(): " + field1.getType().getTypeName());
        System.out.println("genericType instanceof TypeVariable: " + (genericType instanceof TypeVariable));
        System.out.println("genericType instanceof ParameterizedType: " + (genericType instanceof ParameterizedType));

        System.out.println("--------------------------------------------");

        Field field2 = Work.class.getDeclaredField("list2");
        genericType = field2.getGenericType();
        System.out.println("field2.getGenericType(): " + genericType.getTypeName());
        System.out.println("field2.getType(): " + field2.getType().getTypeName());
        System.out.println("genericType instanceof TypeVariable: " + (genericType instanceof TypeVariable));
        System.out.println("genericType instanceof ParameterizedType: " + (genericType instanceof ParameterizedType));

        System.out.println("--------------------------------------------");

        Field field3 = City.class.getDeclaredField("work");
        genericType = field3.getGenericType();
        System.out.println("field3.getGenericType(): " + genericType.getTypeName());
        System.out.println("field3.getType(): " + field2.getType().getTypeName());
        System.out.println("genericType instanceof TypeVariable: " + (genericType instanceof TypeVariable));
        System.out.println("genericType instanceof ParameterizedType: " + (genericType instanceof ParameterizedType));
    }

    private void show(TypeVariable<?>[] typeVariables) {
        Stream.of(typeVariables).forEach(classTypeVariable -> {
            System.out.println("\tclassTypeVariable.getBounds(): " + JSON.toJSONString(classTypeVariable.getBounds()));
            System.out.println("\tclassTypeVariable.getGenericDeclaration(): " + JSON.toJSONString(classTypeVariable.getGenericDeclaration()));
            System.out.println("\tclassTypeVariable.getName(): " + classTypeVariable.getName());
        });
    }

    @Data
    public static class Person {
        private String name;
    }

    @Data
    public static class Work<T extends Person> {
        private T person;
        private List<T> list1;
        private List<String> list2;

        public List<T> queryById(T person) {
            return null;
        }
    }

    @Data
    public static class City {
        private Work<Person> work;
    }

}
