<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>SaaS-Export 部门更新</title>
</head>
<body>
    <div id="frameContent" class="content-wrapper" style="margin-left:0px;">
        <section class="content-header">
            <h1>
                系统管理
                <small>部门管理 - 更新</small>
            </h1>
            <%-- 打印数据进行测试 --%>
<%--            <h4>更新的部门信息</h4>
            ${dept}
            <h4>所有部门信息</h4>
            ${list}--%>
            <ol class="breadcrumb">
                <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            </ol>
        </section>

        <section class="content">
            <div class="box-body">
                <div class="nav-tabs-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab-form" data-toggle="tab">编辑部门</a>
                        </li>
                    </ul>
                    <div class="tab-content">
                        <form id="editForm" action="${path}/system/dept/update" method="post">
                            <input type="hidden" name="deptId" value="${dept.deptId}">
                            <div class="tab-pane active" id="tab-form">
                                <div class="row data-type">
                                    <div class="col-md-2 title">部门名称</div>
                                    <div class="col-md-10 data">
                                        <input type="text" class="form-control" placeholder="部门名称" name="deptName" value="${dept.deptName}">
                                    </div>
                                    <div class="col-md-2 title">上级部门</div>
                                    <div class="col-md-10 data line-height36">
                                        <select class="form-control" name="parentId">
                                            <%-- 成为顶级部门那么就没有上级部门，也就是上级部门为null，这里传空值 --%>
                                            <option value="">成为顶级部门</option>
                                            <c:forEach items="${list}" var="item">
                                                <%-- dept表示正在编辑的部门数据，不能选自己作为上级部门--%>
                                                <c:if test="${dept.deptId != item.deptId}">
                                                    <%-- 选中当前的上级部门，给下拉框添加一个选择效果 --%>
                                                    <option ${dept.parent.deptId == item.deptId ?'selected':''}  value="${item.deptId}">${item.deptName}</option>
                                                </c:if>

                                            </c:forEach>
                                        </select>
                                    </div>
                                    <div class="col-md-2 title">状态</div>
                                    <div class="col-md-10 data">
                                        <div class="form-group form-inline">
                                            <div class="radio"><label><input type="radio" ${dept.state==0?'checked':''} name="state" value="0">停用</label></div>
                                            <div class="radio"><label><input type="radio" ${dept.state==1?'checked':''} name="state" value="1">启用</label></div>
                                        </div>
                                    </div>
                                    <div class="col-md-2 title"></div>
                                    <div class="col-md-10 data text-center">
                                        <button type="button" onclick='document.getElementById("editForm").submit()'  class="btn bg-maroon">保存</button>
                                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </section>
    </div>
</body>

</html>