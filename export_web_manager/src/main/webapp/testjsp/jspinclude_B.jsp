<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重用代码</title>
</head>
<body>
    <h3>我是可以重用的jsp代码部分(jspinclude_B)</h3>
    username : ${param.username}
    <br/>
    url : ${param.url}<%--分页需要使用地址参数，可以通过该方法实现--%>
</body>
</html>
