<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  文件：header.jsp
  功能：页面公共头部，包括导航菜单、搜索框、购物车图标和用户登录/注册状态等
  创建者：piggy
  创建日期：2025/05/30
--%>

<!-- 整体头部容器 -->
<div class="header">
    <div class="container">
        <!-- 导航条开始 -->
        <nav class="navbar navbar-default" role="navigation">
            <!-- 可折叠按钮 和 品牌区域 -->
            <div class="navbar-header">
                <!-- 移动端折叠按钮 -->
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <!-- 无障碍文本 -->
                    <span class="sr-only">Toggle navigation</span>
                    <!-- 三杠图标 -->
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <!-- 品牌链接，点击跳转首页 -->
                <h1 class="navbar-brand"><a href="/index"></a></h1>
            </div>
            <!--navbar-header-->

            <!-- 折叠内容：导航菜单列表 -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <!-- 首页菜单项，flag=1 时高亮 -->
                    <li><a href="/index" <c:if test="${param.flag==1}">class="active"</c:if>>首页</a></li>
                    <!-- 商品分类下拉菜单，flag=2 时高亮 -->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle <c:if test="${param.flag==2}">active</c:if>" data-toggle="dropdown">商品分类<b class="caret"></b></a>
                        <ul class="dropdown-menu multi-column columns-2">
                            <li>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <!-- 分类标题 -->
                                        <h4>商品分类</h4>
                                        <ul class="multi-column-dropdown">
                                            <!-- 全部系列链接 -->
                                            <li><a class="list" href="/goods_list">全部系列</a></li>
                                            <!-- 动态遍历 typeList，输出各分类链接 -->
                                            <c:forEach items="${typeList}" var="t">
                                                <li><a class="list" href="/goods_list?typeid=${t.id}">${t.name}</a></li>
                                            </c:forEach>


                                        </ul>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </li>
                    <!-- 热销推荐链接，flag=3 且 t==2 时高亮 -->
                    <li><a href="/goodsrecommend_list?type=2" <c:if test="${param.flag==3 && t==2}">class="active"</c:if>>热销</a></li>
                    <!-- 新品推荐链接，flag=3 且 t==3 时高亮 -->
                    <li><a href="/goodsrecommend_list?type=3" <c:if test="${param.flag==3 && t==3}">class="active"</c:if>>新品</a></li>
                    <!-- 根据用户登录状态显示注册/登录或个人中心/退出 -->
                    <!-- 未登录状态 -->
                    <c:choose><c:when test="${empty user }">
                        <!-- 注册页，flag=10 时高亮 -->
                        <li><a href="/user_register.jsp" <c:if test="${param.flag==10 }">class="active"</c:if>>注册</a></li>
                        <!-- 登录页，flag=9 时高亮 -->
                        <li><a href="/user_login.jsp" <c:if test="${param.flag==9 }">class="active"</c:if>>登录</a></li>
                        <!-- 已登录状态 -->
                    </c:when><c:otherwise>
                        <!-- 我的订单，flag=5 时高亮 -->
                        <li><a href="/order_list" <c:if test="${param.flag==5 }">class="active"</c:if>>我的订单</a></li>
                        <!-- 个人中心，flag=4 时高亮 -->
                        <li><a href="/user_center.jsp" <c:if test="${param.flag==4 }">class="active"</c:if>>个人中心</a></li>
                        <!-- 退出链接 -->
                        <li><a href="/user_logout" >退出</a></li>
                    </c:otherwise>
                    </c:choose>
                    <!-- 如果是管理员用户，显示后台管理入口 -->
                    <c:if test="${!empty user && user.isadmin }">
                        <li><a href="/admin/index.jsp" target="_blank">后台管理</a></li>
                    </c:if>
                </ul>
                <!--/.navbar-collapse-->
            </div>
            <!--//navbar-header-->
        </nav>
        <!-- 头部右侧：搜索框和购物车图标 -->
        <div class="header-info">
            <!-- 搜索框区域 -->
            <div class="header-right search-box">
                <!-- 搜索图标按钮 -->
                <a href="javascript:;"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></a>
                <!-- 悬浮显示的搜索表单 -->
                <div class="search">
                    <form class="navbar-form" action="/goods_search">
                        <!-- 关键词输入 -->
                        <input type="text" class="form-control" name="keyword">
                        <!-- 搜索按钮，flag=7 时高亮 -->
                        <button type="submit" class="btn btn-default <c:if test="${param.flag==7 }">active</c:if>" aria-label="Left Align">搜索</button>
                    </form>
                </div>
            </div>
            <!-- 购物车图标区域 -->
            <div class="header-right cart">
                <a href="goods_cart.jsp">
                    <!-- 购物车图标和数量显示 -->
                    <span class="glyphicon glyphicon-shopping-cart <c:if test="${param.flag==8 }">active</c:if>" aria-hidden="true"><span class="card_num"><c:choose><c:when test="${empty order}">0</c:when><c:otherwise>${order.amount}</c:otherwise></c:choose></span></span>
                </a>
            </div>
            <!-- 清除浮动 -->
            <div class="clearfix"> </div>
        </div>
        <!-- 清除容器浮动 -->
        <div class="clearfix"> </div>
    </div>
</div>
<!--//header-->