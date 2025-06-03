<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>类目列表</title>
	<meta charset="utf-8"/>
	<link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

	<!-- 引入网站的头部，包括导航栏等公共部分 -->
	<jsp:include page="/admin/header.jsp"></jsp:include>

	<br>

	<!-- 添加类目的表单 -->
	<div>
		<form class="form-inline" method="post" action="/admin/type_add">
			<!-- 输入框用于输入类目名称 -->
			<input type="text" class="form-control" id="input_name" name="name" placeholder="输入类目名称" required="required" style="width: 500px">
			<!-- 提交按钮，点击提交类目名称 -->
			<input type="submit" class="btn btn-warning" value="添加类目"/>
		</form>
	</div>
	<br/>

	<!-- 如果有成功的提示消息，则显示 -->
	<c:if test="${!empty msg }">
		<div class="alert alert-success">${msg }</div>
	</c:if>

	<!-- 如果有失败的提示消息，则显示 -->
	<c:if test="${!empty failMsg }">
		<div class="alert alert-danger">${failMsg }</div>
	</c:if>

	<br>

	<!-- 类目列表表格 -->
	<table class="table table-bordered table-hover">

		<!-- 表格的表头 -->
		<tr>
			<th width="5%">ID</th>
			<th width="10%">名称</th>
			<th width="10%">操作</th>
		</tr>

		<!-- 遍历所有类目，展示类目的ID、名称以及操作按钮 -->
		<c:forEach items="${list }" var="t">
			<tr>
				<!-- 显示类目的ID -->
				<td><p>${t.id }</p></td>
				<!-- 显示类目的名称 -->
				<td><p>${t.name }</p></td>
				<td>
					<!-- 修改类目的链接 -->
					<a class="btn btn-primary" href="/admin/type_edit.jsp?id=${t.id }&name=${t.encodeName }">修改</a>
					<!-- 删除类目的链接 -->
					<a class="btn btn-danger" href="/admin/type_delete?id=${t.id }">删除</a>
				</td>
			</tr>
		</c:forEach>

	</table>

</div>
</body>
</html>
