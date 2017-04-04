package com.java.xdd.shiro.mapper;

import com.github.abel533.mapper.Mapper;
import com.java.xdd.shiro.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserMapper extends Mapper<User>{

    /**
     * 根据id获取用户信息
     * @param id
     * @return
     */
    User get(@Param("id") Long id);

    /**
     * 注册用户
     * @param user
     * @return
     */
    Long save(User user);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findSysUserByUsername(@Param("username") String username);

    List<User> findByInsertTime(@Param("insertTime")Date insertTime);



    User findById(@Param("password")String password);

}
