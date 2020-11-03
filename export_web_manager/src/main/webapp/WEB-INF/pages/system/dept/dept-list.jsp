<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Saas-Export 部门列表</title>
    <meta name="description" content="Saas-Export 部门列表">
    <meta name="keywords" content="Saas-Export">
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>
<script>
    function deleteById() {

        //获取删除的部门id
        var deptId = getCheckId()
        //选择了就有id，就执行
        if(deptId) {
            if(confirm("你确认要删除此条记录吗？")) {
                //location.href="${path}/system/dept/delete?deptId="+deptId;
                $.ajax({
                    type: "POST",
                    datatype : 'text',
                    url: "${path}/system/dept/delete?deptId="+deptId,
                    cache: false,
                    success: function(msg){
                        if(msg.code == 200){
                            alert(msg.message);
                            window.location.href = "${path}/system/dept/toList";    //删除成功刷新部门列表
                        }else {
                            alert(msg.message);
                        }
                    }
                });

            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        系统管理
        <small>部门管理 - 部门信息</small>
    </h1>

    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i>首页</a></li>
    </ol>
</section>
<!-- 内容头部 /-->

<!-- 正文区域 -->
<section class="content">

    <!-- .box-body -->
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">部门列表</h3>
        </div>

        <div class="box-body">

            <!-- 数据表格 -->
            <div class="table-box">

                <!--工具栏-->
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${path}/system/dept/toAdd?companyId=1"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
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
                        <th class="" style="padding-right:0px;">
                            <input type="checkbox" name="selid" onclick="checkAll('id',this)">
                        </th>
                        <th class="sorting">序号</th>
                        <th class="sorting">编号</th>
                        <th class="sorting">上级</th>
                        <th class="sorting">名称</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pi.list}" var="dept"  varStatus="st">
                        <tr>
                            <td><input type="checkbox" name="id" value="${dept.deptId }"/></td>
                            <td>${st.count }</td>
                            <td>${dept.deptId }</td>
                            <td>${dept.parent.deptName }</td>
                            <td><a href="/system/dept/toUpdate?id=${dept.deptId }">${dept.deptName }</a></td>
                            <th class="text-center"><button type="button" class="btn bg-olive btn-xs" onclick='location.href="${path}/system/dept/toUpdate?deptId=${dept.deptId}"'>编辑</button></th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%-- 底部分页信息 --%>
        <div class="box-footer">
            <%-- 分页页面重用，将url参数传给分页页面，分页页面通过表单提交${param.pageUrl}获取地址来实现分页 --%>
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${path}/system/dept/toList" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>
</section>
</div>
</body>
</html>