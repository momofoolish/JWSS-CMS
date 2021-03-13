package com.jwss.cms.dao.comment;

import com.jwss.cms.dao.MyBatisBaseDao;
import com.jwss.cms.model.comment.TbFeedback;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbFeedbackDao extends MyBatisBaseDao<TbFeedback, String> {
    List<TbFeedback> selectByNew(int total);
}
