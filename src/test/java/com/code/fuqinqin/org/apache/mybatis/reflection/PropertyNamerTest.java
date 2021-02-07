package com.code.fuqinqin.org.apache.mybatis.reflection;

import org.apache.ibatis.reflection.property.PropertyNamer;
import org.junit.Test;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/7 15:00
 */
public class PropertyNamerTest {

    @Test
    public void test() {
        String name = "getName";
        System.out.println("propertyName: " + PropertyNamer.methodToProperty(name));
    }

}
