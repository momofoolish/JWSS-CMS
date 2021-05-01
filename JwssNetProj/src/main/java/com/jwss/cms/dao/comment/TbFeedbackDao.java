package com.jwss.cms.dao.comment;

import com.jwss.cms.dao.MyBatisBaseDao;
import com.jwss.cms.model.comment.TbFeedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TbFeedbackDao extends MyBatisBaseDao<TbFeedback, String> {
    List<TbFeedback> selectByNew(int total);
}
