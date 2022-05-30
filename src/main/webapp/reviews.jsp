<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 25.05.2022
  Time: 10:41
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
    <title>Відгуки</title>
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
                <a href="/index.jsp" class="nav-item nav-link "><fmt:message key="home"/></a>
                <a href="/aboutUs.jsp" class="nav-item nav-link">Про нас</a>
                <a href="/info.jsp" class="nav-item nav-link">Тарифи</a>
                <a href="/calculate.jsp" class="nav-item nav-link">Розрахувати вартість посилки</a>
                <a href="/reviews.jsp" class="nav-item nav-link active">Відкуги</a>
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
                            <a href="/changeLanguage?id=10" class="dropdown-item">${sessionScope.secondLang}</a>
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
                        <a href="/changeLanguage?id=10" class="dropdown-item">${sessionScope.secondLang}</a>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center mt-3 mb-3">
        <div class="col-md-auto">
            <h4>Відгуки</h4>
        </div>
    </div>
    <c:if test="${sessionScope.role=='user'}">
    <form method="post" action="/addReview">
        <div class="row justify-content-center">
                <div class="col-6">
                    <p>Залишіть ваш відгук</p>
                    <textarea name="response" required="required" class="form-control mb-3" placeholder="Ваш відгук"></textarea>
                </div>
                <p class="text-center"><input type="submit" value="Відправити &raquo;" class="btn btn-success"></p>
        </div>
    </form>
    </c:if>
    <c:forEach var="review" items="${sessionScope.reviews}">
        <div class="row justify-content-center">
            <div class="col-md-auto rounded bg-light p-3 mb-3">
                <h5>${review.firstName} <small class="text-muted"><i>Добавлено ${review.date}</i></small></h5>
                <p>${review.response}</p>
            </div>
        </div>
    </c:forEach>
</div>
</div>
</body>
</html>
