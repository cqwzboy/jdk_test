package com.code.fuqinqin.org.apache.mybatis.reflection;

import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/7 14:43
 */
public class PropertyTokenizerTest {

    private AtomicInteger count = new AtomicInteger(0);

    @Test
    public void test() {
        String fullname = "f1.f2[0].f3[1].f4";
        PropertyTokenizer propertyTokenizer = new PropertyTokenizer(fullname);
        show(propertyTokenizer);
        while (propertyTokenizer.hasNext()) {
            propertyTokenizer = propertyTokenizer.next();
            show(propertyTokenizer);
        }
    }

    private void show(PropertyTokenizer propertyTokenizer) {
        System.out.println("name = " + propertyTokenizer.getName());
        System.out.println("indexName = " + propertyTokenizer.getIndexedName());
        System.out.println("index = " + propertyTokenizer.getIndex());
        System.out.println("children = " + propertyTokenizer.getChildren());
        System.out.println("------------------- " + count.getAndIncrement() + " --------------------");
    }

}
