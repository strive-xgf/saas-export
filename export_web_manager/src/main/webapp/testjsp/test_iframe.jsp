
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试iframe</title>
</head>
<body>
        <%--以后进行增删改查，就只需要设置一个iframe，
         然后点击怎删改查的时候将信息显示在iframe是上就行--%>

        <a href="http://www.baidu.com" target="baidu">读百度主页到f1</a>
        <a href="http://www.iqiyi.com" target="aiqiyi">读iqi主页到f2</a>

        <a href="http://www.taobao.com" target="f3">读淘宝主页到f3</a>
        <a href="http://www.tianmao.com" target="f3">读天猫主页到f3</a>
        <br/>

        <%-- iframe的name可以被 --%>
        <iframe name="baidu" width="30%" height="50%" style="background-color: blue" ></iframe>
        <iframe name="aiqiyi" width="30%" height="50%" style="background-color: green" ></iframe>
        <iframe name="f3" width="30%" height="50%" style="background-color: aqua" ></iframe>
</body>
</html>
