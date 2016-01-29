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
        <%@include file="tabs.jsp" %>
        <div class="gl-box-wrapper">
            <form action="${ctx }/self/info/save" method="post" class="form-horizontal">
                <div class="form-group">
                    <label class="col-sm-1 control-label">姓名</label>
                    <input type="text" readonly="readonly" value="${user.name }">
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label" for="input-role">用户类型</label>
                    <input type="text" value="${user.role.name }" readonly="readonly" id="input-role">
                </div>
                <div class="form-group">
                    <label for="input-tel" class="col-sm-1 control-label">联系方式</label>
                    <input type="text" value="${user.tel }" name="tel" id="input-tel">
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label" for="input-mail">邮箱地址</label>
                    <input type="text" value="${user.mail }" name="mail" id="input-mail">
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1">
                        <input type="submit" value="保存" class="btn btn-custom btn-sm">
                        <a href="${ctx}/self/password" class="btn btn-custom btn-sm">密码修改</a>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
<%@include file="../common/script.jsp" %>
<script type="text/javascript">
    <c:if test="${result != null}">
    if ("${result.hasError}" == "false") {
        displayAlert("${result.message}", "success");
    } else {
        displayAlert("${result.message}", "danger");
    }
    </c:if>
</script>
</body>
</html>