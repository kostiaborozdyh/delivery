<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="m" uri="/WEB-INF/custom.tld"  %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>

<!DOCTYPE html>
<html lang="${sessionScope.lang}" class="h-100">
<head>
    <title>delivery</title>
</head>
<body>
${sessionScope.user.login} ${sessionScope.money}<br>
<u:role user="${sessionScope.role}"></u:role>
<m:today/>
<h1><fmt:message key="delivery"/></h1>
Введіть міста для виведення інформації про доставку між ними<br>
<form method="post" action="/showInfo">
    <label><input type="text" id="autocomplete" name="cityFrom" required></label>Місто відправлення<br>
    <label><input type="text" id="autocompleteSec" name="cityTo"  required></label>Місто прибуття<br>
    <input type="submit" value="Розрахувати відстань">
</form>
<br/>
<c:if test="${sessionScope.table!=null}">
    <iframe
            width="600"
            height="450"
            style="border:0"
            loading="lazy"
            allowfullscreen
            referrerpolicy="no-referrer-when-downgrade"
            src="https://www.google.com/maps/embed/v1/directions?key=AIzaSyDZ_4ASyzLdt1d16-mekZg5W4X24P0zIR4&origin=${sessionScope.table.cityFrom}
  &destination=${sessionScope.table.cityTo}
  &mode=driving&language=uk&avoid=tolls|highways">
    </iframe><br>
</c:if>

<c:if test="${sessionScope.infoTable!=null}">
    <table border="1">
        <caption>Інформація про доставку між містами: </caption>
        <tr>
            <th>Місто Відправлення</th>
            <th>Місто отримання</th>
            <th>Відстань</th>
            <th>Середня ціна</th>
            <th>Мапа</th>
        </tr>
        <c:forEach var="table" items="${sessionScope.infoTable}">
            <tr>
                <td>${table.cityFrom}</td>
                <td>${table.cityTo}</td>
                <td>${table.distance}</td>
                <td>${table.price}</td>
                <td><abbr title="Мапа" >
                    <a href="/printMap?id=${table.id}">Мапа</a>
                </abbr></td>
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