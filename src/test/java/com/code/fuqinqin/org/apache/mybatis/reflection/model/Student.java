package com.code.fuqinqin.org.apache.mybatis.reflection.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/2/1 15:39
 */
@Data
public class Student extends Person {
    private Integer clazz;
    private Hobby hobby;
    private List list1 = new ArrayList() {
        {
            add("bar");
        }
    };
    private List list2 = Arrays.asList("bar2");
    private List list3 = new ArrayList();
    private List list4;
    private Map<String, Object> map;
}
