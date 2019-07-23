package com.hp.springboot;

import com.hp.springboot.utils.HttpClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 上传测试
 *
 * @author hupan
 * @since 2018-04-10 17:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UploadTest {

    @Test
    public void testUploadImgage() {
        String url = "http://localhost:8088/springboot/upload/image";
        File file = new File("D:/pic.jpg");

        try {
            String s = HttpClientUtil.uploadPic(url, file);
            System.out.println(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
