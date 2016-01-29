<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../../common/head.jsp" %>
</head>
<body>
<%@include file="../../common/header.jsp" %>
<div class="container">
    <%@include file="../../common/sidebar.jsp" %>
    <div class="main">
        <%@include file="../tabs.jsp" %>
        <ol class="breadcrumb ">
            <li><a href="${ctx}/settings/users"><span class="glyphicon glyphicon-chevron-left"></span>返回</a></li>
        </ol>
        <div class="gl-box-wrapper">
            <form action="${ctx}/settings/user/save" method="post" id="uForm">
                <input type="hidden" value="${isUpdated ? true : false}" name="updated">

                <div class="form-group">
                    <label for="name" class="col-sm-1 control-label">用户名</label>
                    <input type="text" id="name"
                           <c:if test="${isUpdated}">readonly="readonly"</c:if> name="name" value="${user.name}"/>
                </div>
                <div class="form-group">
                    <label for="mail" class="col-sm-1 control-label">邮件</label>
                    <input type="email" id="mail" name="mail" value="${user.mail}"/>
                </div>
                <div class="form-group">
                    <label for="tel" class="col-sm-1 control-label">电话</label>
                    <input type="text" id="tel" name="tel" value="${user.tel}"/>
                </div>
                <div class="form-group">
                    <label for="user-role" class="col-sm-1 control-label">用户类型</label>
                    <select id="user-role" name="userRole">
                        <c:forEach var="role" items="${userRoles }">
                            <option value="${role}" <c:if test="${role eq user.role}">selected="selected"</c:if>>
                                    ${role.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1">
                        <input type="button" value="提交" class="btn btn-custom btn-sm" id="saveBtn">
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="../../common/footer.jsp" %>
<%@include file="../../common/script.jsp" %>
<script type="text/javascript">
    $('#saveBtn').click(function (e) {
        e.preventDefault();
        var isOk = checkInputNull($('#name'));
        if (isOk) {
            $('#uForm').submit();
        }
    });
</script>
</body>
</html>


