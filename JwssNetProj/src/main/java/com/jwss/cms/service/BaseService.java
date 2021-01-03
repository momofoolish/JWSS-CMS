package com.jwss.cms.service;

public interface BaseService {

    /**
     * 增加
     * @param o 增加的对象
     * @return 1
     */
    int insert(Object o);

    /**
     * 删除
     * @param o 删除的对象
     * @return 1
     */
    int delete(Object o);

    /**
     * 修改
     * @param o 修改的对象
     * @return 1
     */
    int update(Object o);

    /**
     * 查询
     * @param index 索引值（开始）
     * @param total 查询多少条？
     * @return 对象列表
     */
    Object select(int index, int total);
}
