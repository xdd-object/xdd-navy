package com.java.xdd.shiro.service;

import com.java.xdd.shiro.domain.Permission;
import com.java.xdd.shiro.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findSysUserByUsername(String username);

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User get(Long id);

    /**
     * 注册用户
     * @param user
     * @return
     */
    Long save(User user);

    /**
     * 根据用户获取其所有权限
     * @param id
     * @return
     */
    List<Permission> findPermissionListByUserId(Long id);
}
