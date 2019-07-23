package com.hp.springboot.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 通用工具类
 *
 * @author hupan
 * @since 2018-03-30 9:53
 */
public class CommUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommUtil.class);

    public static String getParamSign(Map<String, String> par, String key) {
        TreeMap<String, String> paramMap = new TreeMap<>(par);
        StringBuilder param = new StringBuilder();

        for (String k : paramMap.keySet()) {
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

        LOGGER.info("params --->>> {}", param);

        return DigestUtils.sha256Hex(param.toString());
    }
}
