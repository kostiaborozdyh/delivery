<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 08.05.2022
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Поповнення рахунку</title>
</head>
<body>
${sessionScope.user.login} ${sessionScope.money}<br>
<a href="/index.jsp">Головна</a><br>
<h1>Введіть суму на яку хочете поповнити рахунок</h1>

<form  method="post" action="/refill">
    <input type="number" name="value" min="1" ><br>
    <input type="submit" value="Поповнити" name="Ok"><br>
</form>
</body>
</html>
