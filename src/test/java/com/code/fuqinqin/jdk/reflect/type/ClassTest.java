package com.code.fuqinqin.jdk.reflect.type;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

/**
 * <p>Class常用的方法</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/8 15:07
 */
public class ClassTest {

    @Test
    public void test() throws NoSuchFieldException, NoSuchMethodException {
        System.out.println("Person.class.getName(): " + Person.class.getName());
        System.out.println("Person.class.getSimpleName(): " + Person.class.getSimpleName());
        System.out.println("Person.class.getTypeName(): " + Person.class.getTypeName());
        System.out.println("-------------------------------------------------");

        System.out.println("Person[].class.getName(): " + Person[].class.getName());
        System.out.println("Person[].class.getSimpleName(): " + Person[].class.getSimpleName());
        System.out.println("Person[].class.getTypeName(): " + Person[].class.getTypeName());
        System.out.println("-------------------------------------------------");

        System.out.println("Person.class.getDeclaredField(\"data\"): " + Person.class.getDeclaredField("data"));
        System.out.println("Student.class.getField(\"data\"): " + Student.class.getField("data"));
        System.out.println("Student.class.getDeclaredMethod(\"learn\"): " + Student.class.getDeclaredMethod("learn"));
        System.out.println("Student.class.getMethod(\"speak\"): " + Student.class.getMethod("speak"));
        System.out.println("-------------------------------------------------");

        System.out.println("Person.class.getTypeParameters(): " + JSON.toJSONString(Person.class.getTypeParameters()));
        System.out.println("Person[].class.getComponentType(): " + JSON.toJSONString(Person[].class.getComponentType()));
        System.out.println("-------------------------------------------------");

        System.out.println("Person.class.getClasses(): " + JSON.toJSONString(Person.class.getClasses()));
        System.out.println("Person.class.getDeclaredClasses(): " + JSON.toJSONString(Person.class.getDeclaredClasses()));
        System.out.println("Student.class.getClasses(): " + JSON.toJSONString(Student.class.getClasses()));
        System.out.println("Student.class.getDeclaredClasses(): " + JSON.toJSONString(Student.class.getDeclaredClasses()));
    }

    @Data
    public static class Person<T> {
        public T data;

        public void speak() {

        }

        public class A {
        }

        public static class B {
        }

        private class C {
        }

        private static class D {
        }

        static class E {
        }

        class F {
        }
    }

    @Data
    public static class Student extends Person<String> {
        private String clazz;

        private void learn() {

        }

        public class G {
        }

        public static class H {
        }

        private class I {
        }

        private static class J {
        }

        static class K {
        }

        class L {
        }
    }

}
