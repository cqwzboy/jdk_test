package com.code.fuqinqin.proxy.jdk_dynamic;

/**
 * @author fuqinqin3
 * @date 2020-11-09
 * */
public class JdkDynamicProxyServiceImpl implements IJdkDynamicProxyService{
    @Override
    public void sayHello(String name) {
        System.out.println("Hello, my name is "+name);
    }
}
