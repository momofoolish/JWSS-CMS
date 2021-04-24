package com.jwss.cms.dao.menu;

import com.jwss.cms.dao.MyBatisBaseDao;
import com.jwss.cms.model.menu.TbMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbMenuDao extends MyBatisBaseDao<TbMenu, String> {
    List<TbMenu> selectAll();

    List<TbMenu> selectByRole(int state);
}
