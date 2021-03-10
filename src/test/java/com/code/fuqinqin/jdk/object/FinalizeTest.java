package com.code.fuqinqin.jdk.object;

import lombok.Data;
import org.junit.Test;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/27 10:46
 */
public class FinalizeTest {

    @Test
    public void test() {
        Person person = new Person();
        person.setName("张三");
//        person.fi
    }

    @Data
    public static class Person {
        private String name;
    }
}
