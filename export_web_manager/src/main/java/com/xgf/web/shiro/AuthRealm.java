package com.xgf.web.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//shiro的一个能够查询权限的类
//不需要再添加@Component,因为在xml中使用bean标签配置了（已经创建了实例）
//继承授权AuthorizingRealm类，AuthorizingRealm两大功能： 认证（登录账号密码）和 授权（查用户有什么权限）
public class AuthRealm extends AuthorizingRealm {
    //日志
    private Logger l = LoggerFactory.getLogger(this.getClass());
    //spring通过反射调用无参构造函数（创建对象）
    public AuthRealm(){
        l.info("AuthRealm 无参构造函数");
    }
    //授权（查用户有什么权限）
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        l.info("AuthRealm doGetAuthorizationInfo 函数执行");
        return null;
    }
    //认证（登录账号密码）
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        l.info("AuthRealm doGetAuthenticationInfo 函数执行");
        return null;
    }
}
