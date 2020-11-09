package com.code.fuqinqin.proxy.jdk_static;

import org.junit.Test;

/**
 * @author fuqinqin3
 * @date 2020-11-09
 * */
public class JdkStaticProxyTest {
    private JdkStaticProxy jdkStaticProxy = new JdkStaticProxy(new JdkStaticProxyServiceImpl());

    @Test
    public void test(){
        jdkStaticProxy.sayHello("张三");
    }
}
