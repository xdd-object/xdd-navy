package com.java.xdd.shiro.realm;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * 用户自定义凭证匹配器
 */
public class CustomerHashedCredentialsMatcher extends HashedCredentialsMatcher{

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //AuthenticationToken   账号+密码
        Object password = token.getCredentials();
        Object username = (String)token.getPrincipal();

        //AuthenticationInfo --> simpleAuthenticationInfo(实现)
        Object credentials = info.getCredentials();
        PrincipalCollection principals = info.getPrincipals();
        super.doCredentialsMatch(token, info);

        //return super.doCredentialsMatch(token, info);
        return "admin".equals(username);
    }

}
