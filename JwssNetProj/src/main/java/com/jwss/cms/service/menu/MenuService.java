package com.jwss.cms.service.menu;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jwss.cms.entity.sqldata.Menu;
import com.jwss.cms.mapper.MenuMapper;
import com.jwss.cms.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MenuService implements BaseService {
    private final Logger logger = LoggerFactory.getLogger(MenuService.class);

    @Resource
    MenuMapper menuMapper;

    /**
     * 查询菜单列表
     *
     * @return 菜单列表
     */
    public List<Menu> queryMenuList() {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        List<Menu> menuList = menuMapper.selectList(queryWrapper);
        logger.info("menuList: " + menuList);
        return menuList;
    }

    @Override
    public int insert(Object o) {
        return 0;
    }

    @Override
    public int delete(Object o) {
        return 0;
    }

    @Override
    public int update(Object o) {
        return 0;
    }

    @Override
    public Object select(int index, int total) {
        return null;
    }
}
