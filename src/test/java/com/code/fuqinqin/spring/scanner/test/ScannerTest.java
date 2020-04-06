package com.code.fuqinqin.spring.scanner.test;

import com.code.fuqinqin.spring.Bootstrap;
import com.code.fuqinqin.spring.scanner.p1.ISmileService1;
import com.code.fuqinqin.spring.scanner.p2.ISmileService2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * 测试类
 *
 * @author fuqinqin
 * @date 2020-03-27
 * */
public class ScannerTest extends Bootstrap {
    @Test
    public void test(){
        ApplicationContext ctx = load("applicationContext.xml");
        String[] definitionNames = ctx.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            System.out.println(definitionName);
        }

        ISmileService1 service1 = ctx.getBean(ISmileService1.class);
        System.out.println("service1: "+service1);  // 为null
        service1.smile1();
        service1.speak("zhangsan");

        ISmileService2 service2 = ctx.getBean(ISmileService2.class);
        service2.smile2();
        service2.eat("香蕉");
    }
}
