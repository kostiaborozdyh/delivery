<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>
<html lang="${sessionScope.lang}" class="h-100 ">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Напрямки доставки</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
    <style type="text/css">
        .table td, th {
            text-align: center;
            border: 5px solid white;
        }
        .dropdown-menu {
            min-width: 30px;
        }
    </style>
</head>
<body class="pb-3">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-2">
    <div class="container-fluid">
        <a href="#" class="navbar-brand"><fmt:message key="delivery"/></a>
        <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
            <div class="navbar-nav">
                <a href="/index.jsp" class="nav-item nav-link ">Головна</a>
                <a href="/aboutUs.jsp" class="nav-item nav-link">Про нас</a>
                <a href="/info.jsp" class="nav-item nav-link active">Напрямки доставки</a>
                <a href="/calculate.jsp" class="nav-item nav-link">Розрахувати вартість посилки</a>
                <a href="/reviews.jsp" class="nav-item nav-link">Відгуки</a>
                <c:if test="${sessionScope.role=='user'}">
                    <a href="/user/createOrder.jsp" class="nav-item nav-link">Оформлення замовлення</a>
                    <a href="/user/order.jsp" class="nav-item nav-link">Мої заявки</a>
                </c:if>
            </div>
            <c:if test="${sessionScope.role==null}">
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
            </c:if>
        </div>
        <c:if test="${sessionScope.role=='user'}">
            <div class="nav navbar-nav navbar-right">
                <a href="#" class="nav-item nav-link disabled">${sessionScope.money}$</a>
            </div>
            <div class="nav navbar-nav navbar-right">
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.user.login}</a>
                    <div class="dropdown-menu ">
                        <a href="/user/refill.jsp" class="dropdown-item">Поповнення</a>
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
        </c:if>
    </div>
</nav>
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-auto">
            <h2>Доставка вантажу</h2>
        </div>
    </div>
    <form method="post" action="/showInfo" id="form">
        <div class="row justify-content-center">
            <div class="col-6">
                <div class="row">
                    <div class="col-6">
                        <div class="row justify-content-center">
                            <div class="col-md-auto">
                                <h5>Міста відправлення</h5>
                                <div class="row justify-content-center">
                                    <div class="col-md-auto">
                                        <label class="mb-1"><input type="text" id="idCityFrom1"
                                                                   name="cityFrom1" required></label><br>
                                        <div id="divCityFrom2"></div>
                                        <div id="divCityFrom3"></div>
                                        <div id="divCityFrom4"></div>
                                        <div id="divCityFrom5"></div>
                                        <div class="row justify-content-center mt-1">
                                            <div class="col-md-auto">
                                                <p><a class="btn btn-success" id="textCityFrom">Додати місто &raquo;</a>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-6">
                        <div class="row justify-content-center">
                            <div class="col-md-auto">
                                <h5 class="text-center">Міста отримання</h5>
                                <div class="row justify-content-center">
                                    <div class="col-md-auto">
                                        <label class="mb-1"><input type="text" id="idCityTo1" name="cityTo1"
                                                                   required></label><br>
                                        <div id="divCityTo2"></div>
                                        <div id="divCityTo3"></div>
                                        <div id="divCityTo4"></div>
                                        <div id="divCityTo5"></div>
                                        <div class="row justify-content-center mt-1">
                                            <div class="col-md-auto">
                                                <p><a class="btn btn-success" id="textCityTo">Додати місто &raquo;</a>
                                                </p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
    <div class="row justify-content-center">
        <div class="col-md-auto">
            <p><input type="submit" form="form" class="btn btn-success" value="Розрахувати відстань"></p>
        </div>
    </div>
    <c:if test="${sessionScope.infoTableShort!=null}">
        <div class="row justify-content-center">
            <div class="col-6">
                <form method="post" action="/sortingTable">
                    <div class="row justify-content-center">
                        <div class="col-md-auto">
                            <h5 class="text-center mb-0 mt-3">Сортування</h5><br>
                            <div class="rounded bg-light p-3 mb-3">
                                <div class="input-group-prepend">
                                    <div class="input-group-text">
                                        <label><input type="radio" name="sort" value="sortByMinPriceTable"
                                        <c:if test="${sessionScope.sort == 'sortByMinPriceTable'}"> checked
                                        </c:if>>По ціні↑</label><br>
                                    </div>
                                    <div class="input-group-text">
                                        <label><input type="radio" name="sort" value="sortByMaxPriceTable"
                                        <c:if test="${sessionScope.sort == 'sortByMaxPriceTable'}"> checked
                                        </c:if>>По ціні↓</label><br>
                                    </div>
                                    <div class="input-group-text">
                                        <label><input type="radio" name="sort" value="sortByMinCityFromTable"
                                        <c:if test="${sessionScope.sort == 'sortByMinCityFromTable'}"> checked
                                        </c:if>>По Місту відправлення(А-Я)</label><br>
                                    </div>
                                    <div class="input-group-text">
                                        <label><input type="radio" name="sort" value="sortByMaxCityFromTable"
                                        <c:if test="${sessionScope.sort == 'sortByMaxCityFromTable'}"> checked
                                        </c:if>>По Місту відправлення(Я-А)</label><br>
                                    </div>
                                    <div class="input-group-text">
                                        <label><input type="radio" name="sort" value="sortByMinCityToTable"
                                        <c:if test="${sessionScope.sort == 'sortByMinCityToTable'}"> checked</c:if>>
                                            По Місту прибуття(А-Я)</label><br>
                                    </div>
                                    <div class="input-group-text">
                                        <label><input type="radio" name="sort" value="sortByMaxCityToTable"
                                        <c:if test="${sessionScope.sort == 'sortByMaxCityToTable'}"> checked
                                        </c:if>>По Місту прибуття(Я-А)</label><br>
                                    </div>
                                </div>
                            </div>
                            <div class="row justify-content-center">
                                <div class="col-md-auto">
                                    <p><input type="submit" class="btn btn-success" value="Сортувати"></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <div class="row tableParent">
            <table class="table table-borderless">
                <tr>
                    <th class="bg-light">Місто Відправлення</th>
                    <th class="bg-light">Місто отримання</th>
                    <th class="bg-light">Відстань</th>
                    <th class="bg-light">Середня ціна</th>
                    <th class="bg-light">Мапа</th>
                </tr>
                <c:forEach var="table" items="${sessionScope.infoTableShort}">
                    <tr>
                        <td class="bg-light">${table.cityFrom}</td>
                        <td class="bg-light">${table.cityTo}</td>
                        <td class="bg-light">${table.distance}</td>
                        <td class="bg-light">${table.price}</td>
                        <td class="bg-light"><a class="btn btn-outline-primary"
                                                href="<c:url value="/printMap?id=${table.id}"/>">Мапа</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="row justify-content-center">
            <div class="col-md-auto">
                <c:if test="${sessionScope.list!=null}">
                    <nav>
                        <ul class="pagination">
                            <li class="page-item">
                                <a href="/changePage?id=${sessionScope.pageNumber-1}" class="page-link">Попередня</a>
                            </li>
                            <c:forEach var="n" items="${sessionScope.list}">
                                <li class="page-item <c:if test="${sessionScope.pageNumber==n}">active</c:if>">
                                    <a href="/changePage?id=${n}" class="page-link">${n}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item">
                                <a href="/changePage?id=${sessionScope.pageNumber+1}" class="page-link">Наступна</a>
                            </li>
                        </ul>
                    </nav>
                </c:if>
            </div>
        </div>
    </c:if>
    <c:if test="${sessionScope.table!=null}">
        <div class="row justify-content-center mb-3">
                <iframe
                        width="600"
                        height="450"
                        style="border:0"
                        loading="lazy"
                        allowfullscreen
                        referrerpolicy="no-referrer-when-downgrade"
                        src="https://www.google.com/maps/embed/v1/directions?key=AIzaSyDZ_4ASyzLdt1d16-mekZg5W4X24P0zIR4
                        &origin=${sessionScope.table.cityFrom}&destination=${sessionScope.table.cityTo}
                        &mode=driving&language=uk&avoid=tolls|highways">
                </iframe><br>
        </div>
    </c:if>
</div>
<script src="/script/script.js"></script>
<%--<script src="/script/editScript.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDZ_4ASyzLdt1d16-mekZg5W4X24P0zIR4&language=uk&libraries=places&callback=initAutocomplete"
        async defer></script>--%>
</body>
</html>



