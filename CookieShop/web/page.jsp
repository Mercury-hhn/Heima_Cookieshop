<%--
  Created by IntelliJ IDEA.
  User: piggy
  Date: 2025/05/30
  Time: 1:43 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>  <!-- 设置页面编码为UTF-8，确保支持中文及其他特殊字符 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  <!-- 引入JSTL库，用于处理逻辑判断和数据渲染 -->

<div style='text-align:center;'>  <!-- 设置整个分页控件居中显示 -->

    <!-- 首页按钮 -->
    <a class='btn btn-info'
       <c:if test="${p.pageNumber==1 }">disabled</c:if>  <!-- 如果当前页是第一页，禁用按钮 -->
    <c:if test="${p.pageNumber!=1 }">href="${param.url }?pageNumber=1${param.param }"</c:if>>首页</a>  <!-- 如果不是第一页，则跳转到第一页 -->

    <!-- 上一页按钮 -->
    <a class='btn btn-info'
       <c:if test="${p.pageNumber==1 }">disabled</c:if>  <!-- 如果当前页是第一页，禁用按钮 -->
    <c:if test="${p.pageNumber!=1 }">href="${pageContext.request.contextPath }${param.url }?pageNumber=${p.pageNumber-1}${param.param }"</c:if>>上一页</a>  <!-- 如果不是第一页，则跳转到上一页 -->

    <!-- 当前页和总页数显示 -->
    <h3 style='display:inline;'>[${p.pageNumber }/${p.totalPage }]</h3>  <!-- 显示当前页和总页数 -->
    <h3 style='display:inline;'>[${p.totalCount }]</h3>  <!-- 显示总条目数 -->

    <!-- 下一页按钮 -->
    <a class='btn btn-info'
       <c:if test="${p.totalPage==0 || p.pageNumber==p.totalPage }">disabled</c:if>  <!-- 如果已经是最后一页，禁用按钮 -->
    <c:if test="${p.pageNumber!=p.totalPage }">href="${param.url }?pageNumber=${p.pageNumber+1}${param.param }"</c:if>>下一页</a>  <!-- 如果不是最后一页，则跳转到下一页 -->

    <!-- 尾页按钮 -->
    <a class='btn btn-info'
       <c:if test="${p.totalPage==0 || p.pageNumber==p.totalPage }">disabled</c:if>  <!-- 如果已经是最后一页，禁用按钮 -->
    <c:if test="${p.pageNumber!=p.totalPage }">href="${param.url }?pageNumber=${p.totalPage}${param.param }"</c:if>>尾页</a>  <!-- 如果不是最后一页，则跳转到最后一页 -->

    <!-- 跳转到指定页数的输入框 -->
    <input type='text' class='form-control' style='display:inline;width:60px;' value=''/><!-- 输入框用于输入页码 -->
    <a class='btn btn-info' href='javascript:void(0);'
       onclick='location.href="${param.url }?pageNumber="+(this.previousSibling.value)+"${param.param }"'>GO</a>  <!-- GO按钮，点击后跳转到指定页码 -->
</div>
