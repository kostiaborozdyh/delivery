<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 07.05.2022
  Time: 14:58
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
    <title><fmt:message key="myOrders"/></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style type="text/css">
        .table td,th {
            text-align: center;
            vertical-align: middle;
            border: 5px solid white;
        }
        .imgr {
            width: 10%;
        }
        .btnsl {
            gap: 5%;
        }
        .b1 {
            width: 150px;
        }
        .sort{
            height: 280px;
        }
        .divtbl{
            gap: 15px;
        }
        .divl{
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
                <a href="/index.jsp" class="nav-item nav-link"><fmt:message key="home"/></a>
                <a href="/aboutUs.jsp" class="nav-item nav-link"><fmt:message key="aboutUs"/></a>
                <a href="/info.jsp" class="nav-item nav-link"><fmt:message key="deliveryDirections"/></a>
                <a href="/calculate.jsp" class="nav-item nav-link"><fmt:message key="calculateCost"/></a>
                <a href="/reviews.jsp" class="nav-item nav-link"><fmt:message key="reviews"/></a>
                <a href="/user/createOrder.jsp" class="nav-item nav-link"><fmt:message key="makingOrder"/></a>
                <a href="/user/order.jsp" class="nav-item nav-link active"><fmt:message key="myOrders"/></a>
            </div>
        </div>
        <div class="nav navbar-nav navbar-right">
            <a href="#" class="nav-item nav-link disabled">${sessionScope.money}$</a>
        </div>
        <div class="nav navbar-nav navbar-right">
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.user.login}</a>
                <div class="dropdown-menu ">
                    <a href="/user/refill.jsp" class="dropdown-item"><fmt:message key="refill"/></a>
                    <a href="/editUser.jsp" class="dropdown-item"><fmt:message key="editing"/></a>
                    <a href="/lout" class="dropdown-item"><fmt:message key="signOut"/></a>
                </div>
            </div>
        </div>
        <div class="nav navbar-nav navbar-right">
            <div class="nav-item dropdown">
                <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.firstLang}</a>
                <div class="dropdown-menu">
                    <a href="/changeLanguage?id=23" class="dropdown-item">${sessionScope.secondLang}</a>
                </div>
            </div>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row justify-content-center mt-3 mb-3">
        <div class="col-md-auto">
            <h4><fmt:message key="myOrders"/></h4>
        </div>
    </div>
    <div class="row">
        <h5 class="text mb-0 mt-3 mb-3"><fmt:message key="filtration"/></h5><br>
    </div>
    <form method="post" action="/filtrationOrder" id="filter">
        <div class="row">
                <div class="col-2 sort">
                    <div class="col-md-auto">
                        <div class="rounded bg-light p-2 mb-3 sort">
                            <div class="input-group-prepend">
                                <p class="pt-3 d-flex align-items-center"><fmt:message key="deliveryPriceSort"/></p>
                                <div class="d-flex flex-column divtbl h-75">
                                    <div class="form-outline mb-3">
                                        <label class="form-label" for="nbm1"><fmt:message key="priceFrom"/></label>
                                        <input type="number" id="nbm1" name="minPrice" min="0" value="${sessionScope.filter.minPrice}"  class="form-control" />
                                    </div>
                                    <div class="form-outline mb-3">
                                        <label class="form-label" for="nbm2"><fmt:message key="priceTo"/></label>
                                        <input type="number" id="nbm2"  name="maxPrice" min="0" value="${sessionScope.filter.maxPrice}" class="form-control" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-2 sort">
                    <div class="col-md-auto">
                        <div class="rounded bg-light p-2 sort">
                            <p class="h-25 d-flex align-items-center"><fmt:message key="orderStatus"/></p>
                            <div class="d-flex flex-column divtbl h-75">
                                <div class="input-group-text mb-1">
                                    <label><input type="checkbox" class="form-check-input" name="paymentStatus" value="На розгляді" <c:if test="${sessionScope.filter.paymentStatus[0] == 'На розгляді'}"> checked</c:if> ><fmt:message key="underConsideration"/></label>
                                </div>
                                <div class="input-group-text mb-1">
                                    <label><input type="checkbox" class="form-check-input" name="paymentStatus" value="Очікує оплату" <c:if test="${sessionScope.filter.paymentStatus[0] == 'Очікує оплату' || sessionScope.filter.paymentStatus[1] == 'Очікує оплату'}"> checked</c:if> ><fmt:message key="awaitingPayment"/></label>
                                </div>
                                <div class="input-group-text mb-1">
                                    <label><input type="checkbox" class="form-check-input" name="paymentStatus" value="Оплачено" <c:if test="${sessionScope.filter.paymentStatus[0] == 'Оплачено' || sessionScope.filter.paymentStatus[1] == 'Оплачено'|| sessionScope.filter.paymentStatus[2] == 'Оплачено'}"> checked</c:if>><fmt:message key="paid"/></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-2 sort">
                    <div class="col-md-auto">
                        <div class="rounded bg-light p-2 sort">
                            <p class="h-25 d-flex align-items-center"><fmt:message key="locationParcel"/></p>
                            <div class="d-flex flex-column divl h-50">
                                <div class="input-group-text mb-1">
                                    <label>
                                        <input type="checkbox" class="form-check-input" name="locationStatus" value="В місті відправлення" <c:if test="${sessionScope.filter.location[0] == 'В місті відправлення'}"> checked</c:if>><fmt:message key="cityOfDepartureSort"/>
                                    </label>
                                </div>
                                <div class="input-group-text mb-1">
                                    <label><input type="checkbox" class="form-check-input" name="locationStatus" value="В дорозі" <c:if test="${sessionScope.filter.location[0] == 'В дорозі' || sessionScope.filter.location[1] == 'В дорозі'}"> checked</c:if> ><fmt:message key="inWay"/></label>
                                </div>
                                <div class="input-group-text mb-1">
                                    <label>
                                        <input type="checkbox" class="form-check-input" name="locationStatus" value="У відділенні пошти" <c:if test="${sessionScope.filter.location[0] == 'У відділенні пошти' || sessionScope.filter.location[1] == 'У відділенні пошти' || sessionScope.filter.location[2] == 'У відділенні пошти'}"> checked</c:if> ><fmt:message key="postOffice"/>
                                    </label>
                                </div>
                                <div class="input-group-text mb-1">
                                    <label><input type="checkbox" class="form-check-input" name="locationStatus" value="Отримано" <c:if test="${sessionScope.filter.location[0] == 'Отримано' || sessionScope.filter.location[1] == 'Отримано' || sessionScope.filter.location[2] == 'Отримано' || sessionScope.filter.location[3] == 'Отримано'}"> checked</c:if> ><fmt:message key="received"/></label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-2 sort">
                    <div class="col-md-auto">
                        <div class="rounded bg-light p-2 sort">
                            <p class="pt-4 d-flex align-items-center"><fmt:message key="parcelDate"/></p>
                            <div class="d-flex flex-column h-50">
                                <p class="mb-1"><fmt:message key="dateOfDispatch"/></p>
                                <label class="d-flex"><p class="w-25"><fmt:message key="from"/> </p><input class="w-75 h-75" type="date" name="minDateCreate" value="${sessionScope.filter.minDateCreate}"></label>
                                <label class="d-flex"><p class="w-25"><fmt:message key="to"/> </p><input  class="w-75 h-75" type="date" name="maxDateCreate" value="${sessionScope.filter.maxDateCreate}"></label>
                                <p class="mb-1"><fmt:message key="dateOfArrival"/></p>
                                <label class="d-flex"><p class="w-25"><fmt:message key="from"/> </p><input type="date"  class="w-75 h-75" name="minDateOfArrival" value="${sessionScope.filter.minDateOfArrival}"></label>
                                <label class="d-flex"><p class="w-25"><fmt:message key="to"/> </p><input type="date"  class="w-75 h-75" name="maxDateOfArrival" value="${sessionScope.filter.maxDateOfArrival}"></label>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-2 sort">
                    <div class="col-md-auto">
                        <div class="rounded bg-light p-2 sort">
                            <p class="pt-4 d-flex align-items-center"><fmt:message key="parcelCities"/></p>
                            <div class="d-flex flex-column h-75">
                                <label for="exampleFormControlSelect1"><fmt:message key="citiesOfDeparture"/></label>
                                <select multiple class="form-control" id="exampleFormControlSelect1" name="cityFrom[]" size="2">
                                    <c:forEach var="city" items="${sessionScope.cityFromSet}">
                                        <option>${city}</option>
                                    </c:forEach>
                                </select>
                                <label for="exampleFormControlSelect2" class="mt-4"><fmt:message key="deliveryCities"/></label>
                                <select multiple class="form-control" id="exampleFormControlSelect2" name="cityTo[]" size="2">
                                    <c:forEach var="city" items="${sessionScope.cityToSet}">
                                        <option>${city}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-2 sort">
                    <div class="rounded bg-light p-2 mb-3 sort">
                        <div class="input-group-prepend">
                            <p class="pt-4 d-flex align-items-center"><fmt:message key="sortingOrders"/></p>
                            <div class="d-flex flex-column divl h-75 pt-3">
                                <div class="input-group-text mb-1">
                                    <label><input type="radio" name="sort" value="sortByMinPrice"  <c:if test="${sessionScope.filter.sort == 'sortByMinPrice'}"> checked</c:if>><fmt:message key="byPrice"/>↑</label>
                                </div>
                                <div class="input-group-text mb-1">
                                    <label><input type="radio" name="sort" value="sortByMaxPrice" <c:if test="${sessionScope.filter.sort == 'sortByMaxPrice'}"> checked</c:if>><fmt:message key="byPrice"/>↓</label>
                                </div>
                                <div class="input-group-text mb-1">
                                    <label><input type="radio" name="sort" value="sortByMinDateCreate" <c:if test="${sessionScope.filter.sort == 'sortByMinDateCreate'}"> checked</c:if>><fmt:message key="byDateCreation"/>↑</label>
                                </div>
                                <div class="input-group-text mb-1">
                                    <label><input type="radio" name="sort" value="sortByMaxDateCreate" <c:if test="${sessionScope.filter.sort == 'sortByMaxDateCreate'}"> checked</c:if>><fmt:message key="byDateCreation"/>↓</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </form>
    <div class="row mt-3">
        <div class="col-6 d-flex btnsl">
            <p><a href="/resetOrder" class="btn btn-warning b1"><fmt:message key="update"/></a></p>
            <p><a href="/pdfOrder" target="_blank" class="btn btn-primary b1"><fmt:message key="report"/></a></p>
        </div>
        <div class="col-6 d-flex btnsl justify-content-end">
            <p><input type="submit" class="btn btn-success b1" form="filter" value="<fmt:message key="sorting"/>"></p>
            <form method="post" action="/resetOrder">
                <input type="submit" class="btn btn-success b1" value="<fmt:message key="resetFilters"/>">
            </form>
        </div>
    </div>
    <div class="row">
        <table class="table table-borderless">
            <tr>
                <th class="bg-light">Id</th>
                <th class="bg-light"><fmt:message key="description"/></th>
                <th class="bg-light"><fmt:message key="price"/></th>
                <th class="bg-light"><fmt:message key="cityOfDeparture"/></th>
                <th class="bg-light"><fmt:message key="cityOfReceipt"/></th>
                <th class="bg-light"><fmt:message key="dateOfCreation"/></th>
                <th class="bg-light"><fmt:message key="dateOfArrival"/></th>
                <th class="bg-light"><fmt:message key="status"/></th>
                <th class="bg-light"><fmt:message key="location"/></th>
                <th class="bg-light"><fmt:message key="payment"/></th>
                <th class="bg-light"><fmt:message key="invoice"/></th>
                <th class="bg-light"><fmt:message key="readMore"/></th>
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
                    <td class="bg-light">
                        <c:if test = "${(sessionScope.money<order.price) && ((order.paymentStatus == 'Очікує оплату') ||(order.paymentStatus == 'На розгляді')) }">
                            <abbr title="Недостатньо грошей, потрібно поповнити рахунок" ><a href="*" class="btn btn-danger" onclick="return false"><fmt:message key="pay"/></a></abbr>
                        </c:if>
                        <c:if test = "${(sessionScope.money>=order.price) && (order.paymentStatus == 'На розгляді')}">
                            <abbr title="Ваша заявка на розгляді" ><a href="*" class="btn btn-danger" onclick="return false"><fmt:message key="pay"/></a></abbr>
                        </c:if>

                        <c:if test = "${(sessionScope.money>=order.price) && (order.paymentStatus == 'Очікує оплату') }">
                            <form method="post" action="/pay?id=${order.id}&value=${order.price}&money=${sessionScope.money}">
                                <p><input type="submit" class="btn btn-success" value="<fmt:message key="pay"/>"></p>
                            </form>
                        </c:if>
                        <c:if test="${order.paymentStatus == 'Оплачено'}">
                            <abbr title="Заявка вже оплачена" >
                                <a href="*" class="btn btn-secondary" onclick="return false"><fmt:message key="pay"/></a>
                            </abbr>
                        </c:if>
                    </td>
                    <td class="bg-light imgr">
                        <a href="/pdfUserOrder?idOrder=${order.id}" target="_blank"> <img src="/img/pdfIcon.png"  width="30%"></a>
                        <a href="/sendEmailOrder?idOrder=${order.id}"><img src="/img/emailIcon.png"  width="35%"></a>
                    </td>
                    <td class="bg-light">
                        <a href="/info?id=${order.id}" class="btn btn-outline-primary"><fmt:message key="readMore"/></a>
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
                            <a href="/changePage?id=${sessionScope.pageNumberOrder-1}&fun=2" class="page-link"><fmt:message key="previous"/></a>
                        </li>
                        <c:forEach var="n" items="${sessionScope.listNumberOrder}">
                            <li class="page-item <c:if test="${sessionScope.pageNumberOrder==n}">active</c:if>">
                                <a href="/changePage?id=${n}&fun=2" class="page-link">${n}</a>
                            </li>
                        </c:forEach>
                        <li class="page-item">
                            <a href="/changePage?id=${sessionScope.pageNumberOrder+1}&fun=2" class="page-link"><fmt:message key="next"/></a>
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
