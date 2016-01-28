<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="nav nav-tabs">
    <li <c:if test="${activeTab == 'users'}">class="active"</c:if>>
        <a href="${ctx}/settings/users">用户管理</a>
    </li>
</ul>