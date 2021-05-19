package com.jwss.cms.controller.article;

import com.jwss.cms.constant.HostConfig;
import com.jwss.cms.constant.RedisKeyType;
import com.jwss.cms.constant.ResultCode;
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
    private ArticleService articleService;
    @Resource
    private HostConfig hostConfig;
    @Autowired
    private StringRedisTemplate redisTemplate;

    //文章增删改查之 增
    @PostMapping("/author/add")
    public Result insert(HttpServletRequest request, @RequestParam("title") String title,
                         @RequestParam("content") String content, @RequestParam("sort") String sort,
                         @RequestParam(value = "cover", required = false) MultipartFile cover,
                         @RequestParam("edKey") String edKey) {
        String myKey = redisTemplate.opsForValue().get(SystemUtils.getClientHost(request) + RedisKeyType.edKey);
        TbArticle article = new TbArticle();
        article.setTitle(title);
        article.setContent(content);
        article.setSortId(Integer.parseInt(sort));
        //判断钥匙是否正确
        if (myKey == null || !myKey.equals(edKey)) {
            return new Result(-1, "请求失败");
        }
        //对已存在文章只进行修改操作
        String articleId = articleService.isExist(title);
        if (!articleId.equals("-1")) {
            article.setId(articleId);
            articleService.update(article);
            return new Result(1, "修改成功");
        }
        //判断是否有封面
        if (null != cover && !cover.isEmpty()) {
            article.setCover(SystemUtils.saveCover(cover, hostConfig));
        }
        //判断分类是否选错了
        if (sort.equals("分类")) {
            return new Result(0, "失败");
        }
        //新增文章
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
        return new Result(1, articleService.selectByPage(index, total));
    }

    //文章搜索
    @GetMapping("/search")
    public Result search(@RequestParam String key) {
        //return articleService.searchArticleList(key);
        return null;
    }

}
