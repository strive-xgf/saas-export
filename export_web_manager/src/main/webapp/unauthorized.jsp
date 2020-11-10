<%-- 未授权页面，用户没有权限访问就跳转到该页面 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./WEB-INF/pages/base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>SaaS-Export 未授权</title>
    <meta name="description" content="SaaS-Export 未授权">
    <meta name="keywords" content="SaaS-Export unauthor">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- Content Header (Page header) -->
    <section class="content-header">
        <h1>
            您的权限不足（请充值？哈哈哈）
        </h1>
    </section>

    <!-- Main content -->
    <section class="content">
        <div class="error-page">
           <div class="error-content" style="margin-left:0">
                <h3><i class="fa fa-warning text-yellow"></i> Sorry! 没有访问权限.</h3>

                <p>
                    没有访问权限, 你可以 <a href="all-admin-index.html">返回到后台首页</a> 或者通过搜索查询
                </p>

                <form class="search-form">
                    <div class="input-group">
                        <input type="text" name="search" class="form-control" placeholder="搜索">

                        <div class="input-group-btn">
                            <button type="submit" name="submit" class="btn btn-warning btn-flat"><i class="fa fa-search"></i>
                            </button>
                        </div>
                    </div>
                    <!-- /.input-group -->
                </form>
            </div>
        <!-- /.error-page -->
        </div>
    </section>
</div>
<!-- 内容区域 /-->
</body>

</html>