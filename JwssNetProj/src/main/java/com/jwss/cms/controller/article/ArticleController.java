package com.jwss.cms.controller.article;

import com.jwss.cms.constant.HostConfig;
import com.jwss.cms.constant.RedisKeyType;
import com.jwss.cms.model.render.Result;
import com.jwss.cms.model.article.TbArticle;
import com.jwss.cms.service.article.ArticleService;
import com.jwss.cms.util.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Resource
    ArticleService articleService;
    @Resource
    HostConfig hostConfig;
    @Autowired
    StringRedisTemplate redisTemplate;

    //文章增删改查之 增
    @PostMapping("/author/add")
    public Result insert(HttpServletRequest request, @RequestParam("title") String title,
                         @RequestParam("content") String content,@RequestParam("sort") String sort,
                         @RequestParam(value = "cover", required = false) MultipartFile cover,
                         @RequestParam("edKey") String edKey) {
        String myKey = redisTemplate.opsForValue().get(SystemUtils.getClientHost(request) + RedisKeyType.edKey);
        if (myKey == null || !myKey.equals(edKey)) {
            return new Result(-1, "请求失败");
        }
        if (articleService.isExist(title) <= 0) {
            return new Result(-1, "已存在此文章!!!");
        }
        TbArticle article = new TbArticle();
        article.setTitle(title);
        article.setContent(content);
        if(null != cover && !cover.isEmpty()){
            article.setCover(SystemUtils.fileSave(cover, hostConfig));
        }
        if (sort.equals("分类")) {
            return new Result(0, "失败");
        }
        article.setSortId(Integer.parseInt(sort));
        int flag = articleService.insert(article);
        if (flag != 0) {
            return new Result(1, "成功");
        } else {
            return new Result(0, "失败");
        }
    }

    //文章增删改查之 删
    @PostMapping("/author/del")
    public Result delete(@RequestParam String aid) {
        TbArticle article = new TbArticle();
        article.setId(aid);
        return new Result(1, articleService.delete(article));
    }

    //文章增删改查之 改
    @PostMapping("/author/alt")
    public Result alter(@RequestBody TbArticle article) {
        return new Result(1, articleService.update(article));
    }

    //文章增删改查之 查
    @GetMapping("/author/sel")
    public Result select(@RequestParam int index, @RequestParam int total) {
        return new Result(1, articleService.select(index, total));
    }

    //文章搜索
    @GetMapping("/search")
    public Result search(@RequestParam String key) {
        //return articleService.searchArticleList(key);
        return null;
    }

}
