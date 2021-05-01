package com.jwss.cms.service.menu;

import com.jwss.cms.model.menu.TbMenu;

import java.util.List;
import java.util.Map;

public interface MenuService {

    /**
     * 新增
     * @param menu 新增的对象
     * @return 1
     */
    int insert(TbMenu menu);

    /**
     * 修改
     *
     * @param menu 修改的对象
     * @return 1
     */
    int update(TbMenu menu);

    /**
     * 删除
     *
     * @param id 菜单id
     * @return 1
     */
    int delete(String id);

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

    /**
     * 分页查询（联表查询出作者名称，分类名称）
     *
     * @param page    页码
     * @param total   条数
     * @param keyWord 搜索关键词
     * @param state   文章状态: -1, 查询全部, 0审核中...
     * @return 表格集合
     */
    List<Map<String, String>> selectByTable(int page, int total, int state, String keyWord);
}
