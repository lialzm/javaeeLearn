import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;

/**
 * Created by lcy on 18/2/26.
 */
public class MyTest {


    /**
     * 资源定位即获取Resource对象
     */
    @Test
    public void getResource() {
        FileSystemXmlApplicationContext context = new FileSystemXmlApplicationContext();
        Resource resource = context.getResource("file://本地xml路径");
        try {
            File file = resource.getFile();
            System.out.println(file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
