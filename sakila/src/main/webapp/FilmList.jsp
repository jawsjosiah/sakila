<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page import = "dao.*" %>
<%
	int currentPage = 1;
	// 현재 페이지의 기본 값은 1페이지 
	if(request.getParameter("currentPage") != null) {
	// 이전, 다음 링크를 통해서 들어왔다면 
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		 
	}
	System.out.println(currentPage+ "<-- currentPage(FilmList.jsp)");
	// 디버깅 코드 
	
	int rowPerPage = 10;
	// 한 페이지당 행의 갯수 
	
	int beginRow = (currentPage-1) * rowPerPage;
	// 현재 페이지가 변경되면 beginRow도 변경되어야 한다. 
	System.out.println(beginRow+"//beginRow(FilmList.jsp)");
	// 디버깅 코드
	 
	int totalRow = 0; 
	// 전체 행의 갯수 0으로 초기화 
	
	List<FilmList> list = new ArrayList<FilmList>();
	
	FilmListDao filmListDao = new FilmListDao();
	
	list = filmListDao.selectFilmListByPage(beginRow, rowPerPage);
	
	totalRow = filmListDao.selectTotalRow();
	
	System.out.println(totalRow+"//totalRow(FilmList.jsp)");
	
	int lastPage = 0;
	
	if(totalRow % rowPerPage == 0) {
		lastPage = totalRow / rowPerPage; 
	} else {
		lastPage = (totalRow / rowPerPage) + 1;
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FilmList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>FilmList</h1>
	<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	<table class = "table table-bordered">
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
					for(FilmList fl : list) {
				%>
				<tr>
					<td><%=fl.getFid()%></td>
					<td><%=fl.getTitle()%></td>
					<td><%=fl.getDescription() %></td>
					<td><%=fl.getCategory() %></td>
					<td><%=fl.getPrice() %></td>
					<td><%=fl.getLength() %></td>
					<td><%=fl.getRating() %></td>
					<td><%=fl.getActors() %></td>
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
				<a href="<%=request.getContextPath() %>/FilmList.jsp?currentPage=<%=currentPage-1 %>">이전</a>
		<%
			}
		%>
		
		<%
			if(currentPage < lastPage) {
		%>
				<a href="<%=request.getContextPath() %>/FilmList.jsp?currentPage=<%=currentPage+1 %>">다음</a>
		<%
			}
		%>
	</div>
</body>
</html>