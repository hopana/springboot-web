package com.hp.springboot.utils.encrypt;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.*;

/**
 * 哈希加密工具
 *
 * @author hupan
 * @since 2018-03-28 15:58
 */
public class SHA1Util {
    private static String byteToHexString(byte ib) {
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0F];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }

    private static String byteArrayToHexString(byte[] bytearray) {
        String strDigest = "";
        for (int i = 0; i < bytearray.length; i++) {
            strDigest += byteToHexString(bytearray[i]);
        }
        return strDigest;
    }

    public static String encrypt(String input) {
        try {
            MessageDigest alga = MessageDigest.getInstance("SHA-1");
            alga.update(input.getBytes("utf-8"));
            byte[] digesta = alga.digest();
            return byteArrayToHexString(digesta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getParamSign(Map<String, String> par, String key) {
        StringBuffer sb = new StringBuffer();
        Collection<String> keyset = par.keySet();
        List<String> list = new ArrayList<String>(keyset);
        Collections.sort(list);
        StringBuilder param = new StringBuilder();
        for (String k : list) {
            //空字符串也要签名
            String value = StringUtils.isNotEmpty(par.get(k)) ? par.get(k) : "";
            param.append(k).append("=").append(value).append("&");
        }
        if (param.length() > 0) {
            param = new StringBuilder(param.substring(0, param.length() - 1));
        }
        if (StringUtils.isNotEmpty(key)) {
            param.append("&key=").append(key);
        }

        return SHA1Util.encrypt(param.toString());
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(encrypt("device_version=-1&merchant_order_id=1435299528016956137&merchant_uid=18093&notify_args=&pay_time=2015-06-2614:19:06&pay_type=10&key=21788681014270070736505344717177"));
    }

}