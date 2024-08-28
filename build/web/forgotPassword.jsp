<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Forgot Password</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: linear-gradient(to right, #e0eafc, #cfdef3);
            color: #505050;
            font-family: 'Rubik', Helvetica, Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            line-height: 1.5;
            margin: 0;
            padding: 0;
        }

        .container {
            padding: 30px;
        }

        .forgot {
            background-color: #ffffff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        .forgot h2 {
            margin-bottom: 20px;
            color: #333;
        }

        .forgot p {
            margin-bottom: 20px;
            color: #555;
        }

        .forgot ol {
            text-align: left;
            margin-bottom: 20px;
        }

        .forgot li {
            margin-bottom: 10px;
            font-size: 16px;
        }

        .forgot li span {
            color: #007bff;
        }

        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .card-body {
            padding: 30px;
        }

        .form-control {
            border-radius: 5px;
            border: 1px solid #ddd;
            box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        .form-control:focus {
            border-color: #007bff;
            box-shadow: 0 0 0 0.2rem rgba(38, 143, 255, 0.25);
        }

        .btn {
            border-radius: 5px;
            font-size: 16px;
            padding: 10px 20px;
            margin: 5px;
        }

        .btn-success {
            background-color: #28a745;
            border: none;
        }

        .btn-success:hover {
            background-color: #218838;
        }

        .btn-danger {
            background-color: #dc3545;
            border: none;
        }

        .btn-danger:hover {
            background-color: #c82333;
        }

        .btn-back {
            background-color: #007bff;
            border: none;
        }

        .btn-back:hover {
            background-color: #0056b3;
        }

        .card-footer {
            display: flex;
            justify-content: center;
            padding: 15px;
        }

        .card-footer .btn {
            margin: 0 10px;
        }
    </style>
</head>
<body>
    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-lg-6 col-md-8">
                <div class="forgot">
                    <h2>Forgot Your Password?</h2>
                    <p>Follow these simple steps to reset your password and secure your account.</p>
                    <ol class="list-unstyled">
                        <li><span class="text-primary">1. </span>Enter your email address below.</li>
                        <li><span class="text-primary">2. </span>We will send you an OTP to this email.</li>
                        <li><span class="text-primary">3. </span>Enter the OTP on the next page to reset your password.</li>
                    </ol>
                </div>
                <form class="card mt-4" action="forgotPassword" method="POST">
                    <div class="card-body">
                        <div class="form-group">
                            <label for="email-for-pass">Enter Your Email Address</label>
                            <input class="form-control" type="email" name="email" id="email-for-pass" required>
                            <small class="form-text text-muted">We will send an OTP to this address.</small>
                        </div>
                    </div>
                    <div class="card-footer">
                        <button class="btn btn-success" type="submit">Get New Password</button>
                        <button class="btn btn-back" type="button" onclick="window.location.href='login.jsp'">Back to Login</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
