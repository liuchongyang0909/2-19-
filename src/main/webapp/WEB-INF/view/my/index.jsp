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
<meta charset=UTF-8">
<title>个人中心</title>
<link rel="stylesheet" href="/resource/css/bootstrap.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>

</head>
<body>
	<div class="container">
		<!-- header -->
		<div class="row" style="height: 80px; margin-top: 10px">
			<div class="col-md-12" style="background-color: white;">
				<img alt="" src="/resource/image/logo.png" height="80px" width="100px" class="rounded-circle">
					<font class=""><b>个人中心</b> 
						登录人:${sessionScope.user.username } <a href="/passport/logout">注销</a>
					</font>
			</div>
		</div>
		<hr style="">
		<div class="row" style="height: 550px">
			<!-- 左侧：菜单 -->
			<div class="col-md-3" style="background-color: white;">
				<div class="list-group" style="margin-top: 20px">
					<a href="#" data="/my/article/articles" class="list-group-item list-group-item-action">我的文章</a>
					<a href="#" data="/my/article/publish" class="list-group-item list-group-item-action">发布文章</a>
					<a href="#" data="/my/article/shouChang" class="list-group-item list-group-item-action">我的收藏</a>
					<a href="#" class="list-group-item list-group-item-action">用户设置</a>
					<a href="#" class="list-group-item list-group-item-action">发布图片</a> 
				</div>
			</div>
			<!-- 中右侧： 内容区域-->
			<div class="col-md-9" id="center" style="background-color: white;"></div>
			<!-- 引入kindeditor -->
			<div style="display: none">
				<jsp:include page="/resource/kindeditor/jsp/demo.jsp"/>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		//默认显示我的文章列表
		$("#center").load("/my/article/articles");
		
		//为a标签添加点击事件
		$("a").click(function(){
			var url = $(this).attr("data");
			//先删除已有的选中样式
			$("a").removeClass("active")
			//再为点击的a标签添加样式
			$(this).addClass("active")
			//alert(url); 
			//在中间区域加载url
			$("#center").load(url);
		})
	})
</script>
</html>
