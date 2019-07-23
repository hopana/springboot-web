package com.hp.springboot.utils;

import com.hp.springboot.exception.fast.FastRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 图片上传工具类
 */
public final class ImageUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);

    /**
     * 默认图片大小，单位KB
     */
    private static final int DEFAULT_MAX_SIZE = 500;
    /**
     * 单位进率
     */
    private static final int RATE = 1024;
    /**
     * 图片格式
     */
    private static final String SUFFIX_LIST = "jpg,jpeg,png,bmp";

    /**
     * 校验上传文件后缀名
     *
     * @param extensionName
     * @return
     */
    private static void checkFile(String extensionName) {
        if (!SUFFIX_LIST.contains(extensionName.toLowerCase())) {
            throw new FastRuntimeException("图片格式错误");
        }
    }

    private ImageUtils() {

    }

    /**
     * 上传图片
     *
     * @param imageFile    图片文件
     * @param savePath     保存物理路径
     * @param maxSize      图片尺寸限制大小（单位：KB）
     * @return 图片地址
     */
    public static String uploadImage(MultipartFile imageFile, String savePath, Integer maxSize) {
        checkSize(imageFile, maxSize);
        // 获取图片的文件名
        String fileName = imageFile.getOriginalFilename();
        // 获取图片的扩展名
        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        checkFile(extensionName);

        // 新的图片文件名
        String newFileName = UUID.randomUUID() + "." + extensionName;

        // 创建目录
        String dateString = DateTimeUtil.formatDate(new Date(), "yyyyMMdd");
        String actSavePath = savePath + "/" + dateString;
        // 服务器存图片路径
        File saveFile = new File(actSavePath);
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }

        // 保存图片
        String filePath = actSavePath + "/" +newFileName;
        File file = new File(filePath);
        try {
            imageFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            LOGGER.error("图片上传失败,{}", e);
            throw new FastRuntimeException("图片上传失败", e);
        }

        return dateString + "/" + newFileName;
    }

    private static void checkSize(MultipartFile imageFile, Integer maxSize) {
        int imageMaxSize = maxSize == null ? DEFAULT_MAX_SIZE : maxSize;
        long imageSize = imageFile.getSize();
        if (imageSize > imageMaxSize * RATE) {
            throw new FastRuntimeException("图片大小超过限制");
        }
    }

    /**
     * 上传图片
     *
     * @param imageFiles 图片文件
     * @param savePath   保存物理路径
     * @param maxSize    图片尺寸限制大小（单位：KB）
     * @return 图片地址
     */
    public static String uploadImage(List<MultipartFile> imageFiles, String savePath, Integer maxSize) {
        String imgPaths = null;
        for (int i = 0; i < imageFiles.size(); ++i) {
            MultipartFile imageFile = imageFiles.get(i);
            if (!imageFile.isEmpty()) {
                checkSize(imageFile, maxSize);
                // 获取图片的文件名
                String fileName = imageFile.getOriginalFilename();
                // 获取图片的扩展名
                String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
                // 新的图片文件名
                String newFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + extensionName;
                String newDate = DateTimeUtil.formatDate(new Date(), "yyyyMMdd") + "/";
                // 服务器存图片路径
                File saveFile = new File(savePath + newDate);
                if (!saveFile.exists()) {
                    saveFile.mkdirs();
                }
                // 保存图片
                String filePath = savePath + newDate + newFileName;
                File file = new File(filePath);
                try {
                    imageFile.transferTo(file);
                } catch (IllegalStateException | IOException e) {
                    LOGGER.error("图片上传失败,{}", e);
                    throw new FastRuntimeException("图片上传失败", e);
                }

                // 图片存放相对路径-存于数据的路径
                String imgPath = newDate + newFileName;
                if (imgPaths == null) {
                    imgPaths = imgPath + ",";
                } else {
                    imgPaths = imgPaths + imgPath + ",";
                }
            }
        }
        return imgPaths;
    }

    /**
     * 移除图片
     *
     * @param imageName 图片名称
     * @param savePath  保存物理路径
     * @return 图片地址
     */
    public static boolean removeImage(String imageName, String savePath) {
        // 服务器存图片路径
        File delFile = new File(savePath + imageName);
        try {
            if (delFile.isFile()) {
                return delFile.delete();
            }
        } catch (IllegalStateException e) {
            LOGGER.error("图片上传失败,{}", e);
            throw new FastRuntimeException("图片上传失败", e);
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }

}
