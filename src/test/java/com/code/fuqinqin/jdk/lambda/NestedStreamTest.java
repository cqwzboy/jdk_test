package com.code.fuqinqin.jdk.lambda;

import lombok.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>嵌套Stream测试</p>
 *
 * @author fuqinqin3
 * @version 1.0
 * @date 2020/12/17 16:33
 */
public class NestedStreamTest {
    @Test
    public void test1() {
        Son son1 = Son.builder()
                .name("张三")
                .age(23)
                .hobbies(Arrays.asList("篮球", "足球"))
                .build();
        Son son2 = Son.builder()
                .name("李四")
                .age(18)
                .hobbies(Arrays.asList("羽毛球", "乒乓球", "徒步"))
                .build();
        Father father = Father.builder().sonList(Arrays.asList(son1, son2)).build();

        val collect = father.getSonList()
                .stream()
                .map(son -> son.getHobbies()
                        .stream()
                        .map(hobby -> Hobby.builder()
                                .name(son.getName())
                                .age(son.getAge())
                                .hobby(hobby)
                                .build())
                        .collect(Collectors.toList())
                ).collect(Collectors.toList());

        collect.stream().forEach(System.out::println);
    }

    @Test
    public void test2() {
        Son son1 = Son.builder()
                .name("张三")
                .age(23)
                .hobbies(Arrays.asList("篮球", "足球"))
                .build();
        Son son2 = Son.builder()
                .name("李四")
                .age(18)
                .hobbies(Arrays.asList("羽毛球", "乒乓球", "徒步"))
                .build();
        Father father = Father.builder().sonList(Arrays.asList(son1, son2)).build();

        List<Hobby> hobbies = new ArrayList<>();
        father.getSonList().forEach(son -> son.getHobbies()
                .forEach(hobby -> hobbies.add(Hobby.builder()
                        .name(son.getName())
                        .age(son.getAge())
                        .hobby(hobby)
                        .build()))
        );

        hobbies.stream().forEach(System.out::println);
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Father {
        private List<Son> sonList;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Son {
        private String name;
        private Integer age;
        private List<String> hobbies;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Hobby {
        private String name;
        private Integer age;
        private String hobby;
    }
}
