<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

    <style type="text/css">
        body {
            background-color: #f7f7f7;
            font-family: Arial, sans-serif;
        }
        .form-gap {
            padding-top: 70px;
        }
        .panel {
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        .panel-body {
            padding: 30px;
        }
        .panel-body h2 {
            margin-top: 0;
        }
        .text-center {
            margin-bottom: 20px;
        }
        .input-group-addon {
            background-color: #f5f5f5;
            border: 1px solid #ddd;
        }
        .form-control {
            border-radius: 5px;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            padding: 12px;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .text-danger {
            margin-top: 10px;
            font-size: 14px;
        }
        .text-danger.ml-1 {
            margin-left: 5px;
        }
        .fa-lock {
            color: #007bff;
        }
    </style>
</head>
<body>
    <div class="form-gap"></div>
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="text-center">
                            <h3>
                                <i class="fa fa-lock fa-4x"></i>
                            </h3>
                            <h2 class="text-center">Enter OTP</h2>
                            <% if (request.getAttribute("message") != null) { %>
                                <p class="text-danger ml-1"><%= request.getAttribute("message") %></p>
                            <% } %>
                        </div>

                        <form id="register-form" action="ValidateOtp" role="form" autocomplete="off" class="form" method="post">
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-envelope color-blue"></i></span>
                                    <input id="opt" name="otp" placeholder="Enter OTP" class="form-control" type="text" required="required">
                                </div>
                            </div>
                            <div class="form-group">
                                <input name="recover-submit" class="btn btn-lg btn-primary btn-block" value="Reset Password" type="submit">
                            </div>
                            <input type="hidden" class="hide" name="token" id="token" value="">
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
