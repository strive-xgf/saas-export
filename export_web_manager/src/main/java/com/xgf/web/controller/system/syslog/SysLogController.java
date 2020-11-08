package com.xgf.web.controller.system.syslog;

import com.github.pagehelper.PageInfo;

import com.xgf.domain.system.syslog.SysLog;
import com.xgf.service.system.syslog.ISysLogService;
import com.xgf.web.controller.system.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//日志，只需要查询，一般不需要对日志进行修改
@Controller
@RequestMapping("/system/syslog")
public class SysLogController extends BaseController {
    private static final Logger l = LoggerFactory.getLogger(SysLogController.class);

    @Autowired
    ISysLogService iSysLogService;

    @RequestMapping(path = "/toList", method = {RequestMethod.GET, RequestMethod.POST})
    public String toList(@RequestParam(defaultValue = "1") int curr, @RequestParam(defaultValue = "10") int pageSize) {
        //查询所有日志信息，分页显示，放到PageInfo中
        PageInfo<SysLog> pi = iSysLogService.findByPage(curr, pageSize, getLoginCompanyId());
        //将pi发送到页面
        request.setAttribute("pi", pi);
        return "system/syslog/log-list";
    }
}