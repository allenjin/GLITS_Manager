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
                <th>名称</th>
                <th>进程名</th>
                <th>脚本路径</th>
                <th>所属服务</th>
                <th>进程种类</th>
                <th>描述</th>
                <th>是否运行</th>
                <th>自动重启</th>
                <th class="op-th">操作</th>
            </tr>
            </thead>
            <c:if test="${page.totalPages > 0}">
                <tbody>
                <c:forEach items="${page.content}" var="role">
                    <tr data-id="${role.id}">
                        <td>${role.displayName}</td>
                        <td>${role.name}</td>
                        <td>${role.script}</td>
                        <td data-sid="${role.service.id}">${role.service.displayName}</td>
                        <td>${role.category.name}</td>
                        <td>${role.description}</td>
                        <td>${role.running ? "是" : "否"}</td>
                        <td>${role.autoRestart ? "是" : "否"}</td>
                        <td>
                            <button class="btn btn-info btn-xs modify-btn">
                                <span class="glyphicon glyphicon-edit"></span>修改
                            </button>
                            <button class="btn btn-danger btn-xs del-btn">
                                <span class="glyphicon glyphicon-trash"></span>删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="9">
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
        $('#data-table').on('click', 'button.modify-btn', function (e) {
            var trHolder = $(this).parents('tr');
            var d = dialog({
                title: '修改角色',
                width: 500
            });
            d.content(buildForm(trHolder));
            d.show();
        });

        $('#addBtn').click(function (e) {
            var d = dialog({
                title: '添加角色',
                width: 500
            });
            d.content(buildForm(false));
            d.show();
        });

        function buildForm(trHolder) {
            var rdName = '', rName = '', desc = '', script = '', category = '',
                    id = 0, sid = 0, isRunning = false, isAutoRestart = false;
            if (trHolder) {
                id = trHolder.attr('data-id');
                rdName = trHolder.find('td:eq(0)').text();
                rName = trHolder.find('td:eq(1)').text();
                script = trHolder.find('td:eq(2)').text();
                sid = trHolder.find('td:eq(3)').attr('data-sid');
                category = trHolder.find('td:eq(4)').text();
                desc = trHolder.find('td:eq(5)').text();
                isRunning = (trHolder.find('td:eq(6)').text() == "是") ? true : false;
                isAutoRestart = (trHolder.find('td:eq(7)').text() == "是") ? true : false;
            }
            var _html = '<form class="form-horizontal">';
            if (id != 0) {
                _html += '<input type="hidden" name="id" value="' + id + '">';
            }
            _html += '<div class="form-group">';
            _html += '<label for="displayName" class="col-sm-2 control-label">名称</label>';
            _html += '<div class="col-sm-10">';
            _html += '<input type="text" class="form-control" name="displayName" id="displayName" value="' + rdName + '">';
            _html += '</div>';
            _html += '</div>';

            _html += '<div class="form-group">';
            _html += '<label for="name" class="col-sm-2 control-label">角色名</label>';
            _html += '<div class="col-sm-10">';
            _html += '<input type="text" class="form-control" name="name" id="name" value="' + rName + '">';
            _html += '</div>';
            _html += '</div>';

            _html += '<div class="form-group">';
            _html += '<label for="script" class="col-sm-2 control-label">脚本路径</label>';
            _html += '<div class="col-sm-10">';
            _html += '<input type="text" class="form-control" name="script" id="script" value="' + script + '">';
            _html += '</div>';
            _html += '</div>';

            _html += '<div class="form-group">';
            _html += '<label for="service" class="col-sm-2 control-label">所属服务</label>';
            _html += '<div class="col-sm-10">';
            _html += '<select class="form-control" id="service" name="service.id">';
            var serviceList = ${serviceList};
            $.each(serviceList, function (index, service) {
                if (sid == service.id) {
                    _html += '<option selected="selected" value="' + service.id + '">';
                } else {
                    _html += '<option value="' + service.id + '">';
                }
                _html += service.displayName + '</option>';
            });
            _html += ''
            _html += '</select>';
            _html += '</div>';
            _html += '</div>';

            _html += '<div class="form-group">';
            _html += '<label for="category" class="col-sm-2 control-label">进程类型</label>';
            _html += '<div class="col-sm-10">';
            _html += '<select class="form-control" id="category" name="category">';
            var categoryList = ${categoryList};
            $.each(categoryList, function (index, pc) {
                if (category == pc.name) {
                    _html += '<option selected="selected" value="' + pc + '">';
                } else {
                    _html += '<option value="' + pc + '">';
                }
                _html += pc + '</option>';
            });
            _html += ''
            _html += '</select>';
            _html += '</div>';
            _html += '</div>';

            var runCheck_Y = isRunning ? "checked" : "";
            var runCheck_N = isRunning ? "" : "checked";
            var autoCheck_Y = isAutoRestart ? "checked" : "";
            var autoCheck_N = isAutoRestart ? "" : "checked";
            _html += '<div class="form-group">';
            _html += '<label for="isRunning" class="col-sm-2 control-label">运行</label>';
            _html += '<div class="col-sm-4">';
            _html += '<label for="isRunning-y" class="radio-inline">';
            _html += '<input type="radio" id="isRunning-y" name="running"' + runCheck_Y + ' value=true>是';
            _html += '</label>';
            _html += '<label for="isRunning-n" class="radio-inline">';
            _html += '<input type="radio" id="isRunning-n" name="running"' + runCheck_N + ' value=false>否';
            _html += '</label>';
            _html += '</div>';
            _html += '<label for="isAuto" class="col-sm-2 control-label">自动重启</label>';
            _html += '<div class="col-sm-4">';
            _html += '<label for="isAuto-y" class="radio-inline">';
            _html += '<input type="radio" id="isAuto-y" name="autoRestart"' + autoCheck_Y + ' value=true>是';
            _html += '</label>';
            _html += '<label for="isAuto-n" class="radio-inline">';
            _html += '<input type="radio" id="isAuto-n" name="autoRestart"' + autoCheck_N + ' value=false>否';
            _html += '</label>';
            _html += '</div>';
            _html += '</div>';

            _html += '<div class="form-group">';
            _html += '<label for="description" class="col-sm-2 control-label">描述</label>';
            _html += '<div class="col-sm-10">';
            _html += '<input type="text" class="form-control" name="description" id="description" value="' + desc + '">';
            _html += '</div>';
            _html += '</div>';

            _html += '<div class="form-group">';
            _html += '<div class="col-sm-offset-5 col-sm-10">';
            _html += '<button id="submit-btn" class="btn btn-custom">提交</button>';
            _html += '</div>';
            _html += '</div>';

            _html += '</form>';
            return _html;
        }

        $('body').on('click', 'button#submit-btn', function (e) {
            e.preventDefault();
            var form = $(this).parents('form');
            var url = "${ctx}/role/add";
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

        $('#data-table').on('click', 'button.del-btn', function (e) {
            var trHolder = $(this).parents('tr');
            var rackId = trHolder.attr('data-id');
            var d = dialog({
                title: '提示',
                content: '确定删除该角色?',
                width: 350,
                okValue: '确定',
                ok: function () {
                    var data = "id=" + rackId;
                    var url = "${ctx}/role/del";
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