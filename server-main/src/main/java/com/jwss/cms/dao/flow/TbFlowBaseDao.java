package com.jwss.cms.dao.flow;

import com.jwss.cms.dao.MyBatisBaseDao;
import com.jwss.cms.model.flow.TbFlowBase;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface TbFlowBaseDao extends MyBatisBaseDao<TbFlowBase, String> {
    List<Map<String, String>> selectByTable(int index, int total, String keyWord);
    List<Map<String, String>> selectByAuditState(int index, int total, String keyWord, int state);
}
