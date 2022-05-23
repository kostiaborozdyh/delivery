<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 22.05.2022
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Склад товару</title>
    <h1>Склад товару</h1>
</head>
<body>
${sessionScope.login}<br>
<a href="logout.jsp">Вийти</a>
<a href="/employee/acceptOrder.jsp">Прийняти посилки</a>
<a href="/editUser.jsp">Редагувати профіль</a><br>
<c:if test="${sessionScope.city!=null}">
        <h3>Поточне місто:${sessionScope.city}</h3><br>
    <form method="post" action="/changeCity">
        <input type="submit" value="Змінити місто">
    </form>
    <form method="post" action="/chooseCity">
        <input type="submit" value="Оновити">
    </form>
</c:if>
<c:if test="${sessionScope.city==null}">
    <form method="post" action="/chooseCity">
        <label><input type="text" id="autocomplete" name="city" required></label>Місто в якому працюєш<br>
        <input type="submit" value="Вибрати">
    </form>
</c:if>


<c:if test="${sessionScope.ordersTable!=null}">
    <table border="1">
        <caption>Товари які зараз знаходяться на складі</caption>
        <tr>
            <th>Номер товару</th>
            <th>Опис</th>
            <th>Місто Відправлення</th>
            <th>Місто отримання</th>
            <th>Статус оплати</th>
            <th>Віддати</th>
        </tr>
        <c:forEach var="order" items="${sessionScope.ordersTable}">
            <tr>
                <td>${order.id}</td>
                <td>${order.description}</td>
                <td>${order.cityFrom}</td>
                <td>${order.cityTo}</td>
                <td>${order.paymentStatus}</td>
                <td>
                    <c:if test="${order.paymentStatus == 'Очікує оплату'}">
                        <form>
                            <input type="submit" value="Віддати">
                        </form>
                    </c:if>
                    <c:if test="${order.paymentStatus == 'Оплачено'}">
                        <form method="post" action="/giveOrder?id=${order.id}">
                            <input type="submit" value="Віддати">
                        </form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
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