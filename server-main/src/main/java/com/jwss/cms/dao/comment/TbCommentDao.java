package com.jwss.cms.dao.comment;

import com.jwss.cms.dao.MyBatisBaseDao;
import com.jwss.cms.model.comment.TbComment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbCommentDao extends MyBatisBaseDao<TbComment, String> {

}
