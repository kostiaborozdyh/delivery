<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 03.05.2022
  Time: 17:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Розрахунок вартості багажа</title>
</head>
<body>
${sessionScope.user.login} ${sessionScope.money}<br>
<a href="/index.jsp">Головна</a><br>
<H1>Розрахунок вартості доставки</H1>
<form method="post" action="/calculateBag">
    <label><input type="text" id="autocomplete" name="cityFrom" required></label>Місто відправлення<br>
    <label><input type="text" id="autocompleteSec" name="cityTo"  required></label>Місто прибуття<br>
    <label><input type="number" name="weight" min="1"  required></label>Вага kg<br>
    <label><input type="number" name="height" min="1"  required></label>Висота м<br>
    <label><input type="number" name="length" min="1"  required></label>Довжина м<br>
    <label><input type="number" name="width"  min="1"  required></label>Ширина м<br>
    <input type="submit" value="Обрахувати ціну вантажу" name="Ok"><br>
</form>
<c:if test="${sessionScope.calculateTable!=null}">
<h1>Доставка з міста "${sessionScope.calculateTable.cityFrom}"<br>
    в місто "${sessionScope.calculateTable.cityTo}"  <br>
    з вагою  ${sessionScope.calculateTable.weight} <br>
    та об'ємом ${sessionScope.calculateTable.volume}  <br>
    коштує ${sessionScope.calculateTable.price} <br> </h1>

    <iframe
            width="600"
            height="450"
            style="border:0"
            loading="lazy"
            allowfullscreen
            referrerpolicy="no-referrer-when-downgrade"
            src="https://www.google.com/maps/embed/v1/directions?key=AIzaSyDZ_4ASyzLdt1d16-mekZg5W4X24P0zIR4&origin=${sessionScope.calculateTable.cityFrom}
  &destination=${sessionScope.calculateTable.cityTo}
  &mode=driving&language=uk&avoid=tolls|highways">
    </iframe><br>
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