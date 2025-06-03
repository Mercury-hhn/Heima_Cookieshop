<%@ page contentType="text/html;charset=UTF-8" language="java" %>  <!-- 设置页面内容类型为UTF-8的HTML -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  <!-- 引入JSTL标签库，用于条件判断等 -->

<!DOCTYPE html>
<html>
<head>
	<title>用户注册</title>  <!-- 页面标题 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">  <!-- 设置视口，适应移动设备 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  <!-- 设置字符集为UTF-8 -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">  <!-- 引入Bootstrap样式 -->
	<link type="text/css" rel="stylesheet" href="css/style.css">  <!-- 引入自定义样式 -->
	<script type="text/javascript" src="js/jquery.min.js"></script>  <!-- 引入jQuery库 -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>  <!-- 引入Bootstrap JavaScript库 -->
	<script type="text/javascript" src="js/simpleCart.min.js"></script>  <!-- 引入购物车相关脚本 -->
</head>
<body>

<!-- 引入头部内容，flag为10用来设置当前导航栏为注册页面 -->
<jsp:include page="/header.jsp">
	<jsp:param name="flag" value="10"></jsp:param>  <!-- 参数flag用于激活当前页面的导航项 -->
</jsp:include>
<!--//header-->

<!-- 用户注册表单 -->
<div class="account">
	<div class="container">
		<div class="register">
			<!-- 如果有msg（如错误或成功消息），则显示 -->
			<c:if test="${!empty msg }">
				<div class="alert alert-danger">${msg }</div>  <!-- 显示错误消息 -->
			</c:if>

			<!-- 注册表单 -->
			<form action="/user_rigister" method="post">  <!-- 表单提交到/user_register，表示用户提交注册请求 -->
				<div class="register-top-grid">
					<h3>注册新用户</h3>  <!-- 表单标题 -->

					<!-- 用户名输入框 -->
					<div class="input">
						<span>用户名 <label style="color:red;">*</label></span>  <!-- 必填字段 -->
						<input type="text" name="username" placeholder="请输入用户名" required="required">  <!-- 必填项，用户输入用户名 -->
					</div>

					<!-- 邮箱输入框 -->
					<div class="input">
						<span>邮箱 <label style="color:red;">*</label></span>  <!-- 必填字段 -->
						<input type="text" name="email" placeholder="请输入邮箱" required="required">  <!-- 必填项，用户输入邮箱 -->
					</div>

					<!-- 密码输入框 -->
					<div class="input">
						<span>密码 <label style="color:red;">*</label></span>  <!-- 必填字段 -->
						<input type="password" name="password" placeholder="请输入密码" required="required">  <!-- 必填项，用户输入密码 -->
					</div>

					<!-- 收货人输入框 -->
					<div class="input">
						<span>收货人<label></label></span>
						<input type="text" name="name" placeholder="请输入收货人">  <!-- 用户输入收货人 -->
					</div>

					<!-- 收货电话输入框 -->
					<div class="input">
						<span>收货电话<label></label></span>
						<input type="text" name="phone" placeholder="请输入收货电话">  <!-- 用户输入收货电话 -->
					</div>

					<!-- 收货地址输入框 -->
					<div class="input">
						<span>收货地址<label></label></span>
						<input type="text" name="address" placeholder="请输入收货地址">  <!-- 用户输入收货地址 -->
					</div>

					<div class="clearfix"> </div>  <!-- 清除浮动 -->
				</div>

				<!-- 提交按钮 -->
				<div class="register-but text-center">
					<input type="submit" value="提交">  <!-- 注册按钮 -->
					<div class="clearfix"> </div>  <!-- 清除浮动 -->
				</div>
			</form>
			<div class="clearfix"> </div>  <!-- 清除浮动 -->
		</div>
	</div>
</div>
<!--//account-->

<!-- 引入页面底部内容 -->
<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>
