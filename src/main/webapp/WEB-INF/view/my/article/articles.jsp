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

		<div>
			<ul class="list-unstyled">
				<c:forEach items="${info.list }" var="a" >
				<li class="media"><img src="/pic/${a.picture }" class="mr-3"
					alt="..." style="width: 150px" height="100px">
					<div class="media-body">
						<h5 class="mt-0 mb-1"><a href="/my/article/article?id=${a.id }" target="_blank">${a.title }</a></h5>
						<div style="margin-top: 50px">
						<fm:formatDate value="${a.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
								
								
						<c:if test="${a.deleted==0 }">
							<span style="float: right">
								<button type="button" onclick="update(${a.id},this)" class="btn-sm btn-danger">删除</button>
							</span>
						</c:if>
						<c:if test="${a.deleted==1 }">
							<span style="float: right">
								<button type="button" onclick="update(${a.id},this)" class="btn-sm btn-warning">已删除</button>
							</span>
						</c:if>
						</div>
						
					</div></li>
					<hr>
				</c:forEach>
			</ul>


			<jsp:include page="/WEB-INF/view/common/pages.jsp" />

		</div>
	</div>
</body>
<script type="text/javascript">
	function goPage(pageNum) {
		//	location.href = "/user/selects?pageNum=" + pageNum
		var url = "/my/article/articles?pageNum=" + pageNum
		$("#center").load(url);
	}
	
	//更新用户的状态  1.停用；2.正常
	function update(id,obj) {
		//要改变为的状态
		var deleted = $(obj).text()=="删除"?1:0;
		$.post("/my/article/update",{id:id,deleted:deleted},function(flag){
			if(flag){
				//alert("操作成功");
				//改变内容
				$(obj).text(deleted==1?"已删除":"删除");
				//改变颜色
				$(obj).attr("class",deleted==1?"btn-sm btn-warning":"btn-sm btn-danger")
			}else{
				alert("操作失败");
				
			}
		})
	}
</script>
</html>