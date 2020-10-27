package com.code.fuqinqin.org.alibaba.fastjson;

import com.alibaba.fastjson.JSON;
import org.badger.common.lang.util.DateUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fuqinqin3
 * @date 2020-10-26
 * */
public class JSONFieldTest {

    @Test
    public void test(){
        JSONFieldEntity entity = new JSONFieldEntity();
        entity.setId(1325251L);
        entity.setName("张三");
        entity.setAge(29);
        entity.setBirthday(DateUtil.parse("1991-11-06 12:00:00"));
        entity.setHobies(Arrays.asList("篮球", "足球", "乒乓球", "单车"));
        Map<String, Integer> grades = new HashMap<>();
        grades.put("语文", 110);
        grades.put("数学", 140);
        grades.put("英语", 130);
        grades.put("理综", 250);
        entity.setGrades(grades);
        entity.setContent("{}");

        String jsonString = JSON.toJSONString(entity);
        System.out.println(jsonString);

        JSONFieldEntity entity1 = JSON.parseObject(jsonString, JSONFieldEntity.class);
        System.out.println(entity1);
        System.out.println(entity1.getName().getClass().getName());
    }

}
