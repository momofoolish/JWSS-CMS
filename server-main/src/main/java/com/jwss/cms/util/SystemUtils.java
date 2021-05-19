package com.jwss.cms.util;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.google.code.kaptcha.Producer;
import com.jwss.cms.constant.HostConfig;
import com.jwss.cms.constant.RedisKeyType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统工具类
 *
 * @author Jwss
 */
public class SystemUtils {
    /**
     * md5 封装
     *
     * @param s 字符串
     * @return md5字符串
     */
    public static String md5(String s) {
        return DigestUtils.md5DigestAsHex(s.getBytes());
    }

    /**
     * 保存封面
     *
     * @param file MultipartFile文件
     * @return 文件路径
     */
    public static String saveCover(MultipartFile file, HostConfig hostConfig) {
        return fileSave(file, hostConfig.getCoverSavePath(), hostConfig.getCoverBrowserPath());
    }

    /**
     * 保存文章内容的图片
     *
     * @param file MultipartFile文件
     * @return 文件路径
     */
    public static String saveContentImage(MultipartFile file, HostConfig hostConfig) {
        return fileSave(file, hostConfig.getContentSavePath(), hostConfig.getContentBrowserPath());
    }

    /**
     * 获取用户真实IP
     *
     * @param request HttpServletRequest
     * @return 用户真实IP
     */
    public static String getClientHost(HttpServletRequest request) {
        String ipAddress;
        try {
            ipAddress = request.getHeader("x-forwarded-for");
            if (ipAddress == null || ipAddress.length() == 0 || "UNKNOWN".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "UNKNOWN".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0 || "UNKNOWN".equalsIgnoreCase(ipAddress)) {
                ipAddress = request.getRemoteAddr();
                if ("127.0.0.1".equals(ipAddress)) {
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    assert inet != null;
                    ipAddress = inet.getHostAddress();
                }
            }
            // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            // "***.***.***.***".length()
            if (ipAddress != null && ipAddress.length() > 15) {
                if (ipAddress.indexOf(",") > 0) {
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            ipAddress = "";
        }
        return ipAddress;
    }

    /**
     * 设置图片流
     *
     * @param response HttpServletResponse
     */
    public static void responseImageSetting(HttpServletResponse response) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
    }

    /**
     * uuid生成器
     *
     * @return UUID字符串（除去“-”）
     */
    public static String uuid() {
        String u = UUID.randomUUID().toString();
        String[] us = u.split("-");
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : us) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    /**
     * 保存jpg图片文件
     *
     * @param file        MultipartFile文件
     * @param savePath    保存路径
     * @param browserPath 浏览路径
     * @return 文件路径
     */
    private static String fileSave(MultipartFile file, String savePath, String browserPath) {
        if (!file.isEmpty()) {
            String fileNmeReal = file.getOriginalFilename();
            //获取文件后缀
            String suffix;
            String[] fileNmeReals = fileNmeReal.split("\\.");
            if (fileNmeReals.length > 0) {
                suffix = "." + fileNmeReals[fileNmeReals.length - 1];
            } else {
                return "empty";
            }
            // 获取文件名并转成md5
            String fileName = md5(fileNmeReal + new Date().getTime());
            // 保存的文件路径
            String filePath = savePath + fileName + suffix;
            System.out.println(file.getName());
            try {
                File localFile = new File(filePath);
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(localFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                return "error" + e.getMessage();
            }
            return browserPath + "/" + fileName + suffix;
        } else {
            return "empty";
        }
    }

    /**
     * 生成校验码（并将字符串存到redis）
     *
     * @param request       request
     * @param response      response
     * @param redisTemplate redisTemplate
     * @param producer      producer
     * @param redisKeyType redis Key类型
     * @throws IOException IO异常
     */
    public static void verifyCodeProduct(HttpServletRequest request, HttpServletResponse response,
                                  StringRedisTemplate redisTemplate, Producer producer,String redisKeyType) throws IOException {
        String capText = producer.createText();//创建随机字符串
        String userIp = SystemUtils.getClientHost(request);//用户ip
        String redisKey = userIp + redisKeyType;//redis存储key
        //redis key值用主机+接口类型存储，以防造成相同key
        redisTemplate.opsForValue().set(redisKey, capText, 10, TimeUnit.MINUTES);
        //创建验证码
        BufferedImage bi = producer.createImage(capText);
        //返回验证码图形给客户端
        SystemUtils.responseImageSetting(response);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
