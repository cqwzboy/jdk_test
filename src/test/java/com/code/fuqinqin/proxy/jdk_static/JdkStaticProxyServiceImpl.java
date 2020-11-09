package com.code.fuqinqin.proxy.jdk_static;

/**
 * @author fuqinqin3
 * @date 2020-11-09
 * */
public class JdkStaticProxyServiceImpl implements IJdkStaticProxyService {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello, my name is "+name);
    }
}
