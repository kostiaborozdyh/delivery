<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 23.05.2022
  Time: 10:34
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
    <title>Інформація про посилку</title>
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
                <a href="/index.jsp" class="nav-item nav-link active"><fmt:message key="home"/></a>
                <a href="/aboutUs.jsp" class="nav-item nav-link"><fmt:message key="aboutUs"/></a>
                <a href="/info.jsp" class="nav-item nav-link"><fmt:message key="deliveryDirections"/></a>
                <a href="/calculate.jsp" class="nav-item nav-link"><fmt:message key="calculateCost"/></a>
                <a href="/reviews.jsp" class="nav-item nav-link"><fmt:message key="reviews"/></a>
                <a href="/user/createOrder.jsp" class="nav-item nav-link "><fmt:message key="makingOrder"/></a>
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
                    <a href="/changeLanguage?id=25" class="dropdown-item">${sessionScope.secondLang}</a>
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
                <p class="mb-2"><fmt:message key="cargoWeight"/> ${sessionScope.infoOrder.weight}кг</p>
                <p class="mb-2"><fmt:message key="cargoVolume"/> ${sessionScope.infoOrder.volume}м<sup>3</sup></p>
                <p class="mb-2"><fmt:message key="deliveryPrice"/> ${sessionScope.infoOrder.price}</p>
                <p class="mb-2"><fmt:message key="cityDeparture"/> ${sessionScope.infoOrder.cityFrom}</p>
                <p class="mb-2"><fmt:message key="deliveryCity"/> ${sessionScope.infoOrder.cityTo}</p>
                <p class="mb-2"><fmt:message key="deliveryAddress"/> ${sessionScope.infoOrder.address}</p>
                <p class="mb-2"><fmt:message key="orderCreationDate"/> ${sessionScope.infoOrder.dateCreate}</p>
                <c:if test="${sessionScope.infoOrder.dateOfSending!=null}">
                    <p class="mb-2"><fmt:message key="departureDate"/> ${sessionScope.infoOrder.dateOfSending}</p>
                </c:if>
                <p class="mb-2"><fmt:message key="deliveryDate"/> ${sessionScope.infoOrder.dateOfArrival}</p>
                <p class="mb-2"><fmt:message key="paymentStatus"/>: ${sessionScope.infoOrder.paymentStatus}</p>
                <p class="mb-2"><fmt:message key="locationParcel"/>: ${sessionScope.infoOrder.locationStatus}</p>
                </p>
            </div>
        </div>
    </div>
    <div class="row justify-content-center mt-3">
        <div class="col-3 btnsl">
            <c:if test = "${(sessionScope.money<sessionScope.infoOrder.price) && ((sessionScope.infoOrder.paymentStatus == 'Очікує оплату') ||(sessionScope.infoOrder.paymentStatus == 'На розгляді')) }">
                <abbr title="Недостатньо грошей, потрібно поповнити рахунок" >
                    <p><a href="*" class="btn btn-danger b1" onclick="return false"><fmt:message key="pay"/></a></p>
                </abbr>
            </c:if>
            <c:if test = "${(sessionScope.money>=sessionScope.infoOrder.price) && (sessionScope.infoOrder.paymentStatus == 'На розгляді')}">
                <abbr title="Ваша заявка на розгляді" >
                    <p><a href="*" class="btn btn-danger b1" onclick="return false"><fmt:message key="pay"/></a></p>
                </abbr>
            </c:if>

            <c:if test = "${(sessionScope.money>=sessionScope.infoOrder.price) && (sessionScope.infoOrder.paymentStatus == 'Очікує оплату') }">
            <form method="post" action="/pay?id=${sessionScope.infoOrder.id}&value=${sessionScope.infoOrder.price}&money=${sessionScope.money}">
                    <p><input type="submit" class="btn btn-success b1" value="<fmt:message key="pay"/>"></p>
            </form>
            </c:if>
            <c:if test = "${sessionScope.infoOrder.paymentStatus == 'Оплачено' }">
                <abbr title="Заявка оплачена" >
                    <p><a href="#" class="btn btn-secondary b1"><fmt:message key="pay"/></a></p>
                </abbr>
            </c:if>
            <p><a href="/info?id=${sessionScope.infoOrder.id}" class="btn btn-warning b1"><fmt:message key="update"/> &raquo;</a></p>
        </div>
        <div class="col-3 btnsl">
            <p><a href="/showLocation?id=${sessionScope.infoOrder.id}" class="btn btn-info b1"
                            <c:if test = "${sessionScope.infoOrder.locationStatus == 'В місті відправлення' || sessionScope.infoOrder.locationStatus == 'Отримано'}">
                             onclick="return false"
                            </c:if>>
                    <fmt:message key="locationParcel"/></a></p>
            <p><a href="/resetOrder" class="btn btn-primary b1"><fmt:message key="returnToOrders"/></a></p>
        </div>
    </div>
</div>
</body>
</html>
