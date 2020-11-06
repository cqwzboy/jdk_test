package com.code.fuqinqin.cache.ehcache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author fuqinqin3
 * @date 2020-10-30
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {
    private int no;
    private String name;
    private Date birthday;
    private List<String> hobies;
}
