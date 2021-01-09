package com.jwss.cms.util;

import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.UUID;

import com.jwss.cms.constant.HostConfig;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统工具类
 *
 * @author Jwss
 */
public class Sys {
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
     * 保存jpg图片文件
     *
     * @param file MultipartFile文件
     * @return 文件路径
     */
    public static String fileSave(MultipartFile file, HostConfig hostConfig) {
        if (!file.isEmpty()) {
            // 获取文件名并转成md5
            String fileName = md5(file.getName() + new Date().getTime());
            // 保存的文件路径
            String filePath = hostConfig.getFilePath() + fileName + ".jpg";
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
            return hostConfig.getCoverMapping() + "/" + fileName + ".jpg";
        } else {
            return "empty";
        }
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
     * @return UUID字符串（除去“-”）
     */
    public static String uuid() {
        String u = UUID.randomUUID().toString();
        String[] us=u.split("-");
        StringBuilder stringBuilder=new StringBuilder();
        for (String s : us) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(uuid().length());
    }
}
