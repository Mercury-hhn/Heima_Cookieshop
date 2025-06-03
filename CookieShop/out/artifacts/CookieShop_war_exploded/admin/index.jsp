<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>后台管理</title>
	<!-- 引入Bootstrap样式文件，确保页面样式一致性 -->
	<link rel="stylesheet" href="css/bootstrap.css"/>
</head>
<body>
<div class="container-fluid">

	<!-- 引入头部页面，通常包含网站的导航栏等公共部分 -->
	<jsp:include page="header.jsp"></jsp:include>

	<br><br>

	<!-- 显示一个成功的提示消息 -->
	<div class="alert alert-success" role="alert">恭喜你! 登录成功了</div>

</div>
</body>
</html>
