<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 02.05.2022
  Time: 19:19
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
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
    <title>Авторизація</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <style>
        .dropdown-menu {
            min-width: 15px;
        }
        body {
            color: #999;
            background: #f3f3f3;
            font-family: 'Roboto', sans-serif;
        }

        .form-control {
            border-color: #eee;
            min-height: 41px;
            box-shadow: none !important;
        }

        .form-control:focus {
            border-color: #5cd3b4;
        }

        .form-control, .btn {
            border-radius: 3px;
        }

        .signup-form {
            width: 500px;
            margin: 0 auto;
            padding: 30px 0;
        }

        .signup-form h2 {
            color: #333;
            margin: 0 0 30px 0;
            display: inline-block;
            padding: 0 5px 10px 0;
            border-bottom: 3px solid #5cd3b4;
        }

        .signup-form form {
            color: #999;
            border-radius: 3px;
            margin-bottom: 15px;
            background: #fff;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 30px;
        }

        .signup-form .form-group {
            margin-bottom: 20px;
        }

        .signup-form label {
            font-weight: normal;
            font-size: 13px;
        }

        .signup-form input[type="checkbox"] {
            margin-top: 2px;
        }

        .signup-form .btn {
            font-size: 16px;
            font-weight: bold;
            background: #5cd3b4;
            border: none;
            margin-top: 20px;
            min-width: 140px;
        }

        .signup-form .btn:hover, .signup-form .btn:focus {
            background: #41cba9;
            outline: none !important;
        }

        .signup-form a {
            color: #5cd3b4;
            text-decoration: underline;
        }

        .signup-form a:hover {
            text-decoration: none;
        }

        .signup-form form a {
            color: #5cd3b4;
            text-decoration: none;
        }

        .signup-form form a:hover {
            text-decoration: underline;
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
                <a href="/index.jsp" class="nav-item nav-link ">Головна</a>
                <a href="/aboutUs.jsp" class="nav-item nav-link">Про нас</a>
                <a href="/info.jsp" class="nav-item nav-link">Напрямки доставки</a>
                <a href="/calculate.jsp" class="nav-item nav-link">Розрахувати вартість посилки</a>
                <a href="/reviews.jsp" class="nav-item nav-link">Відгуки</a>
            </div>
            <div class="navbar-nav ms-auto">
                <a href="/login.jsp" class="nav-item nav-link active">Вхід</a>
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
<div class="signup-form justify-content-center">
    <form action="/loginUser" method="post" class="form-horizontal">
        <div class="col-md-auto">
            <div class="row justify-content-center">
                <div class="col-md-auto">
                    <h2 class="text-center">Авторизація</h2>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-2">Логін</label>
            <div class="col-xs-10">
                <c:if test="${sessionScope.changePassword !=null}">
                    <text style="color: #3598dc">Використовуйте новий пароль для входу на сторінку</text>
                    <br>
                </c:if>
                <c:if test="${sessionScope.successful !=null}">
                    <text style="color: #3598dc">Реєстрація пройшла успішно. Використовуйте свої данні для авторизації
                    </text>
                    <br>
                </c:if>
                <input type="text" class="form-control" name="login" required="required" placeholder="Логін">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-2">Пароль</label>
            <div class="col-xs-10">
                <input type="password" class="form-control" name="password" required="required" placeholder="Пароль">
                <c:if test="${sessionScope.invalid =='invalid'}">
                    <text style="color: red">Логін або пароль неправильний</text>
                    <br>
                </c:if>
                <c:if test="${sessionScope.invalid =='block'}">
                    <text style="color: red">Ваш акаунт заблоковано</text>
                    <br>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-8 col-xs-offset-4">
                <p class="text-center"><button type="submit" class="btn btn-primary btn-lg">Вхід</button></p>
            </div>
        </div>
    </form>
    <div class="text-center">Забули пароль?<a href="/restore/restore.jsp">Відновити пароль</a></div>
    <div class="text-center">Ще не зареєстровані?<a href="/registration.jsp">Реєстрація!</a></div>
</div>
</body>
</html>
