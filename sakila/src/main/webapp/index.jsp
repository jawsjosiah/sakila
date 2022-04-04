<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="jumbotron text-center">
		<h1>INDEX</h1>
	</div>
	
	<div class="container pt-3 p-3 my-3 border">
		<ol>
		<li><a href="<%=request.getContextPath()%>/storeList.jsp">Store List</a></li>
		<li><a href="<%=request.getContextPath()%>/staffList.jsp">Staff List</a></li>
		<li><a href="<%=request.getContextPath()%>/actorInfoList.jsp">ActorInfo List</a></li>
	</ol>
	</div>
	
</body>
</html>