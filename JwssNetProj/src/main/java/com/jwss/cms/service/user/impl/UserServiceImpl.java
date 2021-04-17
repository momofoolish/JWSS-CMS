package com.jwss.cms.service.user.impl;

import java.util.*;

import com.jwss.cms.constant.RedisKeyType;
import com.jwss.cms.dao.user.TbUserDao;

import com.jwss.cms.model.user.TbUser;
import com.jwss.cms.service.user.UserService;
import com.jwss.cms.util.SqlUtils;
import com.jwss.cms.util.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    TbUserDao userDao;
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public int accountExist(String account) {
        //判断是否存在该账号
        TbUser user = userDao.selectByColName("account", account);
        if (user.getAccount() == null) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int userNameExist(String name) {
        TbUser user = userDao.selectByColName("name", name);
        if (user == null) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int register(TbUser user, String verifyCode, HttpServletRequest request) {
        String userIp = SystemUtils.getClientHost(request);
        String redisKey = userIp + RedisKeyType.registerKey;
        String redisCode = redisTemplate.opsForValue().get(redisKey);
        if (!verifyCode.equals(redisCode)) {
            return -2;
        }
        String passWord = user.getPassword();
        String account = user.getAccount();
        //判断是否存在该账号
        TbUser oldUser = userDao.selectByColName("account", account);
        logger.info("该用户进行了注册，account：" + account + "\t IP：" + userIp);
        if (StringUtils.isEmpty(oldUser)) {
            byte[] bytes = passWord.getBytes();
            //转换成md5字符串
            String pwdMd5 = DigestUtils.md5DigestAsHex(bytes);
            //设置用户的数据
            user.setId(SystemUtils.uuid());
            user.setName(account);
            user.setAccount(account);
            user.setPassword(pwdMd5);
            user.setLikes_number(0);
            user.setRoles("user");
            user.setRegister_date(new Date());
            //向数据库插入数据
            int flag = userDao.insertByUUID(user);
            //大于0插入成功
            if (flag > 0) {
                return flag;
            } else {
                return -1;
            }
        } else {
            return -3;
        }
    }

    @Override
    public TbUser getUserInfo(String account) {
        return userDao.selectByColName("account", account);
    }

    @Override
    public List<TbUser> queryUserNewList(int total) {
        List<String> colsGenerator = SqlUtils.colsGenerator("id", "account", "name");
        return userDao.selectByNew(total, colsGenerator);
    }

    @Override
    public List<Map<String, String>> selectByPage(int index, int total, Map<String, Object> map, List<String> cols) {
        String keyWord = map.get("name") == null ? "" : map.get("name").toString();
        //如果roles不为空则通过用户名称搜索
        if (map.get("roles") == null || "".equals(map.get("roles"))) {
            keyWord = "";
        }
        List<Map<String, String>> mapList = userDao.selectBySearch((index - 1) * total, total, keyWord, cols);
        Map<String, String> countMap = new HashMap<>();
        countMap.put("total", Integer.toString(userDao.count()));
        mapList.add(countMap);
        return mapList;
    }

    @Override
    public int deleteBatch(Map<String, Object> map) {
        String[] idArray = String.valueOf(map.get("ids")).split(",");
        List<String> idList = new ArrayList<>();
        Collections.addAll(idList, idArray);
        return userDao.deleteBatch(idList);
    }

    @Override
    public int updateUserRoles(String uuid, String role) {
        if (uuid.equals("")) {
            return 0;
        }
        TbUser user = new TbUser();
        user.setRoles(role);
        user.setId(uuid);
        return userDao.updateByPrimaryKeySelective(user);
    }

}
