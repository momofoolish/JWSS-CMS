package com.jwss.cms.dao.menu;

import com.jwss.cms.dao.MyBatisBaseDao;
import com.jwss.cms.model.menu.TbMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TbMenuDao extends MyBatisBaseDao<TbMenu, String> {
    List<TbMenu> selectAll();

    List<TbMenu> selectByRole(int state);

    List<Map<String, String>> selectByTable(int index, int total, int state, String keyWord);
}
