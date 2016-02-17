<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../common/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../common/head.jsp" %>
</head>
<body>
<%@include file="../common/header.jsp" %>
<div class="container">
    <%@include file="../common/sidebar.jsp"%>
    <div class="main">
        <div class="operation-wrapper">
            <a class="btn btn-custom btn-sm" href="${ctx}/management/host/add">
                <span class="glyphicon glyphicon-plus"></span>添加
            </a>
        </div>
        <%@include file="tabs.jsp"%>
        <table class="table gl-table" id="data-table">
            <thead>
            <tr>
                <th width="10"><input type="checkbox" class="gl-table-checkbox"></th>
                <th>主机名</th>
                <th>IP地址</th>
                <th>所属机架</th>
                <th>角色列表</th>
                <th width="70">操作</th>
            </tr>
            </thead>
            <c:if test="${page.totalPages > 0}">
            <tbody>
                <form id="check-form" action="${ctx}/host/del" method="post">
                    <c:forEach items="${page.content}" var="host">
                        <tr>
                            <td><input type="checkbox" name="ids" value="${host.id}"
                                       class="gl-table-checkbox"></td>
                            <td>${host.hostName}</td>
                            <td>${host.ipAddress}</td>
                            <td>${host.rack.rackName}</td>
                            <td>
                                <c:forEach items="${host.roles}" var="role">
                                    <a class="label label-success" href="${ctx}/management/role/modify?id=${role.id}">${role.displayName}</a>
                                </c:forEach>
                            </td>
                            <td>
                                <a class="btn btn-info btn-xs" href="${ctx}/management/host/modify?id=${host.id}">
                                    <span class="glyphicon glyphicon-edit"></span>修改
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </form>
            </tbody>
            <tfoot>
            <tr>
                <td><input type="checkbox" class="gl-table-checkbox"></td>
                <td colspan="6">
                    <div class="pull-left">
                        <div class="gl-table-toolbar">
                            <button class="btn btn-default" id="delBtn" disabled="disabled">删除
                            </button>
                        </div>
                    </div>
                    <div class="pull-right">
                        <nav>
                            <div class="pagination-info">
                                <span>共有${page.totalElements}条</span>
                            </div>
                            <ul class="pagination pagination-sm gl-pagination">
                                <li  <c:if test="${0 eq page.number}">class="disabled"</c:if>>
                                    <a href="${ctx}/management/hosts?page=0" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <c:forEach begin="0" end="${page.totalPages - 1}" varStatus="status">
                                    <li  <c:if test="${status.index eq page.number}">class="active"</c:if>>
                                        <a href="${ctx}/management/hosts?page=${status.index}">${status.index + 1}</a>
                                    </li>
                                </c:forEach>
                                <li  <c:if test="${page.number eq page.totalPages-1}">class="disabled"</c:if>>
                                    <a href="${ctx}/management/hosts?page=${page.totalPages-1}" aria-label="Next">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </td>
            </tr>
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
<%@include file="../common/footer.jsp" %>
<%@include file="../common/script.jsp" %>
<script>
    <c:if test="${result != null}">
    if ("${result.hasError}" == "false") {
        displayAlert("${result.message}", "success");
    } else {
        displayAlert("${result.message}", "danger");
    }
    </c:if>
    (function init() {
        registerCheckTable($('#data-table'));
        $('#delBtn').click(function (e) {
            e.preventDefault();
            var d = dialog({
                title: '提示',
                content: '确定删除已选机器?',
                width: 350,
                okValue: '确定',
                ok: function () {
                    $('#check-form').submit();
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