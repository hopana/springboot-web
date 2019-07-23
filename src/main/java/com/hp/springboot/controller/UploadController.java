package com.hp.springboot.controller;

import com.hp.springboot.utils.ImageUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 文件上传入口
 *
 * @author hupan
 * @since 2018-03-07 15:49
 */
@RestController
@RequestMapping("/upload")
@PropertySource(value= {"classpath:config.properties"})
public class UploadController {
    private final static Logger LOGGER = LoggerFactory.getLogger(UploadController.class);

    @Value("${image.upload.path}")
    private String imageSavePath;
    @Value("${image.web.path}")
    private String imageWebPath;
    @Value("${image.max.size}")
    private String imageMaxSize;

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public String uploadImage(final HttpServletRequest request, @RequestParam(name = "file", required = false) MultipartFile multipartFile) {
        String path = "";
        if (multipartFile == null) {
            LOGGER.info("上传图片参数(multipartFile)为空，referer:{}", request.getHeader("Referer"));
            return path;
        }

        return imageWebPath + "/" + ImageUtils.uploadImage(multipartFile,imageSavePath, Integer.parseInt(imageMaxSize));
    }

}
