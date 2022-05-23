<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 23.05.2022
  Time: 17:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Прийняття посилок</title>
    <h1>Прийняття посилок</h1>
</head>
<body>
<a href="/employee/ordersTable.jsp">Головна</a><br>
<c:if test="${sessionScope.orderList!=null}">
    <table border="1">
        <caption>Товари які потрібно поставити на облік</caption>
        <tr>
            <th>Номер товару</th>
            <th>Опис</th>
            <th>Місто Відправлення</th>
            <th>Місто отримання</th>
            <th>Статус оплати</th>
            <th>Облік</th>
        </tr>
        <c:forEach var="order" items="${sessionScope.orderList}">
            <tr>
                <td>${order.id}</td>
                <td>${order.description}</td>
                <td>${order.cityFrom}</td>
                <td>${order.cityTo}</td>
                <td>${order.paymentStatus}</td>
                <td>
                    <form method="post" action="/putOnRecord?id=${order.id}">
                        <input type="submit" value="Поставити на облік">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
