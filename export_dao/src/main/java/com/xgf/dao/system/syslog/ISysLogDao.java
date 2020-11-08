package com.xgf.dao.system.syslog;


import com.xgf.domain.system.syslog.SysLog;

import java.util.List;

public interface ISysLogDao {
    //通过公司的id查找所有的日志
    List<SysLog> findAll(String companyId);
    //新增一条日志记录（用户调用方法，进入controller等都记录日志）
    void save(SysLog sysLog);
}
