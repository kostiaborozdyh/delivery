<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 02.05.2022
  Time: 19:19
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
    <form action="/loginUser" method="post" class="form-horizontal">
        <div class="col-xs-8 col-xs-offset-4">
            <h2>Авторизація</h2>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">Логін</label>
            <div class="col-xs-8">
                <c:if test = "${sessionScope.changePassword !=null}">
                    <text style="color: #3598dc">Використовуйте новий пароль для входу на сторінку</text><br>
                </c:if>
                <c:if test = "${sessionScope.successful !=null}">
                    <text style="color: #3598dc">Реєстрація пройшла успішно. Використовуйте свої данні для авторизації</text><br>
                </c:if>
                <input type="text" class="form-control" name="login" required="required" placeholder="Логін">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-xs-4">Пароль</label>
            <div class="col-xs-8">
                <input type="password" class="form-control" name="password" required="required" placeholder="Пароль">
                <c:if test = "${sessionScope.invalid =='invalid'}">
                    <text style="color: red">Логін або пароль неправильний</text><br>
                </c:if>
                <c:if test = "${sessionScope.invalid =='block'}">
                    <text style="color: red">Ваш акаунт заблоковано</text><br>
                </c:if>
            </div>
        </div>
        <div class="form-group">
            <div class="col-xs-8 col-xs-offset-4">
                <button type="submit" class="btn btn-primary btn-lg">Вхід</button>
            </div>
        </div>
    </form>
    <div class="text-center">Забули пароль?<a href="/restore/restore.jsp">Відновити пароль</a></div>
    <div class="text-center">Ще не зареєстровані?<a href="/registration.jsp">Реєстрація!</a></div>
</div>
</body>
</html>
