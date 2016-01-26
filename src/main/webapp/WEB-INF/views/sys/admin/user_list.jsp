<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/global.jsp" %>
<c:set var="pageSize" value="20"></c:set>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%@include file="../../common/head.jsp" %>
</head>
<body>
    <%@include file="../../common/header.jsp"%>
    <div class="container">
        <%@include file="../../common/sidebar.jsp"%>
        <div class="main">
            <form action="/sys/admin/yhlb"  method="get">
                <div class="query-container">
                    <div class="form-query">
                        <label for="name">用户名</label>
                        <input type="text" id="name" name="name"/>
                    </div>
                    <div class="form-query">
                        <label for="user-role">用户角色</label>
                        <select id="user-role" name="userRole" >
                            <option value="全部">全部</option>
                            <c:forEach var="t" items="${userRoles }">
                                <option value="${t}"
                                        <c:if test="${t} eq ${params['userRole']}">selected="selected"</c:if>>${t.name }</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-query">
                        <label for="isEnable">是否有效：</label>
                        <select id="isEnable" name="isEnable">
                            <option value="-1" selected="selected">全部</option>
                            <option value="0">有效</option>
                            <option value="1">已失效</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary btn-sm">查询</button>
                </div>
            </form>
            <c:if test="${page.totalPages eq 0}">
                <div class="tip-no-result">暂无条目</div>
            </c:if>
            <c:if test="${page.totalPages ne 0}">
                <table class="table gl-table" id="data-table">
                    <thead>
                    <tr>
                        <th>用户名</th>
                        <th>联系电话</th>
                        <th>电子邮件</th>
                        <th>用户角色</th>
                        <th>状态</th>
                        <th>状态操作</th>
                        <th>密码重置</th>
                        <th>删除</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${page.content}">
                        <tr data-id="${user.id}">
                            <td>${user.name }</td>
                            <td>${user.tel }</td>
                            <td>${user.mail }</td>
                            <td>${user.role.name }</td>
                            <td>
                                <c:choose>
                                    <c:when test="${!user.isEnable}">失效</c:when>
                                    <c:otherwise>有效</c:otherwise>
                                </c:choose>
                            <td>
                                <button class="btn btn-danger btn-xs enable-btn">
                                    <span class="glyphicon glyphicon-flash"></span>
                                     <c:choose>
                                        <c:when test="${user.isEnable}">禁用</c:when>
                                        <c:otherwise>激活</c:otherwise>
                                     </c:choose>
                                </button>
                            </td>
                            <td>
                                <button class="btn btn-danger btn-xs rspw-btn">
                                    <span class="glyphicon glyphicon-cog"></span>重置
                                </button>
                            </td>
                            <td>
                                <button class="btn btn-danger btn-xs del-btn">
                                    <span class="glyphicon glyphicon-trash"></span>删除
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td colspan="8">
                            <div class="pull-right">
                                <nav>
                                    <div class="pagination-info">
                                        <span>共有${page.totalElements}条</span>
                                    </div>
                                    <ul class="pagination pagination-sm gl-pagination">
                                        <li <c:if test="${0 eq page.number}">class="disabled"</c:if>>
                                            <a href="${ctx}/sys/admin/yhlb?name=${params['name']}&userRole=${params['userRole']}&isEnable=${isEnable}" aria-label="Previous">
                                                <span aria-hidden="true">&laquo;</span>
                                            </a>
                                        </li>
                                        <c:forEach begin="0" end="${page.totalPages - 1}" varStatus="status">
                                            <li <c:if test="${status.index eq page.number}">class="active"</c:if>>
                                                <a href="${ctx}/sys/admin/yhlb?page=${status.index}&name=${params['name']}&userRole=${params['userRole']}&isEnable=${isEnable}">${status.index + 1}</a>
                                            </li>
                                        </c:forEach>
                                        <li <c:if test="${page.number eq page.totalPages-1}">class="disabled"</c:if>>
                                            <a href="${ctx}/sys/admin/yhlb?page=${page.totalPages-1}&name=${params['name']}&userRole=${params['userRole']}&isEnable=${isEnable}" aria-label="Next">
                                                <span aria-hidden="true">&raquo;</span>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </div>
                        </td>
                    </tfoot>
                </table>
            </c:if>
        </div>
    </div>
    <%@include file="../../common/footer.jsp"%>
    <%@include file="../../common/script.jsp"%>
    <script>
        (function init() {
            $('#data-table').on('click', 'button.rspw-btn', function (e) {
                var trHolder = $(this).parents('tr');
                var userId = trHolder.attr('data-id');
                var d = dialog({
                    title: '提示',
                    content: '确定重置该用户密码?',
                    width: 350,
                    okValue: '确定',
                    ok: function () {
                        var data = "id=" + userId;
                        var url = "${ctx}/sys/admin/yhlb/rspw";
                        ajaxRequest(url, data, function (result) {
                            if (result.hasError) {
                                displayAlert(result.message, 'danger');
                            } else {
                                displayAlert(result.message, 'success');
                            }
                        }, function () {
                        });
                    },
                    cancelValue: '取消',
                    cancel: function () {
                    }
                });
                d.show();
            });

            $('#data-table').on('click', 'button.del-btn', function (e) {
                var trHolder = $(this).parents('tr');
                var userId = trHolder.attr('data-id');
                var d = dialog({
                    title: '提示',
                    content: '确定删除该用户?',
                    width: 350,
                    okValue: '确定',
                    ok: function () {
                        var data = "id=" + userId;
                        var url = "${ctx}/sys/admin/yhlb/delete";
                        ajaxRequest(url, data, function (result) {

                            if (result.hasError) {
                                displayAlert(result.message, 'danger');
                            } else {
                                displayAlert(result.message, 'success');
                                trHolder.remove();
                            }
                        }, function () {
                        });
                    },
                    cancelValue: '取消',
                    cancel: function () {
                    }
                });
                d.show();
            });

            $('#data-table').on('click', 'button.enable-btn', function (e) {
                var isEnable,tip;
                var trHolder = $(this).parents('tr');
                var userId = trHolder.attr('data-id');
                var isEnableStr = trHolder.find('td:eq(4)').text().trim();
                if(isEnableStr === "有效"){
                    isEnable = true;
                    tip = "确定禁用用户？"
                }else{
                    isEnable = false;
                    tip = "确定激活用户？"
                }
                var d = dialog({
                    title: '提示',
                    content: tip,
                    width: 350,
                    okValue: '确定',
                    ok: function () {
                        var data = "id=" + userId+"&isEnable="+isEnable;
                        var url = "${ctx}/sys/admin/yhlb/enable";
                        ajaxRequest(url, data, function (result) {

                            if (result.hasError) {
                                displayAlert(result.message, 'danger');
                            } else {
                                displayAlert(result.message, 'success');
                            }
                        }, function () {
                        });
                    },
                    cancelValue: '取消',
                    cancel: function () {
                    }
                });
                d.show();
            });
        })();
    </script>
</body>
</html>


