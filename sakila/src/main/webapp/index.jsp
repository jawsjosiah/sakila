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
	</ol>	
	
	<h3>뷰 리스트</h3>
	<ol>	
		<!-- view 7개 리스트 -->
		<li><a href="<%=request.getContextPath()%>/actorInfoList.jsp">actorInfoList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/customerList.jsp">customerList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/filmList.jsp">filmList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/nicerButSlowerFilmList.jsp">nicerButSlowerFilmList(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/salesByFilmCategory.jsp">salesByFilmCategory(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/salesByStore.jsp">salesByStore(view)</a></li>
		<li><a href="<%=request.getContextPath()%>/staffListView.jsp">staffList(view)</a></li>
	</ol>
	
	<h3>프로시저</h3>
	
	<h3>상세 검색</h3>
	<ol>
		<li><a href="<%=request.getContextPath()%>/filmSearchForm.jsp">filmSearchForm</a></li>
	</ol>
</body>
</html> 