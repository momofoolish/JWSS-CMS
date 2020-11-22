package com.jwss.blog.entity.sqldata;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@TableName("tb_user")
public class User {

    private String id;
    @NotNull(message = "账号不能为空")
    @Size(min = 6, max = 16, message = "账号必须在6~16间")
    private String account;
    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 16, message = "密码必须在6~16间")
    private String password;
    @Size(min = 2, max = 32, message = "用户名必须在2~32间")
    private String name;
    @Email(message = "请输入正确的邮箱地址")
    private String mail;
    private String roles;
    private String avatar;
    private int readsNumber;       //阅读总量
    private int likesNumber;       //点赞总量
    @Size(min = 2, max = 128, message = "心情必须在2~128间")
    private String sign;
    private Date registerDate;
}
