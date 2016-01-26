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
        <form action="/sys/admin/adduser"  method="post">
            <div class="form-group">
                <label for="name">用户名</label>
                <input type="text" id="name" name="name"/>
            </div>
            <div class="form-group">
                <label for="mail">邮件</label>
                <input type="email" id="mail" name="mail"/>
            </div>
            <div class="form-group">
                <label for="tel">电话</label>
                <input type="text" id="tel" name="tel"/>
            </div>
            <div class="form-group">
                <label for="user-role">用户角色</label>
                <select id="user-role" name="user-role" >
                    <c:forEach var="t" items="${userRoles }">
                        <option value="${t}">${t.name }</option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-primary btn-sm">添加</button>
        </form>
    </div>
</div>
<%@include file="../../common/footer.jsp"%>
<%@include file="../../common/script.jsp"%>
</body>
</html>


