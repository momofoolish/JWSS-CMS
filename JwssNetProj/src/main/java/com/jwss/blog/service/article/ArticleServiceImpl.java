package com.jwss.blog.service.article;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jwss.blog.entity.sqldata.Article;
import com.jwss.blog.mapper.ArticleMapper;
import com.jwss.blog.service.BaseService;
import com.jwss.blog.service.BaseServiceImpl;
import com.jwss.blog.util.Sys;
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
     * 分页查询
     *
     * @param index 索引值（开始）
     * @param total 查询多少条？
     * @return 文章队列
     */
    public Map<String, Object> selectByPage(int index, int total) {
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        IPage<Article> iPage = new Page<>(index, total);
        queryWrapper.select("id", "title", "description", "label", "author_id", "create_date",
                "reads_number", "comment_number");
        Map<String, Object> hashMap = new HashMap<>();
        IPage<Article> iPageResult = articleMapper.selectPage(iPage, queryWrapper);
        hashMap.put("data", iPageResult.getRecords());
        hashMap.put("count", iPageResult.getTotal());
        hashMap.put("msg", "成功");
        hashMap.put("code", 0);
        return hashMap;
    }

    /**
     * 批量删除
     * @param map 文章id列表
     * @return 返回受影响数量
     */
    public Integer deleteBatch(Map<String, Object> map){
        String[] idArray = String.valueOf(map.get("ids")).split(",");
        List<String> idList = new ArrayList<>();
        Collections.addAll(idList, idArray);
        return articleMapper.deleteBatch(idList);
    }
}
