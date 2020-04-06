package com.code.fuqinqin.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bootstrap {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ApplicationContext load(String path){
        return new ClassPathXmlApplicationContext(path);
    }
}
