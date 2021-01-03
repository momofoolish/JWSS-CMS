package com.jwss.cms.controller.admin;

import com.jwss.cms.entity.render.Result;
import com.jwss.cms.service.article.ArticleServiceImpl;
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
        String keyWork = map.get("title") == null ? "" : map.get("title").toString();
        int p = Integer.parseInt((String) map.get("page"));
        int l = Integer.parseInt((String) map.get("limit"));
        return articleServiceImpl.selectByPage(p, l, keyWork);
    }

    //批量删除
    @PostMapping("/article/delete/batch")
    public Object articleDelete(@RequestParam Map<String, Object> map) {
        return new Result(1, articleServiceImpl.deleteBatch(map));
    }

    //分页查询
    @GetMapping("/article/examineList")
    public Object examineList(@RequestParam Map<String, Object> map) {
        String keyWork = map.get("title") == null ? "" : map.get("title").toString();
        int p = Integer.parseInt((String) map.get("page"));
        int l = Integer.parseInt((String) map.get("limit"));
        return articleServiceImpl.selectExamineList(p, l, keyWork);
    }

    //更新文章状态
    @PostMapping("/article/updateState")
    public Object updateState(@RequestParam Map<String, Object> map) {
        int state = Integer.parseInt((String) map.get("state"));
        int re = articleServiceImpl.updateArticleState(map.get("aId").toString(), state);
        return new Result(1, re);
    }
}
