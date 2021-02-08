package com.code.fuqinqin.jdk.reflect.type;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.List;

/**
 * <p>Method</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/8 17:42
 */
public class MethodTest {

    @Test
    public void test() throws NoSuchMethodException {
        Method method = Person.class.getMethod("query", Object.class);

        System.out.println("method.getName(): " + method.getName());

        System.out.println("method.getReturnType(): " + JSON.toJSONString(method.getReturnType()));
        System.out.println("method.getGenericReturnType(): " + JSON.toJSONString(method.getGenericReturnType()));
        System.out.println("method.getParameterTypes(): " + JSON.toJSONString(method.getParameterTypes()));
        System.out.println("method.getGenericParameterTypes(): " + JSON.toJSONString(method.getGenericParameterTypes()));

        System.out.println("method.getDeclaringClass(): " + method.getDeclaringClass());
        System.out.println("method.getTypeParameters(): " + JSON.toJSONString(method.getTypeParameters()));
        System.out.println("method.getParameterCount(): " + method.getParameterCount());
    }

    @Data
    public static class Person<T> {
        private T param;

        public List<? extends Person> query(T param) {
            return null;
        }
    }

}
