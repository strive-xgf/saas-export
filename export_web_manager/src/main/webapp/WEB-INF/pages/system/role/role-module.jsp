<%-- 角色(通过roleid)-的模块权限 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>

<head>

    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>SaaS-Export 角色的模块树</title>
    <meta name="description" content="SaaS-Export 角色的模块树">
    <meta name="keywords" content="SaaS-Export module tree">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->

    <%--步骤：
         第一步：拷贝zTree的css/js文件到项目中
         第二步：拷贝js导入到当前页面
         第三步：页面定义显示树的区域
    --%>
    <%--拷贝zTree的js脚本、css样式导入到当前页面--%>
    <link rel="stylesheet" type="text/css" href="${path}/plugins/ztree/css/zTreeStyle/zTreeStyle.css">
    <script type="text/javascript" src="${path}/plugins/ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${path}/plugins/ztree/js/jquery.ztree.all-3.5.min.js"></script>

    <script type="text/javascript">

        //当前的配置信息
        var setting = {
            check: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            }
        };
        //zTree的数据（模拟数据）
        //pId是树形菜单的等级（0最高） open:true，就是菜单是打开的（显示子菜单）
        //checked:true 默认选中
        /*var zNodes =[
            { id:1, pId:0, name:"Sass管理", open:true},
            { id:11, pId:1, name:"企业管理", open:true,checked:true},
            { id:111, pId:1, name:"模块管理"},
            { id:112, pId:1, name:"用户管理"},
            { id:113, pId:1, name:"角色管理"}
        ];*/
        $(document).ready(function(){
            var fn =function(data){
                //菜单的初始化
                //参1 显示的标签  参2 设置的参数 比如支持复选 check enable = true  参3 数据
                $.fn.zTree.init($("#treeDemo"), setting, data);
            }
            //通过角色的id（roleId）来获取角色的模块权限信息
            $.get('${path}/system/role/getZtreeData?roleId=${role.roleId}',fn,'json')
        });
    </script>
</head>

<body style="overflow: visible;">
<div id="frameContent" class="content-wrapper" style="margin-left:0px;height: 1200px" >
    <section class="content-header">
        <h1>
            菜单管理
            <small>菜单列表 - 角色模块</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">角色 [ ${role.name} ] 权限(模块)列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">
                    <!--工具栏-->
                    <div class="box-tools text-left">
                        <button type="button" class="btn bg-maroon" onclick="submitCheckedNodes();">保存</button>
                        <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
                    </div>
                    <!--工具栏/-->
                    <!-- 树菜单 -->
                    <script type="text/javascript">
                        function submitCheckedNodes() {
                            //先读取树状菜单的moduleId，再拼接成 201,202,203 赋值给隐藏框
                            var tree= $.fn.zTree.getZTreeObj("treeDemo");
                            //再获取选中的moduleId
                            //tree.getCheckedNodes(true); 返回被选中的节点，放在一个数组中
                            var nodes = tree.getCheckedNodes(true);
                            var moduleIds = ''
                            for(var i = 0;i<nodes.length;i++){
                                console.info(nodes[i])
                                var moduleId = nodes[i].id
                                moduleIds += moduleId
                                //201,202,203 如果是最后一个元素，不需要拼接,
                                if(i != nodes.length-1){
                                    moduleIds += ','
                                }//end if

                            }//end for
                            console.info("moduleIds = "+moduleIds)
                            //将得到的moduleIds 设置给隐藏输入框，方便提交到后台控制器
                            $('#moduleIds').val(moduleIds)
                            //提交表单
                            $('#icform').submit()
                        }
                    </script>

                    <%-- zTree角色权限树，打钩就是拥有权限 --%>
                    <form id="icform" method="post" action="${path}/system/role/updateRoleModule">
                        <%-- 隐藏域角色id，后台需要根据id对角色权限进行更新 --%>
                        <input type="hidden" name="roleId" value="${role.roleId}"/>
                        <!-- 先读取树状菜单的moduleId，再拼接成 201,202,203 赋值给隐藏框-->
                        <input type="hidden" id="moduleIds" name="moduleIds" value=""/>
                        <div class="content_wrap">
                            <div class="zTreeDemoBackground left" style="overflow: visible">
                                <%-- 将模块显示出来 --%>
                                <ul id="treeDemo" class="ztree"></ul>
                            </div>
                        </div>

                    </form>
                    <!-- 树菜单 /-->

                </div>
                <!-- /数据表格 -->

            </div>
            <!-- /.box-body -->
        </div>
    </section>
</div>
</body>
</html>