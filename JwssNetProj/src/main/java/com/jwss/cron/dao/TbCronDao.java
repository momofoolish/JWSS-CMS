package com.jwss.cron.dao;

import com.jwss.cron.model.TbCron;
import org.springframework.stereotype.Repository;

@Repository
public interface TbCronDao {
    int deleteByPrimaryKey(Integer id);

    int insert(TbCron record);

    int insertSelective(TbCron record);

    TbCron selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TbCron record);

    int updateByPrimaryKey(TbCron record);
}