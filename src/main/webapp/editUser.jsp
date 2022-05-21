<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 08.05.2022
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
${sessionScope.user.login} <br>
<c:if test = "${sessionScope.role == 'user'}">
    <a href="/index.jsp">Головна</a><br>
</c:if>
<c:if test = "${sessionScope.role == 'manager'}">
    <a href="/man/orderList.jsp">Головна</a><br>
</c:if>

<h1>Редагування аккаунта</h1>
<form method="post" action="/editUser">
    <label><input type="text" name="firstName" value="${sessionScope.user.firstName}">Ім'я</label><br>
    <c:if test = "${sessionScope.validList.invalidFirstName != null}">
        <text style = "color:red;">Ім'я - ${sessionScope.validList.invalidFirstName} повинно складатися тільки з букв і бути більше 3х символів</text><br>
    </c:if>
    <label><input type="text" name="lastName" value="${sessionScope.user.lastName}">Прізвище</label><br>
    <c:if test = "${sessionScope.validList.invalidLastName != null}">
        <text style = "color:red;">Прізвище - ${sessionScope.validList.invalidLastName} повинно складатися тільки з букв і бути більше 3х символів</text><br>
    </c:if>
    <label><input type="password" name="password" >Пароль</label><br>
    <c:if test = "${sessionScope.validList.invalidPassword != null}">
        <text style = "color:red;">Паролі повинні співпадати</text><br>
    </c:if>
    <c:if test = "${sessionScope.validList.invalidPasswordName != null}">
        <text style = "color:red;">Пароль обов'язково повинен містити цифру, велику та малу латинську літеру і бути не менше 8 символів</text><br>
    </c:if>
    <label><input type="password" name="secondPassword" >Повторити Пароль</label><br>
    <label><input type="text" name="phoneNumber" value="${sessionScope.user.phoneNumber}">Номер телефону</label><br>
    <c:if test = "${sessionScope.validList.invalidPhoneNumber != null}">
        <text style = "color:red;">телефон - ${sessionScope.validList.invalidPhoneNumber} повинен мати 10 цифр та починатися з 0 або +380</text><br>
    </c:if>
    <label><input type="text" name="email" value="${sessionScope.user.email}">Пошта</label><br>
    <c:if test = "${sessionScope.validList.invalidEmail != null}">
        <text style = "color:red;">Користувач з email ${sessionScope.validList.invalidEmail} уже існує</text><br>
    </c:if>
    <c:if test = "${sessionScope.validList.invalidEmailName != null}">
        <text style = "color:red;">Email - ${sessionScope.validList.invalidEmailName} повинен дотримуватися стандартів email</text><br>
    </c:if>
    <c:if test = "${sessionScope.role == 'user'}">
        <input type="checkbox" name="notify" value="notifyEmail" <c:if test="${sessionScope.user.notify =='yes'}"> checked </c:if> >Отримувати повідомлення на пошту<br>
    </c:if>
    <input type="submit" value="Редагувати">
</form>

</body>
</html>
