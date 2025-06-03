<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>  <!-- 设置页面的编码格式为UTF-8，确保正确显示中文字符 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  <!-- 引入JSTL库，便于后续逻辑判断和动态内容渲染 -->

<!DOCTYPE html>
<html>
<head>
	<title>个人中心</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">  <!-- 设置响应式设计，确保在不同设备上有良好表现 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  <!-- 设置页面的字符编码为UTF-8 -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">  <!-- 引入Bootstrap样式表 -->
	<link type="text/css" rel="stylesheet" href="css/style.css">  <!-- 引入自定义样式表 -->
	<script type="text/javascript" src="js/jquery.min.js"></script>  <!-- 引入jQuery库 -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>  <!-- 引入Bootstrap脚本 -->
	<script type="text/javascript" src="js/simpleCart.min.js"></script>  <!-- 引入SimpleCart脚本 -->
</head>
<body>

<!-- 引入页面头部，传递参数flag值为4 -->
<jsp:include page="/header.jsp">
	<jsp:param value="4" name="flag"/>
</jsp:include>

<!-- 如果用户未登录，则重定向到首页 -->
<c:if test="${empty user}">
	<%response.sendRedirect("/index");%>  <!-- 检查是否有用户信息，如果没有，跳转到首页 -->
</c:if>

<!-- 用户个人中心模块 -->
<div class="account">
	<div class="container">
		<div class="register">
			<!-- 显示成功消息 -->
			<c:if test="${!empty msg }">
				<div class="alert alert-success">${msg }</div>  <!-- 如果msg不为空，显示成功消息 -->
			</c:if>

			<!-- 显示失败消息 -->
			<c:if test="${!empty failMsg }">
				<div class="alert alert-danger">${failMsg }</div>  <!-- 如果failMsg不为空，显示失败消息 -->
			</c:if>

			<!-- 用户个人信息表单 -->
			<div class="register-top-grid">
				<h3>个人中心</h3>
				<form action="/user_changeaddress" method="post">  <!-- 表单提交到/user_changeaddress -->
					<!-- 收货信息 start -->
					<h4>收货信息</h4>
					<div class="input">
						<span>收货人<label></label></span>
						<input type="text" name="name" value="${user.name }" placeholder="请输入收货人">  <!-- 显示当前用户的收货人信息 -->
					</div>
					<div class="input">
						<span>收货电话</span>
						<input type="text" name="phone" value="${user.phone }" placeholder="请输入收货电话">  <!-- 显示当前用户的电话信息 -->
					</div>
					<div class="input">
						<span>收货地址</span>
						<input type="text" name="address" value="${user.address }" placeholder="请输入收货地址">  <!-- 显示当前用户的地址信息 -->
					</div>
					<div class="register-but text-center">
						<input type="submit" value="提交">  <!-- 提交按钮 -->
					</div>
					<!-- 收货信息 end -->
				</form>

				<hr>  <!-- 分隔线 -->

				<!-- 用户安全信息表单 -->
				<form action="/user_changepwd" method="post">  <!-- 表单提交到/user_changepwd -->
					<h4>安全信息</h4>
					<div class="input">
						<span>原密码</span>
						<input type="text" name="password" placeholder="请输入原密码">  <!-- 输入原密码 -->
					</div>
					<div class="input">
						<span>新密码</span>
						<input type="text" name="newPassword" placeholder="请输入新密码">  <!-- 输入新密码 -->
					</div>
					<div class="clearfix"> </div>  <!-- 清除浮动 -->
					<div class="register-but text-center">
						<input type="submit" value="提交">  <!-- 提交按钮 -->
					</div>
				</form>
			</div>

			<div class="clearfix"> </div>  <!-- 清除浮动 -->
		</div>
	</div>
</div>
<!--//account-->

<!-- 引入页面底部 -->
<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>
