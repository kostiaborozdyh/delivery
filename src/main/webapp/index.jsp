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
${sessionScope.user.login} ${sessionScope.money}
<u:role user="${sessionScope.role}"></u:role>
<m:today/>
<h1><fmt:message key="delivery"/></h1>
Введіть міста для виведення інформації про доставку між ними<br>
<form method="post" action="/showInfo" id="kok">
    <label>Міста відправлення</label><br>
    <label><input type="text" id="aCityFrom1" name="cityFrom1" required></label>
   <div id="divk"></div>
    <c:if test="${sessionScope.numberCityFrom>1}">
        <label><input type="text" id="autocomplete2" name="cityFrom2" required></label>
    </c:if>
    <c:if test="${sessionScope.numberCityFrom>2}">
        <label><input type="text" id="autocomplete3" name="cityFrom3" required></label><br>
    </c:if>
    <c:if test="${sessionScope.numberCityFrom>3}">
        <label><input type="text" id="autocomplete4" name="cityFrom4" required></label>
    </c:if>
    <c:if test="${sessionScope.numberCityFrom>4}">
        <label><input type="text" id="autocomplete5" name="cityFrom5" required></label><br>
    </c:if>
    <c:if test="${sessionScope.numberCityFrom!=5}">
        <%--  <input type="submit" value="input" > --%>
        <text id="textEdit">AddCity</text>
      <a href="/addCity?id=1">Додати місто</a><br>
  </c:if>

  <label>Міста прибуття</label><br>
  <label><input type="text" id="aCityTo1" name="cityTo"  required></label>
  <c:if test="${sessionScope.numberCityTo>1}">
      <label><input type="text" id="autocomplete7" name="cityTo2" required></label>
  </c:if>
  <c:if test="${sessionScope.numberCityTo>2}">
      <label><input type="text" id="autocomplete8" name="cityTo2" required></label><br>
  </c:if>
  <c:if test="${sessionScope.numberCityTo>3}">
      <label><input type="text" id="autocomplete9" name="cityTo2" required></label>
  </c:if>
  <c:if test="${sessionScope.numberCityTo>4}">
      <label><input type="text" id="autocomplete10" name="cityTo2" required></label><br>
  </c:if>
  <c:if test="${sessionScope.numberCityTo!=5}"><a href="/addCity?id=2">Додати місто</a><br></c:if>
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
  <form method="post" action="/sortingTable">
      <text>Сортування</text><br>
      <label><input type="radio" name="sort" value="sortByMinPriceTable"  <c:if test="${sessionScope.sort == 'sortByMinPriceTable'}"> checked</c:if>>По ціні↑</label>
      <label><input type="radio" name="sort" value="sortByMaxPriceTable" <c:if test="${sessionScope.sort == 'sortByMaxPriceTable'}"> checked</c:if>>По ціні↓</label><br>
      <label><input type="radio" name="sort" value="sortByMinCityFromTable"  <c:if test="${sessionScope.sort == 'sortByMinCityFromTable'}"> checked</c:if>>По Місту відправлення(А-Я)</label>
      <label><input type="radio" name="sort" value="sortByMaxCityFromTable"<c:if test="${sessionScope.sort == 'sortByMaxCityFromTable'}"> checked</c:if>>По Місту відправлення(Я-А)</label><br>
      <label><input type="radio" name="sort" value="sortByMinCityToTable"<c:if test="${sessionScope.sort == 'sortByMinCityToTable'}"> checked</c:if>>По Місту прибуття(А-Я)</label>
      <label><input type="radio" name="sort" value="sortByMaxCityToTable"<c:if test="${sessionScope.sort == 'sortByMaxCityToTable'}"> checked</c:if>>По Місту прибуття(Я-А)</label><br>
      <input type="submit" value="Сортувати">
  </form>
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
                  <a href="<c:url value="/printMap?id=${table.id}"/>">Мапа</a>
              </abbr></td>
          </tr>
      </c:forEach>
  </table>
</c:if>
<script src="script.js"> </script>
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