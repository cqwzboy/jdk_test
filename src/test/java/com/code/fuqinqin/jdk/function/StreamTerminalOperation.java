package com.code.fuqinqin.jdk.function;

import org.junit.Test;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.stream.Stream;

/**
 * <p>Stream终端操作</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2021/3/12 18:02
 */
public class StreamTerminalOperation {

    @Test
    public void iterator() {
        Iterator<String> iterator = Stream.of("a", "b", "c", "d", "e").iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void spliterator() {
        Spliterator<String> spliterator = Stream.of("a", "b", "c", "d", "e").spliterator();
    }

}
