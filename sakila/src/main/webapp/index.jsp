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

			<!-- view 7개 리스트 -->
			<li><a href="<%=request.getContextPath()%>/actorInfoList.jsp">actorInfoList(view)</a></li>
			<li><a href="<%=request.getContextPath()%>/customerList.jsp">customerList(view)</a></li>
			<li><a href="<%=request.getContextPath()%>/filmList.jsp">filmList(view)</a></li>
			<li><a href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp">nicerButSlowerFilmList(view)</a></li>
			<li><a href="<%=request.getContextPath()%>/salesByFilmCategory.jsp">salesByFilmCategory(view)</a></li>
			<li><a href="<%=request.getContextPath()%>/salesByStore.jsp">salesByStore(view)</a></li>
			<li><a href="<%=request.getContextPath()%>/staffListView.jsp">staffList(view)</a></li>
			
			<!-- procedure 3개 결과 화면 -->
			<li><a href="<%=request.getContextPath()%>/filmInStock.jsp">filmInStock</a></li>
			<li><a href="<%=request.getContextPath()%>/filmNotInStock.jsp">filmNotInStock</a></li>
			<li><a href="<%=request.getContextPath()%>/rewardsReport.jsp">rewardsReport</a></li>
		</ol>
	</div>
	
</body>
</html>