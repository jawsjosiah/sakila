<%@page import="java.sql.Types"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import = "dao.FilmDao" %>
<%@ page import = "java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%

	int filmId = 0;
	if(request.getParameter("filmId")!= null && !request.getParameter("filmId").equals("")) {
		filmId = Integer.parseInt(request.getParameter("filmId"));
	}
	// 디버깅 코드 
	System.out.println(filmId + " // filmId (filmInStock.jsp)");
	
	int storeId = 0;
	if(request.getParameter("storeId")!=null && !request.getParameter("storeId").equals("")) {
		storeId = Integer.parseInt(request.getParameter("storeId"));
	}
	// 디버깅 코드 
	System.out.println(storeId + " // storeId (filmInStock.jsp)");

	int count = 0; 
	// dao 프로시저 호출 
	FilmDao filmDao = new FilmDao(); 
	
	Map<String, Object> map= filmDao.filmInStockCall(filmId, storeId);
	
	List<Integer> inventoryList = (List<Integer>)map.get("list");
	count = (Integer)map.get("count");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>FilmInStock</h1>
	<a href="<%=request.getContextPath() %>/index.jsp">index</a>
		<form method="post" action="<%=request.getContextPath()%>/filmInStock.jsp">
			<table class = "table table-bordered">
				<tr>
					<td>필름 번호 선택</td>
					<td><input type = "number" name = "filmId"></td>
				</tr>
				<tr>
					<td>매장 번호 선택</td>
					<td><input type = "number" name = "storeId"></td>
				</tr>
				<tr>
					<td colspan="2"><button type="submit">제출</button></td>
				</tr>
			</table>
		</form>
		
		<%
			if(filmId!=0 && storeId!=0) {
		%>
			<h2><%=filmId %>번 영화가 <%=storeId %>번 가게에 <%=count %>개 남음</h2>
			<h2>inventoryID : 
		<%
				for(int id : inventoryList) {
					
		%>
					<%=id %>
		<%
				} 
		%>
			</h2>
		<%
			}
		%>
</body>
</html>