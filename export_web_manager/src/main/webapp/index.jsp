<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<% pageContext.setAttribute("path",request.getContextPath()); %>
<html>
<body>
<h2>Hello World!</h2>


<h3>后台接收日期测试</h3>
<form action="${path}/testDate.do" method="get">
    请选择日期：<input type="date" name="date">
    <br/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
