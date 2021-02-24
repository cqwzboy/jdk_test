package com.code.fuqinqin.jdk.string;

import org.junit.Test;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/23 11:13
 */
public class StringConstantTest {
    
    @Test
    public void test() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        System.out.println(s1 + s2 == s3);
        System.out.println("a" + s2 == s3);
        System.out.println("a" + "b" == s3);

        final String s4 = "c";
        String s5 = "ac";
        System.out.println(s1 + s4 == s5);
        System.out.println("a" + s4 == s5);
    }

    @Test
    public void internTest() {
        String s1 = "Hello World";
        System.out.println(s1.intern() == s1);
        System.out.println(s1.intern().equals(s1));
    }

    @Test
    public void internTest2() {
        String str2 = new String("str");
        str2.intern();
        String str1 = "str";
        System.out.println(str2 == str1);

        str2 = new String("str") + new String("01");
        str2.intern();
        str1 = "str01";
        System.out.println(str2 == str1);//true
    }
}
