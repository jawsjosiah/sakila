<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*"%>
<%@ page import = "dao.*"%>
<%@ page import = "vo.*"%>
<%
	String category = request.getParameter("category");
	String rating = request.getParameter("rating");
	double price = -1; // price 데이터가 입력되지 않았을때
	if(!request.getParameter("price").equals("")) {
		price = Double.parseDouble(request.getParameter("price"));
	}
	int length = -1; // length 데이터가 입력되지 않았을때
	if(!request.getParameter("length").equals("")) {
		length = Integer.parseInt(request.getParameter("length"));
	}
	String title = request.getParameter("title");
	String actors = request.getParameter("actors");
	
	
	int rowPerPage = 10;
	
	FilmDao filmDao = new FilmDao();
	
	
	
	
	int currentPage = 1;
	// 현재 페이지의 기본 값은 1페이지 
	if(request.getParameter("currentPage") != null) {
	// 이전, 다음 링크를 통해서 들어왔다면 
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		 
	}
	System.out.println(currentPage+ "<-- currentPage(filmSearchAction.jsp)");
	// 디버깅 코드 
	
	int beginRow = (currentPage-1) * rowPerPage; 
	// 현재 페이지가 변경되면 beginRow도 바뀌어야 한다. 
	System.out.println(beginRow+ "<-- beginRow(filmSearchAction.jsp)");
	// 디버깅 코드 
	
	List<FilmList> list = filmDao.selectFilmListSearch(beginRow ,rowPerPage ,category, rating, price, length, title, actors);
	
	System.out.println(list.size()); // 0
	
	int totalRow = 0;
	
	

	totalRow = filmDao.selectTotalRow(category, rating, price, length, title, actors); 

	System.out.println(totalRow+"//totalRow(ActorInfoList.jsp)");

	int lastPage = 0;
		// 마지막 페이지 0으로 초기화 
		if(totalRow % rowPerPage == 0) {
			lastPage = totalRow / rowPerPage;
			// 딱 떨어지는 경우 
		} else {
			lastPage = (totalRow / rowPerPage) + 1;
			// 나누어 떨어지지 않는 경우 
		}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmSearchAction</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>filmSearchAction</h1>
	<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	<table class="table table-bordered">
		<thead>
			<tr>
				<td>fid</td>
				<td>title</td>
				<td>description</td>
				<td>category</td>
				<td>price</td>
				<td>length</td>
				<td>rating</td>
				<td>actors</td>
			</tr>
		</thead>
		
		<tbody>
			<%
				for(FilmList f : list) {
			%>
		
		
				<tr>
					<td><%=f.getFid()%></td>
					<td><%=f.getTitle()%></td>
					<td><%=f.getDescription()%></td>
					<td><%=f.getCategory()%></td>
					<td><%=f.getPrice()%></td>
					<td><%=f.getLength()%></td>
					<td><%=f.getRating()%></td>
					<td><%=f.getActors()%></td>
				</tr>
			<%		
				}
			%>
		</tbody>
	</table>
	
	<div>
	
		<%
			if(currentPage > 1) {
		%>
				<a href="<%=request.getContextPath()%>/filmSearchAction.jsp?currentPage=<%=currentPage-1%>&category=<%=category%>&rating=<%=rating%>&price=<%=price%>&length=<%=length%>&title=<%=title%>&actors=<%=actors%>" class="btn btn-outline-info">이전</a>
		<%
			}
		%>
		
		<%
			if(currentPage < lastPage) {
		%>
				<a href="<%=request.getContextPath()%>/filmSearchAction.jsp?currentPage=<%=currentPage+1%>&category=<%=category%>&rating=<%=rating%>&price=<%=price%>&length=<%=length%>&title=<%=title%>&actors=<%=actors%>" class="btn btn-outline-info">다음</a>
   
		<%
			}
		%>
	</div>
</body>
</html>
