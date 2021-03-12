package com.code.fuqinqin.jdk.function;

import org.junit.Test;

import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * <p>供应商，即无入参，有出参</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/3/11 11:34
 */
public class SupplierTest {
    private Supplier<String> supplier = () -> UUID.randomUUID().toString().replaceAll("-", "");

    @Test
    public void test1() {
        for (int i = 0; i < 5; i++) {
            System.out.println(supplier.get());
        }
    }

    @Test
    public void test2() {
        Stream.generate(supplier).limit(5).forEach(System.out::println);
    }

}
