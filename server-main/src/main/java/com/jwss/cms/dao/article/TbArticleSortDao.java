package com.jwss.cms.dao.article;

import com.jwss.cms.dao.MyBatisBaseDao;
import com.jwss.cms.model.article.TbArticleSort;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TbArticleSortDao继承基类
 */
@Mapper
public interface TbArticleSortDao extends MyBatisBaseDao<TbArticleSort, Integer> {
    List<TbArticleSort> selectByPage(int index, int total);
}
