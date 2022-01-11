package com.code.fuqinqin.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author fuqinqin
 */
public class MaxMinReduceTest {
    private static final List<Student> STUDENTS = Arrays.asList(
            new Student("张三", 34),
            new Student("李四", 12),
            new Student("王五", 99),
            new Student("赵六", 123),
            new Student("何七", 10)
    );

    public static void main(String[] args) {
//        maxTest();
//        minTest();
        reduce();
    }

    /**
     * Max
     */
    private static void maxTest() {
        Optional<Student> optional = STUDENTS.stream().max(Comparator.comparing(Student::getAge));
        if (optional.isPresent()) {
            Student student = optional.get();
            System.out.println("max Student: " + student);
        } else {
            System.out.println("not exists");
        }
    }

    /**
     * Min
     */
    private static void minTest() {
        Optional<Student> optional = STUDENTS.stream().min(Comparator.comparing(Student::getAge));
        if (optional.isPresent()) {
            Student student = optional.get();
            System.out.println("min Student: " + student);
        } else {
            System.out.println("not exists");
        }
    }

    /**
     * reduce
     */
    private static void reduce() {
        Optional<Student> optional = STUDENTS.stream()
                .reduce((student, student2) -> student.getAge() > student2.getAge() ? student2 : student);
        if (optional.isPresent()) {
            Student student = optional.get();
            System.out.println("reduce Student: " + student);
        } else {
            System.out.println("not exists");
        }
    }

    /**
     * @author fuqinqin
     * @date 2022-01-06
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class Student {
        private String name;
        private Integer age;
    }
}
