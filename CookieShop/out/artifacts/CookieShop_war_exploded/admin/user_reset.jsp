<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>重置密码</title>
	<meta charset="utf-8"/>
	<!-- 引入 Bootstrap 样式库 -->
	<link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

	<!-- 引入后台管理头部，包括导航栏等公共部分 -->
	<jsp:include page="/admin/header.jsp"></jsp:include>

	<br><br>

	<!-- 重置密码表单，提交到 /admin/user_reset -->
	<form class="form-horizontal" action="/admin/user_reset" method="post">
		<!-- 隐藏字段：传递要重置密码的用户 ID -->
		<input type="hidden" name="id" value="${param.id }">

		<!-- 显示用户名，不可编辑 -->
		<div class="form-group">
			<label class="col-sm-1 control-label">用户名</label>
			<div class="col-sm-5">
				${param.username }
			</div>
		</div>

		<!-- 显示邮箱，不可编辑 -->
		<div class="form-group">
			<label class="col-sm-1 control-label">邮箱</label>
			<div class="col-sm-5">
				${param.email }
			</div>
		</div>

		<!-- 密码输入框，用于输入新的密码 -->
		<div class="form-group">
			<label for="input_password" class="col-sm-1 control-label">密码</label>
			<div class="col-sm-6">
				<!-- 必填字段，输入新密码 -->
				<input type="text" class="form-control" id="input_password" name="password"
					   value="" required="required">
			</div>
		</div>

		<!-- 提交按钮：提交表单以完成密码重置 -->
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<button type="submit" class="btn btn-success">提交修改</button>
			</div>
		</div>
	</form>

	<!-- 错误提示信息占位（当前未使用） -->
	<span style="color:red;"></span>

</div>
</body>
</html>
