<%--
  文件：goodsrecommend_list.jsp
  功能：展示推荐商品（热销或新品）列表，支持分页和加入购物车操作
  创建者：piggy
  创建日期：2025/05/30
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- 引入 JSTL Core 标签库，用于<c:choose>、<c:forEach>等标签 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
    <%-- 引入简单购物车插件（可选），用于前端展示简易购物车功能 --%>
    <script type="text/javascript" src="js/simpleCart.min.js"></script>
    <%-- 引入 Layer 弹层库，用于提示消息和弹窗 --%>
    <script type="text/javascript" src="layer/layer.js"></script>
    <%-- 引入自定义购物车脚本，封装 buy() 等操作 --%>
    <script type="text/javascript" src="js/cart.js"></script>
</head>
<body>

<%--
   包含公共头部 header.jsp
   通过 flag 参数高亮导航中的“推荐”菜单项（flag=3 表示新品/热销页）
--%>
<jsp:include page="header.jsp">
    <jsp:param name="flag" value="3"></jsp:param>
</jsp:include>
<!-- //header -->

<%-- ========== 推荐商品列表区 ========== --%>
<div class="products">
    <div class="container">
        <%-- 根据参数 t 决定显示标题：t==2 显示“热销商品”，否则显示“新品商品” --%>
        <h2>
            <c:choose>
                <c:when test="${t == 2}">热销商品</c:when>
                <c:otherwise>新品商品</c:otherwise>
            </c:choose>
        </h2>

        <%-- 商品列表区域，使用 Bootstrap 栅格 col-md-12 --%>
        <div class="col-md-12 product-model-sec">

            <%-- 遍历分页对象 p.list 中的商品模型 g --%>
            <c:forEach items="${p.list}" var="g">
                <div class="product-grid">
                        <%-- 点击跳转到商品详情页，传递商品 ID --%>
                    <a href="/goods_detail?id=${g.id}">
                            <%-- 悬浮查看详情提示层 --%>
                        <div class="more-product"><span> </span></div>
                            <%-- 商品封面图片及动画效果 --%>
                        <div class="product-img b-link-stripe b-animate-go thickbox">
                            <img src="${g.cover}"
                                 class="img-responsive"
                                 alt="${g.name}"
                                 width="240" height="240">
                                <%-- 鼠标悬浮时显示“查看详情”按钮 --%>
                            <div class="b-wrapper">
                                <h4 class="b-animate b-from-left b-delay03">
                                    <button>查看详情</button>
                                </h4>
                            </div>
                        </div>
                    </a>

                        <%-- 商品信息区，包括名称、价格和加入购物车按钮 --%>
                    <div class="product-info simpleCart_shelfItem">
                        <div class="product-info-cust prt_name">
                                <%-- 商品名称 --%>
                            <h4>${g.name}</h4>
                                <%-- 商品价格，前缀人民币符号 --%>
                            <span class="item_price">¥ ${g.price}</span>
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

        <%-- 分页组件：包含 page.jsp，并传入当前推荐列表的 URL 与类型参数 --%>
        <jsp:include page="page.jsp">
            <jsp:param name="url" value="/goodsrecommend_list"></jsp:param>
            <jsp:param name="param" value="&type=${t}"></jsp:param>
        </jsp:include>

    </div> <!-- /.container -->
</div> <!-- /.products -->
<!-- //products -->

<%-- 包含公共底部 footer.jsp --%>
<jsp:include page="footer.jsp"></jsp:include>
<!-- //footer -->

</body>
</html>
