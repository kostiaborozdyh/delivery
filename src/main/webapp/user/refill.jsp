<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 08.05.2022
  Time: 21:37
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
    <title><fmt:message key="refill"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style type="text/css">
        .inpt{
            width: 200px;
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
                <a href="/user/createOrder.jsp" class="nav-item nav-link"><fmt:message key="makingOrder"/></a>
                <a href="/user/order.jsp" class="nav-item nav-link active"><fmt:message key="myOrders"/></a>
            </div>
        </div>
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
                    <a href="/changeLanguage?id=24" class="dropdown-item">${sessionScope.secondLang}</a>
                </div>
            </div>
        </div>
    </div>
</nav>
<form method="post" action="/refill">
    <div class="container">
        <div class="row justify-content-center mt-3 mb-3">
            <div class="col-md-auto">
                <h4><fmt:message key="refill"/></h4>
            </div>
        </div>
        <div class="row justify-content-center">
            <p class="text-center"><label><fmt:message key="enterAmount"/></label></p>
            <input type="number" name="value" min="1" class="form-control inpt" required="required" placeholder="0">
        </div>
        <div class="row justify-content-center mt-3">
            <div class="col-md-auto">
                <p><input type="submit" class="btn btn-success" value="<fmt:message key="topUp"/> &raquo;"></p>
            </div>
        </div>
    </div>
</form>
</body>
</html>
