package com.jwss.cms.service.article;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jwss.cms.entity.sqldata.Article;
import com.jwss.cms.mapper.ArticleMapper;
import com.jwss.cms.service.BaseService;
import com.jwss.cms.service.BaseServiceImpl;
import com.jwss.cms.util.Sys;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ArticleServiceImpl extends BaseServiceImpl implements BaseService {
    @Resource
    ArticleMapper articleMapper;

    @Override
    public int insert(Object o) {
        Article article = (Article) o;
        article.setId("auu" + Sys.uuid());
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
        return articleMapper.insert(article);
    }

    @Override
    public int delete(Object o) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(Article::getAuthorId, getUserInfo().getId())
                .eq(Article::getId, o);
        return articleMapper.delete(queryWrapper);
    }

    @Override
    public int update(Object o) {
        Article article = (Article) o;
        article.setAlterDate(new Date());
        article.setAuthorId(getUserInfo().getId());
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda()
                .eq(Article::getAuthorId, getUserInfo().getId())
                .eq(Article::getId, article.getId());
        return articleMapper.update(article, updateWrapper);
    }

    @Override
    public Object select(int index, int total) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        IPage<Article> iPage = new Page<>(index, total);
        queryWrapper.select("id", "title", "description", "label", "cover", "create_date",
                "reads_number", "comment_number");
        queryWrapper.lambda().eq(Article::getAuthorId, getUserInfo().getId());
        return articleMapper.selectPage(iPage, queryWrapper);
    }

    /**
     * 通过名称判断文章是否存在
     *
     * @param name 文章名称
     * @return 0&1
     */
    public int isExist(String name) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id");
        queryWrapper.lambda().eq(Article::getTitle, name);
        List<Article> articleList = articleMapper.selectList(queryWrapper);
        if (articleList.size() > 0) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 获取最新的文章
     *
     * @param total 条数
     * @return 文章集合
     */
    public List<Article> selectNewList(int total) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        IPage<Article> iPage = new Page<>(0, total);
        queryWrapper.select("id", "title", "author_id", "create_date").orderByDesc("create_date");
        return articleMapper.selectPage(iPage, queryWrapper).getRecords();
    }

    /**
     * 分页查询所有待审核文章
     *
     * @param index 索引值（开始）
     * @param total 查询多少条？
     * @return 审核文章队列
     */
    public Map<String, Object> selectExamineList(int index, int total, String keyWord) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(
                "id", "title", "description", "label", "author_id", "create_date", "content"
        );
        return queryPotting(index, total, keyWord, queryWrapper);
    }

    /**
     * 批量删除
     *
     * @param map 文章id列表
     * @return 返回受影响数量
     */
    public Integer deleteBatch(Map<String, Object> map) {
        String[] idArray = String.valueOf(map.get("ids")).split(",");
        List<String> idList = new ArrayList<>();
        Collections.addAll(idList, idArray);
        return articleMapper.deleteBatch(idList);
    }

    /**
     * 分页查询
     *
     * @param index 索引值（开始）
     * @param total 查询多少条？
     * @return 文章队列
     */
    public Map<String, Object> selectByPage(int index, int total, String keyWord) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(
                "id", "title", "description", "label", "author_id", "create_date",
                "reads_number", "comment_number");
        return queryPotting(index, total, keyWord, queryWrapper);
    }

    /**
     * 更新文章状态
     *
     * @param aId   文章id
     * @param state 提交类型
     * @return 返回数据更新条数
     */
    public int updateArticleState(String aId, int state) {
        if (aId.equals("") || state < 0) {
            return 0;
        }
        Article article = new Article();
        article.setAlterDate(new Date());
        article.setState(state);
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(Article::getId, aId);
        return articleMapper.update(article, updateWrapper);
    }

    /**
     * 查询文章列表封装
     * @param index 索引值（开始）
     * @param total 查询多少条？
     * @param keyWord 搜索关键词（空的话全局搜索）
     * @param queryWrapper 可以扩展QueryWrapper
     * @return 文章队列
     */
    private Map<String, Object> queryPotting(int index, int total, String keyWord, QueryWrapper<Article> queryWrapper) {
        queryWrapper.lambda().eq(Article::getState, 0);//条件为审核状态
        queryWrapper.like(!keyWord.equals(""), "title", keyWord);//模糊查询
        IPage<Article> iPage = new Page<>(index, total);
        IPage<Article> iPageResult = articleMapper.selectPage(iPage, queryWrapper);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("data", iPageResult.getRecords());
        hashMap.put("count", iPageResult.getTotal());
        hashMap.put("msg", "成功");
        hashMap.put("code", 0);
        return hashMap;
    }
}
