<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="width" required="true" rtexprvalue="true" type="java.lang.String" %>
<c:if test="${width.equals('width')}">
    <style type="text/css">
        .dropdown-menu {
            min-width: 30px;
        }
    </style>
</c:if>