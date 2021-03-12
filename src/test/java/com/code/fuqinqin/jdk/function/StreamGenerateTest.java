package com.code.fuqinqin.jdk.function;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * <p>Stream生成方式枚举</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/3/11 11:39
 */
public class StreamGenerateTest {

    /**
     * 空
     */
    @Test
    public void emptyStream() {
        Assert.assertNotNull(Stream.empty());
    }

    @Test
    public void streamOf() {
        Stream.of("a", "b", "c", "d").forEach(System.out::println);
    }

    /**
     * 无限迭代
     */
    @Test
    public void unlimitedIterate() {
        UnaryOperator<Integer> unaryOperator = integer -> ++integer;
        Stream.iterate(1, unaryOperator).forEach(System.out::println);
    }

    /**
     * 有限迭代
     */
    @Test
    public void limitedIterate() {
        UnaryOperator<Integer> unaryOperator = integer -> ++integer;
        Stream.iterate(1, unaryOperator).limit(5).forEach(System.out::println);
    }

    /**
     * generate一般用于随机Stream生成
     */
    @Test
    public void generate() {
        Supplier<Integer> supplier = () -> (int) (Math.random() * 10);
        Stream.generate(supplier).limit(5).forEach(System.out::println);
    }
}
