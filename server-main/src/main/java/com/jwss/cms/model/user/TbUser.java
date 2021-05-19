package com.jwss.cms.model.user;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_user
 * @author jwss
 */
public class TbUser implements Serializable {
    /**
     * UUID
     */
    private String id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private String roles;

    /**
     * 昵称
     */
    private String name;

    /**
     * 签名
     */
    private String sign;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 阅读总量
     */
    private Integer reads_number;

    /**
     * 点赞总量
     */
    private Integer likes_number;

    /**
     * 注册日期
     */
    private Date register_date;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getReads_number() {
        return reads_number;
    }

    public void setReads_number(Integer reads_number) {
        this.reads_number = reads_number;
    }

    public Integer getLikes_number() {
        return likes_number;
    }

    public void setLikes_number(Integer likes_number) {
        this.likes_number = likes_number;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TbUser other = (TbUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccount() == null ? other.getAccount() == null : this.getAccount().equals(other.getAccount()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getRoles() == null ? other.getRoles() == null : this.getRoles().equals(other.getRoles()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSign() == null ? other.getSign() == null : this.getSign().equals(other.getSign()))
            && (this.getMail() == null ? other.getMail() == null : this.getMail().equals(other.getMail()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getReads_number() == null ? other.getReads_number() == null : this.getReads_number().equals(other.getReads_number()))
            && (this.getLikes_number() == null ? other.getLikes_number() == null : this.getLikes_number().equals(other.getLikes_number()))
            && (this.getRegister_date() == null ? other.getRegister_date() == null : this.getRegister_date().equals(other.getRegister_date()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccount() == null) ? 0 : getAccount().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getRoles() == null) ? 0 : getRoles().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSign() == null) ? 0 : getSign().hashCode());
        result = prime * result + ((getMail() == null) ? 0 : getMail().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getReads_number() == null) ? 0 : getReads_number().hashCode());
        result = prime * result + ((getLikes_number() == null) ? 0 : getLikes_number().hashCode());
        result = prime * result + ((getRegister_date() == null) ? 0 : getRegister_date().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", account=").append(account);
        sb.append(", password=").append(password);
        sb.append(", roles=").append(roles);
        sb.append(", name=").append(name);
        sb.append(", sign=").append(sign);
        sb.append(", mail=").append(mail);
        sb.append(", avatar=").append(avatar);
        sb.append(", reads_number=").append(reads_number);
        sb.append(", likes_number=").append(likes_number);
        sb.append(", register_date=").append(register_date);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
