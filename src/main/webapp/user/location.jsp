<%--
  Created by IntelliJ IDEA.
  User: YES
  Date: 24.05.2022
  Time: 14:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Місцерозташування посилки</title>
    <h1>Місцерозташування посилки</h1>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <link rel="stylesheet" type="text/css" href="/user/style.css" />
</head>
<body>
<a href="/resetOrder">Повернутись до замовлень</a><br>
    <div id="map"></div>
    <script
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDZ_4ASyzLdt1d16-mekZg5W4X24P0zIR4&callback=initMap&v=weekly&language=uk"
            defer
    ></script>
    <script>
        function initMap() {
            const myLatLng = { lat: ${sessionScope.latitude}, lng: ${sessionScope.longitude} };
            const map = new google.maps.Map(document.getElementById("map"), {
                zoom: 14,
                center: myLatLng,
            });

            new   google.maps.Marker({
                position: myLatLng,
                map,
                title: "Hello",
            });

        }

        window.initMap = initMap;
        // [END maps_marker_simple]
    </script>
</body>
</html>
