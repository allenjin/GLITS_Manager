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
    <c:forEach items="${racks}" var="rack">
        <div class="panel panel-default rack-panel">
            <div class="panel-heading">
                ${rack.rackName}
            </div>
            <div class="panel-body">
                <c:forEach items="${rack.hosts}" var="host">
                    <div class="machine-item">
                        <a href="${ctx}/physical/host/${host.id}"
                        <c:choose>
                            <c:when test="${host.status eq 'NORMAL'}">
                                class="label label-success"
                            </c:when>
                            <c:when test="${host.status eq 'OFFLINE'}">
                                class="label label-default"
                            </c:when>
                            <c:when test="${host.status eq 'WARNING'}">
                                class="label label-warning"
                            </c:when>
                            <c:when test="${host.status eq 'RISKY'}">
                                class="label label-danger"
                            </c:when>
                            <c:otherwise>
                                class="label label-info"
                            </c:otherwise>
                        </c:choose>
                        >${host.hostName}</a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
    </div>
</div>
<%@include file="../common/footer.jsp" %>
<%@include file="../common/script.jsp" %>
</body>
</html>