package com.hp.springboot.auth;

import com.hp.springboot.utils.CommUtil;
import com.hp.springboot.utils.DynamicProperties;
import com.hp.springboot.utils.HttpClientUtil;
import com.hp.springboot.utils.encrypt.DesUtil;
import com.hp.springboot.utils.encrypt.RSAUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Auth2Test {

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

        RSAPublicKey publicKey = RSAUtil.loadPublicKeyByStr(DynamicProperties.getString("rsa.publickey"));
        String token = Base64.getEncoder().encodeToString(RSAUtil.encrypt(publicKey, sign.getBytes()));
        System.out.println("encryptToken:" + token);

        Map<String, String> headers = new HashMap<>(1);
        headers.put("Authorization", token);
        String result = HttpClientUtil.doPostWithHeaders("http://localhost:8088/springboot/api/auth2", params, headers);

        System.out.println("请求结果：" + result);
    }

}
