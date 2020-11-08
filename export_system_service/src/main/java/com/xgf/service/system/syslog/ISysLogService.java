package com.xgf.service.system.syslog;

import com.github.pagehelper.PageInfo;
import com.xgf.domain.system.syslog.SysLog;


public interface ISysLogService {
    //分页显示日志信息 参1：当前页  参2：每页数据  参3：公司id
    PageInfo<SysLog> findByPage(int curr, int pageSize, String companyId);
    //保存一条日志
    void saveSysLog(SysLog sysLog);
}
