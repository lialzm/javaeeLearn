package org.springframework.core.io;

/**
 * Created by lcy on 18/2/12.
 */
public class DefaultResourceLoader implements ResourceLoader {


    protected Resource getResourceByPath(String path) {
        return null;
    }

    public Resource getResource(String location) {
        if (location != null && location.startsWith("file")) {
            return getResourceByPath(location);
        }
        return null;
    }

}
