package com.jwss.blog.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyEncrypt {
    private final Logger logger = LoggerFactory.getLogger(MyEncrypt.class);
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH");
    private final String keyWord = "www.vjwss.top";// 加密钥匙

    /**
     * 分页加密
     * @param p 索引页
     * @return 加密后数据
     */
    public String encrypt(String p) {
        String middleStr = p + keyWord + dateFormat.format(new Date());
        String outputStr = DigestUtils.md5DigestAsHex(middleStr.getBytes());
        logger.info("当前时间：" + dateFormat.format(new Date()));
        logger.info("原始数据：" + middleStr);
        logger.info("加密数据：" + outputStr);
        return outputStr;
    }

    /**
     * 常用加密
     * @param eType 加密类型
     * @return 加密后数据
     */
    public String encryptPlus(String eType) {
        String middleStr = eType + keyWord + dateFormat.format(new Date());
        String outputStr = DigestUtils.md5DigestAsHex(middleStr.getBytes());
        logger.info("当前时间：" + dateFormat.format(new Date()));
        logger.info("原始数据：" + middleStr);
        logger.info("加密数据：" + outputStr);
        return outputStr;
    }
}
