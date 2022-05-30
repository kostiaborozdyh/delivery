<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 03.05.2022
  Time: 17:52
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
    <title>Розрахунок Вартості доставки</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <u:width width="width"/>
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
                <a href="/index.jsp" class="nav-item nav-link"><fmt:message key="home"/></a>
                <a href="/aboutUs.jsp" class="nav-item nav-link">Про нас</a>
                <a href="/info.jsp" class="nav-item nav-link">Напрямки доставки</a>
                <a href="/calculate.jsp" class="nav-item nav-link active">Розрахувати вартість посилки</a>
                <a href="/reviews.jsp" class="nav-item nav-link">Відгуки</a>
                <c:if test="${sessionScope.role=='user'}">
                    <a href="/user/createOrder.jsp" class="nav-item nav-link">Оформлення замовлення</a>
                    <a href="/user/order.jsp" class="nav-item nav-link">Мої заявки</a>
                </c:if>
            </div>
            <c:if test="${sessionScope.role==null}">
                <div class="navbar-nav ms-auto">
                    <a href="/login.jsp" class="nav-item nav-link">Вхід</a>
                    <span class="nav-item nav-link">/</span>
                    <a href="/registration.jsp" class="nav-item nav-link">Реєстарція</a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.firstLang}</a>
                        <div class="dropdown-menu">
                            <a href="/changeLanguage?id=4" class="dropdown-item">${sessionScope.secondLang}</a>
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
                        <a href="/user/refill.jsp" class="dropdown-item">Поповнення</a>
                        <a href="/editUser.jsp" class="dropdown-item">Редагування</a>
                        <a href="/lout" class="dropdown-item">Вихід</a>
                    </div>
                </div>
            </div>
            <div class="nav navbar-nav navbar-right">
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.firstLang}</a>
                    <div class="dropdown-menu">
                        <a href="/changeLanguage?id=4" class="dropdown-item">${sessionScope.secondLang}</a>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center mt-3 mb-3">
        <div class="col-md-auto">
            <h4>Розрахування вартості доставки</h4>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-8 ">
            <div class="row">
                <div class="col-6">
                    <form method="post" action="/calculateBag" id="form">
                        <div class="row justify-content-center">
                            <div class="col-md-auto">
                                <div class="rounded bg-light p-4 mb-3">
                                    <div class="input-group-prepend">
                                        <div class="form-group mb-3">
                                            <label class="control-label col-xs-4 mb-1">Місто відправки<text style = "color:red;">*</text></label>
                                            <div class="col-xs-8">
                                                <input type="text" class="form-control" name="cityFrom" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label class="control-label col-xs-4 mb-1">Місто доставки<text style = "color:red;">*</text></label>
                                            <div class="col-xs-8">
                                                <input type="text" class="form-control" name="cityTo" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label class="control-label col-xs-4 mb-1">Вага кг<text style = "color:red;">*</text></label>
                                            <div class="col-xs-8">
                                                <input type="number" name="weight" min="1" class="form-control" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label class="control-label col-xs-4 mb-1">Висота м<text style = "color:red;">*</text></label>
                                            <div class="col-xs-8">
                                                <input type="number" name="height" min="1" class="form-control" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label class="control-label col-xs-4 mb-1">Довжина м<text style = "color:red;">*</text></label>
                                            <div class="col-xs-8">
                                                <input type="number" name="length" min="1" class="form-control" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label class="control-label col-xs-4 mb-1">Широта м<text style = "color:red;">*</text></label>
                                            <div class="col-xs-8">
                                                <input type="number" name="width"  min="1" class="form-control" required="required">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-6">
                    <div class="row justify-content-center">
                        <div class="col-md-auto">
                            <div class="row justify-content-center">
                                <div class="col-md-auto">
                                    <h5>Інформація про доставку</h5>
                                    <p>Доставка з міста: ${sessionScope.calculateTable.cityFrom}<br>
                                        Пункт Призначення: ${sessionScope.calculateTable.cityTo} <br>
                                        Вага вантажу:<c:if test="${sessionScope.calculateTable!=null}">
                                            ${sessionScope.calculateTable.weight} кг
                                        </c:if><br>
                                        Об'єм вантажу: <c:if test="${sessionScope.calculateTable!=null}">
                                        ${sessionScope.calculateTable.volume} м<sup>3</sup>
                                        </c:if><br>
                                        Вартість: ${sessionScope.calculateTable.price}
                                    </p>
                                    <div class="row mt-4">
                                        <div class="col-md-auto">
                                            <p><input type="submit" form="form" value="Розрахувати" class="btn btn-success"></p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<%--
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDZ_4ASyzLdt1d16-mekZg5W4X24P0zIR4&libraries=places&callback=initAutocomplete"
        async defer></script>

<script>
    function initAutocomplete() {
        new google.maps.places.Autocomplete(
            (document.getElementById('autocomplete')),
            {types: ['geocode']}
        );
        new google.maps.places.Autocomplete(
            (document.getElementById('autocompleteSec')),
            {types: ['geocode']}
        );
    }
</script>
--%>