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
            <button class="btn btn-primary btn-sm" id="addBtn">
                <span class="glyphicon glyphicon-plus"></span>添加
            </button>
        </div>
        <%@include file="tabs.jsp"%>
        <table class="table gl-table">
            <thead>
            <tr>
                <th>主机名</th>
                <th>IP地址</th>
                <th>所属机架</th>
                <th>角色列表</th>
                <th style="width: 120px;text-align: center">操作</th>
            </tr>
            </thead>
            <c:if test="${page.totalPages > 0}">
            <tbody>
                <c:forEach items="${page.content}" var="host">
                    <tr>
                        <td>${host.hostName}</td>
                        <td>${host.ipAddress}</td>
                        <td>${host.rack.rackName}</td>
                        <td>
                            <c:forEach items="${host.roles}" var="role">
                                ${role.name},
                            </c:forEach>
                        </td>
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
                <td colspan="5">
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
</body>
</html>