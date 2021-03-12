package com.code.fuqinqin.jdk.function;

import org.junit.Assert;
import org.junit.Test;

import java.util.function.Predicate;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/3/11 10:55
 */
public class PredicateTest {

    @Test
    public void test() {
        Predicate<String> p1 = arg1 -> arg1.equalsIgnoreCase("test");
        Predicate<String> p2 = arg2 -> arg2.startsWith("t");

        // 取反逻辑
        Assert.assertFalse(p1.negate().test("test"));

        // 与逻辑
        Assert.assertTrue(p1.and(p2).test("test"));

        // 或逻辑
        Assert.assertTrue(p1.or(p2).test("test"));

        // 构建判断相等的Predicate
        Assert.assertTrue(Predicate.isEqual("test").test("test"));
    }

}
