<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>客户添加</title>
	<meta charset="utf-8" />
	<!-- 引入 Bootstrap 样式库 -->
	<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
<div class="container-fluid">

	<!-- 引入后台管理头部，包括导航栏等公共部分 -->
	<jsp:include page="/admin/header.jsp"></jsp:include>

	<!-- 如果存在失败提示信息，则以红色警告框显示 -->
	<c:if test="${!empty failMsg }">
		<div class="alert alert-danger">${failMsg }</div>
	</c:if>

	<br><br>

	<!-- 客户添加表单，提交到 /admin/user_add -->
	<form class="form-horizontal" action="/admin/user_add" method="post">
		<!-- 用户名 -->
		<div class="form-group">
			<label for="input_username" class="col-sm-1 control-label">用户名</label>
			<div class="col-sm-6">
				<!-- 如果表单回显，则填充原用户名 -->
				<input type="text" class="form-control" id="input_username" name="username"
					   required="required" value="${u.username }" />
			</div>
		</div>

		<!-- 邮箱 -->
		<div class="form-group">
			<label for="input_email" class="col-sm-1 control-label">邮箱</label>
			<div class="col-sm-6">
				<!-- 如果表单回显，则填充原邮箱 -->
				<input type="text" class="form-control" id="input_email" name="email"
					   required="required" value="${u.email }" />
			</div>
		</div>

		<!-- 密码 -->
		<div class="form-group">
			<label for="input_password" class="col-sm-1 control-label">密码</label>
			<div class="col-sm-6">
				<!-- 如果表单回显，则填充原密码 -->
				<input type="password" class="form-control" id="input_password" name="password"
					   required="required" value="${u.password }" />
			</div>
		</div>

		<!-- 收货人姓名 -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">收货人</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="name"
					   value="${u.name }" />
			</div>
		</div>

		<!-- 电话 -->
		<div class="form-group">
			<label for="input_phone" class="col-sm-1 control-label">电话</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_phone" name="phone"
					   value="${u.phone }" />
			</div>
		</div>

		<!-- 地址 -->
		<div class="form-group">
			<label for="input_address" class="col-sm-1 control-label">地址</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_address" name="address"
					   value="${u.address }" />
			</div>
		</div>

		<!-- 提交按钮 -->
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<button type="submit" class="btn btn-success">提交保存</button>
			</div>
		</div>
	</form>

	<!-- 额外的错误提示占位，当前未使用 -->
	<span style="color:red;"></span>
</div>
</body>
</html>
