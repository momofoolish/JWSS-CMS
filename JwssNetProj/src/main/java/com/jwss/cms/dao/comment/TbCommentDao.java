package com.jwss.cms.dao.comment;

import com.jwss.cms.dao.MyBatisBaseDao;
import com.jwss.cms.model.comment.TbComment;
import org.springframework.stereotype.Repository;

@Repository
public interface TbCommentDao extends MyBatisBaseDao<TbComment, String> {

}
