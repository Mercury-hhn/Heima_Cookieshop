<%--
  Created by IntelliJ IDEA.
  User: 19767
  Date: 2018/11/19
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>商品列表</title>
    <%-- 响应式视图支持，使页面在移动设备上自适应宽度 --%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- 再次声明页面字符集为 UTF-8 --%>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%-- 引入 Bootstrap 的 CSS 样式 --%>
    <link type="text/css" rel="stylesheet" href="css/bootstrap.css">
    <%-- 引入自定义的 CSS 样式 --%>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <%-- 引入 jQuery 库，供后续脚本使用 --%>
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <%-- 引入 Bootstrap 的 JavaScript 插件 --%>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <%-- 引入 layer.js 库，用于弹层效果（提示框、对话框等） --%>
    <script type="text/javascript" src="layer/layer.js"></script>
    <%-- 引入自定义购物车功能脚本 --%>
    <script type="text/javascript" src="js/cart.js"></script>
</head>
<body>
<!--header-->
<jsp:include page="/header.jsp">
    <%-- 传递参数 flag=1 给 header.jsp，用于在导航栏高亮“商品列表”这一项 --%>
    <jsp:param name="flag" value="1"></jsp:param>
</jsp:include>
<!--banner-->

<div class="banner">
    <div class="container">
        <%-- Bootstrap Carousel 轮播图容器 --%>
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators（小圆点导航）-->
            <ol class="carousel-indicators" id="olnum">
                <%-- 遍历 scroll 列表，为每一张轮播图生成一个指示器 li --%>
                <c:forEach items="${scroll}" var="g" varStatus="status">
                    <c:choose>
                        <%-- 如果是第一张（status.first 为 true），则给该 li 添加 class="active" --%>
                        <c:when test="${status.first}">
                            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        </c:when>
                        <c:otherwise>
                            <li data-target="#carousel-example-generic" data-slide-to="${status.index}"></li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </ol>

            <!-- Wrapper for slides（轮播图具体内容）-->
            <div class="carousel-inner" role="listbox" id="lunbotu" style="width: 1242px; height: 432px;">
                <%-- 再次遍历 scroll 列表，生成每一张轮播项 --%>
                <c:forEach items="${scroll}" var="g" varStatus="status">
                    <c:choose>
                        <%-- 如果是第一张，则 class="item active"，其余为 class="item" --%>
                        <c:when test="${status.first}">
                            <div class="item active">
                                <%-- 轮播图标题及链接，点击进入商品详情页 --%>
                                <h2 class="hdng"><a href="/goods_detail?id=${g.id}">${g.name}</a><span></span></h2>
                                <p>今日精选推荐</p>
                                <%-- “立刻购买”按钮，调用 buy(${g.id}) 方法将商品加入购物车 --%>
                                <a class="banner_a" href="javascript:;" onclick="buy(${g.id})">立刻购买</a>
                                <div class="banner-text">
                                    <a href="/goods_detail?id=${g.id}">
                                        <%-- 商品封面图 --%>
                                        <img src="${g.cover}" alt="${g.name}" width="350" height="350">
                                    </a>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="item">
                                <h2 class="hdng"><a href="/goods_detail?id=${g.id}">${g.name}</a><span></span></h2>
                                <p>今日精选推荐</p>
                                <a class="banner_a" href="javascript:;" onclick="buy(${g.id})">立刻购买</a>
                                <div class="banner-text">
                                    <a href="/goods_detail?id=${g.id}">
                                        <img src="${g.cover}" alt="${g.name}" width="350" height="350">
                                    </a>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>

            <!-- Controls（左右切换箭头，当前已注释掉，如果需要可取消注释）  -->
            <%--<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>--%>
        </div>
    </div>
</div>

<!--//banner-->

<!-- 订阅栏 / 分隔区 -->
<div class="subscribe2"></div>

<!--gallery-->
<div class="gallery">
    <div class="container">
        <%-- “热销推荐” 标题，用红色警告样式 --%>
        <div class="alert alert-danger">热销推荐</div>
        <div class="gallery-grids">
            <%-- 遍历 hotList，渲染热销商品列表 --%>
            <c:forEach items="${hotList}" var="g">
                <div class="col-md-4 gallery-grid glry-two">
                    <%-- 商品图片，点击可进入详情页 --%>
                    <a href="/goods_detail?id=${g.id}">
                        <img src="${g.cover}" class="img-responsive" alt="${g.name}" width="350" height="350"/>
                    </a>
                    <%-- 悬停时显示“查看详情”和“立刻购买”按钮 --%>
                    <div class="gallery-info galrr-info-two">
                        <p>
                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                            <a href="/goods_detail?id=${g.id}">查看详情</a>
                        </p>
                        <a class="shop" href="javascript:;" onclick="buy(${g.id})">立刻购买</a>
                        <div class="clearfix"></div>
                    </div>
                    <%-- 商品类型及名称，以及价格展示 --%>
                    <div class="galy-info">
                        <p>${g.typeName} > ${g.name}</p>
                        <div class="galry">
                            <div class="prices">
                                <h5 class="item_price">¥ ${g.price}</h5>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </c:forEach>


        </div>

        <div class="clearfix"></div>
        <%-- “新品推荐” 标题，用蓝色信息样式 --%>
        <div class="alert alert-info">新品推荐</div>
        <div class="gallery-grids">
            <%-- 遍历 newList，渲染新品商品列表 --%>
            <c:forEach items="${newList}" var="g">
                <div class="col-md-3 gallery-grid ">
                    <a href="/goods_detail?id=${g.id}">
                        <img src="${g.cover}" class="img-responsive" alt="${g.name}"/>
                    </a>
                    <div class="gallery-info">
                        <p>
                            <span class="glyphicon glyphicon-eye-open" aria-hidden="true"></span>
                            <a href="/goods_detail?id=${g.id}">查看详情</a>
                        </p>
                        <a class="shop" href="javascript:;" onclick="buy(${g.id})">立刻购买</a>
                        <div class="clearfix"></div>
                    </div>
                    <div class="galy-info">
                        <p>${g.typeName} > ${g.name}</p>
                        <div class="galry">
                            <div class="prices">
                                <h5 class="item_price">¥ ${g.price}</h5>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </c:forEach>


        </div>
    </div>
</div>
<!--//gallery-->

<!--subscribe-->
<div class="subscribe"></div>
<!--//subscribe-->


<!--footer-->
<jsp:include page="/footer.jsp"></jsp:include>
</body>
</html>