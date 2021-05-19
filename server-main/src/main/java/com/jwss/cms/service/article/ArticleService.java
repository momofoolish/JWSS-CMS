package com.jwss.cms.service.article;

import com.jwss.cms.model.article.TbArticle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface ArticleService {

    /**
     * 增加
     *
     * @param article 增加的对象
     * @return 1
     */
    int insert(TbArticle article);

    /**
     * 删除
     *
     * @param article 删除的对象
     * @return 1
     */
    int delete(TbArticle article);

    /**
     * 修改
     *
     * @param article 修改的对象
     * @return 1
     */
    int update(TbArticle article);

    /**
     * 查询
     *
     * @param page  页码
     * @param total 查询多少条？
     * @return 对象列表
     */
    List<TbArticle> select(int page, int total);

    /**
     * 分页查询
     * 返回总条数(articleTotal)、当前页码(currentPage)、文章列表(articleList)
     *
     * @param page 当前页码
     * @param total 查询条数
     * @return 查询结果
     */
    Map<String, Object> selectByPage(int page, int total);

    /**
     * 查询文章细节
     *
     * @param aid 文章id
     * @return 文章实体
     */
    TbArticle queryDetail(String aid);

    /**
     * 查询文章列表（含搜索、动态列名）
     *
     * @param page    页码
     * @param total   条数
     * @param keyWord 搜索关键词
     * @param cols    列名
     * @return 文章列表
     */
    List<TbArticle> selectBySearch(int page, int total, String keyWord, List<String> cols);

    /**
     * 分页查询（联表查询出作者名称，分类名称）
     *
     * @param page    页码
     * @param total   条数
     * @param keyWord 搜索关键词
     * @param state   文章状态: -1, 查询全部, 0审核中...
     * @return 表格集合
     */
    List<Map<String, String>> selectByTable(int page, int total, int state, String keyWord);

    /**
     * 批量删除
     *
     * @param map 文章id列表
     * @return 返回受影响数量
     */
    Integer deleteBatch(Map<String, Object> map);

    /**
     * 更新文章状态
     *
     * @param aId   文章id
     * @param state 提交类型
     * @return 返回数据更新条数
     */
    int updateArticleState(String aId, int state);

    /**
     * 通过名称判断文章是否存在
     * 不存在返回-1
     *
     * @param name 文章名称
     * @return 文章id
     */
    String isExist(String name);

    /**
     * 文章总数
     *
     * @return 文章总数
     */
    int count();
}
