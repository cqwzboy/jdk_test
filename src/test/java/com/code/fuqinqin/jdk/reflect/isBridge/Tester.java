package com.code.fuqinqin.jdk.reflect.isBridge;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * <p></p>
 *
 * @author fuqinqin
 * @version 1.0
 * @date 2021/2/12 20:50
 */
public class Tester {
    @Test
    public void test() {
        new Son();
    }

    @Test
    public void isBridgeTest() throws NoSuchMethodException {
        // bridge method
        Method method = Son.class.getDeclaredMethod("speak", Object.class);
        System.out.println(method.isBridge());
        // normal method
        method = Son.class.getDeclaredMethod("speak", String.class);
        System.out.println(method.isBridge());
    }
}
