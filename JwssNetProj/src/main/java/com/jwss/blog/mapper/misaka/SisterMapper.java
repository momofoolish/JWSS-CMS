package com.jwss.blog.mapper.misaka;

import com.jwss.blog.entity.misaka.Sister;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SisterMapper {
    List<Sister> selectListByPage(Integer s, Integer t);
    List<Sister> selectListByKey(String key);
    Integer insertSister(Sister sister);
    Integer countSister();
}
