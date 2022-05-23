<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 22.05.2022
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Реєстрація співробітника</title>
    <h2>Реєстрація співробітника</h2><br/>
</head>
<body>
<a href="/adm/usersTable.jsp">Голловна</a><br>
<form method="post" action="<c:url value='/addEmployee'/>">

    <label><input type="text" name="login" value="${sessionScope.validList.validLoginName}"></label>login<text style = "color:red;">*</text><br>
    <c:if test = "${sessionScope.validList.invalidLogin != null}">
        <text style = "color:red;">Користувач з логіном ${sessionScope.validList.invalidLogin} уже існує</text><br>
    </c:if>
    <c:if test = "${sessionScope.validList.invalidLoginName != null}">
        <text style = "color:red;">Логін ${sessionScope.validList.invalidLoginName} повинен складатися з великих або маленьких літер та містити не менеше 4х символів</text><br>
    </c:if>
    <label><input type="password" name="password"></label>password<text style = "color:red;">*</text><br>
    <c:if test = "${sessionScope.validList.invalidPassword != null}">
        <text style = "color:red;">Паролі повинні співпадати</text><br>
    </c:if>
    <c:if test = "${sessionScope.validList.invalidPasswordName != null}">
        <text style = "color:red;">Пароль обов'язково повинен містити цифру, велику та малу латинську літеру і бути не менше 8 символів</text><br>
    </c:if>
    <label><input type="password" name="secondPassword"></label>Repeat password<text style = "color:red;">*</text><br>
    <label><input type="text" name="firstName" value="${sessionScope.validList.validFirstName}"></label>firstName<text style = "color:red;">*</text><br>
    <c:if test = "${sessionScope.validList.invalidFirstName != null}">
        <text style = "color:red;">Ім'я - ${sessionScope.validList.invalidFirstName} повинно складатися тільки з букв і бути більше 3х символів</text><br>
    </c:if>
    <label><input type="text" name="lastName" value="${sessionScope.validList.validLastName}"></label>lastName<text style = "color:red;">*</text><br>
    <c:if test = "${sessionScope.validList.invalidLastName != null}">
        <text style = "color:red;">Прізвище - ${sessionScope.validList.invalidLastName} повинно складатися тільки з букв і бути більше 3х символів</text><br>
    </c:if>
    <label><input type="email" name="email" value="${sessionScope.validList.validEmailName}"></label>email<text style = "color:red;">*</text><br>
    <c:if test = "${sessionScope.validList.invalidEmail != null}">
        <text style = "color:red;">Користувач з email ${sessionScope.validList.invalidEmail} уже існує</text><br>
    </c:if>
    <c:if test = "${sessionScope.validList.invalidEmailName != null}">
        <text style = "color:red;">Email - ${sessionScope.validList.invalidEmailName} повинен дотримуватися стандартів email</text><br>
    </c:if>
    <label><input type="text" name="phoneNumber" value="${sessionScope.validList.validPhoneNumber}"></label>phoneNumber<br>
    <c:if test = "${sessionScope.validList.invalidPhoneNumber != null}">
        <text style = "color:red;">телефон - ${sessionScope.validList.invalidPhoneNumber} повинен мати 10 цифр та починатися з 0 або +380</text><br>
    </c:if>
    <label><input type="radio" name="employee" value="manager" checked>Менеджер</label>
    <label><input type="radio" name="employee" value="employee">Працівник</label>
    <input type="submit" value="Зареєструвати" name="Ok"><br>
</form>
</body>
</html>
