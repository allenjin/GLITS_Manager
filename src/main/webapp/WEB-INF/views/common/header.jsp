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
                <a class="navbar-brand" href="${ctx}/">运维管理系统</a>
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
                        <a href="${ctx}/data-access/">数据存储</a>
                    </li>
                    <li>
                        <a href="${ctx}/data-access/">定时任务</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>