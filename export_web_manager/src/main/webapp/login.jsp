<%-- 登录页面 --%>
<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>SaaS-Export | 登录</title>

    <%-- 项目路径 --%>
    <% pageContext.setAttribute("path",request.getContextPath());%>
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <link rel="stylesheet" href="${path}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${path}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${path}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${path}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="${path}/plugins/iCheck/square/blue.css">

</head>

<script>
    window.onload = function () {
        if (window.parent.window != window) {
            window.top.location = "/login.jsp";
        }
    }
</script>

<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="all-admin-index.html">SaaS外贸出口云平台</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">登录系统</p>
        <!-- 登录提交表单 -->
        <form action="${path}/system/user/login" method="post">
            <!-- 显示来自request的信息，ajax显示错误信息   -->
            <div class="" style="color: red;margin-bottom: 20px">${error}</div>

            <div class="form-group has-feedback">
                <!-- 参数名称 email password -->
                <input type="email" name="email" class="form-control" placeholder="请输入您的Email邮箱">
                <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="password" class="form-control" placeholder="请输入您的密码">
                <span class="glyphicon glyphicon-lock form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck">
                        <div class="checkbox icheck">
                            <label><input type="checkbox"> 记住密码 下次自动登录</label>
                        </div>
                    </div>
                </div>
                <div class="col-xs-4">
                    <!-- 点击提交，登录 -->
                    <button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
                </div>
            </div>
        </form>
        <div class="social-auth-links text-center">
            <p>- 或者 -</p>
            <a href="#" class="btn btn-block btn-social btn-facebook btn-flat"><i class="fa fa-qq"></i> QQ登录</a>
            <a href="#" class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-weixin"></i> 微信登录</a>
            <br/>
            <a href="#" title="密码忘记啦，点我找回试试" style="font-size: 16px">忘记密码？</a>
            &emsp;&emsp;
            <a href="all-admin-register.html" class="text-center" title="注册成为新用户" style="font-size: 16px">新用户注册</a>
        </div>
    </div>
</div>
<script src="${path}/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="${path}/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="${path}/plugins/iCheck/icheck.min.js"></script>
<script>
    $(function() {
        $('input').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%' // optional
        });
    });
</script>
</body>
</html>
