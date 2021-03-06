<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path=request.getContextPath(); %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="content-type" content="text/html">
    <title>legend仓库管理系统</title>

    <link type="text/css" rel="stylesheet" href="../static/css/login.css">
</head>

<body>
    <div id="container">
        <div class="block-display">
            <div class="login-title">
                <h1 style="font-size:55px">Legend</h1>
                <h2>仓库管理系统</h2>
            </div>
            <div class="log-input login-form">
                <!--<form action="#" method="GET" id="log-form">-->
                    <div class="account input-form">
                        <label for="staffNum">工号</label>
                        <input type="text" name="staffNum" id="staffNum" placeholder="请输入工号" />
                        <span class="error-msg hide"></span>
                    </div>
                    <div class="password input-form">
                        <label for="staffPassword">密码</label>
                        <input type="password" name="staffPassword" id="staffPassword" placeholder="请输入密码" />
                        <span class="error-msg hide"></span>
                    </div>
                    <div class="btn-wrap">
                        <div class="btn-default">
                            <a id="login-submit" href="#">登录</a>
                        </div>
                        <div class="login-error-div">
                            <span class="hide" id="login-error-msg"></span>
                        </div>
                    </div>
                <!--</form>-->
            </div>
        </div>
    </div>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="../static/js/lib/jquery.min.js"></script>
    <!--<script src="../static/js/lib/bootstrap.min.js"></script> -->
    <script src="../static/js/login.js"></script>

</body>

</html>