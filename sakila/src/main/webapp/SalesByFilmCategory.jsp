<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%@ page import = "vo.*" %>
<%
	int currentPage = 1;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt("currentPage");
	}
	System.out.println(currentPage+" // currentPage(SalesByFilmCategory.jsp)");
	
	int rowPerPage = 10;
	
	int beginRow = (currentPage - 1) * rowPerPage;
	
	List<SalesByFilmCategory> list = new ArrayList<>();
	
	SalesByFilmCategoryDao salesByFilmCategoryDao = new SalesByFilmCategoryDao(); 
	
	list = salesByFilmCategoryDao.selectSalesByFilmCategoryByPage(beginRow, rowPerPage);
			
	int totalRow = salesByFilmCategoryDao.selectTotalRow();
	
	
	
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
<title>SalesByFilmCategory</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>SalesByFilmCategory</h1>
	<a href="<%=request.getContextPath() %>/index.jsp">index</a>
	<table class="table table-bordered">
		<thead>
			<tr>
				<td>category</td>
				<td>totalSales</td>
			</tr>
		</thead>
		<tbody>
			<%
				for(SalesByFilmCategory sbfc : list) {
			%>
			<tr>
				<td><%=sbfc.getCategory()%></td>
				<td><%=sbfc.getTotalSales()%></td>
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
				<a href="<%=request.getContextPath() %>/SalesByFilmCategory.jsp?currentPage=<%=currentPage-1 %>">이전</a>
		<%
			}
		%>
		
		<%
			if(currentPage > lastPage) {
		%>
				<a href="<%=request.getContextPath() %>/SalesByFilmCategory.jsp?currentPage=<%=currentPage+1 %>">다음</a>
		<%
			}
		%>
	</div>
</body>
</html>