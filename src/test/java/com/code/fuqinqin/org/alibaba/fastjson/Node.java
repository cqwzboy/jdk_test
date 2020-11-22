package com.code.fuqinqin.org.alibaba.fastjson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Iterator;

/**
 * @author fuqinqin
 * @date 2020-10-23
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Node implements Iterable<Node> {
    private Node head;
    private Node next;

    private String key;
    private Object value;
    private Class<?> className;

    @Override
    public Iterator<Node> iterator() {
        return new Iterator(){
            @Override
            public boolean hasNext() {
                return Node.this.next != null;
            }

            @Override
            public Object next() {
                return Node.this.next;
            }
        };
    }
}
