<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 22.05.2022
  Time: 16:40
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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Склад</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style type="text/css">
        .table td,th {
            text-align: center;
            border: 5px solid white;
        }
        .b1 {
            width: 150px;
        }
        .dropdown-menu {
            min-width: 30px;
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
                <a href="/employee/ordersTable.jsp" class="nav-item nav-link active">Cклад</a>
                <a href="/employee/acceptOrder.jsp" class="nav-item nav-link">Прийняти посилки</a>
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
                    <a href="/changeLanguage?id=15" class="dropdown-item">${sessionScope.secondLang}</a>
                </div>
            </div>
        </div>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center mt-3 mb-3">
        <div class="col-md-auto">
            <h4>Склад</h4>
        </div>
    </div>
    <c:if test="${sessionScope.city==null}">
        <form method="post" action="/chooseCity">
            <div class="row justify-content-center">
                <div class="col-3">
                    <div class="form-group mb-3">
                        <label class="control-label col-xs-4 mb-1">Введіть місто в якому працюєте<text style = "color:red;">*</text></label>
                        <input type="text" class="form-control" id="autocomplete" name="city" required="required">
                    </div>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-md-auto">
                    <p><input type="submit" class="btn btn-success" value="Вибрати"></p>
                </div>
            </div>
        </form>
    </c:if>
    <c:if test="${sessionScope.city!=null}">
        <div class="row justify-content-center">
            <div class="col-md-auto">
                <p>Поточне місто: ${sessionScope.city}</p>
            </div>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-auto ">
                <form method="post" action="/changeCity">
                    <p><input type="submit" class="btn btn-success b1" value="Змінити місто"></p>
                </form>
            </div>
            <div class="col-md-auto ">
                <form method="post" action="/chooseCity?on=1">
                    <p><input type="submit" class="btn btn-success b1" value="Оновити"></p>
                </form>
            </div>
        </div>
    </c:if>
    <c:if test="${sessionScope.shortOrders!=null}">
        <div class="row">
            <table class="table table-borderless">
                <tr>
                    <th class="bg-light">Номер товару</th>
                    <th class="bg-light">Опис</th>
                    <th class="bg-light">Місто відправлення</th>
                    <th class="bg-light">Місто отримання</th>
                    <th class="bg-light">Статус оплати</th>
                    <th class="bg-light">Віддати</th>
                </tr>
                <c:forEach var="order" items="${sessionScope.shortOrders}">
                    <tr>
                        <td class="bg-light">${order.id}</td>
                        <td class="bg-light">${order.description}</td>
                        <td class="bg-light">${order.cityFrom}</td>
                        <td class="bg-light">${order.cityTo}</td>
                        <td class="bg-light">${order.paymentStatus}</td>
                        <td class="bg-light">
                            <c:if test="${order.paymentStatus == 'Очікує оплату'}">
                                <form>
                                    <input type="submit" class="btn btn-danger" value="Віддати">
                                </form>
                            </c:if>
                            <c:if test="${order.paymentStatus == 'Оплачено'}">
                                <form method="post" action="/giveOrder?id=${order.id}">
                                    <input type="submit" class="btn btn-success" value="Віддати">
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-auto">
                <c:if test="${sessionScope.listNumberOrder!=null}">
                    <nav>
                        <ul class="pagination">
                            <li class="page-item">
                                <a href="/changePage?id=${sessionScope.pageNumberOrder-1}&fun=2" class="page-link">Попередня</a>
                            </li>
                            <c:forEach var="n" items="${sessionScope.listNumberOrder}">
                                <li class="page-item <c:if test="${sessionScope.pageNumberOrder==n}">active</c:if>">
                                    <a href="/changePage?id=${n}&fun=2" class="page-link">${n}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item">
                                <a href="/changePage?id=${sessionScope.pageNumberOrder+1}&fun=2" class="page-link">Наступна</a>
                            </li>
                        </ul>
                    </nav>
                </c:if>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
<%--
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDZ_4ASyzLdt1d16-mekZg5W4X24P0zIR4&libraries=places&callback=initAutocomplete"
        async defer></script>

<script>
    function initAutocomplete() {
        new google.maps.places.Autocomplete(
            (document.getElementById('autocomplete')),
            {types: ['geocode']}
        );
        new google.maps.places.Autocomplete(
            (document.getElementById('autocompleteSec')),
            {types: ['geocode']}
        );
    }
</script>
--%>