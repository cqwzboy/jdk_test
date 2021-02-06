package com.code.fuqinqin.org.apache.mybatis.test;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p></p>
 *
 * @author fuqinqin
 * @version 1.0
 * @date 2021/2/6 17:51
 */
@Data
public class PersonEntity implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private Date birthday;
}
