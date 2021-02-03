package com.code.fuqinqin.org.apache.mybatis.reflection;

import org.apache.ibatis.reflection.ArrayUtil;
import org.junit.Test;

import java.util.Arrays;

/**
 * <p>ArrayUtil UT</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/1 13:55
 */
public class ArrayUtilTest {
    private String[] stringArray = Arrays.asList("1", "2", "3").toArray(new String[0]);
    private String[] stringArray2 = Arrays.asList("1", "2", "3").toArray(new String[0]);
    private Long[] longArray = Arrays.asList(1L, 2L, 3L).toArray(new Long[0]);
    private Integer[] integerArray = Arrays.asList(1, 2, 3).toArray(new Integer[0]);

    @Test
    public void hashCodeTest() {
        System.out.println("stringArray.hashCode1 = " + ArrayUtil.hashCode(stringArray));
        System.out.println("longArray.hashCode1 = " + ArrayUtil.hashCode(longArray));
        System.out.println("integerArray.hashCode1 = " + ArrayUtil.hashCode(integerArray));

        System.out.println("stringArray.hashCode2 = " + Arrays.hashCode(stringArray));
        System.out.println("longArray.hashCode2 = " + Arrays.hashCode(longArray));
        System.out.println("integerArray.hashCode2 = " + Arrays.hashCode(integerArray));
    }

    @Test
    public void equalsTest(){
        System.out.println("stringArray.equals1 = " + ArrayUtil.equals(stringArray, stringArray2));
        System.out.println("stringArray.equals2 = " + Arrays.equals(stringArray, stringArray2));
    }

    @Test
    public void toStringTest(){
        System.out.println("stringArray.toString1 = " + ArrayUtil.toString(stringArray));
        System.out.println("longArray.toString1 = " + ArrayUtil.toString(longArray));
        System.out.println("integerArray.toString1 = " + ArrayUtil.toString(integerArray));

        System.out.println("stringArray.toString2 = " + Arrays.toString(stringArray));
        System.out.println("longArray.toString2 = " + Arrays.toString(longArray));
        System.out.println("integerArray.toString2 = " + Arrays.toString(integerArray));
    }

    @Test
    public void typeTransferTest(){
        Class clazz1 = longArray.getClass();
        Class clazz2 = clazz1.getComponentType();
        System.out.println("clazz1="+clazz1.getName()+", clazz2="+clazz2.getName());
    }
}
