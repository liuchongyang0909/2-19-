<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>文章列表</title>

<link rel="stylesheet" href="/resource/css/bootstrap.css" />

</head>

 
<body>
	<div class="container">
		<div style="margin-top: 10px" class="form-inline">
			<label for="title">标题:</label>
			  <input id="title" class="form-control" type="text" name="title" value="${article.title }">
			    &nbsp;
			    审核状态:
			    <select class="form-control" name="status" id="status">
			     <option value="0">待审</option>
			     <option value="1">已审</option>
			     <option value="-1">驳回</option>
			     <option value="99">全部</option>
			    </select>
			       &nbsp;
			    <button class="btn btn-info" type="button" onclick="query()"> 查询</button>
		</div>
		<table class="table table-hover" style="margin-top: 10px">
			<tr align="center">
				<td>序号</td>
				<td>文章标题</td>
				<td>栏目</td>
				<td>分类</td>
				<td>作者</td>
				<td>文章状态</td>
				<td>发布时间</td>
				<td colspan="2">操作</td>
			</tr align="center">
			<c:forEach items="${info.list }" var="a" varStatus="i">
				<tr align="center">
					<td>${i.count }</td>
					<td>${a.title }</td>
					<td>${a.channel.name }</td>
					<td>${a.category.name }</td>
					<td>${a.user.username }</td>
					<td>${a.status==0?"待审":a.status==1?"已审":"驳回" }</td>
					<td><fm:formatDate value="${a.created }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<c:if test="${a.hot==0}">
				 			<button type="button" class="btn btn-success" onclick="update(${a.id},this)">非热门</button>
						</c:if>
						<c:if test="${a.hot==1}">
				 			<button type="button" class="btn btn-warning" onclick="update(${a.id},this)">热门</button>
						</c:if>
					</td>
					<td><a href="/admin/article/select?id=${a.id }" target="_blank">详情</a>	</td>
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
//	location.href = "/user/selects?pageNum=" + pageNum
	var url="/admin/article/selects?pageNum="+pageNum+"&title="+$("[name='title']").val()+"&status="+$("[name=status]").val()
	$("#center").load(url);
}
function query(){
	var url = "/admin/article/selects?title="+$("[name='title']").val()+"&status="+$("[name=status]").val()
	$("#center").load(url);
}

//让下拉框的查询条件回显
$(function() {
	$("#status").val('${article.status}');  
})

//更新文章热门状态   1:热门, 0:非热门
function update(id,obj){
	//要改变为的状态
	var hot =$(obj).text()=="非热门"?1:0;
	
	$.post("/admin/article/update",{id:id,hot:hot},function(flag){
		if(flag){
			//alert("操作成功");
			//改变内容
			$(obj).text(hot==1?"热门":"非热门");
			//改变颜色
			$(obj).attr("class",hot==1?"btn btn-warning":"btn btn-success")
		}else{
			alert("操作失败")
		}
	})
	
	
}
</script>
</html>