<%--
  Created by IntelliJ IDEA.
  User: piggy
  Date: 2025/05/30
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- 导航栏开始 -->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <!-- 导航栏头部 -->
        <div class="navbar-header">
            <!-- 网站品牌名称，点击后返回首页 -->
            <a class="navbar-brand" href="index.jsp">蛋糕店后台</a>
        </div>

        <!-- 导航栏链接部分 -->
        <div>
            <ul class="nav navbar-nav">
                <!-- 订单管理链接，跳转到订单管理页面 -->
                <li><a href="/admin/order_list">订单管理</a></li>

                <!-- 客户管理链接，跳转到客户管理页面 -->
                <li><a href="/admin/user_list">客户管理</a></li>

                <!-- 商品管理链接，跳转到商品管理页面 -->
                <li><a href="/admin/goods_list">商品管理</a></li>

                <!-- 类目管理链接，跳转到类目管理页面 -->
                <li><a href="/admin/type_list">类目管理</a></li>

                <!-- 退出链接，点击后退出当前用户并跳转到退出页面 -->
                <li><a href="/user_logout">退出</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- 导航栏结束 -->
