package com.java.xdd.shiro.service.impl;

import com.java.xdd.common.util.AESUtil;
import com.java.xdd.common.util.SaltRandom;
import com.java.xdd.shiro.domain.Permission;
import com.java.xdd.shiro.domain.User;
import com.java.xdd.shiro.exception.CustomRuntimeException;
import com.java.xdd.shiro.mapper.*;
import com.java.xdd.shiro.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserMapper userMapper;//用户
    @Autowired
    private RoleMapper roleMapper;//角色
    @Autowired
    private UserRoleMapper userRoleMapper;//用户角色
    @Autowired
    private PermissionMapper permissionMapper;//权限
    @Autowired
    private RolePermissionMapper rolePermissionMapper;//角色权限



    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Override
    public User findSysUserByUsername(String username) {
        return StringUtils.isEmpty(username) ? null : userMapper.findSysUserByUsername(username);
    }

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    @Override
    public User get(Long id) {
        return this.userMapper.get(id);
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    @Override
    public Long save(User user){
        user.setSalt(SaltRandom.getSalt());//盐
        try {
            user.setPassword(AESUtil.getInstance().encrypt(user.getPassword() + user.getSalt()));
        } catch (Exception e){
            logger.error("密码加密出错！");
            e.printStackTrace();
            throw new CustomRuntimeException("密码加密出错！");
        }
        Long save = userMapper.save(user);
        return save;
    }

    @Override
    public List<Permission> findPermissionListByUserId(Long id) {
        return permissionMapper.findPermissionListByUserId(id);
    }

}
