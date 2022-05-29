<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 08.05.2022
  Time: 23:46
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
    <title>Редагування профілю</title>
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
                <c:if test="${sessionScope.role=='user'}">
                    <a href="/index.jsp" class="nav-item nav-link">Головна</a>
                    <a href="#" class="nav-item nav-link">Про нас</a>
                    <a href="/info.jsp" class="nav-item nav-link">Тарифи</a>
                    <a href="/calculate.jsp" class="nav-item nav-link">Розрахувати вартість посилки</a>
                    <a href="/reviews.jsp" class="nav-item nav-link">Відгуки</a>
                    <a href="/user/createOrder.jsp" class="nav-item nav-link">Оформлення замовлення</a>
                    <a href="/user/order.jsp" class="nav-item nav-link">Мої заявки</a>
                </c:if>
                <c:if test="${sessionScope.role=='manager'}">
                    <a href="/man/orderList.jsp" class="nav-item nav-link">Заявки користувачів</a>
                </c:if>
                <c:if test="${sessionScope.role=='admin'}">
                    <a href="/adm/usersTable.jsp" class="nav-item nav-link">Таблиця користувачів</a>
                    <a href="/adm/createEmployeeAccount.jsp" class="nav-item nav-link">Оформлення нового працівника</a>
                </c:if>
                <c:if test="${sessionScope.role=='employee'}">
                    <a href="/employee/ordersTable.jsp" class="nav-item nav-link">Склад товару</a>
                    <a href="/employee/acceptOrder.jsp" class="nav-item nav-link">Прийняття товару на облік</a>
                </c:if>
            </div>
        </div>
        <c:if test="${sessionScope.role=='user'}">
            <div class="nav navbar-nav navbar-right">
                <a href="#" class="nav-item nav-link disabled">${sessionScope.money}$</a>
            </div>
        </c:if>
        <div class="nav navbar-nav navbar-right">
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.user.login}</a>
                <div class="dropdown-menu ">
                    <c:if test="${sessionScope.role=='user'}">
                        <a href="/user/refill.jsp" class="dropdown-item">Поповнення</a>
                    </c:if>
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
            <h4>Редагування профілю</h4>
        </div>
    </div>
    <div class="row justify-content-center">
        <form method="post" action="/editUser">
            <div class="row justify-content-center">
                <div class="col-md-auto">
                    <div class="rounded bg-light p-4 mb-3">
                        <div class="input-group-prepend">
                            <div class="form-group mb-2">
                                <label class="control-label col-xs-4 mb-2">Ім'я</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" name="firstName" value="${sessionScope.user.firstName}">
                                </div>
                                <c:if test = "${sessionScope.validList.invalidFirstName != null}">
                                    <div class="col-xs-8">
                                        <text style = "color:red;">Ім'я - ${sessionScope.validList.invalidFirstName} повинно складатися тільки з букв і бути більше 3х символів</text>
                                    </div>
                                </c:if>
                            </div>
                            <div class="form-group mb-2">
                                <label class="control-label col-xs-4 mb-2">Прізвище</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" name="lastName" value="${sessionScope.user.lastName}">
                                </div>
                                <c:if test = "${sessionScope.validList.invalidLastName != null}">
                                    <div class="col-xs-8">
                                        <text style = "color:red;">Прізвище - ${sessionScope.validList.invalidLastName} повинно складатися тільки з букв і бути більше 3х символів</text>
                                    </div>
                                </c:if>
                            </div>
                            <div class="form-group mb-2">
                                <label class="control-label col-xs-4 mb-2">Пароль</label>
                                <div class="col-xs-8">
                                    <input class="form-control" type="password" name="password">
                                </div>
                                <c:if test = "${sessionScope.validList.invalidPassword != null}">
                                    <div class="col-xs-8">
                                        <text style = "color:red;">Паролі повинні співпадати</text>
                                    </div>
                                </c:if>
                                <c:if test = "${sessionScope.validList.invalidPasswordName != null}">
                                    <div class="col-xs-8">
                                        <text style = "color:red;">Пароль обов'язково повинен містити цифру, велику та малу латинську літеру і бути не менше 8 символів</text>
                                    </div>
                                </c:if>
                            </div>
                            <div class="form-group mb-2">
                                <label class="control-label col-xs-4 mb-2">Повторити пароль</label>
                                <div class="col-xs-8">
                                    <input class="form-control" type="password" name="secondPassword">
                                </div>
                            </div>
                            <div class="form-group mb-2">
                                <label class="control-label col-xs-4 mb-2">Номер телефону</label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control"  name="phoneNumber" value="${sessionScope.user.phoneNumber}">
                                </div>
                                <c:if test = "${sessionScope.validList.invalidPhoneNumber != null}">
                                    <div class="col-xs-8">
                                        <text style = "color:red;">телефон - ${sessionScope.validList.invalidPhoneNumber} повинен мати 10 цифр та починатися з 0 або +380</text>
                                    </div>
                                </c:if>
                            </div>
                            <div class="form-group mb-2">
                                <label class="control-label col-xs-4 mb-2">Пошта</label>
                                <div class="col-12">
                                    <input type="text" class="form-control"name="email" value="${sessionScope.user.email}">
                                </div>
                                <c:if test = "${sessionScope.validList.invalidEmail != null}">
                                    <div class="col-xs-8">
                                        <text style = "color:red;">Користувач з email ${sessionScope.validList.invalidEmail} уже існує</text>
                                    </div>
                                </c:if>
                                <c:if test = "${sessionScope.validList.invalidEmailName != null}">
                                    <div class="col-xs-8">
                                        <text style = "color:red;">Email - ${sessionScope.validList.invalidEmailName} повинен дотримуватися стандартів email</text>
                                    </div>
                                </c:if>
                            </div>
                            <c:if test = "${sessionScope.role == 'user'}">
                                <p><label class="checkbox-inline"><input type="checkbox" name="notify" value="notifyEmail" class="me-2" <c:if test="${sessionScope.user.notify =='yes'}"> checked </c:if>>Підписатися на розсилку</label></p>
                            </c:if>
                            <div class="form-group mb-2">
                                <div class="col-12 text-center">
                                    <input type="submit" class="btn btn-primary btn-lg" value="Редагувати">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
