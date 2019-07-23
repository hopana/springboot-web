package com.hp.springboot.utils.encrypt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * DES加算法
 *
 * @author hupan
 * @since 2018-03-28 11:48
 */
public class DesUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DesUtil.class);

    public static void main(String[] args) throws Exception {
        String data = "123456dgdsgryrz";
        String key = "12345678901gssgfsgrwsd64643";
        String result = encryptDES(data, key);
        String resultq = encryptDES(data, key);
        String resultresult = decryptDES(result, key);

        String encryptStr = encryptDES(data, key);
        System.err.println(encryptStr);
        System.err.println(decryptDES(encryptStr, key));

        final String tripleDESKey = "cf410f84904a44cc8a7f48fc4134e8f9";
        String src = "good after noon every body!";

        String tripleEncryptStr = encryptTripleDES(src, tripleDESKey);
        System.err.println(tripleEncryptStr);
        System.err.println(decryptTripleDES(tripleEncryptStr, tripleDESKey));
    }

    /**
     * DES加密
     *
     * @param src 待加密的字符串
     * @param key 加密键byte数组
     * @return 加密后的字符串
     * @throws Exception
     */
    public static String encryptDES(final String src, final String key) throws Exception {
        return Base64.getEncoder().encodeToString(encryptDES(src.getBytes(), key.getBytes()));
    }

    /**
     * Description 根据键值进行加密
     *
     * @param src 待加密的字符串
     * @param key 加密键byte数组
     * @return 加密后的字符串
     * @throws Exception 抛出异常
     */
    private static byte[] encryptDES(final byte[] src, final byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

        return cipher.doFinal(src);
    }

    /**
     * DES解密
     *
     * @param src 待解密的字符串
     * @param key 加密键byte数组
     * @return 解密后的字符串
     * @throws Exception 抛出异常
     */
    public static String decryptDES(final String src, final String key) throws Exception {
        if (src == null) {
            return null;
        }

        return new String(decryptDES(Base64.getDecoder().decode(src), key.getBytes()));
    }

    /**
     * DES解密
     *
     * @param src 待解密的字符串
     * @param key 加密密钥字节数组
     * @return 解密后的字节数组
     * @throws Exception 抛出异常
     */
    private static byte[] decryptDES(final byte[] src, final byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);

        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey securekey = keyFactory.generateSecret(dks);

        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");

        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        return cipher.doFinal(src);
    }

    /**
     * 3DES加密，key必须是长度大于等于24位
     *
     * @param src 待加密的字符串
     * @param key 加密密钥字节数组
     * @return 加密后的字节数组
     * @throws Exception 抛出异常
     */
    public static String encryptTripleDES(final String src, final String key) throws Exception {
        final byte[] bytes = encryptTripleDES(src.getBytes("UTF-8"), key.getBytes("UTF-8"));

        return Base64.getEncoder().encodeToString(bytes).replaceAll("\r", "").replaceAll("\n", "");
    }

    /**
     * 3DES加密，key必须是长度大于等于24位
     *
     * @param src 待加密的字节数组
     * @param key 加密密钥字节数组
     * @return 加密后的字节数组
     * @throws Exception 抛出异常
     */
    private static byte[] encryptTripleDES(final byte[] src, final byte[] key) throws Exception {
        final DESedeKeySpec dks = new DESedeKeySpec(key);
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        final SecretKey securekey = keyFactory.generateSecret(dks);

        final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, securekey);

        return cipher.doFinal(src);
    }

    /**
     * 3DES解密，密钥必须是长度大于等于24
     *
     * @param src 待解密的字符串
     * @param key 加密密钥字节数组
     * @return 解密后的字符串
     * @throws Exception 抛出异常
     */
    public static String decryptTripleDES(final String src, final String key) throws Exception {
        // --通过base64,将字符串转成byte数组
        final byte[] bytesrc = Base64.getDecoder().decode(src);
        // --解密的key
        final DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        final SecretKey securekey = keyFactory.generateSecret(dks);

        // --Chipher对象解密
        final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, securekey);
        final byte[] retByte = cipher.doFinal(bytesrc);

        return new String(retByte);
    }

    /**
     *
     * 3DES解密，密钥必须是长度大于等于24
     *
     * @param src 待解密的字节数组
     * @param key 加密密钥字节数组
     * @return 解密后的字节数组
     * @throws Exception 抛出异常
     */
    private static byte[] decryptTripleDES(final byte[] src, final byte[] key) throws Exception {
        // --解密的key
        final DESedeKeySpec dks = new DESedeKeySpec(key);
        final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        final SecretKey securekey = keyFactory.generateSecret(dks);

        // --Chipher对象解密
        final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, securekey);

        return cipher.doFinal(src);
    }

}
