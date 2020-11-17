package com.code.fuqinqin.jdk.properties;

import org.junit.Test;

import java.util.Properties;
import java.util.Set;

/**
 * @author fuqinqin3
 * */
public class PropertiesTest {
    @Test
    public void test(){
        Properties properties = new Properties();
        properties.setProperty("a", "AA");
        properties.setProperty("b", "BB");
        properties.setProperty("c", "CC");
        properties.setProperty("d", "DD");
        Set<String> stringPropertyNames = properties.stringPropertyNames();
        for (String propertyName : stringPropertyNames) {
            System.out.println(propertyName +  " - " + properties.get(propertyName));
        }
    }
}
