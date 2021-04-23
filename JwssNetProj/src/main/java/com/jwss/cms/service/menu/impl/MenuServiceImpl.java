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
    TbMenuDao menuDao;

    @Override
    public List<TbMenu> selectAll() {
        return menuDao.selectAll();
    }

}
