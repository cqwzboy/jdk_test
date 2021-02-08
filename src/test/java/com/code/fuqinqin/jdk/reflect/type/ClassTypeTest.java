package com.code.fuqinqin.jdk.reflect.type;

import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.*;
import java.util.List;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/8 18:00
 */
public class ClassTypeTest {

    @Test
    public void test() throws NoSuchFieldException, NoSuchMethodException {
        Field fieldB = C.class.getField("b");
        Type bType = fieldB.getGenericType();
        System.out.println("genericType instanceof Class: " + (bType instanceof Class));

        Field fieldC = C.class.getField("c");
        Type cType = fieldC.getGenericType();
        System.out.println("cType: " + cType);
        System.out.println("cType instanceof ParameterizedType: " + (cType instanceof ParameterizedType));
        Assert.assertTrue(cType instanceof ParameterizedType);
        ParameterizedType cParameterizedType = (ParameterizedType) cType;
        System.out.println("cParameterizedType.getActualTypeArguments()[0]: " + cParameterizedType.getActualTypeArguments()[0]);
//        Assert.assertEquals(String.class, cParameterizedType.getActualTypeArguments()[0]);

        Method methodQuery = B.class.getMethod("query");
        Type returnType = methodQuery.getGenericReturnType();
        Assert.assertTrue(returnType instanceof ParameterizedType);
        ParameterizedType parameterizedType = (ParameterizedType) returnType;
        Assert.assertEquals(String.class, parameterizedType.getActualTypeArguments()[0]);
    }

    @Data
    public abstract static class A<Y, Z> {
        public String a;
        public Integer[] b;
        public List<Y> c;

        public List<Y> query() {
            return null;
        }
    }

    @Data
    public static class B<X> extends A<String, X> {

    }

    @Data
    public static class C extends B<Integer> {

    }

}
