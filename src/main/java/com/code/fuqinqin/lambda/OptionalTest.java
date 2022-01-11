package com.code.fuqinqin.lambda;

import com.alibaba.fastjson.JSON;
import jodd.util.MathUtil;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author fuqinqin
 * @date 2022-01-06
 */
public class OptionalTest {
    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list = Collections.emptyList();
        Integer value = list.stream().findFirst()
//                .orElse(-999);
                .orElseGet(() -> MathUtil.randomInt(0, 10));
        System.out.println(value);
        synchronized (OptionalTest.class){
            try {
                OptionalTest.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
