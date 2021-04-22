package com.jwss.cms.dao.article;

import com.jwss.cms.dao.MyBatisBaseDao;
import com.jwss.cms.model.article.TbArticle;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * TbArticleDao继承基类
 */
@Repository
public interface TbArticleDao extends MyBatisBaseDao<TbArticle, String> {
    List<TbArticle> selectByPage(int index, int total);
    List<TbArticle> selectByTitle(String name);
    List<TbArticle> selectBySearch(int index, int total, String keyWord, List<String> cols);
    List<Map<String, String>> selectByTable(int index, int total, String keyWord);
    List<Map<String, String>> selectByTableExam(int index, int total, int state, String keyWord);
    int deleteByAuthor(String id, String authorId);
    int deleteBatch(List<String> idList);
}
