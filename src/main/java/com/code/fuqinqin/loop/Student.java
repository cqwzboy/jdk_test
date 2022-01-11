package com.code.fuqinqin.loop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer no;
    private String name;
    private Integer type;

    public static List<Student> buildList(Integer size) {
        List<Student> list = new ArrayList<>(size);
        for (Integer i = 0; i < size; i++) {
            list.add(new Student(i, "name" + i, i % 10));
        }
        return list;
    }
}
