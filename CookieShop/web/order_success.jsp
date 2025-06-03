<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>支付成功</title>  <!-- 页面标题，显示“支付成功” -->
	<meta name="viewport" content="width=device-width, initial-scale=1">  <!-- 设置响应式布局 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  <!-- 设置字符编码为UTF-8 -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">  <!-- 引入Bootstrap样式表 -->
	<link type="text/css" rel="stylesheet" href="css/style.css">  <!-- 引入自定义样式表 -->
	<script type="text/javascript" src="js/jquery.min.js"></script>  <!-- 引入jQuery库 -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>  <!-- 引入Bootstrap的JavaScript库 -->
	<script type="text/javascript" src="layer/layer.js"></script>  <!-- 引入Layer弹出层库 -->
	<script type="text/javascript" src="js/cart.js"></script>  <!-- 引入购物车的JS -->
</head>
<body>

<!--header-->
<jsp:include page="header.jsp"></jsp:include>  <!-- 包含公共头部 -->
<!--//header-->

<!--cart-items-->
<div class="cart-items">
	<div class="container">

		<!-- 判断是否有支付成功的提示信息 -->
		<c:if test="${!empty msg }">
			<div class="alert alert-success">${msg }</div>  <!-- 显示支付成功的消息 -->
		</c:if>

		<!-- 提供跳转到订单列表的按钮 -->
		<p><a class="btn btn-success" href="/order_list">查看我的订单</a></p>
	</div>
</div>
<!--//cart-items-->

<!--footer-->
<jsp:include page="footer.jsp"></jsp:include>  <!-- 包含公共页脚 -->
<!--//footer-->

</body>
</html>
