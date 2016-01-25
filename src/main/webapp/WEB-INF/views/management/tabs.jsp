<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="nav nav-tabs">
    <li <c:if test="${activeTab == 'racks'}">class="active"</c:if>>
        <a href="${ctx}/management/racks">机架管理</a>
    </li>
    <li <c:if test="${activeTab == 'hosts'}">class="active"</c:if>>
        <a href="${ctx}/management/hosts">机器管理</a>
    </li>
    <li <c:if test="${activeTab == 'services'}">class="active"</c:if>>
        <a href="${ctx}/management/services">服务管理</a>
    </li>
    <li <c:if test="${activeTab == 'roles'}">class="active"</c:if>>
        <a href="${ctx}/management/roles">角色管理</a>
    </li>
</ul>