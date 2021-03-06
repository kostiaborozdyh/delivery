<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 27.05.2022
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><fmt:message key="home"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style type="text/css">
        html::before,html::after{
            box-sizing: content-box !important;
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
                <a href="/index.jsp" class="nav-item nav-link active"><fmt:message key="home"/></a>
                <a href="/aboutUs.jsp" class="nav-item nav-link"><fmt:message key="aboutUs"/></a>
                <a href="/info.jsp" class="nav-item nav-link"><fmt:message key="deliveryDirections"/></a>
                <a href="/calculate.jsp" class="nav-item nav-link"><fmt:message key="calculateCost"/></a>
                <a href="/reviews.jsp" class="nav-item nav-link"><fmt:message key="reviews"/></a>
                <c:if test="${sessionScope.role=='user'}">
                    <a href="/user/createOrder.jsp" class="nav-item nav-link"><fmt:message key="makingOrder"/></a>
                    <a href="/user/order.jsp" class="nav-item nav-link"><fmt:message key="myOrders"/></a>
                </c:if>
            </div>
            <c:if test="${sessionScope.role==null}">
                <div class="navbar-nav ms-auto">
                    <a href="/login.jsp" class="nav-item nav-link"><fmt:message key="signIn"/></a>
                    <span class="nav-item nav-link">/</span>
                    <a href="/registration.jsp" class="nav-item nav-link"><fmt:message key="registration"/></a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.firstLang}</a>
                        <div class="dropdown-menu">
                            <a href="/changeLanguage?id=1" class="dropdown-item">${sessionScope.secondLang}</a>
                        </div>
                    </div>
                </div>
            </c:if>
        </div>
        <c:if test="${sessionScope.role=='user'}">
            <div class="nav navbar-nav navbar-right">
                <a href="#" class="nav-item nav-link disabled">${sessionScope.money}$</a>
            </div>
            <div class="nav navbar-nav navbar-right">
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.user.login}</a>
                    <div class="dropdown-menu ">
                        <a href="/user/refill.jsp" class="dropdown-item"><fmt:message key="refill"/></a>
                        <a href="/editUser.jsp" class="dropdown-item"><fmt:message key="editing"/></a>
                        <a href="/lout" class="dropdown-item"><fmt:message key="signOut"/></a>
                    </div>
                </div>
            </div>
            <div class="nav navbar-nav navbar-right">
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.firstLang}</a>
                    <div class="dropdown-menu">
                        <a href="/changeLanguage?id=1" class="dropdown-item">${sessionScope.secondLang}</a>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-7">
            <h1><fmt:message key="worldwideDelivery"/></h1>
            <img src="img/company.jpg" class="w-75">
            <p class="lead w-75"><fmt:message key="firstInfo"/></p>
            <p><a href="/user/createOrder.jsp"  class="btn btn-success btn-lg"><fmt:message key="orderDelivery"/></a></p>
        </div>
        <div class="col-5">
            <div class="row">
                <div class="col-12">
                    <h2><fmt:message key="deliveryWays"/></h2>
                    <img src="img/map.jpg" class="w-100 mb-1">
                    <p class="w-100"><fmt:message key="secondInfo"/></p>
                    <p><a href="/info.jsp"  class="btn btn-success"><fmt:message key="revise"/> &raquo;</a></p>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <h2><fmt:message key="deliveryCost"/></h2>
                    <p class="w-100"><fmt:message key="thirdInfo"/></p>
                    <p><a href="/calculate.jsp"  class="btn btn-success"><fmt:message key="calculate"/> &raquo;</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>