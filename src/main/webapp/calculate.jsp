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
    <title><fmt:message key="calculateCost"/></title>
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
                <a href="/aboutUs.jsp" class="nav-item nav-link"><fmt:message key="aboutUs"/></a>
                <a href="/info.jsp" class="nav-item nav-link"><fmt:message key="deliveryDirections"/></a>
                <a href="/calculate.jsp" class="nav-item nav-link active"><fmt:message key="calculateCost"/></a>
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
            <h4><fmt:message key="calculateCost"/></h4>
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
                                            <label class="control-label col-xs-4 mb-1"><fmt:message key="cityOfDispatch"/><text style = "color:red;">*</text></label>
                                            <div class="col-xs-8">
                                                <input type="text" class="form-control" name="cityFrom" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label class="control-label col-xs-4 mb-1"><fmt:message key="cityOfDelivery"/><text style = "color:red;">*</text></label>
                                            <div class="col-xs-8">
                                                <input type="text" class="form-control" name="cityTo" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label class="control-label col-xs-4 mb-1"><fmt:message key="parcelWeight"/><text style = "color:red;">*</text></label>
                                            <div class="col-xs-8">
                                                <input type="number" name="weight" min="1" class="form-control" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label class="control-label col-xs-4 mb-1"><fmt:message key="parcelHeight"/><text style = "color:red;">*</text></label>
                                            <div class="col-xs-8">
                                                <input type="number" name="height" min="1" class="form-control" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label class="control-label col-xs-4 mb-1"><fmt:message key="parcelLength"/><text style = "color:red;">*</text></label>
                                            <div class="col-xs-8">
                                                <input type="number" name="length" min="1" class="form-control" required="required">
                                            </div>
                                        </div>
                                        <div class="form-group mb-3">
                                            <label class="control-label col-xs-4 mb-1"><fmt:message key="parcelWidth"/><text style = "color:red;">*</text></label>
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
                                    <h5><fmt:message key="deliveryInformation"/></h5>
                                    <p><fmt:message key="departurePoint"/> ${sessionScope.calculateTable.cityFrom}<br>
                                        <fmt:message key="destination"/> ${sessionScope.calculateTable.cityTo} <br>
                                        <fmt:message key="cargoWeight"/><c:if test="${sessionScope.calculateTable!=null}">
                                            ${sessionScope.calculateTable.weight} <fmt:message key="kg"/>
                                        </c:if><br>
                                        <fmt:message key="cargoVolume"/> <c:if test="${sessionScope.calculateTable!=null}">
                                        ${sessionScope.calculateTable.volume} <fmt:message key="m"/><sup>3</sup>
                                        </c:if><br>
                                        <fmt:message key="deliveryPrice"/><c:if test="${sessionScope.calculateTable!=null}">
                                                    ${sessionScope.calculateTable.price}$
                                                </c:if>
                                    </p>
                                    <div class="row mt-4">
                                        <div class="col-md-auto">
                                            <p><input type="submit" form="form" value="<fmt:message key="calculate"/>" class="btn btn-success"></p>
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