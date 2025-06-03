<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>类目编辑</title>
	<meta charset="utf-8"/>
	<link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

	<!-- 引入网站的头部，包括导航栏等公共部分 -->
	<jsp:include page="/admin/header.jsp"></jsp:include>

	<br><br>

	<!-- 类目编辑表单 -->
	<form class="form-horizontal" action="/admin/type_edit" method="post">
		<!-- 传递类目的ID，用于编辑特定类目 -->
		<input type="hidden" name="id" value="${param.id }">

		<!-- 输入框，用于编辑类目名称 -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">类目名称</label>
			<div class="col-sm-6">
				<!-- 设置输入框默认值为传递过来的类目名称 -->
				<input type="text" class="form-control" id="input_name" name="name" value="${param.name }" required="required">
			</div>
		</div>

		<!-- 提交按钮 -->
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<button type="submit" class="btn btn-success">提交修改</button>
			</div>
		</div>
	</form>

	<!-- 错误提示框 -->
	<span style="color:red;"></span>

</div>
</body>
</html>
