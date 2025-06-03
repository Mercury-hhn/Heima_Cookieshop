<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>客户修改</title>
	<meta charset="utf-8"/>
    <!-- 引入 Bootstrap CSS 样式 -->
	<link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

    <!-- 包含后台管理的头部导航 -->
	<jsp:include page="/admin/header.jsp"></jsp:include>

	<br><br>

    <!-- 客户信息修改表单 -->
	<form class="form-horizontal" action="/admin/user_edit" method="post">
	 <!-- 隐藏字段：用户 ID，用于提交到后端进行更新 -->
		<input type="hidden" name="id" value="${u.id }">
		<!-- 显示用户名（只读，不可修改） -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">用户名</label>
			<div class="col-sm-5">${u.username }</div>
		</div>

        <!-- 显示邮箱（只读，不可修改） -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">邮箱</label>
			<div class="col-sm-5">${u.email }</div>
		</div>
        </div>

        <!-- 收货人姓名输入框 -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">收货人</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="name" value="${u.name }">
			</div>
		</div>

        <!-- 电话输入框 -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">电话</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="phone" value="${u.phone }">
			</div>
		</div>

        <!-- 地址输入框 -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">地址</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="address" value="${u.address }">
			</div>
		</div>

        <!-- 提交修改按钮 -->
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<button type="submit" class="btn btn-success">提交修改</button>
			</div>
		</div>
	</form>

    <!-- 错误提示占位，如需显示错误则填充内容 -->
	<span style="color:red;"></span>

</div>
</body>
</html>