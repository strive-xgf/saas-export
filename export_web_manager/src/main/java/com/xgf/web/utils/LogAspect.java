package com.xgf.web.utils;

import com.xgf.domain.system.syslog.SysLog;
import com.xgf.domain.system.user.User;
import com.xgf.service.system.syslog.ISysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

//日志切面类
@Aspect  //配置@Aspect注解
@Component //交给spring容器管理创建
public class LogAspect {
    //日志
    private Logger l = LoggerFactory.getLogger(LogAspect.class);


    public LogAspect() {
        l.info("LogAspect 无参构造方法");
    }

    /* execution(* com.xgf.web.controller..*.*Controller.*(..))
        第一个*号： 表示返回类型，*号表示所有的类型都可以。
        包名（com.xgf.web.controller..*：)com.xgf.web.controller表示需要拦截的包名，中间的两个点一个星``..*``表示**当前包和当前包的所有子包**
        类名（*Controller`)：表示所有的（满足前面包名条件）以Controller结尾的类（比如XxxController）
        *(..)表示方法：*号表示所有的方法，(..)括号内表示方法的参数，..两个点表示任何参数都可以。
    */

    //对所有的Controller的方法进行配置切面曾倩，实现日志记录
    //拦截指定com.xgf.web.controller包下以及它的所有子包的以*开头Controller结尾的所有类的所有方法
    @Around(value = "execution(* com.xgf.web.controller..*.*Controller.*(..))")
    public Object writeLog(ProceedingJoinPoint jp) {    //写日志
        // jp表示Controller中的任意方法 toList toAdd toUpdate add update delete
        Object result = null;   //返回一个表示页面的字符串，也可以是json数据
        try {
            //这里可以加前置通知
            result = jp.proceed();//执行方法
            //这里可以加后置通知
            //保存日志，执行saveSysLog方法（下面定义）
            saveSysLog(jp);
            l.info("LogAspect切面 - Around");
        } catch (Throwable e) {
            //发生异常
        } finally {
            //释放资源
        }
        return result;
    }

    @Autowired
    ISysLogService iSysLogService;

    //登录成功之后session中是保存一个user对象的
    @Autowired
    HttpSession session;

    //request对象可以直接获取对方浏览器的IP，记录日志用户的ip地址
    @Autowired
    HttpServletRequest request;

    private void saveSysLog(ProceedingJoinPoint jp) {
        //日志对象
        SysLog sysLog = new SysLog();
        //获取登录session
        User user = (User) session.getAttribute("loginUser");
        if(user !=null){
            //记录日志
            sysLog.setUserName(user.getUserName());
            //设置企业信息
            sysLog.setCompanyId(user.getCompanyId());
            sysLog.setCompanyName(user.getCompanyName());
        }

        //IP地址  request.getRemoteAddr()获取请求中的ip地址
        sysLog.setIp(request.getRemoteAddr());
        //设置记录时间
        sysLog.setTime(new Date());
        //执行的方法名称  jp.getSignature() 当前执行的方法
        sysLog.setMethod(jp.getSignature().getName());
        //执行的类名称 jp.getTarget()目标对象
        sysLog.setAction(jp.getTarget().getClass().getName());
        l.info("LogAspect - saveSysLog sysLog "+sysLog);

        //调用service保存日志
        iSysLogService.saveSysLog(sysLog);
    }
}
