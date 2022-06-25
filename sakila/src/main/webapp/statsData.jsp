<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%
	StatsDataDao statsDataDao = new StatsDataDao();
	
	// 1. customer별 총 amount 
	List<Map<String, Object>> amountByCustomer = statsDataDao.amountByCustomer();
	
	// 2. rental_rate 별 영화 개수 
	List<Map<String, Object>> filmCountByRentalRate = statsDataDao.filmCountByRentalRate();
	
	// 3. rating 별 영화 개수
	List<Map<String, Object>> filmCountByRating = statsDataDao.filmCountByRating();
	
	// 4. language별 영화 개수 
	List<Map<String, Object>> filmCountByLanguage = statsDataDao.filmCountByLanguage();
	
	// 5. length별 영화 개수(구간, 기준을 정해서)
	List<Map<String, Object>> filmCountByLength = statsDataDao.filmCountByLength();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>statsData</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>1. amountByCustomer</h1>
	<table border="1">
		<tr>
			<th>고객 아이디</th>
			<th>고객 이름</th>
			<th>총 지불액</th>
		</tr>
		
	<%
		for(Map<String, Object> m : amountByCustomer) {
	%>
			<tr>
				<td><%=m.get("customerId")%></td>
				<td><%=m.get("name")%></td>
				<td><%=m.get("total")%></td>
			</tr>
	<%
		}
	%>
	</table>
	
	<h1>2. rental_rate별 영화 개수</h1>
	<table border="1">
		<tr>
			<td>rentalRate</td>
			<td>count</td>
		</tr>
		
		<%
			for(Map<String, Object> m : filmCountByRentalRate) {
		%>
				<tr>
					<td><%=m.get("rentalRate") %></td>
					<td><%=m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>3. rating별 영화 갯수</h1>
	<table border="1">
		<tr>
			<td>Rating</td>
			<td>count</td>
		</tr>
		
		<%
			for(Map<String, Object> m : filmCountByRating) {
		%>
				<tr>
					<td><%=m.get("rating") %></td>
					<td><%=m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>4. language별 영화 갯수</h1>
	<table border="1">
		<tr>
			<td>name</td>
			<td>count</td>
		</tr>
		
		<%
			for(Map<String, Object> m : filmCountByLanguage) {
		%>
				<tr>
					<td><%=m.get("name") %></td>
					<td><%=m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<h1>5. length별 영화 갯수</h1>
	<table border="1">
		<tr>
			<td>length</td>
			<td>count</td>
		</tr>
		
		<%
			for(Map<String, Object> m : filmCountByLength) {
		%>
				<tr>
					<td><%=m.get("t.length2") %></td>
					<td><%=m.get("cnt") %></td>
				</tr>
		<%
			}
		%>
	</table>
</body>
</html>