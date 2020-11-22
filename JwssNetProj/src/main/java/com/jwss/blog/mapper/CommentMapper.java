package com.jwss.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jwss.blog.entity.sqldata.Comment;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

}
