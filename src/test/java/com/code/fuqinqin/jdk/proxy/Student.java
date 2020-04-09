package com.code.fuqinqin.jdk.proxy;

/**
 * @author fuqinqin
 * @date 2020-04-09
 * */
public class Student implements Person {
    @Override
    public void sayHello(String name) {
        System.out.println("hello, my name is "+name);
    }
}
