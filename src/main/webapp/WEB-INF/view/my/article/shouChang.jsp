<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>收藏列表</title>

<link rel="stylesheet" href="/resource/css/bootstrap.css" />

</head>
<body>
	<div class="container">
		<table class="table table-hover" style="margin-top: 10px">
			<tr align="center">
				<td>收藏夹文本</td>
				<td>收藏夹地址</td>
				<td>所属用户</td>
				<td>添加时间</td>
				<td colspan="2">操作</td>
			</tr align="center">
			<c:forEach items="${info.list }" var="a">
				<tr align="center">
					<td>${a.texts }</td>
					<td>${a.url }</td>
					<td>${a.user_id }</td>
					<td>${a.created }</td>
					<td><input type="button" onclick="doDelete(${a.id})" value="删除"></td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="100">
					<jsp:include page="/WEB-INF/view/common/pages.jsp" />
				</td>
			</tr>
		</table>
	</div>
</body>
<script type="text/javascript">
	function goPage(pageNum) {
		var url = "my/article/sclist?pageNum=" + pageNum;
		$("#center").load(url);
	}
	
	function doDelete(id) {
		$.ajax({
			url:'my/article/delShouChang',
			type:'post',
			data:{id:id},
			dataType:'json',
			success:function(flag) {
				if(falg) {
					alert("删除成功~!");
					location.href="my/article/shouChang";
				} else {
					alert("删除失败~!");
				}
			}
		})
	}
</script>

</html>