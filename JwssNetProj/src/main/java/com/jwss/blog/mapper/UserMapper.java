package com.jwss.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jwss.blog.entity.sqldata.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {

}
