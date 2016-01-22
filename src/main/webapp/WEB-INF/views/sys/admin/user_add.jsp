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
    <form action="/sys/admin/adduser"  method="post">
        <div class="form-group">
            <label for="name">用户名</label>
            <input type="text" id="name" name="name" class="form-control"/>
        </div>
        <!--<div class="form-group">
            <label for="password">密码</label>
            <input type="password" id="password" name="password" class="form-control" />
        </div>-->
        <div class="form-group">
            <label for="mail">邮件</label>
            <input type="text" id="mail" name="mail" class="form-control" />
        </div>
        <div class="form-group">
            <label for="tel">电话</label>
            <input type="text" id="tel" name="tel" class="form-control" />
        </div>
        <button type="submit" class="btn btn-primary btn-block">添加</button>
    </form>
</div>
<%@include file="../../common/footer.jsp"%>
<%@include file="../../common/script.jsp"%>
</body>
</html>


