<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>SaaS-Export 用户管理</title>
    <meta name="description" content="SaaS-Export 用户管理">
    <meta name="keywords" content="SaaS-Export user manage ">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
              //  location.href="/system/user/delete?id="+id;
                //使用ajax请求
                var url= '${path}/system/user/delete?userId='+id;
                var fn = function(result){ //{code:200,msg:'删除成功',data:null}
                    //弹出提示
                    alert(result.message)
                    window.location.reload() //重新加载
                }
                $.get(url,fn,'json')
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    //选中用户点击角色的时候，进入用户的角色列表
    function roleList() {
        var userId = getCheckId()
        if(userId) {
            location.href="${path}/system/user/toUserRole?userId="+userId;
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
        <small>用户管理 - 用户列表</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<section class="content">
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">用户列表</h3>
        </div>
        <div class="box-body">
            <div class="table-box">
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${path}/system/user/toAdd"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="角色" onclick="roleList()"><i class="fa fa-user-circle-o"></i> 角色</button>
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
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;">

                        </th>
                        <th class="sorting">序号</th>
                        <th class="sorting">用户名</th>
                        <th class="sorting">部门</th>
                        <th class="sorting">邮箱</th>
                        <th class="sorting">联系电话</th>
                        <th class="sorting">性别</th>
                        <th class="sorting">职位</th>
                        <th class="sorting">状态</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pi.list}" var="item">
                    <tr>
                        <td><input name="ids" value="${item.userId}" type="checkbox"></td>
                        <td>${status.index+1}</td>
                        <td><a href="${path}/system/user/toUpdate?userId=${item.userId}">${item.userName}</a></td>
                        <td>${item.deptName }</td>
                        <td>${item.email }</td>
                        <td>${item.telephone }</td>
                        <td>${item.gender ==0?'男':'女'}</td>
                        <td>${item.station }</td>
                        <td>${item.state  ==0?'停用':'启用'}</td>
                        <th class="text-center">
                            <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${path}/system/user/toUpdate?userId=${item.userId}"'>编辑</button>
                        </th>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <%-- 分页信息 --%>
        <div class="box-footer">
            <jsp:include page="../../common/page.jsp">
                <jsp:param value="${path}/system/user/toList" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>

</section>
</div>
</body>

</html>