package com.jwss.cms.dao.user;

import com.jwss.cms.dao.MyBatisBaseDao;
import com.jwss.cms.model.user.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * TbUserDao继承基类
 */
@Repository
public interface TbUserDao extends MyBatisBaseDao<TbUser, String> {
    TbUser selectByColName(String colName, String col);

    List<Map<String, String>> selectBySearch(int index, int total, String keyWord, List<String> cols);

    List<TbUser> selectByNew(int total, List<String> cols);

    int deleteBatch(List<String> idList);

    int insertByUUID(TbUser tbUser);
}
