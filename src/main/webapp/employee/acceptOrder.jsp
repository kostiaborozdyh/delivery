<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 23.05.2022
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/custom.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Склад</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style type="text/css">
        .table td,th {
            text-align: center;
            border: 5px solid white;
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
                <a href="/employee/ordersTable.jsp" class="nav-item nav-link">Cклад</a>
                <a href="/employee/acceptOrder.jsp" class="nav-item nav-link active">Прийняти посилки</a>
            </div>
        </div>
        <div class="nav navbar-nav navbar-right">
            <a href="#" class="nav-item nav-link disabled"><m:today/></a>
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
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.firstLang}</a>
                <div class="dropdown-menu">
                    <a href="/changeLanguage?id=13" class="dropdown-item">${sessionScope.secondLang}</a>
                </div>
            </div>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center mt-3 mb-3">
        <div class="col-md-auto">
            <h4>Прийняття посилок</h4>
        </div>
    </div>
    <c:if test="${sessionScope.orderList!=null}">
        <div class="row">
            <table class="table table-borderless">
                <tr>
                    <th class="bg-light">Номер товару</th>
                    <th class="bg-light">Опис</th>
                    <th class="bg-light">Місто відправлення</th>
                    <th class="bg-light">Місто отримання</th>
                    <th class="bg-light">Статус оплати</th>
                    <th class="bg-light">Облік</th>
                </tr>
                <c:forEach var="order" items="${sessionScope.orderList}">
                    <tr>
                        <td class="bg-light">${order.id}</td>
                        <td class="bg-light">${order.description}</td>
                        <td class="bg-light">${order.cityFrom}</td>
                        <td class="bg-light">${order.cityTo}</td>
                        <td class="bg-light">${order.paymentStatus}</td>
                        <td class="bg-light">
                            <form method="post" action="/putOnRecord?id=${order.id}">
                                <input type="submit" class="btn btn-outline-primary" value="Поставити на облік">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</div>
</body>
</html>
