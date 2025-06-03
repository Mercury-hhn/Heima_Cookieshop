<%--
    页面：商品详情页面 single.jsp
    功能：展示单个商品的详细信息，包括轮播图、商品名称、分类链接、简介、价格及“加入购物车”按钮；侧边栏展示所有商品分类列表
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
	<title>商品详情</title>
	<!-- 适配移动端视窗 -->
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 页面字符编码 -->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<!-- 引入 Bootstrap 样式 -->
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
	<!-- 引入自定义页面样式 -->
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<!-- 引入 Flexslider 样式，用于图像轮播 -->
	<link type="text/css" rel="stylesheet" href="css/flexslider.css">
	<!-- 引入 jQuery 库 -->
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<!-- 引入 Flexslider 插件脚本 -->
	<script type="text/javascript" src="js/jquery.flexslider.js"></script>
	<!-- 引入 Bootstrap 脚本 -->
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<!-- 引入弹层插件 layer.js（可用于提示） -->
	<script type="text/javascript" src="layer/layer.js"></script>
	<!-- 引入购物车操作脚本（增加、减少、删除等） -->
	<script type="text/javascript" src="js/cart.js"></script>
	<!-- 初始化 Flexslider 轮播效果 -->
	<script>
		$(function() {
			$('.flexslider').flexslider({
				animation: "slide",         // 切换动画效果为滑动
				controlNav: "thumbnails"    // 使用缩略图作为控制导航
			});
		});
	</script>
</head>
<body>

<!--—————— 头部 header ——————-->
<jsp:include page="/header.jsp"></jsp:include>
<!--//header-->

<!--—————— 单页主体 single-page ——————-->
<div class="single">
	<div class="container">
		<div class="single-grids">

			<!--—————— 左侧：商品轮播图 flexslider ——————-->
			<div class="col-md-4 single-grid">
				<div class="flexslider">
					<ul class="slides">
						<%-- 封面图片缩略图项 --%>
						<li data-thumb="${g.cover}">
							<div class="thumb-image">
								<!-- 大图，启用 imagezoom 属性可放大 -->
								<img src="${g.cover}" data-imagezoom="true" class="img-responsive">
							</div>
						</li>
						<%-- 详情图1 缩略图项 --%>
						<li data-thumb="${g.image1}">
							<div class="thumb-image">
								<img src="${g.image1}" data-imagezoom="true" class="img-responsive">
							</div>
						</li>
						<%-- 详情图2 缩略图项 --%>
						<li data-thumb="${g.image2}">
							<div class="thumb-image">
								<img src="${g.image2}" data-imagezoom="true" class="img-responsive">
							</div>
						</li>
					</ul>
				</div>
			</div>

			<!--—————— 中部：商品信息与操作 simpleCart_shelfItem ——————-->
			<div class="col-md-4 single-grid simpleCart_shelfItem">
				<!-- 商品名称 -->
				<h3>${g.name}</h3>
				<!-- 所属分类，点击可按分类筛选商品列表 -->
				<div class="tag">
					<p>分类 :
						<a href="goods_list?typeid=${g.type.id}">${g.type.name}</a>
					</p>
				</div>
				<!-- 商品简介 -->
				<p>${g.intro}</p>
				<!-- 价格显示区 -->
				<div class="galry">
					<div class="prices">
						<h5 class="item_price">¥ ${g.price}</h5>
					</div>
					<div class="clearfix"></div>
				</div>
				<!-- 加入购物车按钮，调用 cart.js 中的 buy() 方法 -->
				<div class="btn_form">
					<a href="javascript:;" class="add-cart item_add"
					   onclick="buy(${g.id})">加入购物车</a>
				</div>
			</div>

			<!--—————— 右侧：商品分类列表 single-grid1 ——————-->
			<div class="col-md-4 single-grid1">
				<ul>
					<!-- 全部系列链接 -->
					<li>
						<a href="/goods_list">全部系列</a>
					</li>
					<!-- 遍历所有分类 typeList，生成分类筛选链接 -->
					<c:forEach items="${typeList}" var="t">
						<li>
							<a href="/goods_list?typeid=${t.id}">${t.name}</a>
						</li>
					</c:forEach>
				</ul>
			</div>

			<!-- 清除浮动 -->
			<div class="clearfix"> </div>
		</div>
	</div>
</div>
<!--//single-page-->

<!--—————— 底部 footer ——————-->
<jsp:include page="/footer.jsp"></jsp:include>
<!--//footer-->

</body>
</html>
