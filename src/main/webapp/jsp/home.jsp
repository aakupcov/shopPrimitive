<%--
  Created by IntelliJ IDEA.
  User: xxxx
  Date: 11.04.18
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home</title>
</head>
<body>
<div align="center">
    <table>
        <tbody>
        <tr><td>
            <h1>Welcome to Online Shop!</h1>
        </td></tr>
        <tr><td>
            <h3>Please, enter your name.</h3>
            <form action="/shop/homepage.do" method="post">
                <input type="text" name="login"><br/>
                <p><input type="checkbox" name="agreement" orderId="agreement"> <label for="agreement">I agree with the terms of service</label><br/><p>
                <input type="submit" value="Enter" style="background-color: #87cefa;">
            </form>
        </td></tr>
        </tbody>
    </table>
</div>
</body>
</html>