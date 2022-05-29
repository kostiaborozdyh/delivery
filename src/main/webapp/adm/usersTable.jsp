<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 22.05.2022
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Користувачі</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style type="text/css">
        .table td,th {
            text-align: center;
            border: 5px solid white;
        }
        .bt1 {
            width: 130px;
        }
        .dropdown-menu {
            min-width: 30px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-2">
    <div class="container-fluid">
        <a href="#" class="navbar-brand">Delivery Holder</a>
        <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav">
                <a href="/adm/usersTable.jsp" class="nav-item nav-link active">Користувачі</a>
                <a href="/adm/createEmployeeAccount.jsp" class="nav-item nav-link">Зареєструвати працівника</a>
            </div>
        </div>
        <div class="nav navbar-nav navbar-right">
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.user.login}</a>
                <div class="dropdown-menu ">
                    <a href="/editUser.jsp" class="dropdown-item">Редагування</a>
                    <a href="/lout" class="dropdown-item">Вихід</a>
                </div>
            </div>
        </div>
        <div class="nav navbar-nav navbar-right">
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Укр</a>
                <div class="dropdown-menu">
                    <a href="#" class="dropdown-item">Англ</a>
                </div>
            </div>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center mt-3 mb-3">
        <div class="col-md-auto">
            <h4>Користувачі</h4>
        </div>
    </div>
    <div class="row">
        <table class="table table-borderless">
            <tr>
                <th class="bg-light">Id</th>
                <th class="bg-light">Логін</th>
                <th class="bg-light">Ім'я</th>
                <th class="bg-light">Прізвище</th>
                <th class="bg-light">Пошта</th>
                <th class="bg-light">Блокування</th>
                <th class="bg-light">Видалення</th>
            </tr>
            <c:forEach var="user" items="${sessionScope.shortUsers}">
                <tr>
                <td class="bg-light">${user.id}</td>
                <td class="bg-light">${user.login}</td>
                <td class="bg-light">${user.firstName}</td>
                <td class="bg-light">${user.lastName}</td>
                <td class="bg-light">${user.email}</td>
                <td class="bg-light">
                    <c:if test="${user.ban=='no'}">
                        <form method="post" action="/blockUser?id=${user.id}">
                            <p><input type="submit" class="btn btn-warning bt1" value="Заблокувати"></p>
                        </form>
                    </c:if>
                    <c:if test="${user.ban=='yes'}">
                        <form method="post" action="/unblockUser?id=${user.id}">
                            <input type="submit" class="btn btn-success bt1" value="Розблокувати">
                        </form>
                    </c:if>
                </td>
                <td class="bg-light">
                    <form method="post" action="/deleteUser?id=${user.id}">
                        <p><input type="submit"  class="btn btn-danger" value="Видалити"></p>
                    </form>
                </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-auto">
            <c:if test="${sessionScope.listNumberUser!=null}">
                <nav>
                    <ul class="pagination">
                        <li class="page-item">
                            <a href="/changePage?id=${sessionScope.pageNumberUser-1}&fun=3" class="page-link">Попередня</a>
                        </li>
                        <c:forEach var="n" items="${sessionScope.listNumberUser}">
                            <li class="page-item <c:if test="${sessionScope.pageNumberUser==n}">active</c:if>">
                                <a href="/changePage?id=${n}&fun=3" class="page-link">${n}</a>
                            </li>
                        </c:forEach>
                        <li class="page-item">
                            <a href="/changePage?id=${sessionScope.pageNumberUser+1}&fun=3" class="page-link">Наступна</a>
                        </li>
                    </ul>
                </nav>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
