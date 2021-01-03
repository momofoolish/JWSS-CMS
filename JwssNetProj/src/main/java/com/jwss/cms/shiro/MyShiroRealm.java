package com.jwss.cms.shiro;

import com.jwss.cms.entity.sqldata.User;
import com.jwss.cms.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //开始授权处理
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        //查询用户信息
        User user = userService.getUserInfo(subject.getPrincipal().toString());
        info.addRole(user.getRoles());
        return info;
    }

    //认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken myToken = (UsernamePasswordToken) token;
        if (!myToken.getUsername().equals("")) {
            //查用户密码进而匹配
            User user = userService.getUserPassword(myToken.getUsername());
            if (!StringUtils.isEmpty(user)) {
                ByteSource salt = new Md5Hash(user.getName()); //加盐处理
                return new SimpleAuthenticationInfo(user.getAccount(), user.getPassword(), salt, myToken.getUsername());
            }
            return null;
        }
        return null;
    }
}
