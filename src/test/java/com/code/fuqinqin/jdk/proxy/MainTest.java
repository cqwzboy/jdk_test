package com.code.fuqinqin.jdk.proxy;

import java.lang.reflect.Proxy;

/**
 * @author fuqinqin
 * @date 2020-04-09
 * */
public class MainTest {
    public static void main(String[] args){
        Student student = new Student();
        MyProxyInvoker proxy = new MyProxyInvoker(student);
        Person person = (Person) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                student.getClass().getInterfaces(),
                proxy);
        person.sayHello("张三");
    }
}
