<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 29.05.2022
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Error500</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <u:width width="width"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-3">
    <div class="container-fluid">
        <a href="#" class="navbar-brand">Delivery Holder</a>
        <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav">
                <c:if test="${sessionScope.role=='user' || sessionScope.role==null}">
                    <a href="/index.jsp" class="nav-item nav-link active">Головна</a>
                </c:if>
                <c:if test="${sessionScope.role=='manager'}">
                    <a href="/man/orderList.jsp" class="nav-item nav-link active">Головна</a>
                </c:if>
                <c:if test="${sessionScope.role=='admin'}">
                    <a href="/adm/usersTable.jsp" class="nav-item nav-link active">Головна</a>
                </c:if>
                <c:if test="${sessionScope.role=='employee'}">
                    <a href="/employee/ordersTable.jsp" class="nav-item nav-link active">Головна</a>
                </c:if>
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
    <div class="row justify-content-center mb-3">
        <img src="/img/e500.jpg" class="w-75 ps-5">
    </div>
    <div class="row justify-content-center">
        <div class="col-md-auto">
            <p>Сталася помилка на сервері, зв'яжіться з нами((((((((</p>
        </div>
    </div>
</div>
</body>
</html>
