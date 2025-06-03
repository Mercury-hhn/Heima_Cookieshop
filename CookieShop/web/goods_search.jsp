<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%-- 引入 JSTL Core 标签库，用于<c:forEach>、<c:choose>等标签 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<title>首页</title>
    <%-- 响应式布局视窗设置，适配移动端 --%>
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- 页面字符编码声明 --%>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%-- 引入 Bootstrap 样式表，提供基础组件样式和栅格系统 --%>
	<link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <%-- 引入自定义样式，进行页面个性化布局和样式覆盖 --%>
	<link type="text/css" rel="stylesheet" href="css/style.css">
    <%-- 引入 jQuery，供后续 JavaScript 插件和自定义脚本使用 --%>
	<script type="text/javascript" src="js/jquery.min.js"></script>
    <%-- 引入 Bootstrap 脚本，实现下拉菜单、模态框等交互功能 --%>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
    <%-- 引入 simpleCart 插件，用于简易购物车逻辑（若未使用，可删减） --%>
	<script type="text/javascript" src="js/simpleCart.min.js"></script>
    <%-- 引入 Layer 弹层库，用于提示消息和弹窗 --%>
	<script type="text/javascript" src="layer/layer.js"></script>
    <%-- 引入自定义购物车脚本，封装 buy()、lessen()、deletes() 方法 --%>
	<script type="text/javascript" src="js/cart.js"></script>
</head>
<body>

    <%--
       包含公共头部 header.jsp
       通过 flag 参数高亮导航中的“搜索”菜单项（此处 flag=8）
    --%>
<jsp:include page="/header.jsp">
	<jsp:param value="8" name="flag"/>
</jsp:include>

    <!-- ========== 商品搜索结果区 ========== -->
<div class="products">
	<div class="container">
            <%-- 显示搜索关键字提示，${param.keyword} 来自请求参数 --%>
		<h2> 搜索 ‘${param.keyword }’的结果 </h2>

            <%-- 商品列表区域，使用 Bootstrap 栅格 col-md-12 --%>
		<div class="col-md-12 product-model-sec">

                <%-- 遍历分页对象 p.list 中的商品模型 g --%>
			<c:forEach items="${p.list }" var="g">
				<div class="product-grid">
                        <%-- 点击图片或按钮跳转到商品详情页，传递商品 ID --%>
					<a href="${pageContext.request.contextPath }/goods_detail?id=${g.id}">
                            <%-- 悬浮查看详情提示层 --%>
						<div class="more-product"><span> </span></div>
                            <%-- 商品封面图片容器，包含动画效果类 b-animate-go 等 --%>
						<div class="product-img b-link-stripe b-animate-go  thickbox">
                                <%-- 商品封面图，响应式布局 --%>
                                <img src="${pageContext.request.contextPath}${g.cover}"
                                     class="img-responsive"
                                     alt="${g.name}"
                                     width="240" height="240">
                                <%-- 鼠标悬浮时显示“查看详情”按钮 --%>
							<div class="b-wrapper">
								<h4 class="b-animate b-from-left  b-delay03">
									<button>查看详情</button>
								</h4>
							</div>
						</div>
					</a>

                        <%-- 商品信息区域，包括名称、价格和加入购物车按钮 --%>
					<div class="product-info simpleCart_shelfItem">
						<div class="product-info-cust prt_name">
                                <%-- 商品名称 --%>
							<h4>${g.name }</h4>
                                <%-- 商品价格，前缀人民币符号 --%>
							<span class="item_price">¥ ${g.price }</span>
                                <%-- 加入购物车按钮，调用 cart.js 中定义的 buy() 方法 --%>
                                <input type="button"
                                       class="item_add items"
                                       value="加入购物车"
                                       onclick="buy(${g.id})">
							<div class="clearfix"> </div>
						</div>
					</div>
				</div>
			</c:forEach>

                <%-- 清除浮动，保证布局正常 --%>
			<div class="clearfix"> </div>
            </div> <!-- /.product-model-sec -->

            <%-- 分页组件：包含 page.jsp，并传入当前搜索的 URL 与 keyword 参数 --%>
		<div>
			<jsp:include page="page.jsp">
                    <jsp:param name="url" value="/goods_search"/>
                    <jsp:param name="param" value="&keyword=${param.keyword}"/>
			</jsp:include>
		</div>
        </div> <!-- /.container -->
    </div> <!-- /.products -->
<!--//products-->

    <%-- 包含公共底部 footer.jsp --%>
    <jsp:include page="/footer.jsp"/>

</body>
</html>