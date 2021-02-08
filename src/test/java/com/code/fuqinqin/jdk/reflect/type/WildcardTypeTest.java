package com.code.fuqinqin.jdk.reflect.type;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;

/**
 * <p>通配符</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/8 11:30
 */
public class WildcardTypeTest {

    @Test
    public void test() throws NoSuchMethodException {
        Method method = Factory.class.getDeclaredMethod("workers");
        Type type = method.getGenericReturnType();
        System.out.println("type instanceof WildcardType: " + (type instanceof WildcardType));
        Type actualTypeArgumentType = ((ParameterizedType) type).getActualTypeArguments()[0];
        System.out.println("actualTypeArgumentType instanceof WildcardType: " + (actualTypeArgumentType instanceof WildcardType));
        WildcardType wildcardType = (WildcardType) actualTypeArgumentType;
        System.out.println("wildcardType.getUpperBounds(): " + JSON.toJSONString(wildcardType.getUpperBounds()));
        System.out.println("wildcardType.getLowerBounds(): " + JSON.toJSONString(wildcardType.getLowerBounds()));
    }

    @Data
    public static class Person {
        private String name;
    }

    @Data
    public static class Factory {
        public List<? super Person> workers() {
            return null;
        }
    }

}
