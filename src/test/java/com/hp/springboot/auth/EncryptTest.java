package com.hp.springboot.auth;

import com.hp.springboot.utils.DynamicProperties;
import com.hp.springboot.utils.encrypt.RSAUtil;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryptTest {

    @Test
    public void testGenKeyPair() {
        Map<String, String> keyPair = RSAUtil.genKeyPair();

        System.out.println(keyPair.toString());
    }

    @Test
    public void testMatch1() throws Exception {
        String token = "Bs67HuVSbBUn7U0ifzG+tMGGSlLwG6zvEbaROiDyG1DXjhaBhs+FjqCVf6+I7/R1OkGa+hEiaWHBQSzwfgWxMzgNWdOKC9LHKaJ3zH9Uy8qMVPZY3Qd8x1VyXDb+xrpkkf8Tu0320cMD9w4eQHvieqq5Xmg3RNwgsYiUOM0W2Ic=";
        String sign = "5cf27ed44344ea3b39901b45b9364298ac50f8b7b60a9109466f7d4e32bc8b42";

        RSAPrivateKey rsaPrivateKey = RSAUtil.loadPrivateKeyByStr(DynamicProperties.getString("rsa.privatekey"));
        String decryptToken = new String(RSAUtil.decrypt(rsaPrivateKey, Base64.decodeBase64(token)));

        System.out.println(decryptToken);
    }

    @Test
    public void testMatch2() throws Exception {
        String sign = "5cf27ed44344ea3b39901b45b9364298ac50f8b7b60a9109466f7d4e32bc8b42";

        RSAPrivateKey rsaPrivateKey = RSAUtil.loadPrivateKeyByStr(DynamicProperties.getString("rsa.privatekey"));
        RSAPublicKey rsaPublicKey = RSAUtil.loadPublicKeyByStr(DynamicProperties.getString("rsa.publickey"));
        String ecryptToken1 = Base64.encodeBase64String(RSAUtil.encrypt(rsaPrivateKey, sign.getBytes()));
        String ecryptToken2 = Base64.encodeBase64String(RSAUtil.encrypt(rsaPublicKey, sign.getBytes()));

        System.out.println("ecryptToken1=" + ecryptToken1);
        System.out.println("ecryptToken2=" + ecryptToken2);

        System.out.println(ecryptToken1.equals(ecryptToken2));
    }

}
