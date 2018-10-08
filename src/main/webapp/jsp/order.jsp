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
            <h1>Hello <c:out value="${sessionScope.user.login}"/>!</h1>
        </td></tr>
        <tr><td>
            <h3>You have already ordered:</h3>
            <c:forEach var="orderedItem" items="${sessionScope.orderedItems}" varStatus="status">
                ${status.count}) ${orderedItem.itemName} ${orderedItem.price} $<br/>
            </c:forEach>
        </td>
        <td>
            <c:choose>
                <c:when test="${empty sessionScope.currentItems}">
                    <h3>Please, make your order.</h3>
                </c:when>
                <c:otherwise>
                    <h3>You have already chosen:</h3>
                    <c:forEach var="currentItem" items="${sessionScope.currentItems}" varStatus="status">
                        ${status.count}) ${currentItem.itemName} ${currentItem.price} $<br/>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </td></tr>
        <tr>
            <td></td>
            <td>
                <form action="/shop/order.do" method="post">
                    <select name="itemName">
                        <c:forEach var="item" items="${applicationScope.items}">
                            <option value="${item.itemName}"><c:out value="${item.itemName} (${item.price} $)"/></option>
                        </c:forEach>
                    </select><br/><br/>
                    <button type="submit" name="action" value="add" style="background-color: #d3d3d3;">Add item</button>
                    &nbsp;&nbsp;
                    <button type="submit" name="action" value="enter" style="background-color: #87cefa;">Submit</button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>