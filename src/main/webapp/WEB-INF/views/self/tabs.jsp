<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="nav nav-tabs">
    <li <c:if test="${activeTab == 'info'}">class="active"</c:if>>
        <a href="${ctx}/self/info">个人信息</a>
    </li>
    <li <c:if test="${activeTab == 'message'}">class="active"</c:if>>
        <a href="${ctx}/self/message">我的消息</a>
    </li>
</ul>