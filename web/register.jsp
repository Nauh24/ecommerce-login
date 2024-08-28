<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(to right, #ff7e5f, #feb47b);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .register-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
            text-align: center;
            position: relative;
        }
        .register-container::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: rgba(255, 255, 255, 0.2);
            border-radius: 12px;
            z-index: 0;
        }
        .register-container h2 {
            margin: 0;
            color: #333;
            font-size: 28px;
            position: relative;
            z-index: 1;
        }
        .register-container input[type="text"],
        .register-container input[type="email"],
        .register-container input[type="password"] {
            width: calc(100% - 20px);
            padding: 15px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 8px;
            font-size: 16px;
            box-sizing: border-box;
            position: relative;
            z-index: 1;
        }
        .register-container input[type="submit"] {
            width: calc(100% - 20px);
            padding: 15px;
            background-color: #ff7e5f;
            border: none;
            border-radius: 8px;
            color: white;
            font-size: 18px;
            cursor: pointer;
            margin-top: 10px;
            position: relative;
            z-index: 1;
            transition: background-color 0.3s ease;
        }
        .register-container input[type="submit"]:hover {
            background-color: #feb47b;
        }
        .error, .success {
            text-align: center;
            margin-top: 15px;
            position: relative;
            z-index: 1;
        }
        .error {
            color: #d9534f;
        }
        .success {
            color: #5bc0de;
        }
    </style>
</head>
<body>

<div class="register-container">
    <h2>Register</h2>

    <form action="register" method="post">
        <input type="text" name="username" placeholder="Enter Username" required>
        <input type="email" name="email" placeholder="Enter Email" required>
        <input type="text" name="phone" placeholder="Enter Phone Number" required>
        <input type="password" name="password" placeholder="Enter Password" required>
        <input type="submit" value="Register">
    </form>

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
    <c:if test="${not empty success}">
        <p class="success">${success}</p>
    </c:if>
</div>

</body>
</html>
