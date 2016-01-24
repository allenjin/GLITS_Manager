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
                        <a href="#">
                            <span class="glyphicon glyphicon-bell"></span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span class="glyphicon glyphicon-user"></span>
                            <sec:authentication property="principal.name"/>
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