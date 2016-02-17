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
            <button class="btn btn-custom btn-sm" id="addBtn">
                <span class="glyphicon glyphicon-plus"></span>添加
            </button>
        </div>
        <%@include file="tabs.jsp" %>
        <table class="table gl-table" id="data-table">
            <thead>
            <tr>
                <th width="10"><input type="checkbox" class="gl-table-checkbox"></th>
                <th>名称</th>
                <th>服务名</th>
                <th>描述</th>
                <th>包含角色</th>
                <th width="70">操作</th>
            </tr>
            </thead>
            <c:if test="${page.totalPages > 0}">
                <tbody>
                <form id="check-form" action="${ctx}/service/del" method="post">
                    <c:forEach items="${page.content}" var="service">
                        <tr data-id="${service.id}">
                            <td><input type="checkbox" name="ids" value="${service.id}"
                                       class="gl-table-checkbox"></td>
                            <td>${service.displayName}</td>
                            <td>${service.name}</td>
                            <td>${service.description}</td>
                            <td>
                                <c:forEach items="${service.roles}" var="role">
                                    <a class="label label-success" href="${ctx}/management/role/modify?id=${role.id}">${role.displayName}</a>
                                </c:forEach>
                            </td>
                            <td>
                                <button class="btn btn-info btn-xs modify-btn">
                                    <span class="glyphicon glyphicon-edit"></span>修改
                                </button>
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
                                    <li <c:if test="${0 eq page.number}">class="disabled"</c:if>>
                                        <a href="${ctx}/management/services?page=0" aria-label="Previous">
                                            <span aria-hidden="true">&laquo;</span>
                                        </a>
                                    </li>
                                    <c:forEach begin="0" end="${page.totalPages - 1}" varStatus="status">
                                        <li <c:if test="${status.index eq page.number}">class="active"</c:if>>
                                            <a href="${ctx}/management/services?page=${status.index}">${status.index + 1}</a>
                                        </li>
                                    </c:forEach>
                                    <li <c:if test="${page.number eq page.totalPages-1}">class="disabled"</c:if>>
                                        <a href="${ctx}/management/services?page=${page.totalPages-1}"
                                           aria-label="Next">
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
                content: '确定删除所选服务,同时会删除包含的角色?',
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

        $('#data-table').on('click', 'button.modify-btn', function (e) {
            e.preventDefault();
            var trHolder = $(this).parents('tr');
            var d = dialog({
                title: '修改服务',
                width: 500
            });
            d.content(buildForm(trHolder));
            d.show();
        });

        $('#addBtn').click(function (e) {
            var d = dialog({
                title: '添加服务',
                width: 500
            });
            d.content(buildForm(false));
            d.show();
        });

        function buildForm(trHolder) {
            var sdName = '', sName = '', desc = '', id = 0;
            if (trHolder) {
                id = trHolder.attr('data-id');
                sdName = trHolder.find('td:eq(1)').text();
                sName = trHolder.find('td:eq(2)').text();
                desc = trHolder.find('td:eq(3)').text();
            }
            var _html = '<form class="form-horizontal">';
            if (id != 0) {
                _html += '<input type="hidden" name="id" value="' + id + '">';
            }
            _html += '<div class="form-group">';
            _html += '<label for="displayName" class="col-sm-2 control-label">显示名称</label>';
            _html += '<div class="col-sm-10">';
            _html += '<input type="text" class="form-control" name="displayName" id="displayName" value="' + sdName + '">';
            _html += '</div>';
            _html += '</div>';
            _html += '<div class="form-group">';
            _html += '<label for="name" class="col-sm-2 control-label">服务名</label>';
            _html += '<div class="col-sm-10">';
            _html += '<input type="text" class="form-control" name="name" id="name" value="' + sName + '">';
            _html += '</div>';
            _html += '</div>';
            _html += '<div class="form-group">';
            _html += '<label for="description" class="col-sm-2 control-label">描述</label>';
            _html += '<div class="col-sm-10">';
            _html += '<input type="text" class="form-control" name="description" id="description" value="' + desc + '">';
            _html += '</div>';
            _html += '</div>';
            _html += '<div class="form-group">';
            _html += '<div class="col-sm-offset-2 col-sm-10">';
            _html += '<button id="submit-btn" class="btn btn-default">提交</button>';
            _html += '</div>';
            _html += '</div>';
            _html += '</form>';
            return _html;
        }

        $('body').on('click', 'button#submit-btn', function (e) {
            e.preventDefault();
            var form = $(this).parents('form');
            var url = "${ctx}/service/add";
            ajaxRequest(url, form.serialize(), function (result) {
                if (result.hasError) {
                    displayAlert(result.message, 'danger');
                } else {
                    displayAlert(result.message, 'success');
                    location.reload();
                }
            }, function () {
            });

        });

    })();
</script>
</body>
</html>