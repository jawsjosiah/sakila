<%@page import="java.sql.Types"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.CallableStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import = "dao.FilmDao" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Class.forName("org.mariadb.jdbc.Driver");
	Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
	
	String call = "{call film_in_stock(?,?,?)}";
	CallableStatement cstmt = conn.prepareCall(call);
	// 1번 필름이 1번 가게에 남아있는 inventory_id 출력
	cstmt.setInt(1,1);
	// 1번 필름
	cstmt.setInt(2,1);
	// 1번 가게 
	cstmt.registerOutParameter(3, Types.INTEGER);
	// 몇 개 남았는지 out 매개 변수 
	ResultSet rs = cstmt.executeQuery();
	System.out.println(rs+"//rs(filmInStock.jsp)");
	// 테이블 형태  ResultSet에 넣는다. 
	
	// List, ArrayList, HashMap
	while(rs.next()) {
		System.out.println(rs.getInt(1));
	}
	System.out.println(cstmt.getInt(3));
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
		<form method="post" action="<%=request.getContextPath()%>/filmInStock.jsp">
			<table class = "table table-bordered">
				<tr>
					<td>필름 번호 선택</td>
					<td><input type = "number" name = "filmNo"></td>
				</tr>
				<tr>
					<td>매장 번호 선택</td>
					<td><input type = "number" name = "storeNo"></td>
				</tr>
				<tr>
					<td colspan="2"><button type="submit">제출</button></td>
				</tr>
			</table>
		</form>
		
		<table class="table table-striped">
			<tr>
				<th>inventory_id</th>
			</tr>
			
			
		</table>
</body>
</html>