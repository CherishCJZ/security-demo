<%--
  Created by IntelliJ IDEA.
  User: CJZ
  Date: 2018/9/30
  Time: 9:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form <%--name="f"--%> action="/authentication/form" method="post">
    <br/>
    用户名:
    <input type="text" name="username" placeholder="name"><br/>
    密码:
    <input type="password" name="password" placeholder="password"><br/>
    <input name="submit" type="submit" value="提交">
    <h1>${sessionScope.loginErrorMessage}</h1>
</form>
</body>
</html>
