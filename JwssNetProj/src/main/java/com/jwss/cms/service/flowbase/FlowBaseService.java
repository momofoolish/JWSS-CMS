package com.jwss.cms.service.flowbase;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jwss.cms.entity.sqldata.FlowBase;
import com.jwss.cms.mapper.FlowBaseMapper;
import com.jwss.cms.service.BaseService;
import com.jwss.cms.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class FlowBaseService implements BaseService {
    @Resource
    FlowBaseMapper flowBaseMapper;

    /**
     * 分页查询
     *
     * @param index 索引值（开始）
     * @param total 查询多少条？
     * @return 文章队列
     */
    public Map<String, Object> selectByPage(int index, int total, String keyWord) {
        QueryWrapper<FlowBase> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(
                "id", "title", "content", "opinion", "audit_state", "flow_id",
                "current_operator", "create_date");
        return queryPotting(index, total, keyWord, queryWrapper);
    }

    /**
     * 分页查询（查询audit_state为0）
     *
     * @param index 索引值（开始）
     * @param total 查询多少条？
     * @return 文章队列
     */
    public Map<String, Object> selectByAuditState(int index, int total, String keyWord, int state) {
        QueryWrapper<FlowBase> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(FlowBase::getAuditState, state);
        queryWrapper.select(
                "id", "title", "content", "opinion", "audit_state", "flow_id",
                "current_operator", "create_date");
        return queryPotting(index, total, keyWord, queryWrapper);
    }

    /**
     * 查询流程审批队列表封装
     *
     * @param index        索引值（开始）
     * @param total        查询多少条？
     * @param keyWord      搜索关键词（空的话全局搜索）
     * @param queryWrapper 可以扩展QueryWrapper
     * @return 流程审批队列
     */
    private Map<String, Object> queryPotting(int index, int total, String keyWord, QueryWrapper<FlowBase> queryWrapper) {
        queryWrapper.like(!keyWord.equals(""), "title", keyWord);//模糊查询
        IPage<FlowBase> iPage = new Page<>(index, total);
        IPage<FlowBase> iPageResult = flowBaseMapper.selectPage(iPage, queryWrapper);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("data", iPageResult.getRecords());
        hashMap.put("count", iPageResult.getTotal());
        hashMap.put("msg", "成功");
        hashMap.put("code", 0);
        return hashMap;
    }

    @Override
    public int insert(Object o) {
        return 0;
    }

    @Override
    public int delete(Object o) {
        return 0;
    }

    @Override
    public int update(Object o) {
        Map<String, Object> hashMap = JSONObject.parseObject(JSONObject.toJSONString(o));

        int examineType = Integer.parseInt(hashMap.get("examineType").toString());
        int id = Integer.parseInt(hashMap.get("id").toString());
        String content = StringUtils.isNotEmpty(hashMap.get("content") == null ? "" : hashMap.get("content").toString());
        String opinion = StringUtils.isNotEmpty(hashMap.get("opinion") == null ? "" : hashMap.get("opinion").toString());

        FlowBase flowBase = new FlowBase();
        flowBase.setId(id);
        flowBase.setAuditState(examineType);
        if (!content.equals("")) {
            flowBase.setContent(content);
        }
        if (!opinion.equals("")) {
            flowBase.setOpinion(opinion);
        }
        UpdateWrapper<FlowBase> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(FlowBase::getId, id);
        return flowBaseMapper.update(flowBase, updateWrapper);
    }

    @Override
    public Object select(int index, int total) {
        return null;
    }

}
