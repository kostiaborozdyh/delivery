<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 02.05.2022
  Time: 21:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Roboto:400,700" rel="stylesheet">
    <title>Реєстрація</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
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
            padding: 0 30px 10px 0;
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
<div class="signup-form">
    <form action="<c:url value='/registrationUser'/>" method="post" class="form-horizontal">
        <div class="col-xs-8 col-xs-offset-4">
            <h2>Реєстрація</h2>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">Логін<text style = "color:red;">*</text></label>
            <div class="col-xs-8">
                <input type="text" class="form-control" name="login" required="required" value="${sessionScope.validList.validLoginName}">
                <c:if test = "${sessionScope.validList.invalidLogin != null}">
                    <text style = "color:red;">Користувач з логіном ${sessionScope.validList.invalidLogin} уже існує</text><br>
                </c:if>
                <c:if test = "${sessionScope.validList.invalidLoginName != null}">
                    <text style = "color:red;">Логін ${sessionScope.validList.invalidLoginName} повинен складатися з великих або маленьких літер та містити не менеше 4х символів</text><br>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">Ім'я<text style = "color:red;">*</text></label>
            <div class="col-xs-8">
                <input type="text" class="form-control" name="firstName" required="required" value="${sessionScope.validList.validFirstName}">
                <c:if test = "${sessionScope.validList.invalidFirstName != null}">
                    <text style = "color:red;">Ім'я - ${sessionScope.validList.invalidFirstName} повинно складатися тільки з букв і бути більше 3х символів</text><br>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">Прізвище<text style = "color:red;">*</text></label>
            <div class="col-xs-8">
                <input type="text" class="form-control" name="lastName" required="required" value="${sessionScope.validList.validLastName}">
                <c:if test = "${sessionScope.validList.invalidLastName != null}">
                    <text style = "color:red;">Прізвище - ${sessionScope.validList.invalidLastName} повинно складатися тільки з букв і бути більше 3х символів</text><br>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">Пароль<text style = "color:red;">*</text></label>
            <div class="col-xs-8">
                <input type="password" class="form-control" name="password" required="required">
                <c:if test = "${sessionScope.validList.invalidPassword != null}">
                    <text style = "color:red;">Паролі повинні співпадати</text><br>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">Повторити пароль<text style = "color:red;">*</text></label>
            <div class="col-xs-8">
                <input type="password" class="form-control" name="secondPassword" required="required">
                <c:if test = "${sessionScope.validList.invalidPasswordName != null}">
                    <text style = "color:red;">Пароль обов'язково повинен містити цифру, велику та малу латинську літеру і бути не менше 8 символів</text><br>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">Пошта<text style = "color:red;">*</text></label>
            <div class="col-xs-8">
                <input type="email" class="form-control" name="email" required="required" value="${sessionScope.validList.validEmailName}">
                <c:if test = "${sessionScope.validList.invalidEmail != null}">
                    <text style = "color:red;">Користувач з email ${sessionScope.validList.invalidEmail} уже існує</text><br>
                </c:if>
                <c:if test = "${sessionScope.validList.invalidEmailName != null}">
                    <text style = "color:red;">Email - ${sessionScope.validList.invalidEmailName} повинен дотримуватися стандартів email</text><br>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">Телефон</label>
            <div class="col-xs-8">
                <input type="text" class="form-control" name="phoneNumber"  value="${sessionScope.validList.validPhoneNumber}">
                <c:if test = "${sessionScope.validList.invalidPhoneNumber != null}">
                    <text style = "color:red;">телефон - ${sessionScope.validList.invalidPhoneNumber} повинен мати 10 цифр та починатися з 0 або +380</text><br>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-8 col-xs-offset-4">
                <p><label class="checkbox-inline"><input type="checkbox" name="notify" value="notifyEmail">Підписатися на розсилку</label></p>
                <button type="submit" class="btn btn-primary btn-lg">Зареєструватися</button>
            </div>
        </div>
    </form>
    <div class="text-center">Уже зареєстровані?<a href="/login.jsp">Авторизуйтессь!</a></div>
</div>
</body>
</html>
