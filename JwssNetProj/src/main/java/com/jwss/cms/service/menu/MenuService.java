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

    /**
     * 根据用户角色查询菜单列表
     *
     * @param role 用户角色
     * @return 菜单列表
     */
    List<TbMenu> selectByRole(String role);

}
