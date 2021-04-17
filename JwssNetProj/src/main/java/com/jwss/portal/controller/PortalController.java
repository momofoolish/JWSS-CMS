package com.jwss.portal.controller;

import com.jwss.cms.controller.BaseController;
import com.jwss.cms.service.article.ArticleService;
import com.jwss.cms.util.SqlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PortalController extends BaseController {
    @Resource
    private ArticleService articleService;

    @GetMapping("/")
    public String index(Model model) {
        List<String> colsGenerator = SqlUtils.colsGenerator("create_date", "id", "title", "author_id", "cover");
        model.addAttribute("articleList", articleService.selectBySearch(1, 40, "", colsGenerator));
        renderMenu(model, "首页", "/");
        return "index";
    }


}
