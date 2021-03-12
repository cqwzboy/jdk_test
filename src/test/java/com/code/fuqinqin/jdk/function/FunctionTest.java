package com.code.fuqinqin.jdk.function;

import org.junit.Test;

import java.util.function.Function;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/3/11 10:27
 */
public class FunctionTest {

    @Test
    public void test() {
        Function<Integer, Integer> f1 = arg1 -> ++arg1;
        Function<Integer, Integer> f2 = arg2 -> arg2 * 2;

        // compose()，先执行f2，f2的结果作为f1的输入
        System.out.println(f1.compose(f2).apply(2));

        // andThen()，先执行f1，f1的结果作为f2的输入
        System.out.println(f1.andThen(f2).apply(2));

        // identity()，静态方法，将输入原样返回
        System.out.println(Function.identity().apply("Hello"));
    }

}
