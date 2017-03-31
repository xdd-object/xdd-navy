package com.java.xdd.shiro.domain;

import com.java.xdd.common.domain.BaseDomain;

import java.io.Serializable;

/**
 * 用户角色
 */
public class UserRole extends BaseDomain implements Serializable{
    private static final long serialVersionUID = -3571733424852356341L;

    private Long id;//id
    private String username;//用户名
    private String password;//密码
    private String salt;//盐

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
