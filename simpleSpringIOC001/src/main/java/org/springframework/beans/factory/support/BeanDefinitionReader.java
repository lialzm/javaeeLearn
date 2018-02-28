package org.springframework.beans.factory.support;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * Created by lcy on 18/2/12.
 */
public interface BeanDefinitionReader {

    ResourceLoader getResourceLoader();

    int loadBeanDefinitions(Resource resource) throws Exception;

    int loadBeanDefinitions(Resource... resources) throws Exception;

    int loadBeanDefinitions(String location) throws Exception;

    int loadBeanDefinitions(String... locations) throws Exception;

}
