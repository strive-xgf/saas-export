package com.xgf.web.controller.system;

import com.xgf.domain.system.user.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//定义父类BaseController，让子类继承父类的request,session,response,并且注入对象
//子类继承父类，可以自动拥有父类的非私有成员（方法或者变量）
//当前默认登录的公司id为1，名称为吉首大学
public class BaseController {
    //定义一个可以返回companyId，对部门管理要看公司，这里先设死为1，也就是对id为1的公司的部门进行管理
    public String getLoginCompanyId(){
        //从session中获取登录的user
        User user = (User) session.getAttribute("loginUser");
        if (user != null) {
            return user.getCompanyId();
        } else {
            return "1";
        }
    }
    //定义一个可以返回companyName
    public String getLoginCompanyName(){
        User user = (User) session.getAttribute("loginUser");
        if (user != null) {
            return user.getCompanyName();
        } else {
            return "吉首大学";  //默认值
        }
    }

    //在父类中定义成员变量 request,session,response,并且注入对象
    // 以后控制器方法可以直接使用
    @Autowired
    protected HttpServletRequest request;
    //注入session
    @Autowired
    protected HttpSession session;
    //注入response
    @Autowired
    protected HttpServletResponse response;//需要disable inspection

}
