<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>商品添加</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
<div class="container-fluid">

	<!-- 包含头部文件 -->
	<jsp:include page="/admin/header.jsp"></jsp:include>

	<br><br>

	<!-- 商品添加表单 -->
	<form class="form-horizontal" action="/admin/goods_add" method="post" enctype="multipart/form-data">

		<!-- 商品名称输入框 -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">名称</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="name" required="required">
			</div>
		</div>

		<!-- 商品价格输入框 -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">价格</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="price">
			</div>
		</div>

		<!-- 商品介绍输入框 -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">介绍</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="intro">
			</div>
		</div>

		<!-- 商品库存输入框 -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">库存</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="stock">
			</div>
		</div>

		<!-- 商品封面图片上传 -->
		<div class="form-group">
			<label for="input_file" class="col-sm-1 control-label">封面图片</label>
			<div class="col-sm-6">
				<input type="file" name="cover" id="input_file" required="required">推荐尺寸: 500 * 500
			</div>
		</div>

		<!-- 商品详情图片1上传 -->
		<div class="form-group">
			<label for="input_file" class="col-sm-1 control-label">详情图片1</label>
			<div class="col-sm-6">
				<input type="file" name="image1" id="input_file" required="required">推荐尺寸: 500 * 500
			</div>
		</div>

		<!-- 商品详情图片2上传 -->
		<div class="form-group">
			<label for="input_file" class="col-sm-1 control-label">详情图片2</label>
			<div class="col-sm-6">
				<input type="file" name="image2" id="input_file" required="required">推荐尺寸: 500 * 500
			</div>
		</div>

		<!-- 商品类目选择下拉框 -->
		<div class="form-group">
			<label for="select_topic" class="col-sm-1 control-label">类目</label>
			<div class="col-sm-6">
				<select class="form-control" id="select_topic" name="typeid">
					<!-- 使用 JSTL 标签遍历 typeList，将类目添加到下拉框 -->
					<c:forEach items="${typeList }" var="t">
						<option value="${t.id }">${t.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<!-- 提交按钮 -->
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<button type="submit" class="btn btn-success">提交保存</button>
			</div>
		</div>
	</form>
</div>
</body>
</html>
