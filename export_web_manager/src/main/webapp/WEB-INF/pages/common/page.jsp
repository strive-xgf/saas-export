<%-- 分页 公共部分 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<body>
<div class="pull-left">
    <div class="form-group form-inline">
        <%-- pi --%>
        总共${pi.pages} 页，共${pi.total} 条数据。
    </div>
</div>

<div class="box-tools pull-right">
    <ul class="pagination" style="margin: 0px;">
        <li>
            <%-- goPage(1)去第一页 --%>
            <a href="javascript:goPage(1)" aria-label="Previous">首页</a>
        </li>
        <%-- c:if判断，如果是第一页，就不显示上一页按钮 --%>
        <c:if test="${pi.pageNum != 1 }">
            <li><a href="javascript:goPage(${pi.prePage})">上一页</a></li>
        </c:if>

        <c:forEach begin="1" end="${pi.pages}" var="i">
            <%-- 循环显示页数，如果页数是当前页，就active的css效果，高亮，点击页数就跳转goPage(i)指定页面 --%>
            <li class="paginate_button ${pi.pageNum==i ? 'active':''}"><a href="javascript:goPage(${i})">${i}</a></li>
        </c:forEach>

        <c:if test="${pi.pageNum != pi.pages }">
            <li><a href="javascript:goPage(${pi.nextPage})">下一页</a></li>
        </c:if>

        <li>
            <a href="javascript:goPage(${pi.pages})" aria-label="Next">尾页</a>
        </li>
    </ul>
</div>
<%-- 点击分页其实就等于在提交表单，传给后台当前页、每页数据大小 pageUrl是页面传来的参数信息controller的地址url --%>
<form id="pageForm" action="${param.pageUrl}" method="post">
    <input type="hidden" name="curr" id="curr">
    <input type="hidden" name="pageSize" id="pageSize">
</form>
<script>
    //goPage重新发送请求，请求page页
    function goPage(page) {
        //跳转到page页显示为当前页
        document.getElementById("curr").value = page
        //每页多少数据从pi分页pageInfo里面获取
        //当你点击分页信息（上一页、下一页、第几页、首尾页）的时候，就等于在提交form表单
        //然后通过页面传来的jsp:param参数（传递的是需要访问的controller的url地址，然后访问controller实现分页
        document.getElementById("pageSize").value = ${pi.pageSize}
            document.getElementById("pageForm").submit()
    }
</script>
</body>
</html>