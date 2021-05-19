package com.jwss.cms.service.user;

import com.jwss.cms.model.user.TbUser;
import com.jwss.cms.service.BaseService;
import com.jwss.cms.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OnlineService extends BaseServiceImpl implements BaseService {
    @Override
    public int insert(Object o) {return 0;

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

    /**
     * 查询用户信息
     *
     * @return 用户信息
     */
    public TbUser userInfo() {
        return getUserInfo();
    }
}
