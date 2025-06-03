<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>支付</title>  <!-- 页面标题 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">  <!-- 设置响应式布局 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  <!-- 设置字符编码为UTF-8 -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">  <!-- 引入Bootstrap样式表 -->
	<link type="text/css" rel="stylesheet" href="css/style.css">  <!-- 引入自定义样式表 -->
	<script type="text/javascript" src="js/jquery.min.js"></script>  <!-- 引入jQuery -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>  <!-- 引入Bootstrap JavaScript -->
	<script type="text/javascript" src="layer/layer.js"></script>  <!-- 引入Layer弹出层库 -->
	<script type="text/javascript" src="js/cart.js"></script>  <!-- 引入购物车的JS -->
</head>
<body>

<!--header-->
<jsp:include page="header.jsp"></jsp:include>  <!-- 包含公共头部 -->
<!--//header-->

<!--订单信息表单-->
<div class="cart-items">
	<div class="container">
		<h2>确认收货信息</h2>  <!-- 显示页面标题 -->

		<!-- 确认收货信息的表单 -->
		<form class="form-horizontal" action="/order_confirm" method="post" id="payform">
			<!-- 收货人 -->
			<div class="row">
				<label class="control-label col-md-1">收货人</label>
				<div class="col-md-6">
					<input type="text" class="form-control" name="name" value="${user.name }" style="height:auto;padding:10px;" placeholder="输入收货人" required="required"><br>
				</div>
			</div>
			<!-- 收货电话 -->
			<div class="row">
				<label class="control-label col-md-1">收货电话</label>
				<div class="col-md-6">
					<input type="text" class="form-control" name="phone" value="${user.phone }" style="height:auto;padding:10px;" placeholder="输入收货电话" required="required"><br>
				</div>
			</div>
			<!-- 收货地址 -->
			<div class="row">
				<label class="control-label col-md-1">收货地址</label>
				<div class="col-md-6">
					<input type="text" class="form-control" name="address" value="${user.address }" style="height:auto;padding:10px;" placeholder="输入收货地址" required="required"><br>
				</div>
			</div>

			<br><hr><br>

			<h2>选择支付方式</h2>  <!-- 显示支付方式标题 -->
			<h3>支付金额: ${order.total }</h3><br><br>  <!-- 显示支付金额 -->

			<!-- 微信支付 -->
			<div class="col-sm-6 col-md-4 col-lg-3 " >
				<label>
					<div class="thumbnail">
						<input type="radio" name="paytype" value="1" checked="checked" />  <!-- 默认选中微信支付 -->
						<img src="images/wechat.jpg" alt="微信支付">  <!-- 显示微信支付图标 -->
					</div>
				</label>
			</div>

			<!-- 支付宝支付 -->
			<div class="col-sm-6 col-md-4 col-lg-3 " >
				<label>
					<div class="thumbnail">
						<input type="radio" name="paytype" value="2"  />  <!-- 支付宝支付 -->
						<img src="images/alipay.jpg" alt="支付宝支付">  <!-- 显示支付宝支付图标 -->
					</div>
				</label>
			</div>

			<!-- 货到付款支付方式（注释掉了）-->
			<%--
            <div class="col-sm-6 col-md-4 col-lg-3 ">
                <label>
                    <div class="thumbnail">
                        <input type="radio" name="paytype" value="3"  />  <!-- 货到付款 -->
                        <img src="images/offline.jpg" alt="货到付款">
                    </div>
                </label>
            </div>
            --%>

			<div class="clearfix"> </div>
			<!-- 提交按钮 -->
			<div class="register-but text-center">
				<input type="submit" value="确认订单">  <!-- 确认订单按钮 -->
				<div class="clearfix"> </div>
			</div>
		</form>
	</div>
</div>

<!--footer-->
<jsp:include page="footer.jsp"></jsp:include>  <!-- 包含公共页脚 -->
<!--//footer-->

<script type="text/javascript">
	function dopay(paytype){
		$("#paytype").val(paytype);  // 设置支付类型
		$("#payform").submit();  // 提交表单
	}
</script>

</body>
</html>
