package com.code.fuqinqin.jdk.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;

import java.util.*;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2020/12/22 10:57
 */
public class StreamMapTest {
    private Map<Integer, List<Item>> map;

    @Before
    public void init(){
        map = new HashMap<>();
        map.put(1, Arrays.asList(new Item("a", 12), new Item("b", 13), new Item("c", 12)));
        map.put(2, Arrays.asList(new Item("d", 11), new Item("e", 12)));
    }

    @Data
    @AllArgsConstructor
    private static class Item {
        private String name;
        private int age;
    }
}
