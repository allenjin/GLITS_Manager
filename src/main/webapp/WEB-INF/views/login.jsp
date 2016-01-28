<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="common/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <style type="text/css">
        .login-form {
            margin: 0 auto;
            width: 340px;
        }

        .login-form-header {
            background-color: transparent;
            border: 0;
            color: #333;
            text-shadow: none;
            text-align: center;
            margin-bottom: 15px;
            padding: 10px 0;
        }

        .login-form-header h1 {
            font-weight: 300;
            font-size: 28px;
            letter-spacing: -0.5px;
            margin-top: 0;
            margin-bottom: 0;
        }

        .login-form-body {
            border-radius: 5px;
            padding: 20px;
            font-size: 14px;
            background-color: #fff;
            border: 1px solid #d8dee2;
        }

        .header {
            background-color: transparent;
            border-bottom: 0;
            padding: 40px 0 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="header">
        <div class="logo-container">
            <p>logo</p>
        </div>
    </div>
    <div class="login-form">
        <form action="${ctx}/logon" method="post">
            <div class="login-form-header">
                <h1>${siteConfig.title}</h1>
            </div>
            <div class="login-form-body">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input type="text" id="username" name="username" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input type="password" id="password" name="password" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="remember-me">记住我</label>
                    <input type="checkbox" name="remember-me" id="remember-me" class="forget-pwd">
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-custom btn-block">登录</button>
            </div>
        </form>
    </div>
</div>
<%@include file="common/footer.jsp" %>
<%@include file="common/script.jsp" %>
<script type="text/javascript">
    var error = "${param.error}";
    var logout = "${param.logout}";
    console.log(error + "," + logout);
</script>
</body>
</html>