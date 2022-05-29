<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 10.05.2022
  Time: 21:44
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
    <title>Зміна паролю</title>
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
                <a href="/index.jsp" class="nav-item nav-link">Головна</a>
                <a href="#" class="nav-item nav-link">Про нас</a>
                <a href="/info.jsp" class="nav-item nav-link">Тарифи</a>
                <a href="/calculate.jsp" class="nav-item nav-link">Розрахувати вартість посилки</a>
            </div>
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
        </div>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center mt-3 mb-3">
        <div class="col-md-auto">
            <h4>Зміна паролю</h4>
        </div>
    </div>
    <form method="post" action="/enterPassword">
        <div class="row justify-content-center">
            <div class="col-3">
                <div class="form-group mb-3">
                    <label class="control-label col-xs-4 mb-1">Новий пароль<text style = "color:red;">*</text></label>
                    <input type="password" name="password" class="form-control" required="required">
                </div>
                <c:if test = "${sessionScope.passwordNameInvalid != null}">
                    <div class="form-group mb-3">
                        <text style="color:red">Пароль обов'язково повинен містити цифру, велику та малу латинську літеру і бути не менше 8 символів</text><br>
                    </div>
                </c:if>
                <c:if test = "${sessionScope.passwordInvalid != null}">
                    <div class="form-group mb-3">
                        <text style="color:red">Паролі повинні співпадати</text><br>
                    </div>
                </c:if>
                <div class="form-group mb-3">
                    <label class="control-label col-xs-4 mb-1">Повторити пароль<text style = "color:red;">*</text></label>
                    <input type="password" name="secondPassword" class="form-control" required="required">
                </div>
            </div>
        </div>
        <div class="row justify-content-center mt-3">
            <div class="col-md-auto">
                <p><input type="submit" class="btn btn-success" value="Замінити пароль"></p>
            </div>
        </div>
    </form>
</div>
</body>
</html>
