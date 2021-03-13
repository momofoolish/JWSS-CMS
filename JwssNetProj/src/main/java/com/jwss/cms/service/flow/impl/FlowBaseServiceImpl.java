package com.jwss.cms.service.flow.impl;

import com.jwss.cms.dao.flow.TbFlowBaseDao;
import com.jwss.cms.model.flow.TbFlowBase;
import com.jwss.cms.service.flow.FlowBaseService;
import com.jwss.cms.util.SqlUtils;
import com.jwss.cms.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FlowBaseServiceImpl implements FlowBaseService {

    @Resource
    TbFlowBaseDao flowBaseDao;

    @Override
    public List<Map<String, String>> selectByTable(int page, int total, String keyWord) {
        if (page > 0) {
            List<Map<String, String>> maps = flowBaseDao.selectByTable((page - 1) * total, total, keyWord);
            maps.add(SqlUtils.addCount(flowBaseDao.count()));
            return maps;
        }
        return null;
    }

    @Override
    public List<Map<String, String>> selectByAuditState(int page, int total, String keyWord, int state) {
        if (page > 0) {
            List<Map<String, String>> maps = flowBaseDao.selectByAuditState((page - 1) * total, total, keyWord, state);
            maps.add(SqlUtils.addCount(flowBaseDao.count()));
            return maps;
        }
        return null;
    }

    @Override
    public int update(Map<String, Object> hashMap) {
        int examineType = Integer.parseInt(hashMap.get("examineType").toString());
        int id = Integer.parseInt(hashMap.get("id").toString());
        String content = StringUtils.isNotEmpty(hashMap.get("content") == null ? "" : hashMap.get("content").toString());
        String opinion = StringUtils.isNotEmpty(hashMap.get("opinion") == null ? "" : hashMap.get("opinion").toString());

        TbFlowBase flowBase = new TbFlowBase();
        flowBase.setId(id);
        flowBase.setAudit_state(examineType);
        if (!content.equals("")) {
            flowBase.setContent(content);
        }
        if (!opinion.equals("")) {
            flowBase.setOpinion(opinion);
        }
        return flowBaseDao.updateByPrimaryKeySelective(flowBase);
    }

}
