<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 22.05.2022
  Time: 16:30
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
    <title>Реєстрація</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        .dropdown-menu {
            min-width: 30px;
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
            padding: 0 5px 5px 0;
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
        .bl{
            height: 50px;
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
                <a href="/adm/usersTable.jsp" class="nav-item nav-link">Користувачі</a>
                <a href="/adm/createEmployeeAccount.jsp" class="nav-item nav-link active">Зареєструвати працівника</a>
            </div>
        </div>
        <div class="nav navbar-nav navbar-right">
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.user.login}</a>
                <div class="dropdown-menu ">
                    <a href="/editUser.jsp" class="dropdown-item">Редагування</a>
                    <a href="/lout" class="dropdown-item">Вихід</a>
                </div>
            </div>
        </div>
        <div class="nav navbar-nav navbar-right">
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.firstLang}</a>
                <div class="dropdown-menu">
                    <a href="/changeLanguage?id=11" class="dropdown-item">${sessionScope.secondLang}</a>
                </div>
            </div>
        </div>
    </div>
</nav>
<div class="signup-form justify-content-center">
    <form action="<c:url value='/addEmployee'/>" method="post" class="form-horizontal">
        <div class="col-12 col-xs-offset-4">
            <div class="row justify-content-center">
                <div class="col-md-auto">
                    <h2 class="text-center">Реєстрація працівника</h2>
                </div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-2">Логін<text style = "color:red;">*</text></label>
            <div class="col-xs-10">
                <input type="text" class="form-control" name="login" value="${sessionScope.validList.validLoginName}" required="required">
            </div>
            <c:if test = "${sessionScope.validList.invalidLogin != null}">
                <text style = "color:red;">Користувач з логіном ${sessionScope.validList.invalidLogin} уже існує</text>
            </c:if>
            <c:if test = "${sessionScope.validList.invalidLoginName != null}">
                <text style = "color:red;">Логін ${sessionScope.validList.invalidLoginName} повинен складатися з великих або маленьких літер та містити не менеше 4х символів</text>
            </c:if>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-2">Ім'я<text style = "color:red;">*</text></label>
            <div class="col-xs-10">
                <input type="text" class="form-control" name="firstName" value="${sessionScope.validList.validFirstName}" required="required">
            </div>
            <c:if test = "${sessionScope.validList.invalidFirstName != null}">
                <text style = "color:red;">Ім'я - ${sessionScope.validList.invalidFirstName} повинно складатися тільки з букв і бути більше 3х символів</text>
            </c:if>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-2">Прізвище<text style = "color:red;">*</text></label>
            <div class="col-xs-10">
                <input type="text" class="form-control" name="lastName" value="${sessionScope.validList.validLastName}" required="required">
            </div>
            <c:if test = "${sessionScope.validList.invalidLastName != null}">
                <text style = "color:red;">Прізвище - ${sessionScope.validList.invalidLastName} повинно складатися тільки з букв і бути більше 3х символів</text><br>
            </c:if>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-2">Пароль<text style = "color:red;">*</text></label>
            <div class="col-xs-10">
                <input type="password" class="form-control" name="password" required="required">
            </div>
            <c:if test = "${sessionScope.validList.invalidPassword != null}">
                <text style = "color:red;">Паролі повинні співпадати</text>
            </c:if>
            <c:if test = "${sessionScope.validList.invalidPasswordName != null}">
                <text style = "color:red;">Пароль обов'язково повинен містити цифру, велику та малу латинську літеру і бути не менше 8 символів</text>
            </c:if>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-2">Повторити пароль<text style = "color:red;">*</text></label>
            <div class="col-xs-10">
                <input type="password" class="form-control" name="secondPassword" required="required">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-2">Пошта<text style = "color:red;">*</text></label>
            <div class="col-xs-10">
                <input type="email" class="form-control" name="email" value="${sessionScope.validList.validEmailName}" required="required" >
            </div>
            <c:if test = "${sessionScope.validList.invalidEmail != null}">
                <text style = "color:red;">Користувач з email ${sessionScope.validList.invalidEmail} уже існує</text>
            </c:if>
            <c:if test = "${sessionScope.validList.invalidEmailName != null}">
                <text style = "color:red;">Email - ${sessionScope.validList.invalidEmailName} повинен дотримуватися стандартів email</text>
            </c:if>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-2">Телефон</label>
            <div class="col-xs-10">
                <input type="text" class="form-control" name="phoneNumber" value="${sessionScope.validList.validPhoneNumber}">
            </div>
            <c:if test = "${sessionScope.validList.invalidPhoneNumber != null}">
                <text style = "color:red;">телефон - ${sessionScope.validList.invalidPhoneNumber} повинен мати 10 цифр та починатися з 0 або +380</text>
            </c:if>
        </div>
        <div class="d-flex w-100">
            <div class="d-inline-block text-center w-25">
                <input type="radio" name="employee" value="manager" class="align-middle me-2"><label>Менеджер</label>
            </div>
            <div class="d-inline-block text-center w-25">
                <input type="radio" name="employee" value="employee" class="align-middle me-2"><label>Працівник</label>
            </div>
        </div>
        <div class="form-group justify-content-center d-flex">
            <div class="col-12 text-center">
                <button type="submit" class="btn btn-primary btn-lg">Зареєструвати</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
