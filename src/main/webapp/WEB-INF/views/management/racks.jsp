<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../common/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<%@include file="../common/header.jsp" %>
<div class="container">
    <%@include file="../common/sidebar.jsp" %>
    <div class="main">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>机架名称</th>
                <th>机架描述</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${racks}" var="rack">
                <tr>
                    <td>${rack.rackName}</td>
                    <td>${rack.description}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
<%@include file="../common/script.jsp" %>
</body>
</html>