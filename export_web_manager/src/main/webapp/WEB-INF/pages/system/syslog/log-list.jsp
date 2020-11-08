<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>SaaS-Export 日志列表</title>
    <meta name="description" content="SaaS-Export 日志列表">
    <meta name="keywords" content="SaaS-Export log list">
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>

<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        系统管理
        <small>系统日志 - 列表</small>
    </h1>
</section>

<section class="content">
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">日志列表</h3>
        </div>


        <div class="box-body">
            <div class="table-box">
                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>
                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <!--工具栏/-->


                <!--数据列表-->
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="sorting">序号</th>
                        <th class="sorting">用户名</th>
                        <th class="sorting">ip地址</th>
                        <th class="sorting">时间</th>
                        <th class="sorting">类与方法</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pi.list}" var="log"  varStatus="st">
                        <tr>
                            <td>${st.count }</td>
                            <td>${log.userName }</td>
                            <td>${log.ip}</td>
                            <td><fmt:formatDate value="${log.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            <td>${log.action}.${log.method}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%-- 分页信息 --%>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${path}/system/syslog/toList" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>
</section>
</div>
</body>
</html>