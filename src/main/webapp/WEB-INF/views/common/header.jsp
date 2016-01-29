<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div class="header">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="${ctx}/">${siteConfig.title}</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

                <ul class="user-panel-list pull-right">
                    <li>
                        <a href="${ctx}/self/message">
                            <span class="glyphicon glyphicon-bell"></span>
                        </a>
                    </li>
                    <li>
                        <div class="dropdown">
                            <a href="#" class="dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                                <span class="glyphicon glyphicon-user"></span>
                                <sec:authentication property="principal.name"/>
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                                <li><a href="${ctx}/self/info">个人信息</a></li>
                                <li><a href="${ctx}/self/password">密码修改</a></li>
                            </ul>
                        </div>

                        </a>
                    </li>
                    <li>
                        <a href="/logout">
                            <span class="glyphicon glyphicon-log-out"></span>
                            退出
                        </a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>