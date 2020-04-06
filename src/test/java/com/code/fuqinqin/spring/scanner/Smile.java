package com.code.fuqinqin.spring.scanner;

import java.lang.annotation.*;

/**
 * 设定被扫描的注解
 * @author fuqinqin
 * @date 2020-03-27
 * */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Smile {
    String value() default "";
}
