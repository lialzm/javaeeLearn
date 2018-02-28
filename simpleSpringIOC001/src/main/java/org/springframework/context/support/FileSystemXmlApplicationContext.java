package org.springframework.context.support;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

/**
 * Created by lcy on 18/2/12.
 */
public class FileSystemXmlApplicationContext  extends AbstractXmlApplicationContext {


    @Override
    protected Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }


    public Object getBean(String name) {
        return null;
    }
}
