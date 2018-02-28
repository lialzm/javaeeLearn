package org.springframework.core.io;

import java.io.File;
import java.io.IOException;

/**
 * Created by lcy on 18/2/9.
 */
public interface Resource {

    File getFile() throws IOException;



}
