package com.jwss.cms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jwss.cms.entity.sqldata.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {
    Integer deleteBatch(@Param("idList") List<String> idList);
}
