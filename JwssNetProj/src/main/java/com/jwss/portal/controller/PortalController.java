package com.jwss.portal.controller;

import com.jwss.cms.model.menu.TbMenu;
import com.jwss.cms.service.article.ArticleService;
import com.jwss.cms.service.menu.impl.MenuServiceImpl;
import com.jwss.cms.service.user.OnlineService;
import com.jwss.cms.util.SqlUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PortalController {
    @Resource
    MenuServiceImpl menuService;
    @Resource
    OnlineService onlineService;
    @Resource
    ArticleService articleService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("baseTitle", "Jwss");
        model.addAttribute("title", "首页");
        model.addAttribute("user", onlineService.userInfo());
        List<String> colsGenerator = SqlUtils.colsGenerator("create_date", "id", "title", "author_id", "cover");
        model.addAttribute("articleList", articleService.selectBySearch(1, 40, "", colsGenerator));
        renderMenu(model);
        return "index";
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
