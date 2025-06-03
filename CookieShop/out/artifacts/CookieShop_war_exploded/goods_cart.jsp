<%--
    页面：购物车页面 cart.jsp
    功能：展示用户当前购物车中的商品列表及总金额，并提供增加、减少、删除、提交订单等操作按钮
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>购物车</title>
	<!-- 适配移动端视窗 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 页面字符编码 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!-- 引入 Bootstrap 样式 -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<!-- 引入自定义样式 -->
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<!-- 引入 jQuery 库 -->
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<!-- 引入 Bootstrap 脚本 -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<!-- 引入弹层插件 layer.js -->
	<script type="text/javascript" src="layer/layer.js"></script>
	<!-- 引入购物车操作脚本（增加、减少、删除等） -->
	<script type="text/javascript" src="js/cart.js"></script>
</head>
<body>

<!--—————— 头部 header ——————-->
<jsp:include page="header.jsp">
	<!-- 标记当前导航项为“购物车” -->
	<jsp:param name="flag" value="7"></jsp:param>
</jsp:include>
<!--//header-->

<!--—————— 购物车项目 cart-items ——————-->
<div class="cart-items">
	<div class="container">

		<!-- 页标题 -->
		<h2>我的购物车</h2>

		<!-- 循环遍历 session 中 order 对象的 itemMap，item.key 为商品 id，item.value 为购物项详情 -->
		<c:forEach items="${order.itemMap }" var="item">
			<!-- 单个购物项外容器，使用 Bootstrap 栅格 col-md-6 -->
			<div class="cart-header col-md-6">
				<div class="cart-sec simpleCart_shelfItem">
					<!-- 商品图片区域 -->
					<div class="cart-item cyc">
						<a href="/goods_detail?id=${item.key}">
							<!-- EL 表达式拼接图片路径 -->
							<img src="${pageContext.request.contextPath }${item.value.goods.cover}" class="img-responsive">
						</a>
					</div>
					<!-- 商品信息区域 -->
					<div class="cart-item-info">
						<!-- 商品名称，可点击跳转到详情页 -->
						<h3>
							<a href="/goods_detail?id=${item.key}">
									${item.value.goods.name}
							</a>
						</h3>
						<!-- 单价信息 -->
						<h3><span>单价: ¥ ${item.value.price}</span></h3>
						<!-- 数量信息 -->
						<h3><span>数量: ${item.value.amount}</span></h3>
						<!-- 增加数量操作按钮，调用 cart.js 中的 buy() -->
						<a class="btn btn-info" href="javascript:buy(${item.key});">增加</a>
						<!-- 减少数量操作按钮，调用 cart.js 中的 lessen() -->
						<a class="btn btn-warning" href="javascript:lessen(${item.key});">减少</a>
						<!-- 删除本项操作按钮，调用 cart.js 中的 deletes() -->
						<a class="btn btn-danger" href="javascript:deletes(${item.key});">删除</a>
					</div>
					<!-- 清除浮动 -->
					<div class="clearfix"></div>
				</div>
			</div>
		</c:forEach>

		<!--—————— 订单总金额及提交按钮 ——————-->
		<div class="cart-header col-md-12">
			<hr> <!-- 分隔线 -->
			<!-- 显示订单总金额 -->
			<h3>订单总金额: ¥ ${order.total}</h3>
			<!-- 提交订单按钮，跳转到提交订单页面 -->
			<a class="btn btn-success btn-lg" style="margin-left:74%" href="/order_submit">提交订单</a>
		</div>

	</div>
</div>
<!--//cart-items-->

<!--—————— 底部 footer ——————-->
<jsp:include page="footer.jsp"></jsp:include>
<!--//footer-->

</body>
</html>
