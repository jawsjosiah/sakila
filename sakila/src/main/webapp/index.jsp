<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="jumbotron text-center">
		<h1>INDEX</h1>
	</div>
		
	<ol>
		<li><a href="<%=request.getContextPath()%>/storeList.jsp">Store List</a></li>
		<li><a href="<%=request.getContextPath()%>/staffList.jsp">Staff List</a></li>
		<li><a href="<%=request.getContextPath()%>/statsData.jsp">stats Data</a></li>
	</ol>	
	
	<h3>뷰 리스트</h3>
	<ol>	
		<!-- view 7개 리스트 -->
		<li><a href="<%=request.getContextPath()%>/view/actorInfoList.jsp">actorInfoList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/view/customerList.jsp">customerList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/view/filmList.jsp">filmList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/view/nicerButSlowerFilmList.jsp">nicerButSlowerFilmList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/view/salesByFilmCategory.jsp">salesByFilmCategory(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/view/salesByStore.jsp">salesByStore(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/view/staffListView.jsp">staffList(view)</a></li>
	</ol>
	
	<h3>프로시저</h3>
	<ol>
		<li><a href="<%=request.getContextPath()%>/procedure/filmInStock.jsp">filmInStock(procedure)</a></li>
		<li><a href="<%=request.getContextPath()%>/procedure/filmNotInStock.jsp">filmNotInStock(procedure)</a></li>
		<li><a href="<%=request.getContextPath()%>/procedure/rewardsReport.jsp">rewardsReport(procedure)</a></li>
	</ol>
	
	<h3>상세 검색</h3>
	<ol>
		<li><a href="<%=request.getContextPath()%>/search/filmSearchForm.jsp">filmSearchForm</a></li>
		<li><a href="<%=request.getContextPath()%>/search/rentalSearchForm.jsp">rentalSearchForm</a></li>
	</ol>
</body>
</html> 