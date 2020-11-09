package com.code.fuqinqin.proxy.cglib;

import org.junit.Test;

public class CglibTest {
    @Test
    public void test(){
        CglibHandler handler = new CglibHandler();
        CglibServiceImpl service = (CglibServiceImpl) handler.getInstance(new CglibServiceImpl());
        service.sayHello("王麻子");
    }
}
