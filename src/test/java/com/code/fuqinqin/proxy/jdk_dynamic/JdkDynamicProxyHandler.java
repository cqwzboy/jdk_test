package com.code.fuqinqin.proxy.jdk_dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理会根据被代理对象生成一个继承了Proxy类，并实现了该业务接口的jdk代理类，该类的字节码会被传进去的ClassLoader加载，创建了jdk代理对象实例
 *
 * @author fuqinqin3
 * @date 2020-11-09
 * */
public class JdkDynamicProxyHandler implements InvocationHandler {
    private Object target;

    public JdkDynamicProxyHandler(Object target){
        super();
        this.target = target;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                this.target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("动态代理前");
        result = method.invoke(target, args);
        System.out.println("动态代理后");
        return result;
    }
}
