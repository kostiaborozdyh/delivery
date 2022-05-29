<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 29.05.2022
  Time: 22:59
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
    <title>Про нас</title>
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
                <a href="/index.jsp" class="nav-item nav-link ">Головна</a>
                <a href="/aboutUs.jsp" class="nav-item nav-link active">Про нас</a>
                <a href="/info.jsp" class="nav-item nav-link">Тарифи</a>
                <a href="/calculate.jsp" class="nav-item nav-link">Розрахувати вартість посилки</a>
                <a href="/reviews.jsp" class="nav-item nav-link">Відкуги</a>
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
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Укр</a>
                        <div class="dropdown-menu">
                            <a href="#" class="dropdown-item">Англ</a>
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
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Укр</a>
                    <div class="dropdown-menu">
                        <a href="#" class="dropdown-item">Англ</a>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center mt-3 mb-3">
        <div class="col-md-auto">
            <h4>Про нас</h4>
        </div>
    </div>
    <div class="row justify-content-center mt-3 mb-3">
        <div class="rounded bg-light p-3 col-6">
            <p>Delivery Holder – поштово-вантажна компанія, що забезпечує легку доставку кожному клієнту - до відділення,за адресою. Компанія надає логістичні та дистрибуційні послуги, доставляючи як найдрібніші посилки, так і великі вантажі. Delivery Holder дозволяє тисячам підприємців створювати і розвивати бізнес не тільки в Україні, а й за кордоном. Мережа компанії налічує більше 15000 відділень по всій Україні та понад 15000 відділень за межами України, а кількість відправлень за 2021 рік перевищила 600 млн посилок та вантажів</p>

            <p><b>Контакти:</b> manager.delivery.holder@gmail.com</p>
        </div>
    </div>
</div>
</div>
</body>
</html>
