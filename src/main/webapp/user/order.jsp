<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 07.05.2022
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Мої замовлення</title>
</head>
<body>
${sessionScope.user.login} ${sessionScope.money}<br>
<a href="/index.jsp">Головна</a><br>
<form method="post" action="/filtrationOrder">
    <text>Ціна</text><br>
    <label><input type="number" name="minPrice" min="0" value="${sessionScope.minPrice}" ></label>Ціна от<br>
    <label><input type="number" name="maxPrice" min="0" value="${sessionScope.maxPrice}"></label>Ціна до<br>
    <text>Статус</text><br>
    <label> <input type="checkbox" name="paymentStatus" value="На розгляді" <c:if test="${sessionScope.status1 != null}"> checked</c:if> ></label>На розгляді<br>
    <label> <input type="checkbox" name="paymentStatus" value="Очікує оплату" <c:if test="${sessionScope.status2 != null}"> checked</c:if> ></label>Очікує оплату<br>
    <label> <input type="checkbox" name="paymentStatus" value="Оплачено" <c:if test="${sessionScope.status3 != null}"> checked</c:if> ></label>Оплачено<br>
    <text>Дата відправки</text><br>
    <label>от<input type="date" name="minDateCreate" value="${sessionScope.minDateCreate}"></label>
    <label>до<input type="date" name="maxDateCreate" value="${sessionScope.maxDateCreate}"><br></label>
    <text>Дата доставки</text><br>
    <label>от<input type="date" name="minDateOfArrival" value="${sessionScope.minDateOfArrival}"></label>
    <label>до<input type="date" name="maxDateOfArrival" value="${sessionScope.maxDateOfArrival}"><br></label>
    <text>Міста відправки</text><br>
    <select  name="cityFrom[]" multiple>
        <c:forEach var="order" items="${sessionScope.cityFromSet}">
            <option>${order}</option>
        </c:forEach>
    </select>
    <c:forEach var="city" items="${sessionScope.cityFrom}">
        ${city}<text> </text>
    </c:forEach>
    <br>
    <text>Міста доставки</text><br>
    <select  name="cityTo[]" multiple>
        <c:forEach var="order" items="${sessionScope.cityToSet}">
            <option>${order}</option>
        </c:forEach>
    </select>
    <c:forEach var="city" items="${sessionScope.cityTo}">
        ${city}<text> </text>
    </c:forEach><br>
<text>Сортування</text><br>
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

<abbr title="Пдф файлік" ><a href="/pdfOrder" target="_blank">Звіт по доставкам</a></abbr>


<table border="1">
    <caption>Таблиця з замовленнями </caption>
    <tr>
        <th>Опис</th>
        <th>Вага</th>
        <th>Об'єм</th>
        <th>Ціна</th>
        <th>Місто відправлення</th>
        <th>Місто прибуття</th>
        <th>Адреса</th>
        <th>Дата Створення</th>
        <th>Дата прибуття</th>
        <th>Статус</th>
    </tr>
    <c:forEach var="order" items="${sessionScope.orders}">
        <tr>

            <td>${order.description}</td>
            <td>${order.weight}</td>
            <td>${order.volume}</td>
            <td>${order.price}</td>
            <td>${order.cityFrom}</td>
            <td>${order.cityTo}</td>
            <td>${order.address}</td>
            <td>${order.dateCreate}</td>
            <td>${order.dateOfArrival}</td>
            <td>${order.paymentStatus}</td>
            <td>
                <c:if test = "${(sessionScope.money<order.price) && ((order.paymentStatus == 'Очікує оплату') ||(order.paymentStatus == 'На розгляді')) }">
                    <abbr title="Недостатньо грошей, потрібно поповнити рахунок" ><a href="*" onclick="return false">Оплатить</a></abbr>
                </c:if>
                <c:if test = "${(sessionScope.money>=order.price) && (order.paymentStatus == 'На розгляді')}">
                    <abbr title="Ваша заявка на розгляді" ><a href="*" onclick="return false">Оплатить</a></abbr>
                </c:if>

                <c:if test = "${(sessionScope.money>=order.price) && (order.paymentStatus == 'Очікує оплату') }">
                    <abbr title="Оплатити зявку" >
                        <a href="/pay?id=${order.id}&value=${order.price}&money=${sessionScope.money}">Оплатить</a>
                    </abbr>
                </c:if>
            </td>
            <td>
                <abbr title="Відправити інформацію на пошту" >
                    <a href="/pdfUserOrder?idOrder=${order.id}" target="_blank">Пдф Файл</a>
                </abbr>
            </td>
            <td>
                <abbr title="Відправити інформацію на пошту" >
                    <a href="/sendEmailOrder?idOrder=${order.id}">Відправити на пошту</a>
                </abbr>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
