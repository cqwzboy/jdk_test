package com.code.fuqinqin.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理是继承代理，通过ASM字节码框架修改字节码生成新的子类，重写并增强方法的功能。
 *
 * @author fuqinqin3
 * @date 2020-11-09
 * */
public class CglibHandler implements MethodInterceptor {
    private Object target;

    public Object getInstance(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object result;
        System.out.println("代理前");
        result = methodProxy.invokeSuper(o, objects);
        System.out.println("代理后");
        return result;
    }
}
