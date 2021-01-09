package com.jwss.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jwss.cms.entity.sqldata.User;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleMapper extends BaseMapper<User> {

}
