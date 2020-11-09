package com.xgf.web.shiro;

import com.xgf.domain.system.user.User;
import com.xgf.service.system.user.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//shiro的一个能够查询权限的类
//不需要再添加@Component,因为在xml中使用bean标签配置了（已经创建了实例）
//继承授权AuthorizingRealm类，AuthorizingRealm两大功能： 认证（登录账号密码）和 授权（查用户有什么权限）
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    IUserService userService;

    //日志
    private Logger l = LoggerFactory.getLogger(this.getClass());
    //spring通过反射调用无参构造函数（创建对象）
    public AuthRealm(){
        l.info("AuthRealm 无参构造函数");
    }
    //授权（查用户有什么权限）
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        l.info("AuthRealm doGetAuthorizationInfo 【授权】函数执行查用户有什么权限）");
        return null;
    }
    //认证（登录账号密码）
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        l.info("AuthRealm doGetAuthenticationInfo 【认证】函数执行（登录账号密码）");

        //响应login方法和getPrincipal方法的调用 subject.login(token)  （UserController类）
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        String email = usernamePasswordToken.getUsername();
        l.info("doGetAuthenticationInfo --" + email);
        //调service读dao
        User user = userService.findUserByEmail(email);
        l.info("doGetAuthenticationInfo --" + user);
        if (user == null) {
            //用户不存在
            return null;//返回null，系统会将null转成UnknownAccountException异常抛出抛出
        } else {
            //用户存在的
            //AuthenticationInfo 返回给 User user = (User) subject.getPrincipal();（UserController类）
            /**
               参数1：principal，存放用户登录信息，subject.getPrincipal()获取
               参数2：数据库的密码
               参数3：realm的别名，只有在多个Realm的时候才会用，一般不用
             */
            AuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), "");
            return info;
        }
    }
}
