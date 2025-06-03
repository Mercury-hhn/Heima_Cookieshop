<%--
  文件：首页商品列表页面 index.jsp
  功能：展示商品列表（可按分类筛选），并提供加入购物车、查看详情、分页功能
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 导入 JSTL 核心标签库，以便在页面中使用 <c:forEach>、<c:choose> 等标签 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <%-- 响应式视窗设置，确保移动端显示正常 --%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- 页面字符编码设置为 UTF-8 --%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%-- 引入 Bootstrap 样式，提供栅格布局和基础组件样式 --%>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <%-- 引入自定义样式 style.css，用于页面个性化美化 --%>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <%-- 引入 jQuery 库，供后续脚本使用 --%>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <%-- 引入 Bootstrap 脚本，实现下拉、模态框等交互组件功能 --%>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <%-- 引入 layer 弹层库，可用于提示和弹窗 --%>
    <script type="text/javascript" src="layer/layer.js"></script>
    <%-- 引入购物车操作脚本 cart.js，包含增删改查购物车项逻辑 --%>
    <script type="text/javascript" src="js/cart.js"></script>
</head>
<body>

<!-- ==================== 头部 header ==================== -->
<%-- 动态包含头部导航，根据 flag 参数高亮当前菜单项 --%>
<jsp:include page="header.jsp">
    <jsp:param name="flag" value="2"></jsp:param>
</jsp:include>
<!-- //header -->

<!-- ==================== 商品区 products ==================== -->
<div class="products">
    <div class="container">
        <%-- 标题：如果未指定分类 t 则显示“全部系列”，否则显示当前分类名称 --%>
        <h2>
            <c:choose>
                <c:when test="${empty t}">
                    全部系列
                </c:when>
                <c:otherwise>
                    ${t.name}
                </c:otherwise>
            </c:choose>
        </h2>

        <div class="col-md-12 product-model-sec">
            <%-- 遍历分页对象 p.list，渲染每个商品条目 --%>
            <c:forEach items="${p.list}" var="g">
                <div class="product-grid">
                        <%-- 点击跳转到商品详情页面 --%>
                    <a href="/goods_detail?id=${g.id}">
                            <%-- 悬浮查看效果容器 --%>
                        <div class="more-product"><span> </span></div>
                            <%-- 商品封面图及动画效果 --%>
                        <div class="product-img b-link-stripe b-animate-go thickbox">
                            <img src="${g.cover}"
                                 class="img-responsive"
                                 alt="${g.name}"
                                 width="240" height="240">
                            <div class="b-wrapper">
                                <h4 class="b-animate b-from-left b-delay03">
                                    <button>查看详情</button>
                                </h4>
                            </div>
                        </div>
                    </a>
                        <%-- 商品信息区域，包括名称、价格、加入购物车按钮 --%>
                    <div class="product-info simpleCart_shelfItem">
                        <div class="product-info-cust prt_name">
                                <%-- 商品名称 --%>
                            <h4>${g.name}</h4>
                                <%-- 商品价格 --%>
                            <span class="item_price">¥ ${g.price}</span>
                                <%-- 加入购物车按钮，调用 cart.js 中的 buy() 方法 --%>
                            <input type="button"
                                   class="item_add items"
                                   value="加入购物车"
                                   onclick="buy(${g.id})">
                            <div class="clearfix"> </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <%-- 分页组件：包含公共 page.jsp，并传入基础 URL 及额外参数 --%>
        <jsp:include page="page.jsp">
            <jsp:param name="url" value="/goods_list"></jsp:param>
            <%-- 如果按分类查看，则传递 typeid 参数用于分页链接拼接 --%>
            <jsp:param name="param" value="&typeid=${id}"></jsp:param>
        </jsp:include>

    </div> <!-- /.container -->
</div> <!-- /.products -->
<!-- //products -->

<!-- ==================== 底部 footer ==================== -->
<jsp:include page="footer.jsp"></jsp:include>
<!-- //footer -->

</body>
</html>
