package com.jwss.cms.controller;

import com.jwss.cms.constant.RedisKeyType;
import com.jwss.cms.model.article.TbArticle;
import com.jwss.cms.service.article.ArticleService;
import com.jwss.cms.service.article.SortService;
import com.jwss.cms.service.user.OnlineService;
import com.jwss.cms.util.MyEncrypt;
import com.jwss.cms.util.SystemUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class GateWayController extends BaseController {
    @Resource
    private SortService sortService;
    @Resource
    private MyEncrypt myEncrypt;
    @Resource
    private ArticleService articleService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    //登录页面
    @GetMapping("/login")
    public String login(Model model, @RequestParam("preUrl") String preUrl) {
        model.addAttribute("preUrl", preUrl);
        boolean b = renderMenu(model, "欢迎登录━(*｀∀´*)ノ亻!", "/login");
        if (!b) {
            return "user/login";
        } else {
            return "index";
        }
    }

    //退出登录
    @GetMapping("/logout")
    public String logout(Model model) {
        model.addAttribute("isLogin", "no");
        return "user/login";
    }

    //注册
    @GetMapping("/register")
    public String register(Model model) {
        boolean b = renderMenu(model, "用户注册中心", "/register");
        if (!b) {
            return "user/register";
        } else {
            return "index";
        }
    }

    //文章编辑器页面
    @GetMapping("/author/editor")
    public String editor(Model model, HttpServletRequest request) {
        String edKey = myEncrypt.encryptPlus(RedisKeyType.edKey);//生成加密钥匙
        String host = SystemUtils.getClientHost(request);//获取用户真实IP
        model.addAttribute("articleSortList", sortService.select(1, 6));//分类列表
        model.addAttribute("articleList", articleService.select(1, 8));
        model.addAttribute("encryptConst", edKey);
        model.addAttribute("nowDate", new Date());
        redisTemplate.opsForValue().set(host + RedisKeyType.edKey, edKey, 24, TimeUnit.HOURS);//设置redis缓存
        renderMenu(model, "文章编辑中心", "/author/editor");
        return "article/editor";
    }

    //作者中心
    @GetMapping("/author")
    public String author(Model model, @RequestParam(required = false) Integer p) {
        if (!StringUtils.isEmpty(p)) {
            if (p > 1) {
                model.addAttribute("index", p);
                return "author";
            }
            model.addAttribute("index", 1);
            return "author";
        } else {
            model.addAttribute("index", 1);
            return "author";
        }
    }

    //关于本站
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    //文章详细
    @GetMapping("/article/detail")
    public String detail(Model model, @RequestParam String aid) {
        TbArticle article = articleService.queryDetail(aid);
        if (null == article) {
            return "/404";
        }
        model.addAttribute("article", article);
        renderMenu(model, article.getTitle(), "/article/detail?aid=" + aid);
        return "content/detail";
    }


}
