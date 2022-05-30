<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 10.05.2022
  Time: 19:51
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
    <title>Відновлення паролю</title>
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
                <a href="/info.jsp" class="nav-item nav-link">Тарифи</a>
                <a href="/calculate.jsp" class="nav-item nav-link">Розрахувати вартість посилки</a>
            </div>
            <div class="navbar-nav ms-auto">
                <a href="/login.jsp" class="nav-item nav-link">Вхід</a>
                <span class="nav-item nav-link">/</span>
                <a href="/registration.jsp" class="nav-item nav-link">Реєстарція</a>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.firstLang}</a>
                    <div class="dropdown-menu">
                        <a href="/changeLanguage?id=19" class="dropdown-item">${sessionScope.secondLang}</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center mt-3 mb-3">
        <div class="col-md-auto">
            <h4>Відновлення паролю</h4>
        </div>
    </div>
    <form method="post" action="/restore">
        <div class="row justify-content-center">
            <div class="col-3">
                <div class="form-group mb-3">
                    <label class="control-label col-xs-4 mb-1">Введіть пошту або логін<text style = "color:red;">*</text></label>
                    <input type="text" name="login" class="form-control" required="required">
                    <c:if test = "${sessionScope.invalidM != null}">
                        <text style = "color:red;">Логін чи пошта введено неправильно</text>
                    </c:if>
                    <c:if test = "${sessionScope.invalidLogin != null}">
                        <text style = "color:red;">Користувача з таким логіном не існує</text>
                    </c:if>
                    <c:if test = "${sessionScope.invalidEmail != null}">
                        <text style = "color:red;">Користувача з такою поштою не існує</text>
                    </c:if>
                </div>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-auto">
                <p><input type="submit" class="btn btn-success" value="Відновити"></p>
            </div>
        </div>
    </form>
</div>
</body>
</html>
