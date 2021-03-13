package com.jwss.cms.service.user;

import com.jwss.cms.model.user.TbUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 分页查询
     *
     * @param index 索引值（开始）
     * @param total 查询多少条？
     * @param map 请求参数
     * @param cols 查询列名
     * @return 用户队列
     */
    List<Map<String, String>> selectByPage(int index, int total, Map<String, Object> map, List<String> cols);

    /**
     * 是否存在该账号
     *
     * @param account 账号
     * @return 1存在
     */
    int accountExist(String account);

    /**
     * 是否存在用户名
     *
     * @param name 用户名
     * @return 1存在
     */
    int userNameExist(String name);

    /**
     * 用户注册服务
     *
     * @param user       用户实体类
     * @param verifyCode 验证码
     * @param request    HttpServletRequest
     * @return 0验证码错误,-1发生意外错误,1名称已存在
     */
    int register(TbUser user, String verifyCode, HttpServletRequest request);

    /**
     * 通过账号查询用户信息
     *
     * @param account 用户账号
     * @return 用户信息
     */
    TbUser getUserInfo(String account);

    /**
     * 获取最新用户
     *
     * @param total 获取条数
     * @return 最新用户集合
     */
    List<TbUser> queryUserNewList(int total);

    /**
     * 批量删除
     *
     * @param map 文章id列表
     * @return 返回受影响数量
     */
    int deleteBatch(Map<String, Object> map);

    /**
     * 更新用户角色
     *
     * @param uuid 用户UUID
     * @param role 用户角色
     * @return 返回数据更新条数
     */
    int updateUserRoles(String uuid, String role);
}
