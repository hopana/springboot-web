package com.hp.springboot.controller;

import com.hp.springboot.model.User;
import com.hp.springboot.result.CodeMsg;
import com.hp.springboot.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 测试参数绑定
 *
 * @author hupan
 * @since 2018-04-09 15:52
 */
@Controller
@RequestMapping("/test")
public class TestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @ResponseBody
    @RequestMapping("/testString1")
    public Result string1(String name) {
        // 前台以application/json传json={"name":"大伟"}，这样无法接收，返回{"code":0,"msg":null,"data":null}
        LOGGER.info("------------------test------------------");

        return Result.success(name);
    }

    @ResponseBody
    @RequestMapping("/testString2")
    public Result string2(@RequestBody String name) {
        // 前台以application/json传json={"name":"大伟"}，返回{"code":0,"msg":null,"data":"{\"name\":\"大伟\"}"}
        LOGGER.info("------------------test------------------");

        return Result.success(name);
    }

    @ResponseBody
    @RequestMapping("/testString3")
    public Result string3(final HttpServletRequest request) {
        // 前台以application/json传json={"name":"大伟"}，返回{"code":0,"msg":null,"data":"{\"name\":\"大伟\"}"}
        LOGGER.info("------------------test------------------");
        try {
            String params = IOUtils.toString(request.getInputStream());
            return Result.success(params);
        } catch (IOException e) {
            LOGGER.error("请求异常，{}", e);
        }

        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @ResponseBody
    @RequestMapping("/testJson1")
    public Result json1(User user) {
        // 前台以application/json传json={"name":"大伟"}，这样无法接收，返回{"code":0,"msg":null,"data":{"name":null,"age":0,"idCard":null,"gender":null,"address":null,"remark":null}}
        LOGGER.info("------------------test------------------");

        return Result.success(user);
    }

    @ResponseBody
    @RequestMapping("/testJson2")
    public Result json2(@RequestBody User user) {
        // 前台以application/json传json={"name":"大伟"}，返回{"code":0,"msg":null,"data":{"name":"大伟","age":0,"idCard":null,"gender":null,"address":null,"remark":null}}
        LOGGER.info("------------------test------------------");

        return Result.success(user);
    }

}
