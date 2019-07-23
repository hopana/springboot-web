package com.hp.springboot.validate;

import com.hp.springboot.utils.DynamicProperties;
import com.hp.springboot.utils.HttpClientUtil;
import com.hp.springboot.utils.encrypt.RSAUtils;
import com.hp.springboot.utils.encrypt.SHA1Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGenKeyPair() {
        Map<String, String> keyPair = RSAUtils.genKeyPair();

        System.out.println(keyPair.toString());
    }

    @Test
    public void testRequest() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("name", "hupan");
        params.put("age", "30");
        params.put("gender", "男");
        params.put("address", "深圳市宝安区");


        String publicKeyString = DynamicProperties.getString("rsa.publickey");
        RSAPublicKey publicKey = RSAUtils.loadPublicKeyByStr(publicKeyString);

        String sign = SHA1Util.getParamSign(params, publicKeyString);
        params.put("sign", new String((RSAUtils.encrypt(publicKey, sign.getBytes()))));

        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", new String(RSAUtils.encrypt(publicKey, sign.getBytes())));

        String result = HttpClientUtil.doPostWithHeaders("http://localhost:8088/springboot/api/auth", params, headers);

        System.out.println(result);
    }

}
