<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 10.05.2022
  Time: 20:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Відновлення паролю</title>
</head>
<body>
<form method="post" action="/restorePassword">
    <label><input type="number" name="code"></label>Введіть код який прийшов на почту<br>
    <c:if test = "${sessionScope.invalidCode != null}">
        <text style = "color:red;">Неправильний код</text><br>
    </c:if>
    <input type="submit" value="Відновити" name="Ok"><br>
</form><br>
</body>
</html>
