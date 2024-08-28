<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(to right, #00c6ff, #0072ff);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .home-container {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 350px;
            text-align: center;
            position: relative;
        }
        .home-container::before {
            content: "";
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 12px;
            z-index: 0;
        }
        .home-container h2 {
            margin: 0;
            font-size: 24px;
            color: #333;
            position: relative;
            z-index: 1;
        }
        .home-container a {
            display: block;
            margin: 15px 0;
            padding: 12px;
            background-color: #0072ff;
            color: #fff;
            text-decoration: none;
            border-radius: 8px;
            font-size: 16px;
            font-weight: bold;
            transition: background-color 0.3s ease;
            position: relative;
            z-index: 1;
        }
        .home-container a:hover {
            background-color: #005bb5;
        }
        .home-container a.logout {
            background-color: #ff4d4d;
        }
        .home-container a.logout:hover {
            background-color: #cc0000;
        }
        .home-container p {
            color: #555;
            font-size: 14px;
            margin: 10px 0;
            position: relative;
            z-index: 1;
        }
    </style>
</head>
<body>

<div class="home-container">
    <h2>Welcome, ${user.username}!</h2>
    <p>What would you like to do today?</p>

    <a href="changePassword.jsp">Change Password</a>
    <a href="login.jsp" class="logout">Logout</a>
</div>

</body>
</html>
