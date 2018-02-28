package org.springframework.core.io;

/**
 * Created by lcy on 18/2/12.
 */
public interface ResourceLoader {

    Resource getResource(String location);

}
