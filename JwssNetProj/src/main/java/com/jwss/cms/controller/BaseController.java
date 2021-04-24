package com.jwss.cms.controller;

import com.jwss.cms.model.menu.TbMenu;
import com.jwss.cms.model.user.TbUser;
import com.jwss.cms.service.menu.MenuService;
import com.jwss.cms.service.user.OnlineService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.jetbrains.annotations.NotNull;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jwss
 * 最基本的控制器
 * 用作渲染页面
 */
public class BaseController {
    @Resource
    private OnlineService onlineService;
    @Resource
    private MenuService menuService;

    /**
     * 页面最基础渲染
     * 用户已登录返回True
     *
     * @param model Model
     */
    public boolean renderMenu(@NotNull Model model, String title, String thisPath) {
        TbUser user = onlineService.userInfo();
        boolean login = user != null;
        List<TbMenu> menuList;
        //没有登录
        if (!login) {
            menuList = menuService.selectByRole("anon");
            for (TbMenu menu : menuList) {
                //给登录页面一个参数，用来重定向回改页面
                if ("登录".equals(menu.getCol_name())) {
                    menu.setCol_url(menu.getCol_url() + "?preUrl=" + thisPath);
                }
            }
        } else {
            String roles = user.getRoles();
            menuList = menuService.selectByRole(roles);
            model.addAttribute("user", user);
        }
        model.addAttribute("menuList", menuList);
        model.addAttribute("isLogin", login ? 1 : 0);
        model.addAttribute("baseTitle", "JWSS");
        model.addAttribute("title", title);
        return login;
    }
}
