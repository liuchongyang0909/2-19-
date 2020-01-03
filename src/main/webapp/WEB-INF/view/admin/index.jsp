<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CMS后台管理系统</title>
<link rel="stylesheet" href="/resource/css/bootstrap.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>

</head>
<body>
	<div class="container">
	<!-- header -->
		<div class="row" style="height: 80px; margin-top: 10px" >
			<div class="col-md-12" style="background-color: #0073b8">
				<img alt="" src="/resource/image/logo.png" height="80px" width="100px" class="rounded-circle">
				CMS后台管理系统
				<span><font color="white">
					登录人：${sessionScope.admin.username }</font>
					  <a href="/passport/logout">注销</a>
				</span>
			</div>
		</div>
		<hr style="">
		<div class="row" style="height: 550px">
		<!-- 左侧：菜单 -->
			<div class="col-md-3" style="">
				<div class="">
				<!-- As a link -->
					<nav class="navbar navbar-light bg-light">
					  <a class="navbar-brand" href="#" data="/admin/user/selects">用户管理</a>
					</nav>
					
					<!-- As a heading -->
					<nav class="navbar navbar-light bg-light">
					  <span class="navbar-brand mb-0 h1">
					 	<a class="navbar-brand" href="#" data="/admin/article/selects">文章管理</a> 
					  </span>
					</nav>
					<!-- As a heading -->
					<!-- <nav class="navbar navbar-light bg-light">
					  <span class="navbar-brand mb-0 h1">
					 	<a class="navbar-brand" href="#" data="/admin/article/complains">举报管理</a> 
					  </span>
					</nav> -->
					<!-- As a heading -->
					<nav class="navbar navbar-light bg-light">
					  <span class="navbar-brand mb-0 h1">栏目管理</span>
					</nav>
					<!-- As a heading -->
					<nav class="navbar navbar-light bg-light">
					  <span class="navbar-brand mb-0 h1">分类管理</span>
					</nav>
					<!-- As a heading -->
					<nav class="navbar navbar-light bg-light">
					  <span class="navbar-brand mb-0 h1">系统管理</span>
					</nav>
				</div>
			</div>
		<!-- 中右侧： 内容区域-->
			<div class="col-md-9" id="center" style=""></div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		//中间区域默认显示用户列表
		$("#center").load("/admin/user/selects")
		
		
		//为a标签添加点击事件
		$("a").click(function(){
			var url = $(this).attr("data");
			//先删除已有的选中样式
			$("a").removeClass("list-group-item-dark")
			//再为点击的a标签添加样式
			$(this).addClass("list-group-item-dark")
			//alert(url); 
			//在中间区域加载url
			$("#center").load(url);
		})
	})
</script>
</html>