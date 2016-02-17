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
        <ol class="breadcrumb">
            <li><a href="${ctx}/management/roles"><span class="glyphicon glyphicon-chevron-left"></span>返回</a></li>
        </ol>
        <div class="gl-box-wrapper">
            <form class="form-horizontal" action="${ctx}/management/role/save" method="post" id="uForm">
                <input type="hidden" value="${isUpdated ? true : false}" name="updated">
                <div class="form-group">
                    <label for="roleName" class="col-sm-1 control-label">角色名</label>

                    <div class="col-sm-3">
                        <input type="text" id="roleName"
                               <c:if test="${isUpdated}"> readonly="readonly"</c:if> class="form-control input-sm"
                               name="name" value="${role.name}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="displayName" class="col-sm-1 control-label">显示名称</label>

                    <div class="col-sm-3">
                        <input type="text" id="displayName" class="form-control input-sm" name="displayName"
                               value="${role.displayName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="psCategory" class="col-sm-1 control-label">进程类型</label>

                    <div class="col-sm-3">
                        <select id="psCategory" name="psCategory" class="form-control input-sm">
                            <c:forEach var="category" items="${categoryList }">
                                <option value="${category}"
                                        <c:if test="${category eq role.category}">selected="selected"</c:if>>
                                        ${category.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="serviceNameInput" class="col-sm-1 control-label">所属服务</label>

                    <div class="col-sm-3">
                        <input type="hidden" name="serviceId" id="serviceIdInput" value="${role.service.id}"/>

                        <div class="input-group input-group-sm">
                            <input type="text" id="serviceNameInput" class="form-control" readonly="readonly"
                                   value="${role.service.displayName}"/>
                            <span class="input-group-btn">
                            <button class="btn btn-custom" id="serviceSelector"><span
                                    class="glyphicon glyphicon-search"></span></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label for="script" class="col-sm-1 control-label">脚本路径</label>

                    <div class="col-sm-3">
                        <input type="text" id="script" class="form-control input-sm" name="script"
                               value="${role.script}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label">是否运行</label>

                    <div class="col-sm-3">
                        <label for="runningYes" class="radio-inline">
                            <input type="radio" name="running" id="runningYes"
                            <c:if test="${role.running}"> checked="checked"</c:if> value=true>是
                        </label>
                        <label for="runningNo" class="radio-inline">
                            <input type="radio" name="running" id="runningNo"
                            <c:if test="${ not role.running}"> checked="checked"</c:if> value=false>否
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-1 control-label">自动重启</label>

                    <div class="col-sm-3">
                        <label for="restartYes" class="radio-inline">
                            <input type="radio" name="autoRestart" id="restartYes"
                            <c:if test="${role.autoRestart}"> checked="checked"</c:if> value=true>是
                        </label>
                        <label for="restartNo" class="radio-inline">
                            <input type="radio" name="autoRestart" id="restartNo"
                            <c:if test="${ not role.autoRestart}"> checked="checked"</c:if> value=false>否
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="description" class="col-sm-1 control-label">描述</label>

                    <div class="col-sm-3">
                        <textarea id="description" class="form-control input-sm" name="description">${role.description}</textarea>
                    </div>
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
<%@include file="../common/footer.jsp" %>
<%@include file="../common/script.jsp" %>
<script type="text/javascript">

    $('#serviceSelector').CommonSelector({
        title: '服务选择',
        data: ${serviceList},
        val_fun: function (item) {
            return item.displayName;
        },
        callback: function (item) {
            $('#serviceNameInput').val(item.val);
            $('#serviceIdInput').val(item.id);
        }
    });

    $('#saveBtn').click(function (e) {
        e.preventDefault();
        var isOk = checkInputNull($('#roleName'));
        if (isOk) {
            $('#uForm').submit();
        }
    });
</script>
</body>
</html>


