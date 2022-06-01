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
    <title><fmt:message key="editProfile"/></title>
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
                    <a href="/index.jsp" class="nav-item nav-link"><fmt:message key="home"/></a>
                    <a href="/aboutUs.jsp" class="nav-item nav-link"><fmt:message key="aboutUs"/></a>
                    <a href="/info.jsp" class="nav-item nav-link"><fmt:message key="deliveryDirections"/></a>
                    <a href="/calculate.jsp" class="nav-item nav-link"><fmt:message key="calculateCost"/></a>
                    <a href="/reviews.jsp" class="nav-item nav-link"><fmt:message key="reviews"/></a>
                    <a href="/user/createOrder.jsp" class="nav-item nav-link"><fmt:message key="makingOrder"/></a>
                    <a href="/user/order.jsp" class="nav-item nav-link"><fmt:message key="myOrders"/></a>
                </c:if>
                <c:if test="${sessionScope.role=='manager'}">
                    <a href="/man/orderList.jsp" class="nav-item nav-link"><fmt:message key="userRequests"/></a>
                </c:if>
                <c:if test="${sessionScope.role=='admin'}">
                    <a href="/adm/usersTable.jsp" class="nav-item nav-link"><fmt:message key="userTable"/></a>
                    <a href="/adm/createEmployeeAccount.jsp" class="nav-item nav-link"><fmt:message key="registrationNewEmployee"/></a>
                </c:if>
                <c:if test="${sessionScope.role=='employee'}">
                    <a href="/employee/ordersTable.jsp" class="nav-item nav-link"><fmt:message key="storage"/></a>
                    <a href="/employee/acceptOrder.jsp" class="nav-item nav-link"><fmt:message key="acceptanceGoods"/></a>
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
                        <a href="/user/refill.jsp" class="dropdown-item"><fmt:message key="refill"/></a>
                    </c:if>
                    <a href="/editUser.jsp" class="dropdown-item"><fmt:message key="editing"/></a>
                    <a href="/lout" class="dropdown-item"><fmt:message key="signOut"/></a>
                </div>
            </div>
        </div>
        <div class="nav navbar-nav navbar-right">
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.firstLang}</a>
                <div class="dropdown-menu">
                    <a href="/changeLanguage?id=5" class="dropdown-item">${sessionScope.secondLang}</a>
                </div>
            </div>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center mt-3 mb-3">
        <div class="col-md-auto">
            <h4><fmt:message key="editProfile"/></h4>
        </div>
    </div>
    <div class="row justify-content-center">
        <form method="post" action="/editUser">
            <div class="row justify-content-center">
                <div class="col-md-auto">
                    <div class="rounded bg-light p-4 mb-3">
                        <div class="input-group-prepend">
                            <div class="form-group mb-2">
                                <label class="control-label col-xs-4 mb-2"><fmt:message key="firstName"/></label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" name="firstName" value="${sessionScope.user.firstName}">
                                </div>
                                <c:if test = "${sessionScope.validList.invalidFirstName != null}">
                                    <div class="col-xs-8">
                                        <text style = "color:red;"><fmt:message key="firstName"/> - ${sessionScope.validList.invalidFirstName} <fmt:message key="mustConsistOfLetters"/></text>
                                    </div>
                                </c:if>
                            </div>
                            <div class="form-group mb-2">
                                <label class="control-label col-xs-4 mb-2"><fmt:message key="lastName"/></label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control" name="lastName" value="${sessionScope.user.lastName}">
                                </div>
                                <c:if test = "${sessionScope.validList.invalidLastName != null}">
                                    <div class="col-xs-8">
                                        <text style = "color:red;"><fmt:message key="lastName"/> - ${sessionScope.validList.invalidLastName} <fmt:message key="mustConsistOfLetters"/></text>
                                    </div>
                                </c:if>
                            </div>
                            <div class="form-group mb-2">
                                <label class="control-label col-xs-4 mb-2"><fmt:message key="newPassword"/></label>
                                <div class="col-xs-8">
                                    <input class="form-control" type="password" name="password">
                                </div>
                                <c:if test = "${sessionScope.validList.invalidPassword != null}">
                                    <div class="col-xs-8">
                                        <text style = "color:red;"><fmt:message key="passwordMatch"/></text>
                                    </div>
                                </c:if>
                                <c:if test = "${sessionScope.validList.invalidPasswordName != null}">
                                    <div class="col-xs-8">
                                        <text style = "color:red;"><fmt:message key="repeatPassword"/></text>
                                    </div>
                                </c:if>
                            </div>
                            <div class="form-group mb-2">
                                <label class="control-label col-xs-4 mb-2"><fmt:message key="repeatNewPassword"/></label>
                                <div class="col-xs-8">
                                    <input class="form-control" type="password" name="secondPassword">
                                </div>
                            </div>
                            <div class="form-group mb-2">
                                <label class="control-label col-xs-4 mb-2"><fmt:message key="phoneNumber"/></label>
                                <div class="col-xs-8">
                                    <input type="text" class="form-control"  name="phoneNumber" value="${sessionScope.user.phoneNumber}">
                                </div>
                                <c:if test = "${sessionScope.validList.invalidPhoneNumber != null}">
                                    <div class="col-xs-8">
                                        <text style = "color:red;"><fmt:message key="phoneNumber"/> - ${sessionScope.validList.invalidPhoneNumber} <fmt:message key="mustBeDigits"/></text>
                                    </div>
                                </c:if>
                            </div>
                            <div class="form-group mb-2">
                                <label class="control-label col-xs-4 mb-2"><fmt:message key="email"/></label>
                                <div class="col-12">
                                    <input type="text" class="form-control" name="email" value="${sessionScope.user.email}">
                                </div>
                                <c:if test = "${sessionScope.validList.invalidEmail != null}">
                                    <div class="col-xs-8">
                                        <text style = "color:red;"><fmt:message key="userWithEmail"/> ${sessionScope.validList.invalidEmail} <fmt:message key="alreadyExists"/></text>
                                    </div>
                                </c:if>
                                <c:if test = "${sessionScope.validList.invalidEmailName != null}">
                                    <div class="col-xs-8">
                                        <text style = "color:red;"><fmt:message key="email"/> - ${sessionScope.validList.invalidEmailName} <fmt:message key="mustAdhereEmailStandards"/></text>
                                    </div>
                                </c:if>
                            </div>
                            <c:if test = "${sessionScope.role == 'user'}">
                                <p><label class="checkbox-inline"><input type="checkbox" name="notify" value="notifyEmail" class="me-2" <c:if test="${sessionScope.user.notify =='yes'}"> checked </c:if>><fmt:message key="receiveEmails"/></label></p>
                            </c:if>
                            <div class="form-group mb-2">
                                <div class="col-12 text-center">
                                    <input type="submit" class="btn btn-primary btn-lg" value="<fmt:message key="confirm"/>">
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
