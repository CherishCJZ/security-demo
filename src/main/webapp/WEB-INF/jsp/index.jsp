<%--
  Created by IntelliJ IDEA.
  User: CJZ
  Date: 2018/9/30
  Time: 10:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello,${username} has roles: ${roles}</h1>


<sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
    <h2>领导好</h2>
</sec:authorize>


<sec:authorize access="hasRole('开发组长')">
    <h2>我是个小组长</h2>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <a href="${pageContext.request.contextPath}/logout">退出</a>
    <a href="${pageContext.request.contextPath}/hello">hello</a>
</sec:authorize>

<sec:authorize url="${pageContext.request.contextPath}/logout">
    everyone can see this.
</sec:authorize>
</body>
</html>
