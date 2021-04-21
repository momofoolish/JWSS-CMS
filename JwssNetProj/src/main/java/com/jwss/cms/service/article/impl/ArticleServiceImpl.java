package com.jwss.cms.service.article.impl;

import com.jwss.cms.dao.article.TbArticleDao;
import com.jwss.cms.model.article.TbArticle;
import com.jwss.cms.service.BaseServiceImpl;
import com.jwss.cms.service.article.ArticleService;
import com.jwss.cms.util.SystemUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ArticleServiceImpl extends BaseServiceImpl implements ArticleService {
    @Resource
    private TbArticleDao articleDao;

    @Override
    public int insert(TbArticle article) {
        article.setId("auu" + SystemUtils.uuid());
        article.setCreateDate(new Date());  //创建日期
        article.setLikesNumber(0);  //推荐量
        article.setCommentNumber(0);//评论量
        article.setReadsNumber(1);//阅读量
        article.setState(0);//文章状态
        String aId = getUserInfo().getId();
        if (aId.equals("")) {
            return 0;
        }
        article.setAuthorId(aId);//作者ID
        return articleDao.insert(article);
    }

    @Override
    public int delete(TbArticle article) {
        return articleDao.deleteByAuthor(article.getId(), getUserInfo().getId());
    }

    @Override
    public int update(TbArticle article) {
        article.setAlterDate(new Date());
        article.setAuthorId(getUserInfo().getId());
        return articleDao.updateByPrimaryKeySelective(article);
    }

    @Override
    public List<TbArticle> select(int page, int total) {
        if (page > 0) {
            return articleDao.selectByPage((page - 1) * total, total);
        }
        return null;
    }

    @Override
    public TbArticle queryDetail(String aid) {
        return articleDao.selectByPrimaryKey(aid);
    }

    @Override
    public List<TbArticle> selectBySearch(int page, int total, String keyWord, List<String> cols) {
        if (page > 0) {
            //如果keyWord不为空，则为全局搜索
            if (!"".equals(keyWord)) {
                page = 1;
            }
            return articleDao.selectBySearch((page - 1) * total, total, keyWord, cols);
        }
        return null;
    }

    @Override
    public List<Map<String, String>> selectByTable(int page, int total, int state, String keyWord) {
        if (page > 0) {
            List<Map<String, String>> mapList;
            if (state < 0) {
                mapList = articleDao.selectByTable((page - 1) * total, total, keyWord);
            } else {
                mapList = articleDao.selectByTableExam((page - 1) * total, total, state, keyWord);
            }
            if (mapList.size() <= 0) {
                return null;
            }
            Map<String, String> countMap = new HashMap<>();
            countMap.put("total", Integer.toString(articleDao.count()));//总数
            mapList.add(countMap);
            return mapList;
        }
        return null;
    }

    @Override
    public Integer deleteBatch(Map<String, Object> map) {
        String[] idArray = String.valueOf(map.get("ids")).split(",");
        List<String> idList = new ArrayList<>();
        Collections.addAll(idList, idArray);
        return articleDao.deleteBatch(idList);
    }

    @Override
    public int updateArticleState(String aId, int state) {
        if (aId.equals("") || state < 0) {
            return 0;
        }
        TbArticle article = new TbArticle();
        article.setAuthorId(aId);
        article.setState(state);
        return articleDao.updateByPrimaryKey(article);
    }

    @Override
    public int isExist(String name) {
        List<TbArticle> articleList = articleDao.selectByName(name);
        if (articleList.size() > 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int count() {
        return articleDao.count();
    }
}
