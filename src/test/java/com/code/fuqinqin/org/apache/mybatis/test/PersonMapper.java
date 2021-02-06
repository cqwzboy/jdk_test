package com.code.fuqinqin.org.apache.mybatis.test;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p></p>
 *
 * @author fuqinqin
 * @version 1.0
 * @date 2021/2/6 17:50
 */
public interface PersonMapper {
    long insert(PersonEntity entity);
    int delete(@Param("id") Long id);
    PersonEntity queryById(@Param("id") Long id);
    List<PersonEntity> queryAll();
}
