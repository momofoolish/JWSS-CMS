package com.jwss.cms.service.article;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jwss.cms.entity.sqldata.ArticleSort;
import com.jwss.cms.mapper.ArticleSortMapper;
import com.jwss.cms.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SortService implements BaseService {
    @Resource
    ArticleSortMapper articleSortMapper;

    @Override
    public int insert(Object o) {
        return 0;
    }

    @Override
    public int delete(Object o) {
        return 0;
    }

    @Override
    public int update(Object o) {
        return 0;
    }

    @Override
    public Object select(int index, int total) {
        QueryWrapper<ArticleSort> queryWrapper = new QueryWrapper<>();
        IPage<ArticleSort> iPage = new Page<>(index, total);
        queryWrapper.select("id", "name");
        return articleSortMapper.selectPage(iPage,queryWrapper);
    }
}
