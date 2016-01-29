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
        <ol class="breadcrumb ">
            <li><a href="${ctx}/self/info"><span class="glyphicon glyphicon-chevron-left"></span>返回</a></li>
        </ol>
        <div class="gl-box-wrapper">
            <form action="${ctx }/user/password" method="post" id="p-form">
                <div class="form-group">
                    <label for="primary-password" class="col-sm-1 control-label">原密码</label>
                    <input type="password" id="primary-password" name="primaryPassword">
                </div>
                <div class="form-group">
                    <label for="password-1" class="col-sm-1 control-label">新密码</label>
                    <input type="password" id="password-1" name="newPassword">
                </div>
                <div class="form-group">
                    <label for="password-2" class="col-sm-1 control-label">重复密码</label>
                    <input type="password" id="password-2">
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-1">
                        <input type="button" value="修改" class="btn btn-custom btn-sm" id="confirmBtn">
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
    $('#confirmBtn').click(function (e) {
        e.preventDefault();
        var newPasswd1 = $('#password-1');
        var newPasswd2 = $('#password-2');
        var primaryPasswd = $('#primary-password');
        var isOk = checkInputNull(primaryPasswd) && checkInputNull(newPasswd1) && checkInputNull(newPasswd2);
        if (!isOk) {
            return;
        }
        if (newPasswd1.val() != newPasswd2.val()) {
            newPasswd1.parent('div').addClass('has-error');
            newPasswd2.parent('div').addClass('has-error');
            displayAlert("输入的两次密码不一致", 'danger');
        } else {
            $('#p-form').submit();
        }
    });
</script>
</body>
</html>

