package com.linc.muddy.client.spring;

import com.linc.muddy.client.core.MuddyHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.type.filter.AssignableTypeFilter;

public class MuddyScannerConfigurer implements BeanDefinitionRegistryPostProcessor {
    private String basePackage;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        MuddyScanner scan = new MuddyScanner(registry);
        scan.addIncludeFilter(new AssignableTypeFilter(MuddyHandler.class));
        scan.scan(basePackage);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
