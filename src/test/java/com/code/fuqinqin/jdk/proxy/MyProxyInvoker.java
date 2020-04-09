package com.code.fuqinqin.jdk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author fuqinqin
 * @date 2020-04-09
 * */
public class MyProxyInvoker implements InvocationHandler {
    private Object object;

    public MyProxyInvoker(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("execute proxy begin...");
        System.out.println("Proxy: "+proxy.getClass().getName());
        System.out.println("Method: "+method.getName());
        for (Object arg : args) {
            System.out.println("\t arg="+arg);
        }
        method.invoke(object, args);
        System.out.println("exeute proxy completed...");
        return null;
    }
}
