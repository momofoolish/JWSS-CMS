package com.jwss.cms.util;

import java.util.*;

/**
 * 数据库操作工具
 */
public class SqlUtils {

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
     *
     * @param mapList 数据列表
     * @return layui分页数据模板
     */
    public static Map<String, Object> pageResultGenerator(List<Map<String, String>> mapList) {
        if (mapList == null) {
            return null;
        }
        Map<String, Object> hashMap = new HashMap<>();
        if (mapList.size() <= 0) {
            return null;
        }
        hashMap.put("code", 0);
        hashMap.put("msg", "成功");
        hashMap.put("count", mapList.get(mapList.size() - 1).get("total"));
        mapList.remove(mapList.size() - 1);
        hashMap.put("data", mapList);
        return hashMap;
    }

    /**
     * 增加count键值对
     * @param count 总数
     * @return count键值对
     */
    public static Map<String, String> addCount(int count){
        Map<String,String> hashMap=new HashMap<>();
        hashMap.put("count", Integer.toString(count));
        return hashMap;
    }
}
