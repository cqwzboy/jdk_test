package com.code.fuqinqin.proxy.jdk_dynamic;

import org.junit.Test;

/**
 * @author fuqinqin3
 * @date 2020-11-09
 * */
public class JdkDynamicProxyTest {
    @Test
    public void test(){
        JdkDynamicProxyHandler handler = new JdkDynamicProxyHandler(new JdkDynamicProxyServiceImpl());
        IJdkDynamicProxyService service = (IJdkDynamicProxyService) handler.getProxy();
        service.sayHello("李四");
    }
}
