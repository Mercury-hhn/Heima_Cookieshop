<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>我的订单</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">  <!-- 引入Bootstrap样式表 -->
	<link type="text/css" rel="stylesheet" href="css/style.css">      <!-- 引入自定义样式表 -->
	<script type="text/javascript" src="js/jquery.min.js"></script>    <!-- 引入jQuery -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>  <!-- 引入Bootstrap的JavaScript -->
	<script type="text/javascript" src="layer/layer.js"></script>      <!-- 引入Layer弹出层库 -->
	<script type="text/javascript" src="js/cart.js"></script>           <!-- 引入购物车的相关JS -->
</head>
<body>

<!--header-->
<jsp:include page="header.jsp">  <!-- 包含公共头部 -->
	<jsp:param name="flag" value="5"></jsp:param>  <!-- 设置标识符，表示这是“我的订单”页面 -->
</jsp:include>
<!--//header-->

<!--cart-items-->
<div class="cart-items">
	<div class="container">

		<h2>我的订单</h2>  <!-- 页面标题 -->

		<!-- 订单表格 -->
		<table class="table table-bordered table-hover">
			<tr>
				<th width="10%">ID</th>  <!-- 订单ID列 -->
				<th width="10%">总价</th>  <!-- 订单总价列 -->
				<th width="20%">商品详情</th>  <!-- 商品详情列 -->
				<th width="30%">收货信息</th>  <!-- 收货信息列 -->
				<th width="10%">订单状态</th>  <!-- 订单状态列 -->
				<th width="10%">支付方式</th>  <!-- 支付方式列 -->
				<th width="10%">下单时间</th>  <!-- 下单时间列 -->
			</tr>

			<!-- 遍历订单列表 -->
			<c:forEach items="${orderList }" var="order">
				<tr>
					<td><p>${order.id }</p></td>  <!-- 显示订单ID -->
					<td><p>${order.total }</p></td>  <!-- 显示订单总价 -->
					<td>
						<!-- 遍历该订单中的所有商品 -->
						<c:forEach items="${order.itemList }" var="item">
							<p>${item.goodsName }(${item.price }) x ${item.amount }</p>  <!-- 显示商品名称、价格及数量 -->
						</c:forEach>
					</td>
					<td>
						<p>${order.name }</p>  <!-- 收货人姓名 -->
						<p>${order.phone }</p>  <!-- 收货人电话 -->
						<p>${order.address }</p>  <!-- 收货人地址 -->
					</td>
					<td>
						<p>
							<!-- 判断订单状态并显示不同的文本 -->
							<c:if test="${order.status==2 }"><span style="color:red;">已付款</span></c:if>
							<c:if test="${order.status==3 }"><span style="color:green;">已发货</span></c:if>
							<c:if test="${order.status==4 }"><span style="color:black;">已完成</span></c:if>
						</p>
					</td>
					<td>
						<p>
							<!-- 根据支付方式的类型显示对应的支付方式 -->
							<c:if test="${order.paytype==1 }">微信</c:if>
							<c:if test="${order.paytype==2 }">支付宝</c:if>
							<c:if test="${order.paytype==3 }">货到付款</c:if>
						</p>
					</td>
					<td><p>${order.datetime }</p></td>  <!-- 显示下单时间 -->
				</tr>
			</c:forEach>
		</table>

	</div>
</div>
<!--//cart-items-->

<!--footer-->
<jsp:include page="footer.jsp"></jsp:include>  <!-- 包含公共页脚 -->
<!--//footer-->

</body>
</html>
