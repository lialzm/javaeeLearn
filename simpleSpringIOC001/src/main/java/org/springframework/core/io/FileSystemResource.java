package org.springframework.core.io;

import java.io.File;
import java.io.IOException;

/**
 * Created by lcy on 18/2/12.
 */
public class FileSystemResource implements Resource {

    private String path;
    private File file;
    public FileSystemResource(String path) {
        this.path = path;
        this.file= new File(path);
    }

    public File getFile() throws IOException {
        return file;
    }

}
