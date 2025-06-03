<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>订单列表</title>
	<link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

	<!-- 引入头部页面，通常包含网站的导航栏 -->
	<jsp:include page="header.jsp"></jsp:include>

	<br>

	<!-- 订单状态筛选选项，展示不同的订单状态 -->
	<ul role="tablist" class="nav nav-tabs">
		<li <c:if test="${status==0 }">class="active"</c:if> role="presentation"><a href="/admin/order_list">全部订单</a></li>
		<li <c:if test="${status==1 }">class="active"</c:if> role="presentation"><a href="/admin/order_list?status=1">未付款</a></li>
		<li <c:if test="${status==2 }">class="active"</c:if> role="presentation"><a href="/admin/order_list?status=2">已付款</a></li>
		<li <c:if test="${status==3 }">class="active"</c:if> role="presentation"><a href="/admin/order_list?status=3">配送中</a></li>
		<li <c:if test="${status==4 }">class="active"</c:if> role="presentation"><a href="/admin/order_list?status=4">已完成</a></li>
	</ul>

	<br>

	<!-- 订单列表表格 -->
	<table class="table table-bordered table-hover">

		<!-- 表头部分 -->
		<tr>
			<th width="5%">ID</th>
			<th width="5%">总价</th>
			<th width="15%">商品详情</th>
			<th width="20%">收货信息</th>
			<th width="10%">订单状态</th>
			<th width="10%">支付方式</th>
			<th width="10%">下单用户</th>
			<th width="10%">下单时间</th>
			<th width="10%">操作</th>
		</tr>

		<!-- 循环输出每个订单 -->
		<c:forEach items="${p.list }" var="order">
			<tr>
				<td><p>${order.id }</p></td>  <!-- 显示订单ID -->
				<td><p>${order.total }</p></td>  <!-- 显示订单总价 -->

				<!-- 显示商品详情 -->
				<td>
					<c:forEach items="${order.itemList }" var="item">
						<p>${item.goodsName }(${item.price }) x ${item.amount}</p>  <!-- 商品名称、价格和数量 -->
					</c:forEach>
				</td>

				<!-- 显示收货信息 -->
				<td>
					<p>${order.name }</p>  <!-- 收货人姓名 -->
					<p>${order.phone }</p>  <!-- 收货人电话 -->
					<p>${order.address }</p>  <!-- 收货人地址 -->
				</td>

				<!-- 显示订单状态 -->
				<td>
					<p>
						<c:if test="${order.status==2 }"><span style="color:red;">已付款</span></c:if>  <!-- 已付款状态 -->
						<c:if test="${order.status==3 }"><span style="color:green;">已发货</span></c:if>  <!-- 已发货状态 -->
						<c:if test="${order.status==4 }"><span style="color:black;">已完成</span></c:if>  <!-- 已完成状态 -->
					</p>
				</td>

				<!-- 显示支付方式 -->
				<td>
					<p>
						<c:if test="${order.paytype==1 }">微信</c:if>  <!-- 微信支付 -->
						<c:if test="${order.paytype==2 }">支付宝</c:if>  <!-- 支付宝支付 -->
						<c:if test="${order.paytype==3 }">货到付款</c:if>  <!-- 货到付款 -->
					</p>
				</td>

				<!-- 显示下单用户 -->
				<td><p>${order.user.username }</p></td>  <!-- 用户名 -->

				<!-- 显示下单时间 -->
				<td><p>${order.datetime }</p></td>  <!-- 下单时间 -->

				<!-- 操作按钮 -->
				<td>
					<!-- 如果订单状态是“已付款”，显示发货按钮 -->
					<c:if test="${order.status==2 }">
						<a class="btn btn-success" href="/admin/order_status?id=${order.id }&status=3">发货</a>
					</c:if>
					<!-- 如果订单状态是“已发货”，显示完成按钮 -->
					<c:if test="${order.status==3 }">
						<a class="btn btn-warning" href="/admin/order_status?id=${order.id }&status=4">完成</a>
					</c:if>
					<!-- 删除按钮，删除订单 -->
					<a class="btn btn-danger" href="/admin/order_delete?id=${order.id }&pageNumber=${p.pageNumber}&status=${status}">删除</a>
				</td>
			</tr>
		</c:forEach>

	</table>

	<br>
	<!-- 分页组件 -->
	<jsp:include page="/page.jsp">
		<jsp:param value="/admin/order_list" name="url"/>
		<jsp:param value="&status=${status}" name="param"/>
	</jsp:include>
	<br>
</div>
</body>
</html>
