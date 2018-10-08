
<%--
  Created by IntelliJ IDEA.
  User: xxxx
  Date: 12.04.18
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
    isErrorPage="true" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>404</title>
</head>
<body>
<div align="center">
    <h1>This url does not exist!</h1><br/>
    <table style="width: 50%">
    <tr><td>${pageContext.exception.message}</td></tr>
    </table>
</div>
</body>
</html>