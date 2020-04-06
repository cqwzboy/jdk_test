package com.code.fuqinqin.spring.scanner;

import org.springframework.context.annotation.AnnotationBeanNameGenerator;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 被注解@Smile注解的对象的beanName生成器
 * @author fuqinqin
 * @date 2020-03-27
 * */
public class SmileAnnotationBaneNameGenerator extends AnnotationBeanNameGenerator {
    private Class<? extends Annotation> annotation;

    public SmileAnnotationBaneNameGenerator(Class<? extends Annotation> annotation){
        this.annotation = annotation;
    }

    /**
     * 重写此方法, 使得适配@Mapper注解指定的bean name.
     * 决定generateBeanName方法是否解析此annotationType代表的注解
     * 举例:
     * 若 annotationType = org.springframework.stereotype.Service(@Service)
     * 那么 metaAnnotationTypes = [@Target, @Retention, @Documented, @Component]
     * 即标记annotationType这个注解的注解集合
     * 那么 attributes = {value: ''}
     * @param annotationType 想要解析的注解
     * @param metaAnnotationTypes 注解annotationType的注解集合
     * @param attributes annotationType注解的属性集合
     */
    @Override
    protected boolean isStereotypeWithNameValue(String annotationType, Set<String> metaAnnotationTypes, Map<String, Object> attributes) {
        boolean isStereotype = (Objects.equals(annotationType, annotation.getName()))
                || (metaAnnotationTypes != null
                && metaAnnotationTypes.contains(annotation.getName()));
        return isStereotype && attributes.containsKey("value");
    }
}
