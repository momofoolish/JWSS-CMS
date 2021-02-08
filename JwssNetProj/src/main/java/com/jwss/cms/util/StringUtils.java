package com.jwss.cms.util;

/**
 * @author jwss
 * 字符串工具类
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     *
     * @param s 传入字符串
     * @return 为空返回空字符串
     */
    public static String isNotEmpty(String s) {
        if (s == null) {
            return "";
        }
        return s;
    }
}
