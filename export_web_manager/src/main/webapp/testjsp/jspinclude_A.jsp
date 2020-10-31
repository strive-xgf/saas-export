<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试jsp的include标签</title>
</head>
<body>
    <%-- 子标签param，将k-v键值对的值传给被include重用的页面 --%>
    <h3>jsp:include调用B并且传递参数</h3>
        <%-- 传递键值对，分页可以通过传地址实现 --%>
    <jsp:include page="/testjsp/jspinclude_B.jsp">
        <jsp:param name="username" value="strive_day" ></jsp:param>
        <jsp:param name="url" value="http://www.baiud.com" ></jsp:param>
    </jsp:include>

    <h3>jsp:include调用B并不进行参数传递</h3>
    <jsp:include page="/testjsp/jspinclude_B.jsp"></jsp:include>


</body>
</html>
