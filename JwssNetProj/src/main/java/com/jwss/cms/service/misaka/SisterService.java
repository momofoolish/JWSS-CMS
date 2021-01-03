package com.jwss.cms.service.misaka;

import com.jwss.cms.entity.misaka.Sister;
import com.jwss.cms.mapper.misaka.SisterMapper;
import com.jwss.cms.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SisterService implements BaseService {
    private final Logger logger = LoggerFactory.getLogger(SisterService.class);

    @Resource
    SisterMapper sisterMapper;

    @Override
    public int insert(Object obj) {
        return sisterMapper.insertSister((Sister) obj);
    }

    @Override
    public int delete(Object obj) {
        return 1;
    }

    @Override
    public int update(Object o) {
        return 0;
    }

    @Override
    public Object select(int index, int total) {
        logger.info("Sister分页查询,index: " + index + "\t total: " + total);
        return sisterMapper.selectListByPage((index - 1) * total, total);
    }

    public List<Sister> sisterByKey(String key) {
        if (!key.equals("")) {
            return sisterMapper.selectListByKey(key);
        } else {
            return null;
        }
    }

    /**
     * 查询一共有多少条数据
     *
     * @return 总条数
     */
    public int countSister() {
        return sisterMapper.countSister();
    }
}
