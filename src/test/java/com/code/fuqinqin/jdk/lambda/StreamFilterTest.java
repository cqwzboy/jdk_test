package com.code.fuqinqin.jdk.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2020/12/17 16:59
 */
public class StreamFilterTest {
    @Test
    public void test(){
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        integers.stream().filter(integer -> integer<4).forEach(System.out::println);
    }
}
