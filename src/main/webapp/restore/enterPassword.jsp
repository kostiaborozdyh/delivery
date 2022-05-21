<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 10.05.2022
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Заміна паролю</title>
</head>
<body>
<form method="post" action="/enterPassword">
    <label><input type="password" name="password"></label>password<text color="red">*</text><br>
    <c:if test = "${sessionScope.passwordInvalid != null}">
        <text color="red">Паролі повинні співпадати</text><br>
    </c:if>
    <c:if test = "${sessionScope.passwordNameInvalid != null}">
        <text color="red">Пароль обов'язково повинен містити цифру, велику та малу латинську літеру і бути не менше 8 символів</text><br>
    </c:if>
    <label><input type="password" name="secondPassword"></label>Repeat password<text color="red">*</text><br>
    <input type="submit" value="Замінити пароль" name="Ok"><br>
</form>
</body>
</html>
