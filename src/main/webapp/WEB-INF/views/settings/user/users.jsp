<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%@include file="../../common/head.jsp" %>
</head>
<body>
<%@include file="../../common/header.jsp" %>
<div class="container">
    <%@include file="../../common/sidebar.jsp" %>
    <div class="main">
        <%@include file="../tabs.jsp" %>
        <form action="/settings/users" method="get" class="form-inline gl-form-inline">
            <div class="form-group form-group-sm">
                <label for="name" class="control-label">用户名</label>
                <input type="text" id="name" name="name" class="form-control" value="${userForm.name}"/>
            </div>
            <div class="form-group form-group-sm">
                <label for="user-role">用户角色</label>
                <select id="user-role" name="userRole" class="form-control">
                    <option value>全部</option>
                    <c:forEach var="role" items="${userRoles }">
                        <option value="${role}"
                                <c:if test="${role eq userForm.userRole}">selected="selected"</c:if>>${role.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group form-group-sm">
                <label for="isEnable">是否可用：</label>
                <select id="isEnable" name="enabled" class="form-control">
                    <option value <c:if test="${empty userForm.enabled }">selected="selected"</c:if>>全部</option>
                    <option value=true <c:if test="${userForm.enabled}">selected="selected"</c:if>>可用</option>
                    <option value=false <c:if test="${userForm.enabled eq false}">selected="selected"</c:if>>不可用
                    </option>
                </select>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-custom btn-sm">查询</button>
            </div>
        </form>
        <table class="table gl-table" id="data-table">
            <thead>
            <tr>
                <th width="10"><input type="checkbox" class="gl-table-checkbox"></th>
                <th>用户名</th>
                <th>联系方式</th>
                <th>电子邮件</th>
                <th>用户角色</th>
                <th>状态</th>
                <th class="op-th">操作</th>
            </tr>
            </thead>
            <c:if test="${page.totalPages ne 0}">
                <tbody>
                <c:forEach var="user" items="${page.content}">
                    <tr data-id="${user.id}">
                        <td><input type="checkbox" class="gl-table-checkbox"></td>
                        <td>${user.name }</td>
                        <td>${user.tel }</td>
                        <td>${user.mail }</td>
                        <td>${user.role.name }</td>
                        <td>${user.isEnable ? "可用" : "不可用"}</td>
                        <td>
                            <button class="btn btn-info btn-xs modifyBtn">
                                <span class="glyphicon glyphicon-edit"></span>修改
                            </button>
                            <button class="btn btn-warning btn-xs resetBtn">
                                <span class="glyphicon glyphicon-refresh"></span>重置
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td><input type="checkbox" class="gl-table-checkbox"></td>
                    <td colspan="6">
                        <div class="pull-left">
                            <div class="gl-table-toolbar">
                                <button class="btn btn-default delBtn" disabled="disabled">删除
                                </button>
                            </div>
                        </div>
                        <div class="pull-right">
                            <nav>
                                <div class="pagination-info">
                                    <span>共有${page.totalElements}条</span>
                                </div>
                                <ul class="pagination pagination-sm gl-pagination">
                                    <c:set var="requestParams"
                                           value="name=${userForm.name}&userRole=${userForm.userRole}&enabled=${userForm.enabled}"></c:set>

                                    <li <c:if test="${0 eq page.number}">class="disabled"</c:if>>
                                        <a href="${ctx}/settings/users?${requestParams}" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <c:forEach begin="0" end="${page.totalPages - 1}" varStatus="status">
                                        <li <c:if test="${status.index eq page.number}">class="active"</c:if>>
                                            <a href="${ctx}/settings/users?page=${status.index}&${requestParams}">${status.index + 1}</a>
                                        </li>
                                    </c:forEach>
                                    <li <c:if test="${page.number eq page.totalPages-1}">class="disabled"</c:if>>
                                        <a href="${ctx}/settings/users?page=${page.totalPages-1}&${requestParams}"
                                           aria-label="Next">
                                            <span aria-hidden="true">&raquo;</span>
                                        </a>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </td>
                </tfoot>
            </c:if>
        </table>
        <c:if test="${page.totalPages == 0}">
            <div class="gl-table-no-result">
                <span>暂无条目</span>
            </div>
        </c:if>
    </div>
</div>
<%@include file="../../common/footer.jsp" %>
<%@include file="../../common/script.jsp" %>
<script>
    (function init() {
        $('#data-table').on('click', 'button.resetBtn', function (e) {
            var trHolder = $(this).parents('tr');
            var userId = trHolder.attr('data-id');
            var d = dialog({
                title: '提示',
                content: '确定重置该用户密码?',
                width: 350,
                okValue: '确定',
                ok: function () {
                    var data = "id=" + userId;
                    var url = "${ctx}/sys/admin/yhlb/rspw";
                    ajaxRequest(url, data, function (result) {
                        if (result.hasError) {
                            displayAlert(result.message, 'danger');
                        } else {
                            displayAlert(result.message, 'success');
                        }
                    }, function () {
                    });
                },
                cancelValue: '取消',
                cancel: function () {
                }
            });
            d.show();
        });

        $('#data-table').on('click', 'button.delBtn', function (e) {
            var trHolder = $(this).parents('tr');
            var userId = trHolder.attr('data-id');

            var d = dialog({
                title: '提示',
                content: '确定删除该用户?',
                width: 350,
                okValue: '确定',
                ok: function () {
                    var data = "id=" + userId;
                    var url = "${ctx}/sys/admin/yhlb/delete";
                    ajaxRequest(url, data, function (result) {

                        if (result.hasError) {
                            displayAlert(result.message, 'danger');
                        } else {
                            displayAlert(result.message, 'success');
                            trHolder.remove();
                        }
                    }, function () {
                    });
                },
                cancelValue: '取消',
                cancel: function () {
                }
            });
            d.show();
        });

    })();
</script>
</body>
</html>


