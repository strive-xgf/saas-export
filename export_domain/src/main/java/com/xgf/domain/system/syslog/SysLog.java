package com.xgf.domain.system.syslog;

import java.util.Date;

//日志类
public class SysLog {
    private String id;
    private String userName;    //用户名
    private String ip;          //用户的ip地址
    private Date time;          //记录时间
    private String method;      //调用的方法
    private String action;      //调用的controller路径
    private String companyId;   //用户的公司id
    private String companyName; //用户的公司名称

    public SysLog() {
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", ip='" + ip + '\'' +
                ", time=" + time +
                ", method='" + method + '\'' +
                ", action='" + action + '\'' +
                ", companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
