<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 27.05.2022
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Fluid Layout</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style type="text/css">
        html::before,html::after{
            box-sizing: content-box !important;
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
                <a href="/main.jsp" class="nav-item nav-link active">Головна</a>
                <a href="#" class="nav-item nav-link">Про нас</a>
                <a href="/index.jsp" class="nav-item nav-link">Напрямки доставки</a>
                <a href="/calculate.jsp" class="nav-item nav-link">Розрахувати вартість посилки</a>
            </div>
            <div class="navbar-nav ms-auto">
                <a href="/login.jsp" class="nav-item nav-link">Вхід</a>
                <a href="/registration.jsp" class="nav-item nav-link">Реєстарція</a>
            </div>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="col-7">
            <h1>Доставка по Всьому світу</h1>
            <img src="company.jpg"  class="w-75">
            <p class="lead w-75">Наша компанія займається супер пупер доставками по всьому світі, того ми супер пупер круто працюємо і доставляємо всі посилки</p>
            <p><a href="#" target="_blank" class="btn btn-success btn-lg">Замовити доставку</a></p>
        </div>
        <div class="col-5">
            <div class="row">
                <div class="col-12">
                    <h2>Шляхи</h2>
                    <img src="map.jpg"  class="w-100 mb-1">
                    <p class="w-100">Ви можете перглянути куди і звідки доставляються посилки і перглянути шляхи доставки на карті</p>
                    <p><a href="#" target="_blank" class="btn btn-success">Переглянути &raquo;</a></p>
                </div>
            </div>
            <div class="row">
                <div class="col-12">
                    <h2>Вартість</h2>
                    <p class="w-100">Ви можете розрахувати вартість доставки, вказуючи розміри посилки, вагу та міста відправки і доствки</p>
                    <p><a href="#" target="_blank" class="btn btn-success">Розрахувати &raquo;</a></p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>