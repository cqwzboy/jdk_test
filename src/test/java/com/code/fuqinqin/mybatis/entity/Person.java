package com.code.fuqinqin.mybatis.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author fuqinqin
 * @date 2020-04-09
 * */
@Data
public class Person {
    private Long id;
    private String name;
    private Integer age;
    private Date birthday;
}
