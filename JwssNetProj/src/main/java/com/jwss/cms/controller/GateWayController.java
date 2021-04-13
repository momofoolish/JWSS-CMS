package com.jwss.cms.controller;

import com.jwss.cms.constant.RedisKeyType;
import com.jwss.cms.model.article.TbArticle;
import com.jwss.cms.model.menu.TbMenu;
import com.jwss.cms.service.article.ArticleService;
import com.jwss.cms.service.article.SortService;
import com.jwss.cms.service.menu.MenuService;
import com.jwss.cms.service.user.OnlineService;
import com.jwss.cms.util.MyEncrypt;
import com.jwss.cms.util.SystemUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jetbrains.annotations.NotNull;
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
public class GateWayController {
    @Resource
    MenuService menuService;
    @Resource
    SortService sortService;
    @Resource
    MyEncrypt myEncrypt;
    @Resource
    ArticleService articleService;
    @Resource
    OnlineService onlineService;
    @Autowired
    StringRedisTemplate redisTemplate;

    //登录页面
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("baseTitle", "Jwss");
        model.addAttribute("title", "登录欢迎━(*｀∀´*)ノ亻!");
        if (isLogin() == 0) {
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
        model.addAttribute("baseTitle", "Jwss");
        model.addAttribute("title", "用户注册中心");
        if (isLogin() == 0) {
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
        model.addAttribute("baseTitle", "JWSS");
        model.addAttribute("title", "文章编辑中心");
        model.addAttribute("user", onlineService.userInfo());
        model.addAttribute("articleSortList", sortService.select(1, 6));//分类列表
        model.addAttribute("articleList", articleService.select(1, 8));
        model.addAttribute("encryptConst", edKey);
        model.addAttribute("nowDate", new Date());
        redisTemplate.opsForValue().set(host + RedisKeyType.edKey, edKey, 24, TimeUnit.HOURS);//设置redis缓存
        renderMenu(model);
        return "content/editor";
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
        model.addAttribute("baseTitle", "Jwss");
        model.addAttribute("title", article.getTitle());
        model.addAttribute("user", onlineService.userInfo());
        renderMenu(model);
        return "content/detail";
    }

    /**
     * 判断是否已经登录
     *
     * @return 1&0
     */
    private int isLogin() {
        Subject subject = SecurityUtils.getSubject();
        if (StringUtils.isEmpty(subject.getPrincipals())) {
            return 0;   //未登录 0
        } else {
            return 1;   //已登录 1
        }
    }

    /**
     * 渲染菜单
     *
     * @param model Model
     */
    private void renderMenu(@NotNull Model model) {
        List<TbMenu> menuList = menuService.selectAll();
        model.addAttribute("menuList", menuList);
        model.addAttribute("isLogin", isLogin());
    }
}
