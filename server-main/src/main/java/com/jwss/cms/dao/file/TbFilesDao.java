package com.jwss.cms.dao.file;

import com.jwss.cms.dao.MyBatisBaseDao;
import com.jwss.cms.model.file.TbFiles;
import org.apache.ibatis.annotations.Mapper;

/**
 * TbFilesDao继承基类
 */
@Mapper
public interface TbFilesDao extends MyBatisBaseDao<TbFiles, TbFiles> {
}
