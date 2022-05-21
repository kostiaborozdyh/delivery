<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="user" required="true" rtexprvalue="true" type="java.lang.String" %>
<c:choose>
  <c:when test="${user.equals('user')}">
      <a href="logout.jsp">Вийти</a>
      <a href="calculate.jsp">Розрахування вартості багажа</a>
      <a href="user/createOrder.jsp">Створити заявку</a>
      <a href="user/order.jsp">Заявки</a>
      <a href="user/refill.jsp">Поповнити рахунок</a>
      <a href="editUser.jsp">Редагувати профіль</a>
  </c:when>
    <c:when test="${user.equals('manager')}">
        <a href="logout.jsp">Вийти</a>
        <a href="man/orderList.jsp">Заявки</a>
        <a href="editUser.jsp">Редагувати профіль</a>
    </c:when>
    <c:otherwise>
        <a href="login.jsp">Вхід</a>
        <a href="registration.jsp">Реєстрація</a>
        <a href="calculate.jsp">Розрахування вартості багажа</a>
    </c:otherwise>
</c:choose>