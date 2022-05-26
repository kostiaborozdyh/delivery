<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 25.05.2022
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Відгуки</title>
    <h1>Відгуки</h1>
</head>
<body>
<a href="/index.jsp">Головна</a><br>
<c:if test="${sessionScope.role=='user'}">
<form method="post" action="/addReview">
    <input type="text" name="response" placeholder="Веддіть ваш відгук">
    <input type="submit" value="Добавити відгук">
</form>
</c:if>
<table border="1">
    <tr>
        <th>Ім'я</th>
        <th>Відгук</th>
    </tr>
<c:forEach var="review" items="${sessionScope.reviews}">
    <tr>
        <td>${review.firstName}</td>
        <td>${review.response}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
