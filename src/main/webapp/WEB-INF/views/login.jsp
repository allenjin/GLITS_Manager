<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="common/head.jsp" %>
    <%@include file="common/script.jsp"%>
    <script type="text/javascript" src="${ctx }/static/js/dialog-min.js"></script>
    <link rel="stylesheet" href="${ctx}/static/css/ui-dialog.css" type="text/css">
    <style type="text/css">
        .login-form{
            margin: 0 auto;
            width: 340px;
        }
        .login-form-header{
            background-color: transparent;
            border: 0;
            color: #333;
            text-shadow: none;
            text-align: center;
            margin-bottom: 15px;
            padding: 10px 0;
        }
        .login-form-header h1{
            font-weight: 300;
            font-size: 28px;
            letter-spacing: -0.5px;
            margin-top: 0;
            margin-bottom: 0;
        }
        .login-form-body{
            border-radius: 5px;
            border-top: 1px solid #d8dee2;
            padding: 20px;
            font-size: 14px;
            background-color: #fff;
            border: 1px solid #d8dee2;
        }
        .header{
            background-color: transparent;
            border-bottom: 0;
            padding: 40px 0 20px;
        }
        .logo-container{
            width: 980px;
            margin-right: auto;
            margin-left: auto;
        }
        .logo-container p{
            text-align: center;
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
        <c:url value="${ctx }/logon" var="loginUrl"/>
        <form action="${loginUrl}" method="post">
            <div class="login-form-header">
                <h1>观澜运维管理系统</h1>
            </div>
            <div class="login-form-body">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input type="text" id="username" name="username" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="password">密码</label>
                    <input type="password" id="password" name="password" class="form-control" />
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary btn-block">登录</button>
            </div>
        </form>
        <c:if test="${param.error != null}">
            <script type="text/javascript">
                dialog({
                    content: '用户名或密码错误',
                    okValue: '确定',
                    ok: function () {
                        this.close().remove();
                    }
                }).show();
            </script>
        </c:if>
        <c:if test="${param.logout != null}">
            <script type="text/javascript">
                dialog({
                    content: '用户已登出',
                    okValue: '确定',
                    ok: function () {
                        this.close().remove();
                    }
                }).show();
            </script>
        </c:if>
    </div>
</div>
<%@include file="common/footer.jsp"%>

</body>
</html>