package com.java.xdd.common.util;

import com.java.xdd.shiro.domain.User;

import java.lang.reflect.Method;
import java.security.Principal;

public class PrincipalUtil {
    private PrincipalUtil(){}

    public static User getUserPrincipal(Principal principal){
        if (null == principal) return null;
        Class clz = principal.getClass();
        Object invoke;
        try {
            Method method = clz.getDeclaredMethod("getObject"); //获取登录的用户对象
            method.setAccessible(true);
            invoke = method.invoke(principal);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }

        User user = null;
        if (invoke instanceof User) user = (User) invoke;
        return user;
    }
}
