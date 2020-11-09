package com.code.fuqinqin.spring.beans;

import com.code.fuqinqin.spring.Bootstrap;
import org.junit.Test;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author fuqinqin3
 * @date 2020-11-09
 * */
public class SpringBeanTest extends Bootstrap {
    @Test
    public void test(){
        ApplicationContext context = super.load("beanApplicationContext.xml");
        show(context);

        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(D.class);
        builder.addPropertyValue("d", "赵六");
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) context;
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getBeanFactory();
        beanFactory.registerBeanDefinition("d", beanDefinition);

        show(context);
    }

    private void show(ApplicationContext context){
        int beanDefinitionCount = context.getBeanDefinitionCount();
        System.out.println("beanDefinitionCount = "+beanDefinitionCount);
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
        System.out.println("-------------------------------------------------");
    }
}
