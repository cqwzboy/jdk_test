package com.code.fuqinqin.org.alibaba.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

import java.util.Date;

/**
 * Fastjson测试
 *
 * @author fuqinqin3
 * @date 2020-10-21
 * */
public class FastjsonTest {
    private Person person = new Person(1001, "zhangsan", new Date(), 1241414141L);

    @Test
    public void test(){
        System.out.println(person);
        String personJson = JSON.toJSONString(person);
        System.out.println(personJson);
        System.out.println(JSON.parseObject(personJson, Person.class));
    }

    @Test
    public void beanToArray(){
        String personJson = JSON.toJSONString(person, SerializerFeature.BeanToArray);
        System.out.println(personJson);
        System.out.println(JSON.parseObject(personJson, Feature.SupportArrayToBean));
    }

    @Test
    public void test1(){
        int count = 0;
        for (int i = 0; i < 4; i++) {
            System.out.println("-- " + i + " --");
            if(i==2 && count<=2){
                System.out.println("== " + i + " ==");
                i--;
                count++;
            }
        }
    }

}
