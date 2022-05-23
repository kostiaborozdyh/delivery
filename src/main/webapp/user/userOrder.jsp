<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 23.05.2022
  Time: 10:34
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
<h1>Інформація про замовлення</h1><br>
<text>Номер замовлення   </text>${sessionScope.infoOrder.id}<br>
<text>Опис   </text>${sessionScope.infoOrder.description}<br>
<text>Вага   </text>${sessionScope.infoOrder.weight}<br>
<text>Об'єм   </text>${sessionScope.infoOrder.volume}<br>
<text>Ціна   </text>${sessionScope.infoOrder.price}<br>
<text>Місто відправки   </text>${sessionScope.infoOrder.cityFrom}<br>
<text>Місто доставки   </text>${sessionScope.infoOrder.cityTo}<br>
<text>Адреса   </text>${sessionScope.infoOrder.address}<br>
<text>Дата створення заявки   </text>${sessionScope.infoOrder.dateCreate}<br>
<c:if test="${sessionScope.infoOrder.dateOfSending!=null}">
    <text>Дата відправлення   </text>${sessionScope.infoOrder.dateOfSending}<br>
</c:if>
<text>Дата доставки вантажу   </text>${sessionScope.infoOrder.dateOfArrival}<br>
<text>Статус оплати   </text>${sessionScope.infoOrder.paymentStatus}<br>
<text>Місцезнаходження  </text>${sessionScope.infoOrder.locationStatus}<br>
    <c:if test = "${(sessionScope.money<sessionScope.infoOrder.price) && ((sessionScope.infoOrder.paymentStatus == 'Очікує оплату') ||(sessionScope.infoOrder.paymentStatus == 'На розгляді')) }">
        <abbr title="Недостатньо грошей, потрібно поповнити рахунок" ><a href="" onclick="return false">Оплатить</a></abbr>
    </c:if>
    <c:if test = "${(sessionScope.money>=sessionScope.infoOrder.price) && (sessionScope.infoOrder.paymentStatus == 'На розгляді')}">
        <abbr title="Ваша заявка на розгляді" ><a href="" onclick="return false">Оплатить</a></abbr>
    </c:if>

    <c:if test = "${(sessionScope.money>=sessionScope.infoOrder.price) && (sessionScope.infoOrder.paymentStatus == 'Очікує оплату') }">
        <abbr title="Оплатити зявку" >
            <a href="/pay?id=${sessionScope.infoOrder.id}&value=${sessionScope.infoOrder.price}&money=${sessionScope.money}">Оплатить</a>
        </abbr>
    </c:if>
<text><abbr title="Місцерозташування" ><a href="" onclick="return false">Місцерозташування посилки(Бетта)</a></abbr></text>
<a href="/resetOrder">Повернутись до замовлень</a><br>
<a href="/info?id=${sessionScope.infoOrder.id}">Оновити</a><br>
</body>
</html>
