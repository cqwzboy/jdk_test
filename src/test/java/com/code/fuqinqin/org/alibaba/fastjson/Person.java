package com.code.fuqinqin.org.alibaba.fastjson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
public class Person {
    private int id;
    private String name;
    private Date birthday;
    private long sleepTime;
}
