<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.*"%>
<%
	int minMonthlyPurchases = 0;
	if(request.getParameter("minMonthlyPurchases")!= null && !request.getParameter("minMonthlyPurchases").equals("")) {
		minMonthlyPurchases = Integer.parseInt(request.getParameter("minMonthlyPurchases"));
	}
	
	double minDollarAmountPurchased = 0;
	if(request.getParameter("minDollarAmountPurchased")!= null && !request.getParameter("minDollarAmountPurchased").equals("")) {
		minDollarAmountPurchased = Double.parseDouble(request.getParameter("minDollarAmountPurchased"));
	}
	
	int countRewardees = 0;
	
	FilmDao filmDao = new FilmDao();
	
	Map<String, Object> map = filmDao.rewardsReportCall(minMonthlyPurchases, minDollarAmountPurchased);
	
	List<Integer> list = (List<Integer>)map.get("list");
	
	countRewardees = (Integer)map.get("count");
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>RewardsReport</h1>
	<a href="<%=request.getContextPath() %>/index.jsp">index</a>
		<form method="post" actioin="<%=request.getContextPath()%>/rewardsReport.jsp">
			<table border="1">
				<tr>
					<td>최소 구매 횟수 선택</td>
					<td><input type="number" name="minMonthlyPurchases"></td>
				</tr>
				
				<tr>
					<td>최소 구매액 선택</td>
					<td><input type="number" name="minDollarAmountPurchased"></td>
				</tr>
				<tr>
					<td colspan="2"><button type="submit">제출</button></td>
				</tr>
			</table>
		</form>
		
		<%
			if(minMonthlyPurchases!=0 && minDollarAmountPurchased!=0) {	
		%>
			<h2><%=minMonthlyPurchases%>번 구매하고 <%=minDollarAmountPurchased%>달러 지불하여 <%=countRewardees %></h2>
			<h2>i : </h2>
		<%
				for(int i : list) {
		%>
					<%=i %>
		<%
				}
		%>
		
		<%
			}
		%>
</body>
</html>