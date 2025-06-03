<%@ page contentType="text/html;charset=UTF-8" language="java" %>  <!-- 设置页面的内容类型为UTF-8编码的HTML -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  <!-- 引入JSTL标签库，用于条件判断等动态操作 -->

<!DOCTYPE html>
<html>
<head>
	<title>用户登录</title>  <!-- 设置页面标题 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">  <!-- 设置视口，确保页面在移动设备上显示良好 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">  <!-- 设置页面字符编码为UTF-8 -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">  <!-- 引入Bootstrap样式 -->
	<link type="text/css" rel="stylesheet" href="css/style.css">  <!-- 引入自定义样式 -->
	<script type="text/javascript" src="js/jquery.min.js"></script>  <!-- 引入jQuery库 -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>  <!-- 引入Bootstrap JavaScript库 -->
	<script type="text/javascript" src="js/simpleCart.min.js"></script>  <!-- 引入SimpleCart脚本 -->
</head>
<body>

<!-- 引入页面头部，并传递参数flag为9，这通常用来高亮导航菜单的当前项 -->
<jsp:include page="header.jsp">
	<jsp:param name="flag" value="9"></jsp:param>
</jsp:include>

<!-- 用户登录表单 -->
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

			<!-- 用户登录表单 -->
			<form action="/user_login" method="post">  <!-- 表单提交到/user_login，表示用户提交登录请求 -->
				<div class="register-top-grid">
					<h3>用户登录</h3>  <!-- 表单标题 -->
					<div class="input">
						<span>用户名/邮箱 <label style="color:red;">*</label></span>  <!-- 用户名或邮箱输入框 -->
						<input type="text" name="ue" placeholder="请输入用户名" required="required">  <!-- 必填项，用户输入用户名或邮箱 -->
					</div>
					<div class="input">
						<span>密码 <label style="color:red;">*</label></span>  <!-- 密码输入框 -->
						<input type="password" name="password" placeholder="请输入密码" required="required">  <!-- 必填项，用户输入密码 -->
					</div>

					<div class="clearfix"> </div>  <!-- 清除浮动 -->
				</div>
				<div class="register-but text-center">
					<input type="submit" value="提交">  <!-- 登录按钮 -->
					<div class="clearfix"> </div>  <!-- 清除浮动 -->
				</div>
			</form>
			<div class="clearfix"> </div>  <!-- 清除浮动 -->
		</div>
	</div>
</div>
<!--//account-->

<!-- 引入页面底部 -->
<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
