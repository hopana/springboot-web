package com.hp.springboot.controller;

import com.hp.springboot.result.CodeMsg;
import com.hp.springboot.result.Result;
import com.hp.springboot.utils.CommUtil;
import com.hp.springboot.utils.DynamicProperties;
import com.hp.springboot.utils.encrypt.DesUtil;
import com.hp.springboot.utils.encrypt.RSAUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.security.interfaces.RSAPrivateKey;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 对外接口
 *
 * @author hupan
 * @since 2018-03-28 15:16
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @ResponseBody
    @RequestMapping("/auth1")
    public Result auth1(final HttpServletRequest request) throws Exception {
        String sign = request.getParameter("sign");
        if (StringUtils.isBlank(sign)) {
            return Result.error(CodeMsg.INVALID_PARAMETERS);
        }

        //得到所有请求的参数
        Map<String, String> params = getParameterMap(request);
        LOGGER.info("params --->>> {}", params);

        System.out.println(new String(params.get("gender").getBytes("GBK")));

        // 判断sign是否正确
        if (!sign.equals(CommUtil.getParamSign(params, DynamicProperties.getString("des.key")))) {
            return Result.error(CodeMsg.SIGN_ERROR);
        }

        return Result.success();
    }

    @ResponseBody
    @RequestMapping("/auth2")
    public Result auth2(final HttpServletRequest request) throws Exception {
        String token = request.getHeader("Authorization");
        String sign = request.getParameter("sign");

        LOGGER.info("token={}", token);
        LOGGER.info("sign={}", sign);

        if (StringUtils.isBlank(token)) {
            return Result.error(CodeMsg.EMPTY_TOKEN);
        }

        if (StringUtils.isBlank(sign)) {
            return Result.error(CodeMsg.INVALID_PARAMETERS);
        }

        LOGGER.info("token.base64={}", Base64.decodeBase64(token.getBytes()));
        // 判断token是否正确
        String privateKeyString = DynamicProperties.getString("rsa.privatekey");
        RSAPrivateKey privateKey = RSAUtil.loadPrivateKeyByStr(privateKeyString);
        if (!sign.equals(new String(RSAUtil.decrypt(privateKey, Base64.decodeBase64(token.getBytes()))))) {
            return Result.error(CodeMsg.AUTH_FAIL);
        }

        Map<String, String> params = getParameterMap(request);

        // 判断sign是否正确
        if (!sign.equals(CommUtil.getParamSign(params, DynamicProperties.getString("des.key")))) {
            return Result.error(CodeMsg.SIGN_ERROR);
        }

        return Result.success(CodeMsg.SUCCESS);
    }

    private Map<String, String> getParameterMap(HttpServletRequest request) {
        Enumeration<String> parameterNames = request.getParameterNames();
        Map<String, String> params = new HashMap<>();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (!"sign".equals(paramName)) {
                params.put(paramName, request.getParameter(paramName));
            }
        }

        return params;
    }

}
