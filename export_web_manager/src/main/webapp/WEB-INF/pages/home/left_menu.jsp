<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 引入公共的css、js --%>
<%@ include file="../base.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${path}/img/user2-160x160.jpg" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <%-- 显示登录用户名，公司名称 --%>
                <p> ${loginUser.userName}</p>
                <a href="#">${loginUser.companyName}</a>
            </div>
        </div>

        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>

            <%--
                        <c:forEach items="${sessionScope.modules}" var="item">
                            <c:if test="${item.ctype==0}">
                                <li class="treeview">
                                    <a href="#">
                                        <i class="fa fa-cube"></i> <span>${item.name}</span>
                                        <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
                                    </a>
                                    <ul class="treeview-menu">
                                        <c:forEach items="${sessionScope.modules}" var="item2">
                                            <c:if test="${item2.ctype==1 && item2.parentId == item.id}">
                                                <li id="${item2.id}">
                                                    <a onclick="setSidebarActive(this)" href="${item2.curl}" target="iframe">
                                                        <i class="fa fa-circle-o"></i>${item2.name}
                                                    </a>
                                                </li>
                                            </c:if>
                                        </c:forEach>
                                    </ul>
                                </li>
                            </c:if>
                        </c:forEach>
                         --%>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-cube"></i> <span>Saas管理</span>
                    <span class="pull-right-container">
                       <i class="fa fa-angle-left pull-right"></i>
                   </span>
                </a>
                <ul class="treeview-menu">
                    <li id="company-manager">
                        <a href="${path}/company/toList" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>企业管理
                        </a>
                    </li>

                    <li id="module-manager">
                        <a href="${path}/system/module/toList" onclick="setSidebarActive(this)" target="iframe">
                            <i class="fa fa-circle-o"></i>模块管理
                        </a>
                    </li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-cube"></i> <span>货运管理</span>
                    <span class="pull-right-container">
                     <i class="fa fa-angle-left pull-right"></i>
                 </span>
                </a>
                <ul class="treeview-menu">
                    <li id="cargo-contract">
                        <a href="cargo/contract/list" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>购销合同
                        </a>
                    </li>
                    <li id="cargo-out">
                        <a href="/cargo/contract/print" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>出货表
                        </a>
                    </li>
                    <li id="cargo-contractlist">
                        <a href="/cargo/export/contractList" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>合同管理
                        </a>
                    </li>
                    <li id="cargo-export">
                        <a href="/cargo/export/list" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>出口报运
                        </a>
                    </li>
                    <li id="cargo-pack">
                        <a href="/cargo/packing/list" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>装箱管理
                        </a>
                    </li>
                    <li id="cargo-ship">
                        <a href="/cargo/shipping/list" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>委托管理
                        </a>
                    </li>
                    <li id="cargo-invoice">
                        <a href="/cargo/invoice/list" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>发票管理
                        </a>
                    </li>
                    <li id="cargo-finance">
                        <a href="/cargo/finance/list" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>财务管理
                        </a>
                    </li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-cube"></i> <span>统计分析</span>
                    <span class="pull-right-container">
                     <i class="fa fa-angle-left pull-right"></i>
                 </span>
                </a>
                <ul class="treeview-menu">
                    <li id="stat-factory">
                        <a href="/stat/toCharts?chartsType=factory" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>厂家销量统计
                        </a>
                    </li>
                    <li id="stat-sell">
                        <a href="/stat/toCharts?chartsType=sell" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>产品销量排行
                        </a>
                    </li>
                    <li id="stat-online">
                        <a href="/stat/toCharts?chartsType=online" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>在线人数统计
                        </a>
                    </li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-cube"></i> <span>基础信息</span>
                    <span class="pull-right-container">
                     <i class="fa fa-angle-left pull-right"></i>
                 </span>
                </a>
                <ul class="treeview-menu">
                    <li id="system-code">
                        <a href="" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>系统代码
                        </a>
                    </li>
                    <li id="base-factory">
                        <a href="" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>厂家信息
                        </a>
                    </li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-cube"></i> <span>系统管理</span>
                    <span class="pull-right-container">
                     <i class="fa fa-angle-left pull-right"></i>
                 </span>
                </a>
                <ul class="treeview-menu">

                    <li id="sys-dept">
                        <a href="${path}/system/dept/toList"  onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>部门管理
                        </a>
                    </li>
                    <li id="sys-user">
                        <a href="${path}/system/user/toList" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>用户管理
                        </a>
                    </li>
                    <li id="sys-role">
                        <a href="${path}/system/role/toList" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>角色管理
                        </a>
                    </li>
                    <li id="sys-log">
                        <a href="${path}/system/log/toList" onclick="setSidebarActive(this)"  target="iframe">
                            <i class="fa fa-circle-o"></i>日志管理
                        </a>
                    </li>
                </ul>
            </li>

        </ul>

    </section>
    <!-- /.sidebar -->
</aside>
