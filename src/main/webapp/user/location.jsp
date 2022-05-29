<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 24.05.2022
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>
<html>
<head>
    <title>Місцерозташування посилки</title>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        #map {
            height:500px;
            width: 1000px;
        }
        .dropdown-menu {
            min-width: 30px;
        }
    </style>
    <script
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDZ_4ASyzLdt1d16-mekZg5W4X24P0zIR4&callback=initMap&v=weekly&language=uk"
            defer
    ></script>
    <script>
        function initMap() {
            const myLatLng = { lat: ${sessionScope.latitude}, lng: ${sessionScope.longitude} };
            const map = new google.maps.Map(document.getElementById("map"), {
                zoom: 14,
                center: myLatLng,
            });

            new   google.maps.Marker({
                position: myLatLng,
                map,
                title: "Hello",
            });

        }

        window.initMap = initMap;
        // [END maps_marker_simple]
    </script>
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
                <a href="/index.jsp" class="nav-item nav-link active">Головна</a>
                <a href="/aboutUs.jsp" class="nav-item nav-link">Про нас</a>
                <a href="/info" class="nav-item nav-link">Тарифи</a>
                <a href="/calculateBag" class="nav-item nav-link">Розрахувати вартість посилки</a>
                <a href="/reviews.jsp" class="nav-item nav-link">Відгуки</a>
                <a href="/user/createOrder.jsp" class="nav-item nav-link">Оформлення замовлення</a>
                <a href="/user/order.jsp" class="nav-item nav-link">Мої заявки</a>
            </div>
        </div>
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
            <h4>Місце розташування посилки</h4>
        </div>
        <div class="row justify-content-center mt-2 mb-3">
            <div class="col-md-auto">
            </div>
            <div id="map"></div>
        </div>
        <div class="row justify-content-center mb-3">
            <div class="col-md-auto">
                <p><a href="/resetOrder" class="btn btn-primary">Повернутися до заявок</a></p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
