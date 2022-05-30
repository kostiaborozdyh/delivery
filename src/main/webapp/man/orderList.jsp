<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 08.05.2022
  Time: 12:49
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
    <title>Bootstrap Fluid Layout</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style type="text/css">
        .table td, th {
            text-align: center;
            border: 5px solid white;
        }

        .btnsl {
            gap: 5%;
        }

        .b1 {
            width: 150px;
        }

        .sort {
            height: 280px;
        }

        .divtbl {
            gap: 15px;
        }

        .divl {
            gap: 5px;
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
                <a href="/man/orderList.jsp" class="nav-item nav-link active">Заявки користувачів</a>
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
                    <a href="/changeLanguage?id=16" class="dropdown-item">${sessionScope.secondLang}</a>
                </div>
            </div>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row justify-content-center mt-3 mb-3">
        <div class="col-md-auto">
            <h4>Заявки</h4>
        </div>
    </div>
    <div class="row">
        <h5 class="text mb-0 mt-3 mb-3">Фільтрація</h5><br>
    </div>
    <form method="post" action="/filtrationOrder" id="form">
        <div class="row">
            <div class="col-2 sort">
                <div class="col-md-auto">
                    <div class="rounded bg-light p-2 mb-3 sort">
                        <div class="input-group-prepend">
                            <p class="pt-3 d-flex align-items-center">Ціна доставки</p>
                            <div class="d-flex flex-column divtbl h-75">
                                <div class="form-outline mb-3">
                                    <label class="form-label" for="typeNumber1">Ціна від</label>
                                    <input type="number" id="typeNumber1" name="minPrice" min="0"
                                           value="${sessionScope.minPrice}" class="form-control"/>
                                </div>
                                <div class="form-outline mb-3">
                                    <label class="form-label" for="typeNumber2">Ціна до</label>
                                    <input type="number" id="typeNumber2" name="maxPrice" min="0"
                                           value="${sessionScope.maxPrice}" class="form-control"/>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-2 sort">
                <div class="col-md-auto">
                    <div class="rounded bg-light p-2 sort">
                        <p class="h-25 d-flex align-items-center">Статус заявки</p>
                        <div class="d-flex flex-column divtbl h-75">
                            <div class="input-group-text mb-1">
                                <label><input type="checkbox" class="form-check-input" name="paymentStatus"
                                              value="На розгляді" <c:if test="${sessionScope.status1 != null}">
                                              checked</c:if>>На розгляді</label>
                            </div>
                            <div class="input-group-text mb-1">
                                <label><input type="checkbox" class="form-check-input" name="paymentStatus"
                                              value="Очікує оплату"
                                <c:if test="${sessionScope.status2 != null}"> checked</c:if> >Очікує оплату</label>
                            </div>
                            <div class="input-group-text mb-1">
                                <label><input type="checkbox" class="form-check-input" name="paymentStatus" value="Оплачено"
                                <c:if test="${sessionScope.status3 != null}"> checked</c:if> >Оплачено</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-2 sort">
                <div class="col-md-auto">
                    <div class="rounded bg-light p-2 sort">
                        <p class="h-25 d-flex align-items-center">Місцезнаходження посилки</p>
                        <div class="d-flex flex-column divl h-50">
                            <div class="input-group-text mb-1">
                                <label>
                                    <input type="checkbox" class="form-check-input" name="locationStatus"
                                           value="В місті відправлення" <c:if test="${sessionScope.location1 != null}">
                                           checked</c:if>>В місті відправлення
                                </label>
                            </div>
                            <div class="input-group-text mb-1">
                                <label><input type="checkbox" class="form-check-input" name="locationStatus"
                                              value="В дорозі"
                                <c:if test="${sessionScope.location2 != null}"> checked</c:if> >В дорозі</label>
                            </div>
                            <div class="input-group-text mb-1">
                                <label>
                                    <input type="checkbox" class="form-check-input" name="locationStatus"
                                           value="У відділенні пошти"
                                    <c:if test="${sessionScope.location3 != null}"> checked</c:if> >У відділенні пошти
                                </label>
                            </div>
                            <div class="input-group-text mb-1">
                                <label><input type="checkbox" class="form-check-input" name="locationStatus"
                                              value="Отримано"
                                <c:if test="${sessionScope.location4 != null}"> checked</c:if> >Отримано</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-2 sort">
                <div class="col-md-auto">
                    <div class="rounded bg-light p-2 sort">
                        <p class="pt-4 d-flex align-items-center">Дата посилки</p>
                        <div class="d-flex flex-column h-50">
                            <p class="mb-1">Дата Відправки</p>
                            <label class="d-flex"><p class="w-25">Від </p><input class="w-75 h-75" type="date"
                                                                                 name="minDateCreate"
                                                                                 value="${sessionScope.minDateCreate}"></label>
                            <label class="d-flex"><p class="w-25">До </p><input class="w-75 h-75" type="date"
                                                                                name="maxDateCreate"
                                                                                value="${sessionScope.maxDateCreate}"></label>
                            <p class="mb-1">Дата Прибуття</p>
                            <label class="d-flex"><p class="w-25">Від </p><input type="date" class="w-75 h-75"
                                                                                 name="minDateOfArrival"
                                                                                 value="${sessionScope.minDateOfArrival}"></label>
                            <label class="d-flex"><p class="w-25">До </p><input type="date" class="w-75 h-75"
                                                                                name="maxDateOfArrival"
                                                                                value="${sessionScope.maxDateOfArrival}"></label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-2 sort">
                <div class="col-md-auto">
                    <div class="rounded bg-light p-2 sort">
                        <p class="pt-4 d-flex align-items-center">Міста посилки</p>
                        <div class="d-flex flex-column h-75">
                            <label for="exampleFormControlSelect1">Міста відправки</label>
                            <select multiple class="form-control" name="cityFrom[]" id="exampleFormControlSelect1" size="2">
                                <c:forEach var="order" items="${sessionScope.cityFromSet}">
                                    <option>${order}</option>
                                </c:forEach>
                            </select>
                            <label for="exampleFormControlSelect2" class="mt-4">Міста доставвки</label>
                            <select multiple class="form-control" name="cityTo[]" id="exampleFormControlSelect2" size="2">
                                <c:forEach var="order" items="${sessionScope.cityToSet}">
                                    <option>${order}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-2 sort">
                <div class="rounded bg-light p-2 mb-3 sort">
                    <div class="input-group-prepend">
                        <p class="pt-4 d-flex align-items-center">Сортування заявок</p>
                        <div class="d-flex flex-column divl h-75 pt-3">
                            <div class="input-group-text mb-1">
                                <label><input type="radio" name="sort" value="sortByMinPrice"  <c:if
                                        test="${sessionScope.sort == 'sortByMinPrice'}"> checked</c:if>>По ціні↑</label>
                            </div>
                            <div class="input-group-text mb-1">
                                <label><input type="radio" name="sort" value="sortByMaxPrice" <c:if
                                        test="${sessionScope.sort == 'sortByMaxPrice'}"> checked</c:if>>По ціні↓</label>
                            </div>
                            <div class="input-group-text mb-1">
                                <label><input type="radio" name="sort" value="sortByMinDateCreate" <c:if
                                        test="${sessionScope.sort == 'sortByMinDateCreate'}"> checked</c:if>>По Даті
                                    створення↑</label>
                            </div>
                            <div class="input-group-text mb-1">
                                <label><input type="radio" name="sort" value="sortByMaxDateCreate" <c:if
                                        test="${sessionScope.sort == 'sortByMaxDateCreate'}"> checked</c:if>>По Даті
                                    створення↓</label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <div class="row mt-3">
        <div class="col-6 d-flex btnsl">
            <p><a href="/resetOrder" class="btn btn-warning b1">Оновити</a></p>
            <p><a href="/pdfOrderList" target="_blank" class="btn btn-primary b1">Звіт</a></p>
        </div>
        <div class="col-6 d-flex btnsl justify-content-end">
            <p><input type="submit" class="btn btn-success b1" form="form" value="Сортувати"></p>
            <form method="post" action="/resetOrder">
                <input type="submit" class="btn btn-success b1" value="Скинути фільтри">
            </form>
        </div>
    </div>
    <div class="row">
        <table class="table table-borderless">
            <tr>
                <th class="bg-light">Номер</th>
                <th class="bg-light">Опис</th>
                <th class="bg-light">Ціна</th>
                <th class="bg-light">Місто Відправлення</th>
                <th class="bg-light">Місто отримання</th>
                <th class="bg-light">Дата створення</th>
                <th class="bg-light">Дата прибуття</th>
                <th class="bg-light">Статус</th>
                <th class="bg-light">Місце знаходження</th>
                <th class="bg-light">Замовник</th>
                <th class="bg-light">Детальніше</th>
            </tr>
            <c:forEach var="order" items="${sessionScope.shortOrders}">
                <tr>
                    <td class="bg-light">${order.id}</td>
                    <td class="bg-light">${order.description}</td>
                    <td class="bg-light">${order.price}</td>
                    <td class="bg-light">${order.cityFrom}</td>
                    <td class="bg-light">${order.cityTo}</td>
                    <td class="bg-light">${order.dateCreate}</td>
                    <td class="bg-light">${order.dateOfArrival}</td>
                    <td class="bg-light">${order.paymentStatus}</td>
                    <td class="bg-light">${order.locationStatus}</td>
                    <td class="bg-light">${order.userLogin}</td>
                    <td class="bg-light">
                        <abbr title="детальніше про замовлення" >
                            <a href="/info?id=${order.id}" class="btn btn-outline-primary">Детальніше</a>
                        </abbr>
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
</div>
<script src="/script/scriptUser.js"></script>
</body>
</html>
