package com.code.fuqinqin.spring.scanner;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.Proxy;

/**
 * 为注解@Smile的接口动态生成代理对象
 * @author fuqinqin
 * @date 2020-03-27
 * */
public class SmileFactoryBean<T> implements FactoryBean<T>, InitializingBean {
    /**
     * 代理的接口
     * */
    private Class<T> smileInterface;
    /**
     * 代理对象
     * */
    private T smileObject;

    public SmileFactoryBean(Class<T> smileInterface){
        if(smileInterface==null || !smileInterface.isInterface()){
            throw new IllegalArgumentException(smileInterface + " must be a interface...");
        }
        this.smileInterface = smileInterface;
    }

    @Override
    public T getObject() throws Exception {
        if(this.smileObject == null){
            this.smileObject = createProxy();
        }
        return this.smileObject;
    }

    @Override
    public Class<?> getObjectType() {
        return smileInterface;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(smileInterface==null || !smileInterface.isInterface()){
            throw new IllegalArgumentException(smileInterface + " must be a interface...");
        }
        if(this.smileObject == null){
            this.smileObject = createProxy();
        }
    }

    private T createProxy(){
        return (T) Proxy.newProxyInstance(
                smileInterface.getClassLoader(),
                new Class<?>[]{smileInterface},
                (proxy, method, args) -> {
                    System.out.println(method.getName() + " is called with params " + (args==null ? null : args[0]));
                    // 注意代理方法的返回值不能是基本类型, 否则返回null会发生空指针异常.
                    return null;
                });
    }
}
