<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 08.05.2022
  Time: 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Замовлення</title>
</head>
<body>
${sessionScope.user.login} <br>
<a href="/logout.jsp">Вийти</a>
<a href="/editUser.jsp">Редагування профілю</a><br>

<form method="post" action="/filtrationOrder">
    <text>Ціна</text><br>
    <label><input type="number" name="minPrice" min="0" value="${sessionScope.minPrice}" ></label>Ціна от<br>
    <label><input type="number" name="maxPrice" min="0" value="${sessionScope.maxPrice}"></label>Ціна до<br>
    <text>Статус</text><br>
    <input type="checkbox" name="paymentStatus" value="На розгляді" <c:if test="${sessionScope.status1 != null}"> checked</c:if> >На розгляді<br>
    <input type="checkbox" name="paymentStatus" value="Очікує оплату" <c:if test="${sessionScope.status2 != null}"> checked</c:if> >Очікує оплату<br>
    <input type="checkbox" name="paymentStatus" value="Оплачено" <c:if test="${sessionScope.status3 != null}"> checked</c:if> >Оплачено<br>
    <text>Дата відправки</text><br>
    <label>от<input type="date" name="minDateCreate" value="${sessionScope.minDateCreate}"></label>
    <label>до<input type="date" name="maxDateCreate" value="${sessionScope.maxDateCreate}"><br></label>
    <text>Дата доставки</text><br>
    <label>от<input type="date" name="minDateOfArrival" value="${sessionScope.minDateOfArrival}"></label>
    <label>до<input type="date" name="maxDateOfArrival" value="${sessionScope.maxDateOfArrival}"><br></label>
    <text>Міста відправки</text><br>
    <select  name="cityFrom[]" multiple>
        <option>Вінниця</option>
        <option>Дніпро</option>
        <option>Донецьк</option>
        <option>Житомир</option>
        <option>Запоріжжя</option>
        <option>Івано-Франківськ</option>
        <option>Київ</option>
        <option>Кропивницький</option>
        <option>Луганськ</option>
        <option>Луцьк</option>
        <option>Львів</option>
        <option>Миколаїв</option>
        <option>Одеса</option>
        <option>Полтава</option>
        <option>Рівне</option>
        <option>Сімферопіль</option>
        <option>Суми</option>
        <option>Тернопіль</option>
        <option>Ужгород</option>
        <option>Харків</option>
        <option>Херсон</option>
        <option>Хмельницький</option>
        <option>Черкаси</option>
        <option>Чернівці</option>
        <option>Чернігів</option>
    </select>
    <c:forEach var="city" items="${sessionScope.cityFrom}">
        ${city}<text> </text>
    </c:forEach>
    <br>
    <text>Міста доставки</text><br>
    <select  name="cityTo[]" multiple>
        <option>Вінниця</option>
        <option>Дніпро</option>
        <option>Донецьк</option>
        <option>Житомир</option>
        <option>Запоріжжя</option>
        <option>Івано-Франківськ</option>
        <option>Київ</option>
        <option>Кропивницький</option>
        <option>Луганськ</option>
        <option>Луцьк</option>
        <option>Львів</option>
        <option>Миколаїв</option>
        <option>Одеса</option>
        <option>Полтава</option>
        <option>Рівне</option>
        <option>Сімферопіль</option>
        <option>Суми</option>
        <option>Тернопіль</option>
        <option>Ужгород</option>
        <option>Харків</option>
        <option>Херсон</option>
        <option>Хмельницький</option>
        <option>Черкаси</option>
        <option>Чернівці</option>
        <option>Чернігів</option>
    </select>
    <c:forEach var="city" items="${sessionScope.cityTo}">
        ${city}<text> </text>
    </c:forEach><br>
    <label><input type="radio" name="sort" value="sortByMinPrice"  <c:if test="${sessionScope.sort == 'sortByMinPrice'}"> checked</c:if>>По ціні↑</label>
    <label><input type="radio" name="sort" value="sortByMaxPrice" <c:if test="${sessionScope.sort == 'sortByMaxPrice'}"> checked</c:if>>По ціні↓</label><br>
    <label><input type="radio" name="sort" value="sortByMinDateCreate" <c:if test="${sessionScope.sort == 'sortByMinDateCreate'}"> checked</c:if>>По Даті створення↑</label>
    <label><input type="radio" name="sort" value="sortByMaxDateCreate" <c:if test="${sessionScope.sort == 'sortByMaxDateCreate'}"> checked</c:if>>По Даті створення↓</label><br>
    <label><input type="radio" name="sort" value="sortByMinDateOfArrival"<c:if test="${sessionScope.sort == 'sortByMinDateOfArrival'}"> checked</c:if>>По Даті прибуття↑</label>
    <label><input type="radio" name="sort" value="sortByMaxDateOfArrival"<c:if test="${sessionScope.sort == 'sortByMaxDateOfArrival'}"> checked</c:if>>По Даті прибуття↓</label><br>
    <label><input type="radio" name="sort" value="sortByMinCityFrom"  <c:if test="${sessionScope.sort == 'sortByMinCityFrom'}"> checked</c:if>>По Місту відправлення(А-Я)</label>
    <label><input type="radio" name="sort" value="sortByMaxCityFrom"<c:if test="${sessionScope.sort == 'sortByMaxCityFrom'}"> checked</c:if>>По Місту відправлення(Я-А)</label><br>
    <label><input type="radio" name="sort" value="sortByMinCityTo"<c:if test="${sessionScope.sort == 'sortByMinCityTo'}"> checked</c:if>>По Місту прибуття(А-Я)</label>
    <label><input type="radio" name="sort" value="sortByMaxCityTo"<c:if test="${sessionScope.sort == 'sortByMaxCityTo'}"> checked</c:if>>По Місту прибуття(Я-А)</label><br>
    <input type="submit" value="Фільтрувати">
</form>
<form method="post" action="/resetOrder">
    <input type="submit" value="Скинути фільтри">
</form>

<abbr title="Пдф файлік" ><a href="/pdfOrderList" target="_blank">Звіт по доставкам</a></abbr>
<table border="1">
    <caption>Таблиця з замовленнями </caption>
    <tr>
        <th>Номер замовлення</th>
        <th>Опис</th>
        <th>Ціна</th>
        <th>Статус</th>
        <th>Дата Створення</th>
        <th>Дата прибуття</th>
        <th>Місто відправлення</th>
        <th>Місто прибуття</th>
        <th>Замовник</th>
    </tr>
    <c:forEach var="order" items="${sessionScope.orderList}">
        <tr>
            <td>${order.id}</td>
            <td>${order.description}</td>
            <td>${order.price}</td>
            <td>${order.paymentStatus}</td>
            <td>${order.dateCreate}</td>
            <td>${order.dateOfArrival}</td>
            <td>${order.cityFrom}</td>
            <td>${order.cityTo}</td>
            <td>${order.userLogin}</td>
            <td>
                <abbr title="детальніше про замовлення" >
                    <a href="/info?id=${order.id}">Інформація</a>
                </abbr>
            </td>
        </tr>

    </c:forEach>

</table>
</body>
</html>
