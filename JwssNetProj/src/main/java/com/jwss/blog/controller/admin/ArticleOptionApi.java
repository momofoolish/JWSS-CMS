package com.jwss.blog.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.jwss.blog.entity.render.Result;
import com.jwss.blog.service.article.ArticleServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/")
public class ArticleOptionApi {
    @Resource
    private ArticleServiceImpl articleServiceImpl;

    @GetMapping("/page")
    public Object articlePage(@RequestParam Map<String, Object> map) {
        int p = Integer.parseInt((String) map.get("page"));
        int l = Integer.parseInt((String) map.get("limit"));
        return articleServiceImpl.selectByPage(p, l);
    }
}
