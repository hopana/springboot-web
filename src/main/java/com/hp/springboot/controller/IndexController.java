package com.hp.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * index请求处理
 *
 * @author hupan
 * @since 2018-03-28 15:49
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/")
    public String index(Model model) {
        LOGGER.info("------------------index------------------");

        model.addAttribute("name", "管理员");
        return "index";
    }

    @RequestMapping("/test")
    public String test(String str) {
        LOGGER.info("------------------test------------------");
        LOGGER.info("param --->>> {}", str);

        if (true) {
            throw new RuntimeException("测试异常");
        }
        return "test";
    }
}
