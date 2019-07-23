package com.hp.springboot.auth;

import com.hp.springboot.utils.CommUtil;
import com.hp.springboot.utils.DynamicProperties;
import com.hp.springboot.utils.HttpClientUtil;
import com.hp.springboot.utils.encrypt.DesUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Auth1Test {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRequest() throws Exception {
        Map<String, String> params = new HashMap<>();
        String desKey = DynamicProperties.getString("des.key");

        params.put("name", "David");
        params.put("age", "30");
        params.put("gender", "男");
        params.put("idCard", DesUtil.encryptDES("350524199010188457", desKey));
        params.put("address", "深圳市宝安区");
        params.put("remark", "");
        String sign = CommUtil.getParamSign(params, desKey);
        params.put("sign", sign);

        String result = HttpClientUtil.doPost("http://localhost:8088/springboot/api/auth1", params);

        System.out.println("请求结果：" + result);
    }

}
