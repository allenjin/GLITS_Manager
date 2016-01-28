<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="sidebar">
    <ul>
        <li <c:if test="${menuItem eq 'physical'}">class="active"</c:if>>
            <a href="${ctx}/physical/">
                <span class="glyphicon glyphicon-equalizer"></span>
                物理视图
            </a>
        </li>
        <li <c:if test="${menuItem eq 'data-access'}">class="active"</c:if>>
            <a href="${ctx}/data-access/">
                <span class="glyphicon glyphicon-flash"></span>
                数据接入
            </a>
        </li>
        <li <c:if test="${menuItem eq 'data-store'}">class="active"</c:if>>
            <a href="${ctx}/data-store/">
                <span class="glyphicon glyphicon-hdd"></span>
                数据存储
            </a>
        </li>
        <li <c:if test="${menuItem eq 'timed-task'}">class="active"</c:if>>
            <a href="${ctx}/timed-task/">
                <span class="glyphicon glyphicon-hourglass"></span>
                定时任务
            </a>
        </li>
        <li <c:if test="${menuItem eq 'management'}">class="active"</c:if>>
            <a href="${ctx}/management/">
                <span class="glyphicon glyphicon-dashboard"></span>
                平台管理
            </a>
        </li>
        <sec:authorize access="hasRole('ROLE_ADMIN')">
            <li <c:if test="${menuItem eq 'settings'}">class="active"</c:if>>
                <a href="${ctx}/settings/">
                    <span class="glyphicon glyphicon-cog"></span>
                    系统设置
                </a>
            </li>
        </sec:authorize>
    </ul>
    <div class="copyright">
        <p>Copyright &copy; 2015 <br/>
            <a href="http://www.gl-data.com">Grandland Inc.</a>
        </p>
    </div>
</div>