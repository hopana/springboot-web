package com.hp.springboot.auth;

import com.hp.springboot.utils.HttpClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * JSON请求测试
 *
 * @author hupan
 * @since 2018-04-09 15:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostJsonTest {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testPostJson() {
        String response = HttpClientUtil.doPostJson("http://localhost:8088/springboot/test/json", "{\"name\":\"大伟\"}");
        System.out.println(response);
    }

}
