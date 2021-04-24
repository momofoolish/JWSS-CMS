package com.jwss.cms.service.menu.impl;

import com.jwss.cms.dao.menu.TbMenuDao;
import com.jwss.cms.model.menu.TbMenu;
import com.jwss.cms.service.menu.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    private final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    @Resource
    private TbMenuDao menuDao;

    @Override
    public List<TbMenu> selectAll() {
        return menuDao.selectAll();
    }

    @Override
    public List<TbMenu> selectByRole(String role) {
        //权限递增
        int state = 1;
        if ("user".equals(role)) {
            state = 2;
        } else if ("author".equals(role)) {
            state = 3;
        } else if ("admin".equals(role)) {
            state = 4;
        }
        List<TbMenu> menuList = menuDao.selectByRole(state);
        if (state > 1) {
            for (int i = menuList.size() - 1; i >= 0; i--) {
                String colName = menuList.get(i).getCol_name();
                if ("登录".equals(colName) || "注册".equals(colName)) {
                    menuList.remove(i);
                }
            }
        }
        return menuList;
    }

}
