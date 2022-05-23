<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 02.05.2022
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <h2>Авторизація</h2><br />


</head>
<body>
<c:if test = "${sessionScope.changePassword !=null}">
    <text>Використовуйте новий пароль для входу на сторінку</text><br>
</c:if>
<form method="post" action="/loginUser">

    <label><input type="text" name="login"></label>login<br>

    <label><input type="password" name="password"></label>Password<br>

    <input type="submit" value="Вхід" name="Ok"><br>
</form><br>
<c:if test = "${sessionScope.invalid =='invalid'}">
    <text>Логін або пароль неправильний</text><br>
    <a href="restore/restore.jsp">Відновити пароль</a>
</c:if>
<c:if test = "${sessionScope.invalid =='block'}">
    <text>Ваш акаунт заблоковано</text><br>
</c:if>
<a href="registration.jsp">Реєстрація</a>
</body>
</html>
