package com.code.fuqinqin.proxy.jdk_static;

/**
 * jdk静态代理实现比较简单，一般是直接代理对象直接包装了被代理对象
 *
 * @author fuqinqin3
 * @date 2020-11-09
 * */
public class JdkStaticProxy implements IJdkStaticProxyService{
    private JdkStaticProxyServiceImpl jdkStaticProxyService;

    public JdkStaticProxy(JdkStaticProxyServiceImpl jdkStaticProxyService){
        this.jdkStaticProxyService = jdkStaticProxyService;
    }

    @Override
    public void sayHello(String name) {
        System.out.println("sya hello before");
        jdkStaticProxyService.sayHello(name);
        System.out.println("sya hello after");
    }
}
