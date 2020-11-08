package com.xgf.service.system.syslog;

import com.github.pagehelper.PageInfo;

import com.xgf.domain.system.syslog.SysLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:spring/applicationContext-*.xml")
public class TestSysLogService {
    private static final Logger l = LoggerFactory.getLogger(TestSysLogService.class);
    @Autowired
    ISysLogService iSysLogService;

    //测试查询日志记录信息，并分页显示
    @Test
    public void test01() {
        PageInfo<SysLog> pi = iSysLogService.findByPage(1, 3, "1");
        l.info("分页显示日志信息 pi = " + pi);
    }

    //测试增加一条日志记录
    @Test
    public void test02() {
        //
        SysLog sysLog = new SysLog();
        //设置登录用户信息
        sysLog.setUserName("strive_day");
        //设置企业信息
        sysLog.setCompanyId("1");
        sysLog.setCompanyName("吉首大学");
        //IP地址
        sysLog.setIp("192.168.21.168");
        //设置日志产生时间
        sysLog.setTime(new Date());
        //执行的方法名称（操作执行的方法名）
        sysLog.setMethod("system.syslog.toList");
        //执行的类名称
        sysLog.setAction("com.xgf.web.syslog.SysLogController");

        iSysLogService.saveSysLog(sysLog);

    }


}