package com.code.fuqinqin.jdk.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * <p></p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2020/12/31 15:48
 */
public class OptionalTest {
    @Test
    public void test() {
        Person person = new Person(new Dog(new Food("水果")));
//        Person person = new Person(new Dog(null));
        System.out.println(show1(person));
        System.out.println(show2(person));
    }

    @Test
    public void test2() {
//        List<Object> empty = Collections.emptyList();
        List<Object> empty = null;
        System.out.println(Optional.ofNullable(empty).map(List::size).orElse(-1));
    }

    private String show1(Person person) {
        if (person != null) {
            Dog dog = person.getDog();
            if (dog != null) {
                Food food = dog.getFood();
                if (food != null) {
                    return food.getName();
                }
            }
        }
        return "unknown";
    }

    private String show2(Person person) {
        return Optional.ofNullable(person)
                .map(Person::getDog)
                .map(Dog::getFood)
                .map(Food::getName)
                .orElse("unknown");
    }

    @Data
    @AllArgsConstructor
    private static class Person {
        private Dog dog;
    }

    @Data
    @AllArgsConstructor
    private static class Dog {
        private Food food;
    }

    @Data
    @AllArgsConstructor
    private static class Food {
        private String name;
    }
}
