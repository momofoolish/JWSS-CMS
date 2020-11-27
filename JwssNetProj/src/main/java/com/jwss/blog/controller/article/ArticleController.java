package com.jwss.blog.controller.article;

import com.jwss.blog.config.HostConfig;
import com.jwss.blog.entity.render.Result;
import com.jwss.blog.entity.sqldata.Article;
import com.jwss.blog.service.article.ArticleService;
import com.jwss.blog.service.article.ArticleServiceImpl;
import com.jwss.blog.util.Sys;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Resource
    ArticleService articleService;
    @Resource
    ArticleServiceImpl articleServiceImpl;
    @Resource
    HostConfig hostConfig;

    //文章增删改查之 增
    @PostMapping("/author/add")
    public Result insert(@RequestParam String title, @RequestParam String desc, @RequestParam String content, @RequestParam String sort, @RequestParam String tag, @RequestParam MultipartFile cover) {
        Article article = new Article();
        article.setTitle(title);
        article.setDescription(desc);
        article.setContent(content);
        article.setLabel(tag);
        article.setCover(Sys.fileSave(cover, hostConfig));
        if (sort.equals("分类")) {
            return new Result(0, "失败");
        }
        article.setSortId(Integer.parseInt(sort));
        int flag = articleServiceImpl.insert(article);
        if (flag != 0) {
            return new Result(1, "成功");
        } else {
            return new Result(0, "失败");
        }
    }

    //文章增删改查之 删
    @PostMapping("/author/del")
    public Result delete(@RequestParam String aid) {
        return new Result(1, articleServiceImpl.delete(aid));
    }

    //文章增删改查之 改
    @PostMapping("/author/alt")
    public Result alter(@RequestBody Article article) {
        return new Result(1, articleServiceImpl.update(article));
    }

    //文章增删改查之 查
    @GetMapping("/author/sel")
    public Result select(@RequestParam int index, @RequestParam int total) {
        return new Result(1, articleServiceImpl.select(index, total));
    }

    //文章搜索
    @GetMapping("/search")
    public Result search(@RequestParam String key) {
        return articleService.searchArticleList(key);
    }

    //文章细节
    @GetMapping("/detail")
    public Result detail(@RequestParam int aid) {
        return articleService.selectDetail(aid);
    }
}
