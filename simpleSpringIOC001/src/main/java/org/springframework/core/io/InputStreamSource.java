package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lcy on 18/2/9.
 */
public interface InputStreamSource {


    InputStream getInputStream() throws IOException;

}
