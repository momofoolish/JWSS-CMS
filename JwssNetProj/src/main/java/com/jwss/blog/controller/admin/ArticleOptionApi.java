package com.jwss.blog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.jwss.blog.entity.render.Result;
import com.jwss.blog.service.article.ArticleServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/api/admin/")
public class ArticleOptionApi {
    @Resource
    private ArticleServiceImpl articleServiceImpl;

    //分页查询
    @GetMapping("/article/page")
    public Object articlePage(@RequestParam Map<String, Object> map) {
        int p = Integer.parseInt((String) map.get("page"));
        int l = Integer.parseInt((String) map.get("limit"));
        return articleServiceImpl.selectByPage(p, l);
    }

    //批量删除
    @PostMapping("/article/delete/batch")
    public Object articleDelete(@RequestParam Map<String, Object> map) {
        return new Result(1, articleServiceImpl.deleteBatch(map));
    }
}
