<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 06.05.2022
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="text"/>
<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <title><fmt:message key="makingOrder"/></title>
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
   <u:width width="width"/>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-2">
   <div class="container-fluid">
      <a href="#" class="navbar-brand">Delivery Holder</a>
      <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
         <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarCollapse">
         <div class="navbar-nav">
            <a href="/index.jsp" class="nav-item nav-link "><fmt:message key="home"/></a>
            <a href="/aboutUs.jsp" class="nav-item nav-link"><fmt:message key="aboutUs"/></a>
            <a href="/info.jsp" class="nav-item nav-link"><fmt:message key="deliveryDirections"/></a>
            <a href="/calculate.jsp" class="nav-item nav-link"><fmt:message key="calculateCost"/></a>
            <a href="/reviews.jsp" class="nav-item nav-link"><fmt:message key="reviews"/></a>
            <a href="/user/createOrder.jsp" class="nav-item nav-link active"><fmt:message key="makingOrder"/></a>
            <a href="/user/order.jsp" class="nav-item nav-link"><fmt:message key="myOrders"/></a>
         </div>
      </div>
      <div class="nav navbar-nav navbar-right">
         <a href="#" class="nav-item nav-link disabled">${sessionScope.money}$</a>
      </div>
      <div class="nav navbar-nav navbar-right">
         <div class="nav-item dropdown">
            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.user.login}</a>
            <div class="dropdown-menu ">
               <a href="/user/refill.jsp" class="dropdown-item"><fmt:message key="refill"/></a>
               <a href="/editUser.jsp" class="dropdown-item"><fmt:message key="editing"/></a>
               <a href="/lout" class="dropdown-item"><fmt:message key="signOut"/></a>
            </div>
         </div>
      </div>
      <div class="nav navbar-nav navbar-right">
         <div class="nav-item dropdown">
            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">${sessionScope.firstLang}</a>
            <div class="dropdown-menu">
               <a href="/changeLanguage?id=21" class="dropdown-item">${sessionScope.secondLang}</a>
            </div>
         </div>
      </div>
   </div>
</nav>
<div class="container">
   <div class="row justify-content-center mt-3 mb-3">
      <div class="col-md-auto">
         <h4><fmt:message key="makingOrder"/></h4>
      </div>
   </div>
   <div class="row justify-content-center">
      <div class="col-8 ">
         <div class="row">
            <div class="col-6">
               <form method="post" action="/calculateBag" id="form">
                  <div class="row justify-content-center">
                     <div class="col-md-auto">
                        <div class="rounded bg-light p-4 mb-3">
                           <div class="input-group-prepend">
                              <div class="form-group mb-2">
                                 <label class="control-label col-xs-4 mb-1"><fmt:message key="productInformation"/><text style = "color:red;">*</text></label>
                                 <div class="col-xs-8">
                                    <textarea name="info" required="required" class="form-control">${sessionScope.orderInfo}</textarea>
                                 </div>
                              </div>
                              <div class="form-group mb-2">
                                 <label class="control-label col-xs-4 mb-1"><fmt:message key="cityOfDispatch"/><text style = "color:red;">*</text></label>
                                 <div class="col-xs-8">
                                    <input type="text" class="form-control google" name="cityFrom" required="required" value="${sessionScope.newOrder.cityFrom}">
                                 </div>
                              </div>
                              <div class="form-group mb-2">
                                 <label class="control-label col-xs-4 mb-1"><fmt:message key="cityOfDelivery"/><text style = "color:red;">*</text></label>
                                 <div class="col-xs-8">
                                    <input type="text" class="form-control google" name="cityTo" required="required" value="${sessionScope.newOrder.cityTo}">
                                 </div>
                              </div>
                              <div class="form-group mb-2">
                                 <label class="control-label col-xs-4 mb-1"><fmt:message key="deliveryCityAddress"/><text style = "color:red;">*</text></label>
                                 <div class="col-xs-8">
                                    <input type="text" class="form-control" name="address" required="required" value="${sessionScope.orderAddress}">
                                 </div>
                              </div>
                              <div class="form-group mb-2">
                                 <label class="control-label col-xs-4 mb-1"><fmt:message key="parcelWeight"/><text style = "color:red;">*</text></label>
                                 <div class="col-xs-8">
                                    <input type="number" min="1" class="form-control" name="weight" required="required" value="${sessionScope.newOrder.weight}">
                                 </div>
                              </div>
                              <div class="form-group mb-2">
                                 <label class="control-label col-xs-4 mb-1"><fmt:message key="parcelHeight"/><text style = "color:red;">*</text></label>
                                 <div class="col-xs-8">
                                    <input type="number" min="1" class="form-control" name="height" required="required" value="${sessionScope.heightParcel}">
                                 </div>
                              </div>
                              <div class="form-group mb-2">
                                 <label class="control-label col-xs-4 mb-1"><fmt:message key="parcelLength"/><text style = "color:red;">*</text></label>
                                 <div class="col-xs-8">
                                    <input type="number" min="1" class="form-control" name="length" required="required" value="${sessionScope.lengthParcel}">
                                 </div>
                              </div>
                              <div class="form-group mb-2">
                                 <label class="control-label col-xs-4 mb-1"><fmt:message key="parcelWidth"/><text style = "color:red;">*</text></label>
                                 <div class="col-xs-8">
                                    <input type="number" min="1" class="form-control" name="width" required="required" value="${sessionScope.widthParcel}">
                                 </div>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </form>
            </div>
            <div class="col-6">
               <div class="row justify-content-center">
                  <div class="col-md-auto">
                     <div class="row justify-content-center">
                        <div class="col-md-auto">
                           <h5><fmt:message key="deliveryInformation"/></h5>
                           <p><fmt:message key="productInformation"/>: ${sessionScope.orderInfo}<br>
                              <fmt:message key="departurePoint"/> ${sessionScope.newOrder.cityFrom}<br>
                              <fmt:message key="destination"/> ${sessionScope.newOrder.cityTo} <br>
                              <fmt:message key="deliveryAddress"/> ${sessionScope.orderAddress}<br>
                              <fmt:message key="cargoWeight"/> <c:if test="${sessionScope.newOrder!=null}">
                                 ${sessionScope.newOrder.weight} кг
                              </c:if><br>
                              <fmt:message key="cargoVolume"/> <c:if test="${sessionScope.newOrder!=null}">
                                 ${sessionScope.newOrder.volume} м<sup>3</sup>
                              </c:if><br>
                              <fmt:message key="deliveryPrice"/> <c:if test="${sessionScope.newOrder!=null}">
                                                                     ${sessionScope.newOrder.price}$
                                                                  </c:if>
                           </p>
                           <div class="row mt-4">
                              <div class="col-md-auto">
                                 <p><input type="submit" value="<fmt:message key="calculate"/> &raquo;" form="form" class="btn btn-success"></p>
                              </div>
                           </div>
                           <div class="row mt-4">
                              <div class="col-md-auto">
                                 <c:if test="${sessionScope.btn==null}">
                                    <p><a href="#" class="btn btn-secondary"><fmt:message key="makeAnApplication"/> &raquo;</a></p>
                                 </c:if>
                                 <c:if test="${sessionScope.btn=='unblock'}">
                                    <form method="post" action="/createOrder">
                                       <p><input type="submit" value="<fmt:message key="makeAnApplication"/>" class="btn btn-primary"></p>
                                    </form>
                                 </c:if>
                              </div>
                           </div>
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </div>
   </div>
</div>
<script src="/script/editScript.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDZ_4ASyzLdt1d16-mekZg5W4X24P0zIR4&language=${sessionScope.lang}&libraries=places&callback=initAutocomplete"
        async defer></script>
</body>
</html>
