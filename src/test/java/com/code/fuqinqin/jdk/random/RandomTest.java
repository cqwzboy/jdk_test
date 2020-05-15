package com.code.fuqinqin.jdk.random;

/**
 * 随机数测试类
 * @author fuqinqin3
 * @date 2020-04-28
 * */
public class RandomTest {
    public void test(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(String.valueOf((int) (Math.random()*10)));
        }
        System.out.println("#88345 随机数："+sb.toString());
    }
}
