<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        /* Thay đổi giao diện để trông hiện đại hơn */
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #e0e0e0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-image: linear-gradient(to right, #74ebd5, #ACB6E5);
        }
        .login-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 350px;
            text-align: center;
        }
        .login-container h2 {
            margin: 0;
            color: #333;
            font-size: 24px;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 100%;
            padding: 15px;
            margin: 10px 0;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 16px;
            box-sizing: border-box;
        }
        .login-container .password-container {
            position: relative;
            width: 100%;
        }
        .login-container .password-container input[type="password"] {
            width: 100%;
            padding-right: 40px; /* Để chừa không gian cho icon nhưng không ảnh hưởng đến kích thước trường nhập */
        }
        .login-container .toggle-password {
            position: absolute;
            top: 50%;
            right: 15px;
            transform: translateY(-50%);
            cursor: pointer;
            font-size: 18px;
        }
        .login-container input[type="submit"],
        .login-container input[type="button"] {
            width: 100%;
            padding: 15px;
            background-color: #007BFF;
            border: none;
            border-radius: 5px;
            color: white;
            font-size: 18px;
            cursor: pointer;
            margin-top: 10px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s ease;
        }
        .login-container input[type="submit"]:hover,
        .login-container input[type="button"]:hover {
            background-color: #0056b3;
        }
        .login-container a {
            display: block;
            margin-top: 15px;
            color: #007BFF;
            text-decoration: none;
            font-size: 16px;
        }
        .login-container a:hover {
            text-decoration: underline;
        }
        .error {
            color: #d9534f;
            margin-top: 15px;
        }
    </style>
</head>
<body>

<div class="login-container">
    <h2>Login</h2>

    <form action="login" method="post">
        <input type="text" name="username" placeholder="Enter Username" required>

        <div class="password-container">
            <input type="password" name="password" placeholder="Enter Password" id="password" required>
            <span class="toggle-password" id="togglePassword">👁️</span>
        </div>

        <input type="submit" value="Login">
    </form>

    <a href="forgotPassword.jsp">Forgot Password?</a>
    <input type="button" value="Register" onclick="window.location.href='register.jsp'">

    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>
</div>

<script>
    const passwordField = document.getElementById('password');
    const togglePasswordIcon = document.getElementById('togglePassword');

    togglePasswordIcon.addEventListener('click', function() {
        const type = passwordField.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordField.setAttribute('type', type);
        this.textContent = type === 'password' ? '👁️' : '🙈';
    });
</script>

</body>
</html>
