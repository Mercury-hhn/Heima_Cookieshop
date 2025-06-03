<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>商品编辑</title>
	<meta charset="utf-8" />
	<link rel="stylesheet" href="css/bootstrap.css" />
</head>
<body>
<div class="container-fluid">

	<!-- 引入页面头部 -->
	<jsp:include page="/admin/header.jsp"></jsp:include>

	<br><br>

	<!-- 商品编辑表单 -->
	<form class="form-horizontal" action="/admin/goods_edit" method="post" enctype="multipart/form-data">

		<!-- 隐藏字段：商品ID、封面图片、详情图片和分页信息 -->
		<input type="hidden" name="id" value="${g.id }"/>
		<input type="hidden" name="cover" value="${g.cover }"/>
		<input type="hidden" name="image1" value="${g.image1 }"/>
		<input type="hidden" name="image2" value="${g.image2 }"/>
		<input type="hidden" name="pageNo" value="${param.pageNo }"/>
		<input type="hidden" name="type" value="${param.type }"/>

		<!-- 商品名称输入框 -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">名称</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="name" value="${g.name }" required="required">
			</div>
		</div>

		<!-- 商品价格输入框 -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">价格</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="price" value="${g.price }">
			</div>
		</div>

		<!-- 商品介绍输入框 -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">介绍</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="intro" value="${g.intro }">
			</div>
		</div>

		<!-- 商品库存输入框 -->
		<div class="form-group">
			<label for="input_name" class="col-sm-1 control-label">库存</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="input_name" name="stock" value="${g.stock }">
			</div>
		</div>

		<!-- 商品封面图片上传 -->
		<div class="form-group">
			<label for="input_file" class="col-sm-1 control-label">封面图片</label>
			<div class="col-sm-6">
				<!-- 显示当前封面图片 -->
				<img src="${pageContext.request.contextPath }${g.cover }" width="100" height="100"/>
				<!-- 上传新的封面图片 -->
				<input type="file" name="cover" id="input_file">推荐尺寸: 500 * 500
			</div>
		</div>

		<!-- 商品详情图片1上传 -->
		<div class="form-group">
			<label for="input_file" class="col-sm-1 control-label">详情图片1</label>
			<div class="col-sm-6">
				<!-- 显示当前详情图片1 -->
				<img src="${pageContext.request.contextPath }${g.image1 }" width="100" height="100"/>
				<!-- 上传新的详情图片1 -->
				<input type="file" name="image1" id="input_file">推荐尺寸: 500 * 500
			</div>
		</div>

		<!-- 商品详情图片2上传 -->
		<div class="form-group">
			<label for="input_file" class="col-sm-1 control-label">详情图片2</label>
			<div class="col-sm-6">
				<!-- 显示当前详情图片2 -->
				<img src="${pageContext.request.contextPath }${g.image2 }" width="100" height="100"/>
				<!-- 上传新的详情图片2 -->
				<input type="file" name="image2" id="input_file">推荐尺寸: 500 * 500
			</div>
		</div>

		<!-- 商品类目选择 -->
		<div class="form-group">
			<label for="select_topic" class="col-sm-1 control-label">类目</label>
			<div class="col-sm-6">
				<select class="form-control" id="select_topic" name="typeid">
					<!-- 使用 JSTL 标签遍历 typeList，将类目添加到下拉框 -->
					<c:forEach items="${typeList }" var="t">
						<!-- 如果当前商品类型匹配，设置为选中状态 -->
						<option <c:if test="${t.id==g.type.id }">selected="selected"</c:if> value="${t.id }">${t.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>

		<!-- 提交按钮 -->
		<div class="form-group">
			<div class="col-sm-offset-1 col-sm-10">
				<button type="submit" class="btn btn-success">提交修改</button>
			</div>
		</div>
	</form>
</div>
</body>
</html>
