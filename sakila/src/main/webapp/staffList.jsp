<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%
	
	StaffDao staffDao = new StaffDao();
	// 받을 변수 list 생성 
	List<Map<String, Object>> list = staffDao.selectStaffList();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Staff List</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<!-- 인덱스로 이동하는 레퍼런스 생성 -->
	<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	
	<!-- 글씨 크기 h1로 설정 -->
	<div class="jumbotron text-center">
		<h1>Staff List</h1>
	</div>
	<!-- 테이블 테두리 굵기 1로 설정 -->
	
		
			<table border="1">
			<!-- 맨위에 컬럼에 들어갈 이름 -->
			<thead>
				<tr>
					<th>staffId</th>
					<th>staffName</th>
					<th>storeId</th>
					<th>addressId</th>
					<th>email</th>
					<th>active</th>
					<th>staffAddress</th>
					<th>lastUpdate</th>
				</tr>
			</thead>
			
			<!-- 본문 부분 -->
			<tbody>
				<%
					for(Map m : list) {
						
				%>
						<!-- 쿼리에서 받아온 데이터 들여옴 -->
						<tr>
							<td><%=m.get("staffId")%></td>
							<td><%=m.get("staffName")%></td>
							<td><%=m.get("storeId")%></td>
							<td><%=m.get("addressId")%></td>
							<td><%=m.get("email")%></td>
							<td><%=m.get("active")%></td>
							<td><%=m.get("staffAddress")%></td>
							<td><%=m.get("lastUpdate")%></td>
						</tr>
				<%
					}
				%>
			</tbody>
		</table>
	
	
		
</body>
</html>