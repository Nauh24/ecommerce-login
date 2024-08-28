<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .home-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
            text-align: center;
        }
        .home-container h2 {
            margin-bottom: 20px;
        }
        .home-container a {
            display: block;
            margin-top: 10px;
            color: #007BFF;
            text-decoration: none;
        }
        .home-container a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="home-container">
    <h2>Welcome, ${user.username}!</h2>

    <a href="changePassword.jsp">Change Password</a>
    <a href="login.jsp">Logout</a>
</div>

</body>
</html>
