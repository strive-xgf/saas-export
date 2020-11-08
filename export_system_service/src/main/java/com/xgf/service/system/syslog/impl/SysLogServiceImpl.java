package com.xgf.service.system.syslog.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.xgf.dao.system.syslog.ISysLogDao;
import com.xgf.domain.system.syslog.SysLog;
import com.xgf.service.system.syslog.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SysLogServiceImpl implements ISysLogService {
    @Autowired
    ISysLogDao iSysLogDao;

    @Override
    public PageInfo<SysLog> findByPage(int curr, int pageSize, String companyId) {
        //设置分页参数
        PageHelper.startPage(curr,pageSize);
        //调用全查，查找所有日志
        List<SysLog> list = iSysLogDao.findAll(companyId);
        //包装成PageInfo，返回
        PageInfo<SysLog> pi = new PageInfo<>(list);
        return pi;
    }

    //保存一条日志记录
    @Override
    public void saveSysLog(SysLog sysLog) {
        //随机生成UUID作为日志id
        String uuid= UUID.randomUUID().toString();
        sysLog.setId(uuid);
        iSysLogDao.save(sysLog);
    }
}
