<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 22.05.2022
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Таблиця юзерів</title>
</head>
<body>
${sessionScope.login}<br>
<a href="logout.jsp">Вийти</a>
<a href="/adm/createEmployeeAccount.jsp">Добавити працівника</a>
<a href="/editUser.jsp">Редагувати профіль</a><br>
<table border="1">
    <caption>Таблиця юзерів</caption>
    <tr>
        <th>Id</th>
        <th>Логін</th>
        <th>Ім'я</th>
        <th>Прізвище</th>
        <th>Пошта</th>
        <th>Блокування</th>
        <th>Видалити</th>
    </tr>

<c:forEach var="user" items="${sessionScope.userList}">
    <tr>
        <td>${user.id}</td>
        <td>${user.login}</td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.email}</td>
        <td>
            <c:if test="${user.ban=='no'}">
                <form method="post" action="/blockUser?id=${user.id}">
                    <input type="text" name="reason">
                    <input type="submit" value="Заблокувати">
                </form>
            </c:if>
            <c:if test="${user.ban=='yes'}">
                <form method="post" action="/unblockUser?id=${user.id}">
                    <input type="submit" value="Розблокувати">
                </form>
            </c:if>
        </td>
        <td><form method="post" action="/deleteUser?id=${user.id}">
            <input type="submit" value="Видалити">
        </form></td>
    </tr>

</c:forEach>
</table>
</body>
</html>
