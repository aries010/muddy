package com.linc.muddy.client.spring;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.util.CollectionUtils;

import java.util.Set;

public class MuddyScanner extends ClassPathBeanDefinitionScanner {
    public MuddyScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        if (CollectionUtils.isEmpty(beanDefinitionHolders)) {
            return beanDefinitionHolders;
        }
        for (BeanDefinitionHolder beanDefinitionHolder : beanDefinitionHolders) {
            if (this.getRegistry().containsBeanDefinition(beanDefinitionHolder.getBeanName())) {
                continue;
            }
            this.getRegistry().registerBeanDefinition(beanDefinitionHolder.getBeanName(), beanDefinitionHolder.getBeanDefinition());
        }

        return beanDefinitionHolders;
    }

}
