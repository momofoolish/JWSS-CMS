package com.jwss.cms.util;

import com.jwss.cms.entity.ResultCode;

import java.util.*;

/**
 * 数据库操作工具
 */
public class SqlUtil {

    /**
     * 列名选择器
     *
     * @param cols 列名
     * @return 列名列表
     */
    public static List<String> colsGenerator(String... cols) {
        return new ArrayList<>(Arrays.asList(cols));
    }

    /**
     * 封装返回数据JSON
     * @param mapList 数据列表
     * @return layui分页数据模板
     */
    public static Map<String, Object> pageResultGenerator(List<Map<String, String>> mapList) {
        Map<String, Object> hashMap = new HashMap<>();
        if (mapList.size() <= 0) {
            return null;
        }
        hashMap.put("code", 0);
        hashMap.put("msg", "成功");
        hashMap.put("count", mapList.size());
        hashMap.put("data", mapList);
        return hashMap;
    }
}
