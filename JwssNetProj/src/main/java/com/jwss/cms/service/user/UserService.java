package com.jwss.cms.service.user;

import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jwss.cms.entity.ResultCode;
import com.jwss.cms.entity.render.Result;
import com.jwss.cms.entity.sqldata.User;

import com.jwss.cms.mapper.UserMapper;
import com.jwss.cms.service.BaseService;
import com.jwss.cms.util.Sys;
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
public class UserService implements BaseService {
    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Resource
    UserMapper userMapper;
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

    /**
     * 分页查询
     *
     * @param index 索引值（开始）
     * @param total 查询多少条？
     * @return 用户队列
     */
    public Map<String, Object> selectByPage(int index, int total, Map<String, Object> map) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        String keyWord = map.get("name") == null ? "" : map.get("name").toString();
        if (map.get("roles") !=  null) {
            if( !("".equals(map.get("roles"))) ){
                queryWrapper.lambda().eq(User::getRoles, map.get("roles"));
            }
        }
        queryWrapper.select(
                "id", "account", "password", "name", "mail",
                "roles", "register_date"
        );
        return queryPotting(index, total, keyWord, queryWrapper);
    }

    /**
     * 批量删除
     *
     * @param map 文章id列表
     * @return 返回受影响数量
     */
    public Integer deleteBatch(Map<String, Object> map) {
        String[] idArray = String.valueOf(map.get("ids")).split(",");
        List<String> idList = new ArrayList<>();
        Collections.addAll(idList, idArray);
        return userMapper.deleteBatch(idList);
    }

    /**
     * 更新用户角色
     *
     * @param uuid 用户UUID
     * @param role 用户角色
     * @return 返回数据更新条数
     */
    public int updateUserRoles(String uuid, String role) {
        if (uuid.equals("")) {
            return 0;
        }
        User user = new User();
        user.setRoles(role);
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(User::getId, uuid);
        return userMapper.update(user, updateWrapper);
    }

    /**
     * 查询文章列表封装
     *
     * @param index        索引值（开始）
     * @param total        查询多少条？
     * @param keyWord      搜索关键词（空的话全局搜索）
     * @param queryWrapper 可以扩展QueryWrapper
     * @return 用户队列
     */
    private Map<String, Object> queryPotting(int index, int total, String keyWord, QueryWrapper<User> queryWrapper) {
        queryWrapper.like(!keyWord.equals(""), "name", keyWord);//模糊查询
        IPage<User> iPage = new Page<>(index, total);
        IPage<User> iPageResult = userMapper.selectPage(iPage, queryWrapper);
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("data", iPageResult.getRecords());
        hashMap.put("count", iPageResult.getTotal());
        hashMap.put("msg", "成功");
        hashMap.put("code", 0);
        return hashMap;
    }

    @Override
    public int insert(Object o) {
        return 0;
    }

    @Override
    public int delete(Object o) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getId, o);
        return userMapper.delete(queryWrapper);
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
