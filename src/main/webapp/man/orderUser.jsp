<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 09.05.2022
  Time: 19:06
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
<text>Дата доставки вантажу   </text>${sessionScope.infoOrder.dateOfArrival}<br>
<text>Статус оплати   </text>${sessionScope.infoOrder.paymentStatus}<br>
<text>Замовник   </text>${sessionScope.infoOrder.userLogin}<br>
<c:if test = "${sessionScope.infoOrder.paymentStatus == 'На розгляді'}">
    <abbr title="Змінити статус оплати" >
        <a href="/confirmOrder?id=${sessionScope.infoOrder.id}">Підтвердити замовлення</a><br>
    </abbr>
</c:if>

<a href="/man/orderList.jsp">Повернутись до замовлень</a>
</body>
</html>
