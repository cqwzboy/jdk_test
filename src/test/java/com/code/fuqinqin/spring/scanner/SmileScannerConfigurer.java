package com.code.fuqinqin.spring.scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;

/**
 * 注解@Smile配置器，即扫描触发的地方
 * @author fuqinqin
 * @date 2020-03-27
 * */
public class SmileScannerConfigurer implements BeanDefinitionRegistryPostProcessor, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(SmileScannerConfigurer.class);

    private String basePackages;

    /**
     * 默认为@Smile注解
     * */
    private Class<? extends Annotation> markedAnnotation = Smile.class;

    /**
     * beanName生成器
     * */
    private BeanNameGenerator beanNameGenerator;

    public void setBasePackages(String basePackages) {
        this.basePackages = basePackages;
    }

    public void setMarkedAnnotation(Class<? extends Annotation> markedAnnotation) {
        this.markedAnnotation = markedAnnotation;
    }

    public void setBeanNameGenerator(BeanNameGenerator beanNameGenerator) {
        this.beanNameGenerator = beanNameGenerator;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(basePackages == null) {
            throw new IllegalArgumentException("the property 'basePackages' can not be null!");
        }
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        SmileScanner smileScanner = new SmileScanner(beanDefinitionRegistry);
        // 默认只扫描@Smile注解，再加上在SMileScanner的构造器里useDefaultFilters=false，所以指定包
        // 将只扫描@Smile注解
        smileScanner.addIncludeFilter(new AnnotationTypeFilter(markedAnnotation));
        if(beanNameGenerator == null){
            beanNameGenerator = new SmileAnnotationBaneNameGenerator(markedAnnotation);
        }
        smileScanner.setBeanNameGenerator(beanNameGenerator);
        // 开始扫描并且注册bean
        int beanCount = smileScanner.scan(basePackages.split(","));
        LOGGER.info("The count of registered bean is " + beanCount);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
