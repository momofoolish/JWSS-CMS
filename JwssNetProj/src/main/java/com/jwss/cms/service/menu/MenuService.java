package com.jwss.cms.service.menu;

import com.jwss.cms.model.menu.TbMenu;

import java.util.List;

public interface MenuService {
    /**
     * 查询菜单列表
     *
     * @return 菜单列表
     */
    List<TbMenu> selectAll();
}
