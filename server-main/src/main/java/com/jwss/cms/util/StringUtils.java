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

    /**
     * 将字符串转换成功整数型
     * 转换异常返回0，空字符串返回0
     *
     * @param s 字符串
     * @return 整数型
     */
    public static int vInteger(String s) {
        try {
            if (null == s || "".equals(s)) {
                return 0;
            }
            return Integer.parseInt(s);
        } catch (Exception e) {
            return 0;
        }
    }
}
