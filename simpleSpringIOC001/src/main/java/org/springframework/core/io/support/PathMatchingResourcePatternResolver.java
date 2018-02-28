package org.springframework.core.io.support;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.ResourcePatternResolver;

/**
 * Created by lcy on 18/2/27.
 */
public class PathMatchingResourcePatternResolver implements ResourcePatternResolver {

    private ResourceLoader resourceLoader;

    public PathMatchingResourcePatternResolver() {
        this.resourceLoader = new DefaultResourceLoader();
    }

    public Resource getResource(String location) {
        return resourceLoader.getResource(location);
    }

    public Resource[] getResources(String locationPattern){
        return null;
    }

}
