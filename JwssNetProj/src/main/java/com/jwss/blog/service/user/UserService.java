package com.jwss.blog.service.user;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jwss.blog.config.HostConfig;
import com.jwss.blog.entity.ResultCode;
import com.jwss.blog.entity.render.Result;
import com.jwss.blog.entity.sqldata.User;
import com.jwss.blog.mapper.ArticleMapper;

import com.jwss.blog.mapper.UserMapper;
import com.jwss.blog.util.Sys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    HostConfig hostConfig;
    @Autowired
    StringRedisTemplate redisTemplate;

    /**
     * 是否存在该账号
     *
     * @param account 账号
     * @return Result
     */
    public Result accountExist(String account) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("account").lambda().eq(User::getAccount, account);
        //判断是否存在该账号
        User user = userMapper.selectOne(queryWrapper);
        if (user.getAccount() == null) {
            return new Result(1, "该账号可以使用");
        } else {
            return new Result(0, "该账号已存在");
        }
    }

    /**
     * 是否存在用户名
     *
     * @param name 用户名
     * @return Result
     */
    public Result userNameExist(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getName, name);
        User sqlUserName = userMapper.selectOne(queryWrapper);
        if (sqlUserName == null) {
            return new Result(1, "该名称可以使用");
        } else {
            return new Result(0, "该名称已存在");
        }
    }

    /**
     * 用户注册服务
     *
     * @param user       用户实体类
     * @param verifyCode 验证码
     * @param request    HttpServletRequest
     * @return 成功返回用户的账号
     */
    public Result register(User user, String verifyCode, HttpServletRequest request) {
        String userIp = Sys.getClientHost(request);
        String redisCode = redisTemplate.opsForValue().get(userIp);
        if (!verifyCode.equals(redisCode)) {
            return new Result(0, ResultCode.VERIFITY_CODE_ERROR);
        }
        String passWord = user.getPassword();
        String account = user.getAccount();
        //判断是否存在该账号
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getAccount, account);
        User oldUser = userMapper.selectOne(queryWrapper);
        logger.info("该用户进行了注册，account：" + account + "\t IP：" + userIp);
        if (StringUtils.isEmpty(oldUser)) {
            byte[] bytes = passWord.getBytes();
            //转换成md5字符串
            String pwdMd5 = DigestUtils.md5DigestAsHex(bytes);
            //设置用户的数据
            user.setId(Sys.uuid());
            user.setName(account);
            user.setAccount(account);
            user.setPassword(pwdMd5);
            user.setReadsNumber(0);
            user.setLikesNumber(0);
            user.setRoles("user");
            user.setRegisterDate(new Date());
            //向数据库插入数据
            int flag = userMapper.insert(user);
            if (flag >= 0) {
                return new Result(flag, account + "注册成功");
            } else {
                return new Result(flag, ResultCode.SERVER_ERROR);
            }
        } else {
            return new Result(0, ResultCode.USER_EXIST_ACCOUNT);
        }
    }

    /**
     * 查询用户信息
     *
     * @param account 用户账号
     * @return 用户信息
     */
    public User getUserInfo(String account) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id", "account", "mail", "name", "avatar", "reads_number", "likes_number",
                "register_date", "sign", "roles").like("account", account);
        return userMapper.selectOne(queryWrapper);
    }

    /**
     * 查询用户密码
     *
     * @param account 用户账号
     * @return 用户信息
     */
    public User getUserPassword(String account) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("password", "name", "account").like("account", account);
        return userMapper.selectOne(queryWrapper);
    }


    /**
     * 获取最新用户
     *
     * @param total 获取条数
     * @return 最新用户集合
     */
    public List<User> queryUserNewList(int total) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        IPage<User> iPage = new Page<>(0, total);
        queryWrapper.select("id", "account", "name").orderByDesc("register_date");
        return userMapper.selectPage(iPage, queryWrapper).getRecords();
    }
}
