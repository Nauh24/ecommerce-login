<%-- 
    Document   : forgotPassword
    Created on : Aug 23, 2024, 7:40:40 AM
    Author     : vanhu
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Forgot Password</title>
    </head>
    <body>
        <h2>Forgot Password</h2>
        <form action="sendResetLink" method="post">
            <label for="emailOrPhone">Enter your Email or Phone:</label>
            <input type="text" name="emailOrPhone" required><br><br>
            <input type="submit" value="Send Reset Link">
        </form>

    <c:if test="${not empty error}">
        <p style="color: red;">${error}</p>
    </c:if>

    <c:if test="${not empty success}">
        <p style="color: green;">${success}</p>
    </c:if>
</body>
</html>
