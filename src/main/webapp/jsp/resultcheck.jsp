<%--
  Created by IntelliJ IDEA.
  User: xxxx
  Date: 11.04.18
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="by.anjei.shop.db.daomodel.*" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>order</title>
</head>
<body>
<div align="center">
    <table>
        <tbody>
        <tr><td>
            <p>You have already ordered:</p>
            <c:forEach var="orderedItem" items="${sessionScope.orderedItems}" varStatus="status">
                ${status.count}) ${orderedItem.itemName} ${orderedItem.price} $<br/>
            </c:forEach>
        </td>
        <td>
            <c:choose>
                <c:when test="${empty sessionScope.currentItems}">
                    <jsp:forward page="order.jsp"/>
                </c:when>
                <c:otherwise>
                    <p><strong>Dear <c:out value="${sessionScope.user.login}"/>, your order:</strong></p>
                    <c:forEach var="currentItem" items="${sessionScope.currentItems}" varStatus="status">
                        ${status.count}) ${currentItem.itemName} ${currentItem.price} $<br/>
                    </c:forEach>
                    <br/>Total: $ <c:out value="${sessionScope.orderPrice}"/>
                </c:otherwise>
            </c:choose>
        </td></tr>
        <tr><td>
            <a href="/shop">Back to home page</a>
        </td></tr>
        </tbody>
    </table>
</div>
</body>
</html>