package org.springframework.beans.factory.config;

/**
 * Created by lcy on 18/2/12.
 */
public interface BeanDefinition {

    void setBeanClassName(String beanClassName);


    String getBeanClassName();

}
