package com.code.fuqinqin.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fuqinqin
 * @date 20212-01-06
 */
public class FlatMapTest {

    public static void main(String[] args) {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("张三", Arrays.asList("篮球", "足球")));
        studentList.add(new Student("李四", Arrays.asList("羽毛球", "排球", "曲棍球")));
        studentList.add(new Student("王五", Arrays.asList("乒乓球", "桌球", "保龄球")));

        // Case 1
        List<String> hobbies = studentList.stream().map(Student::getHobbies).flatMap(Collection::stream).collect(Collectors.toList());
        for (String hobby : hobbies) {
            System.out.println(hobby);
        }

        // Case 2
        System.out.println("==========================================");
        List<List<String>> hobbies2 = studentList.stream().map(Student::getHobbies).collect(Collectors.toList());
        for (List<String> h1 : hobbies2) {
            System.out.println("---------分割线---------");
            for (String h2 : h1) {
                System.out.println(h2);
            }
        }
    }

    /**
     * @author fuqinqin
     * @date 2022-01-06
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Student {
        private String name;
        private List<String> hobbies;
    }

}
