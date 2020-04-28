package com.code.fuqinqin.mybatis.plugin;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 改变查询参数插件
 * @author fuqinqin
 * @date 2020-04-09
 * */
@Intercepts({
        @Signature(type = StatementHandler.class, method = "parameterize", args = java.sql.Statement.class)
})
public class ChangeParameterPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Method method = invocation.getMethod();
        Object target = invocation.getTarget();
        System.out.println("ChangeParameterPlugin-intercept print args:");
        for (Object arg : args) {
            System.out.println("\targ = "+arg);
        }
        System.out.println("ChangeParameterPlugin-intercept print method="+method.getName());
        System.out.println("ChangeParameterPlugin-intercept print target="+target);

        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("ChangeParameterPlugin-intercept sql语句中的参数："+value);
        metaObject.setValue("parameterHandler.parameterObject", 2L);

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        System.out.println("执行ChangeParameterPlugin-plugin， target="+target);
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("执行ChangeParameterPlugin-setProperties， properties="+properties);
    }
}
