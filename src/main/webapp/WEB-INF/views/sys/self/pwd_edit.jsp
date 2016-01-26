<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../../common/head.jsp" %>
</head>
<body>
<%@include file="../../common/header.jsp"%>
<div class="container">
    <%@include file="../../common/sidebar.jsp"%>
    <div class="main">
        <c:if test="${!empty opResult }">
            <c:if test="${!opResult.hasError }">
                <div class="op-success">
                    <span>${opResult.message }</span>
                </div>
            </c:if>
            <c:if test="${opResult.hasError }">
                <div class="op-error">
                    <span>${opResult.message }</span>
                </div>
            </c:if>
        </c:if>
        <form action="${ctx }/sys/self/password" method="post">
            <div class="form-group">
                <label for="origin-password">原密码</label>
                <input type="password" id="origin-password" name="origin-password">
            </div>
            <div class="form-group">
                <label for="password-1">新密码</label>
                <input type="password" id="password-1" name="password-1">
            </div>
            <div class="form-group">
                <label for="password-2">重复密码</label>
                <input type="password" id="password-2" name="password-2">
            </div>
            <div class="form-group">
                <input type="submit" value="修改" class="btn btn-primary btn-sm">
            </div>
        </form>
    </div>
</div>
<%@include file="../../common/footer.jsp"%>
<%@include file="../../common/script.jsp"%>
</body>
</html>

