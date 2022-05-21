<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 10.05.2022
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Відновлення паролю</title>
</head>
<body>
<form method="post" action="/restore">

    <label><input type="text" name="login"></label>login or email<br>
    <c:if test = "${sessionScope.invalidM != null}">
        <text style = "color:red;">Логін чи пошта введено неправильно</text><br>
    </c:if>
    <c:if test = "${sessionScope.invalidLogin != null}">
        <text style = "color:red;">Користувача з таким логіном не існує</text><br>
    </c:if>
    <c:if test = "${sessionScope.invalidEmail != null}">
        <text style = "color:red;">Користувача з такою поштою не існує</text><br>
    </c:if>
    <input type="submit" value="Відновити" name="Ok"><br>
</form><br>
</body>
</html>
