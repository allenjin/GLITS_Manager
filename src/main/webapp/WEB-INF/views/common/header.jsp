<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="header">
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">导航</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${ctx}/">${siteConfig.title}</a>
            </div>
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="active">
                        <a href="${ctx}/physical/">物理视图</a>
                    </li>
                    <li>
                        <a href="${ctx}/data-access/">数据接入</a>
                    </li>
                    <li>
                        <a href="${ctx}/data-store/">数据存储</a>
                    </li>
                    <li>
                        <a href="${ctx}/timed-task/">定时任务</a>
                    </li>
                    <li>
                        <a href="${ctx}/management/">系统管理</a>
                    </li>
                </ul>
                <ul class="user-panel-list pull-right">
                    <li>
                        <a href="#">
                            <span class="glyphicon glyphicon-bell"></span>
                        </a>
                    </li>
                    <li>
                        <a href="#">
                            <span class="glyphicon glyphicon-user"></span>
                            用户名
                        </a>
                    </li>
                    <li>
                        <a href="#">
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