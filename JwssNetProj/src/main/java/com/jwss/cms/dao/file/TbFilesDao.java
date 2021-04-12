package com.jwss.cms.dao.file;

import com.jwss.cms.dao.MyBatisBaseDao;
import com.jwss.cms.model.file.TbFiles;
import org.springframework.stereotype.Repository;

/**
 * TbFilesDao继承基类
 */
@Repository
public interface TbFilesDao extends MyBatisBaseDao<TbFiles, TbFiles> {
}
