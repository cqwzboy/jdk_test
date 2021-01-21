package com.code.fuqinqin.jdk;

import org.junit.Test;

/**
 * <p>求中值算法</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2020/12/10 15:17
 */
public class MidleCaculateTest {
    public static long sort(long pre, long post) {
        long xor = pre ^ post;
        long temp = xor;

        long symbol;
        for(symbol = 1L; temp != 0L; symbol <<= 1) {
            temp >>= 1;
        }

        symbol >>= 1;
        if (symbol == 0L) {
            return pre;
        } else {
            for(symbol >>= 1; (symbol & xor) != 0L; symbol >>= 1) {
            }

            return pre + symbol;
        }
    }

    @Test
    public void test(){
        long pre = 4;
        long post = 8;
        System.out.println(sort(pre, post));
    }

}
