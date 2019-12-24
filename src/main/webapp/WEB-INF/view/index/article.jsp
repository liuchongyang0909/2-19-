<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fm"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>${a.title }</title>

<link rel="stylesheet" href="/resource/css/bootstrap.css" />

</head>


<body>
	<div class="container">
		<h1 align="center">${a.title }</h1>
		<span style="float: right;color: red"><a href="/complain?id=${a.id }">举报</a></span>
		<h6 align="center">${a.user.username } &nbsp;<fm:formatDate value="${a.created }" pattern="yyyy-MM-dd HH:mm:ss"/></h3>
		${a.content }
	</div>

		
</html>