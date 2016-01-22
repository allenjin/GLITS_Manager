<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../common/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="../../common/head.jsp" %>
	<style type="text/css">
		.info-container{
			padding:25px;
		}
		.field-row{
			padding-top:15px;
		}
		.field-row span{
			display:inline-block;
			width:80px;
		}
	</style>
</head>
<body>
<%@include file="../../common/header.jsp"%>
<div class="container">
	<c:if test="${!empty opResult }">
		<c:if test="${!opResult.hasError }">
			<div class="op-success">
				<span>${opResult.message }</span>
			</div>
		</c:if>
		<c:if test="${opResult.hasError }">
			<div class="op-error">
				<span>${opResult.message }</span>
			</div>
		</c:if>
	</c:if>
	<form action="${ctx }/sys/self/info" method="post">
		<div class="field-row clearfix">
			<div class="default-field">
				<span>姓名</span>
				<input type="text" readonly="readonly" value="${user.name }">
			</div>
		</div>
		<div class="field-row clearfix">
			<div class="default-field">
				<span>联系方式</span>
				<input type="text" value="${user.tel }" name="tel">
			</div>
		</div>
		<div class="field-row clearfix">
			<div class="default-field">
				<span>邮箱地址</span>
				<input type="text" value="${user.mail }" name="mail">
			</div>
		</div>
		<div class="field-row clearfix">
			<input type="submit" value="修改" class="btn btn-primary">
		</div>
	</form>
</div>
<%@include file="../../common/footer.jsp"%>
<%@include file="../../common/script.jsp"%>
</body>
</html>