package com.code.fuqinqin.mybatis.plugin;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.util.Properties;

/**
 * 分页插件
 * @author fuqinqin
 * @date 2020-04-09
 * */
@Intercepts({
        @Signature(type = Executor.class, method = "getBoundSql", args = {})
})
public class PagePlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MetaObject metaObject = SystemMetaObject.forObject(invocation.getTarget());
        Object preValue = metaObject.getValue("parameterHandler.boundSql");
        System.out.println("PagePlugin-setProperties, preValue="+preValue);

        return invocation.proceed();
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("PagePlugin-setProperties， properties="+properties);
    }
}
