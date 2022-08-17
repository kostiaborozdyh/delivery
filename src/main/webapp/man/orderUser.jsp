<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 09.05.2022
  Time: 19:06
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
    <title><fmt:message key="userRequest"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style type="text/css">
        .btnsl {
            gap: 5%;
        }

        .b1 {
            width: 250px;
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
                <a href="/man/orderList.jsp" class="nav-item nav-link active"><fmt:message key="userRequests"/></a>
            </div>
        </div>
        <div class="nav navbar-nav navbar-right">
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.user.login}</a>
                <div class="dropdown-menu ">
                    <a href="/editUser.jsp" class="dropdown-item"><fmt:message key="editing"/></a>
                    <a href="/lout" class="dropdown-item"><fmt:message key="signOut"/></a>
                </div>
            </div>
        </div>
        <div class="nav navbar-nav navbar-right">
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.firstLang}</a>
                <div class="dropdown-menu">
                    <a href="/changeLanguage?id=17" class="dropdown-item">${sessionScope.secondLang}</a>
                </div>
            </div>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center mt-3 mb-3">
        <div class="col-md-auto">
            <h4><fmt:message key="orderInformation"/></h4>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-4">
            <div class="rounded bg-light p-3">
                <p class="mb-2">Id: ${sessionScope.infoOrder.id}</p>
                <p class="mb-2"><fmt:message key="description"/>: ${sessionScope.infoOrder.description}</p>
                <p class="mb-2"><fmt:message key="cargoWeight"/> ${sessionScope.infoOrder.weight}<fmt:message key="kg"/></p>
                <p class="mb-2"><fmt:message key="cargoVolume"/> ${sessionScope.infoOrder.volume}<fmt:message key="m"/><sup>3</sup></p>
                <p class="mb-2"><fmt:message key="deliveryPrice"/> ${sessionScope.infoOrder.price}</p>
                <p class="mb-2"><fmt:message key="cityDeparture"/> ${sessionScope.infoOrder.cityFrom}</p>
                <p class="mb-2"><fmt:message key="deliveryCity"/> ${sessionScope.infoOrder.cityTo}</p>
                <p class="mb-2"><fmt:message key="deliveryAddress"/> ${sessionScope.infoOrder.address}</p>
                <p class="mb-2"><fmt:message key="orderCreationDate"/> ${sessionScope.infoOrder.dateCreate}</p>
                <p class="mb-2"><fmt:message key="deliveryDate"/> ${sessionScope.infoOrder.dateOfArrival}</p>
                <p class="mb-2"><fmt:message key="paymentStatus"/>: ${sessionScope.infoOrder.paymentStatus}</p>
                <p class="mb-0 pb-0"><fmt:message key="customer"/>: ${sessionScope.infoOrder.userLogin}</p>
            </div>
        </div>
    </div>
    <div class="row justify-content-center mt-3">
        <div class="col-5 btnsl d-flex">
            <c:if test="${sessionScope.infoOrder.paymentStatus == 'На розгляді'}">
                <p><a href="/confirmOrder?id=${sessionScope.infoOrder.id}"
                      class="btn btn-success b1"><fmt:message key="confirmOrder"/></a></p>
            </c:if>
            <c:if test="${sessionScope.infoOrder.paymentStatus != 'На розгляді'}">
                <p><a href="#" class="btn btn-secondary b1"><fmt:message key="confirmOrder"/></a></p>
            </c:if>
            <p><a href="/resetOrder" class="btn btn-warning b1"><fmt:message key="returnToOrders"/></a></p>
        </div>
    </div>
</div>
</body>
</html>
