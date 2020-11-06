<%-- 测试zTree树形结构 --%>
<%@  page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>测试zTree</title>
    <%--项目地址--%>
    <% pageContext.setAttribute("path",request.getContextPath()); %>
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
            //ajax获取数据
            $.get('${path}/testZTree/getZtreeData',fn,'json')
        });

    </script>
</head>
<body>
    <!-- ul标签显示树状结构 -->
    <ul id="treeDemo" class="ztree"></ul>
</body>
</html>
