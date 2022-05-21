<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 06.05.2022
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Order</title>
</head>
<body>
${sessionScope.user.login} ${sessionScope.money}<br>
<a href="/index.jsp">Головна</a><br>
<h1>Оформлення замовлення</h1>
<form method="post" action="/createOrder">
 <label>Інформація про товар</label><br>
    <textarea name="info"></textarea><br>
    <label><input type="text" id="autocomplete" name="cityFrom" required></label>Місто відправлення<br>
    <label><input type="text" id="autocompleteSec" name="cityTo"  required></label>Місто прибуття<br>
    <label>Адресса</label><br>
    <textarea name="address"></textarea><br>
    <label><input type="number" name="weight" min="1" autofocus required></label>Вага kg<br>
    <label><input type="number" name="height" min="1" autofocus required></label>Висота м<br>
    <label><input type="number" name="length" min="1" autofocus required></label>Довжина м<br>
    <label><input type="number" name="width"  min="1" autofocus required></label>Ширина м<br>
    <input type="submit" value="Створити заявку" name="Ok"><br>
</form>
</body>
</html>
