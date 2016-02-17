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
            <li><a href="${ctx}/management/hosts"><span class="glyphicon glyphicon-chevron-left"></span>返回</a></li>
        </ol>
        <div class="gl-box-wrapper">
            <form class="form-horizontal" action="${ctx}/management/host/save" method="post" id="uForm">
                <input type="hidden" value="${isUpdated ? true : false}" name="updated">

                <div class="form-group">
                    <label for="hostName" class="col-sm-1 control-label">机器名</label>
                    <div class="col-sm-2">
                        <input type="text" id="hostName" class="form-control input-sm"
                               <c:if test="${isUpdated}">readonly="readonly"</c:if> name="hostName"
                               value="${host.hostName}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="ipAddress" class="col-sm-1 control-label">IP地址</label>
                    <div class="col-sm-2">
                        <input type="text" id="ipAddress" class="form-control input-sm" name="ipAddress" value="${host.ipAddress}"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="rackNameInput" class="col-sm-1 control-label">所属机架</label>
                    <div class="col-sm-2">
                        <input type="hidden" name="rackId" id="rackIdInput" value="${host.rack.id}"/>
                        <div class="input-group input-group-sm">
                            <input type="text" id="rackNameInput" class="form-control" readonly="readonly" value="${host.rack.rackName}"/>
                            <span class="input-group-btn">
                            <button class="btn btn-custom" id="rackSelector"><span class="glyphicon glyphicon-search"></span></button>
                            </span>
                        </div>
                    </div>
                </div>
                <div class="form-group clearfix">
                    <label class="col-sm-1 control-label">所属角色</label>
                    <div class="label-list col-sm-6" id="roleLabelList">
                        <c:forEach items="${host.roles}" var="role">
                            <div class="pull-left">
                                <span class="label label-info">${role.displayName}</span>
                                <input type="checkbox" name="roleIds" checked="checked" value="${role.id}"/>
                            </div>
                        </c:forEach>
                        <a id="roleAddBtn" href="javascript:void(0)" class="label label-danger"><span
                                class="glyphicon glyphicon-plus"></span></a>
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

    $('#roleAddBtn').CommonSelector({
        title: '角色选择',
        data: ${roleList},
        val_fun: function (item) {
            return item.displayName;
        },
        callback: function (item) {
            $('#roleLabelList').prepend(createRoleLabel(item));
        }
    });

    $('#rackSelector').CommonSelector({
        title: '机架选择',
        data: ${rackList},
        val_fun: function (item) {
            return item.rackName;
        },
        callback: function (item) {
            $('#rackNameInput').val(item.val);
            $('#rackIdInput').val(item.id);
        }
    });


    function createRoleLabel(item) {
        var _html = '<div class="pull-left">';
        _html += '<span class="label label-info">' + item.val + '</span>';
        _html += '<input type="checkbox" checked="checked" name="roleIds" value="' + item.id + '"/>';
        _html += '</div>';
        return _html;
    }

    $('#roleLabelList').on('click', 'span.label', function (e) {
        e.preventDefault();
        if ($(this).hasClass('label-info')) {
            $(this).removeClass('label-info').addClass('label-default');
            $(this).parent().find('input').prop("checked", false);
        } else {
            $(this).removeClass('label-default').addClass('label-info');
            $(this).parent().find('input').prop("checked", true);
        }
    });

    $('#saveBtn').click(function (e) {
        e.preventDefault();
        var isOk = checkInputNull($('#hostName'));
        if (isOk) {
            $('#uForm').submit();
        }
    });
</script>
</body>
</html>


