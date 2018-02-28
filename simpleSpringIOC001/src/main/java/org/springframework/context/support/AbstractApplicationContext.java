package org.springframework.context.support;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourcePatternResolver;

/**
 * Created by lcy on 18/2/26.
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {


    @Override
    public void refresh() {
        obtainFreshBeanFactory();
    }

    protected void obtainFreshBeanFactory() {

    }

    protected ResourcePatternResolver getResourcePatternResolver() {
        return null;
    }

}
