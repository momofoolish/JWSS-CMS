package com.jwss.cms.service.flow;

import java.util.List;
import java.util.Map;

public interface FlowBaseService {
    /**
     * 表格分页查询
     *
     * @param page  页码
     * @param total 查询多少条？
     * @return 文章队列
     */
    List<Map<String, String>> selectByTable(int page, int total, String keyWord);

    /**
     * 分页查询（查询audit_state为0）
     *
     * @param page 页码
     * @param total 查询多少条？
     * @return 文章队列
     */
    List<Map<String, String>> selectByAuditState(int page, int total, String keyWord, int state);

    /**
     * 更新流程信息
     * @param hashMap h
     * @return 执行条数
     */
    int update(Map<String, Object> hashMap);
}
