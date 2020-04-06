package com.code.fuqinqin.spring.scanner;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

import java.util.Set;

/**
 * 注解@Smile被扫描的逻辑类，核心类
 * @author fuqinqin
 * @date 2020-03-27
 * */
public class SmileScanner extends ClassPathBeanDefinitionScanner {
    public SmileScanner(BeanDefinitionRegistry registry) {
        // 禁用掉默认的扫描选择条件
        // 默认只扫描被@Component标记的类,
        // 当然了衍生注解(@Controller、@Service、@Repository)也会被扫描
        super(registry, false);
    }

    /**
     * 默认情况下只有顶层具体类才会通过
     * 只返回是接口的beanDefinition
     * @param beanDefinition bean
     * @return true / false
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface()
                && beanDefinition.getMetadata().isIndependent();
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        for (BeanDefinitionHolder holder : beanDefinitionHolders) {
            convert2SmileFactoryBean(holder.getBeanDefinition());
        }
        return beanDefinitionHolders;
    }

    /**
     * 修改原有@Smile标注接口的beanDefinition，将其转化为SmileFactoryBean的beanDefinition
     * */
    private void convert2SmileFactoryBean(BeanDefinition beanDefinition){
        // 强转
        GenericBeanDefinition smileFactoryDefinition = (GenericBeanDefinition) beanDefinition;

        // 只能拿到接口名, 不能拿到Class对象, 因为此时还没有被类加载器加载
        String smileInterfaceName = beanDefinition.getBeanClassName();
        smileFactoryDefinition.setBeanClass(SmileFactoryBean.class);

        // 使用构造函数注入
        // 这里给的只是接口的完全限定名, 而不是Class对象, 因为Spring初始化的时候
        // 会自动将字符串转化成对应的类型, 而对应这里将会使用的是ClassEditor转化功能.
        // 之所以不用Class, 是因为对应Class文件此时还没有被类加载器加载
        smileFactoryDefinition.getConstructorArgumentValues()
                .addIndexedArgumentValue(0, smileInterfaceName);
    }
}
