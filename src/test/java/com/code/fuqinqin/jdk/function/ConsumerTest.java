package com.code.fuqinqin.jdk.function;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/3/11 10:16
 */
public class ConsumerTest {
    @Test
    public void test() {
        Consumer<String> c1 = System.out::println;
        Consumer<String> c2 = (arg) -> System.out.println(arg + "-Hello");
        // 在一个Consumer后添加一个新的Consumer，让相同参数在消费链上传递
        c1.andThen(c2).andThen(c1).accept("Hello China");
    }
}
