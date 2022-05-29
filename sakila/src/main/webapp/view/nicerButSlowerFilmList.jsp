<%@page import="dao.NicerButSlowerFilmListDao"%>
<%@ page import = "java.util.*" %>
<%@ page import = "vo.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	int currentPage = 1;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	System.out.println(currentPage+" // currentPage(NicerButSlowerFilmList.jsp)");
	
	int rowPerPage = 10; 
	
	int beginRow = (currentPage - 1) * rowPerPage;
	System.out.println(beginRow+" // beginRow(NicerButSlowerFilmList.jsp)");
	
	
	
	int totalRow = 0;
	
	List<NicerButSlowerFilmList> list = new ArrayList<>();
	
	NicerButSlowerFilmListDao nicerButSlowerFilmListDao = new NicerButSlowerFilmListDao();
	
	list = nicerButSlowerFilmListDao.selectNicerButSlowerFilmListByPage(beginRow, rowPerPage);
	
	totalRow = nicerButSlowerFilmListDao.selectTotalRow();
	
	System.out.println(totalRow+" // totalRow(NicerButSlowerFilmList.jsp)");
	
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
<title>NicerButSlowerFilmList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>NicerButSlowerFilmList</h1>
	<a href="<%=request.getContextPath() %>/index.jsp">index</a>
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
					for(NicerButSlowerFilmList nbsfl : list) {
				%>
			<tr>
				<td><%=nbsfl.getFid() %></td>
				<td><%=nbsfl.getTitle()%></td>
				<td><%=nbsfl.getDescription()%></td>
				<td><%=nbsfl.getCategory()%></td>
				<td><%=nbsfl.getPrice()%></td>
				<td><%=nbsfl.getLength()%></td>
				<td><%=nbsfl.getRating()%></td>
				<td><%=nbsfl.getActors()%></td>
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
				<a href="<%=request.getContextPath() %>/view/nicerButSlowerFilmList.jsp?currentPage=<%=currentPage-1 %>">이전</a>
		<%
			}
		%>
		
		<%
			if(currentPage < lastPage) {
		%>
				<a href="<%=request.getContextPath() %>/view/nicerButSlowerFilmList.jsp?currentPage=<%=currentPage+1 %>">다음</a>
		<%
			}
		%>
	</div>
</body>
</html>