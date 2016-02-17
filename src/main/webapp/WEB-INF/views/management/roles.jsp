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
        <div class="operation-wrapper">
            <a class="btn btn-custom btn-sm" href="${ctx}/management/role/add">
                <span class="glyphicon glyphicon-plus"></span>添加
            </a>
        </div>
        <%@include file="tabs.jsp" %>
        <table class="table gl-table" id="data-table">
            <thead>
            <tr>
                <th width="10"><input type="checkbox" class="gl-table-checkbox"></th>
                <th>名称</th>
                <th>角色名</th>
                <th>脚本路径</th>
                <th>所属服务</th>
                <th>进程种类</th>
                <th>描述</th>
                <th>是否运行</th>
                <th>自动重启</th>
                <th width="70">操作</th>
            </tr>
            </thead>
            <c:if test="${page.totalPages > 0}">
                <tbody>
                <form id="check-form" action="${ctx}/role/del" method="post">
                    <c:forEach items="${page.content}" var="role">
                    <tr data-id="${role.id}">
                        <td><input type="checkbox" name="ids" value="${role.id}"
                                   class="gl-table-checkbox"></td>
                        <td>${role.displayName}</td>
                        <td>${role.name}</td>
                        <td>${role.script}</td>
                        <td data-sid="${role.service.id}">${role.service.displayName}</td>
                        <td>${role.category.name}</td>
                        <td>${role.description}</td>
                        <td>${role.running ? "是" : "否"}</td>
                        <td>${role.autoRestart ? "是" : "否"}</td>
                        <td>
                            <a class="btn btn-info btn-xs" href="${ctx}/management/role/modify?id=${role.id}">
                                <span class="glyphicon glyphicon-edit"></span>修改
                            </a>
                        </td>
                    </tr>
                    </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td><input type="checkbox" class="gl-table-checkbox"></td>
                    <td colspan="10">
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
                                    <li <c:if test="${0 eq page.number}">class="disabled"</c:if>>
                                        <a href="${ctx}/management/roles?page=0" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <c:forEach begin="0" end="${page.totalPages - 1}" varStatus="status">
                                        <li <c:if test="${status.index eq page.number}">class="active"</c:if>>
                                            <a href="${ctx}/management/roles?page=${status.index}">${status.index + 1}</a>
                                        </li>
                                    </c:forEach>
                                    <li <c:if test="${page.number eq page.totalPages-1}">class="disabled"</c:if>>
                                        <a href="${ctx}/management/roles?page=${page.totalPages-1}" aria-label="Next">
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
<script type="text/javascript">

    (function init() {
        registerCheckTable($('#data-table'));
        $('#delBtn').click(function (e) {
            e.preventDefault();
            var d = dialog({
                title: '提示',
                content: '确定删除选中角色?',
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