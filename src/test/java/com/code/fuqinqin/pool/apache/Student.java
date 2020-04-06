package com.code.fuqinqin.pool.apache;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private Long id;
    private String name;
    private Integer age;
    private Integer hashCode;
}
