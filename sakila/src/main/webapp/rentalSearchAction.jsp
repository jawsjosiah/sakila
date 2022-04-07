<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import = "dao.*" %>
<%
	// rentalSearchForm.jsp 페이지에서 받아올 값들 
	
	int storeId = -1; // 선택 안하고 넘겼을 떄 
	if(request.getParameter("storeId")!=null && !request.getParameter("storeId").equals("")){
		// 만약 storeId가 null도 아니고 공백도 아닌 경우에는 넘겨온 값을 받아서 저장한다. 
		storeId = Integer.parseInt(request.getParameter("storeId"));
		// storeID 디버깅 코드 
		System.out.println(storeId+" // storeId(rentalSearchAction.jsp)");
	}
	
	String customerName = ""; 
	// 유효성 검사 
	if(request.getParameter("customerName")!= null) {
		// null 아니면 넘겨온 값 받는다 
		customerName = request.getParameter("customerName");
		// customerName 디버깅 코드 
		System.out.println(customerName+" // customerName(rentalSearchAction.jsp)");
	}
	
	String beginDate = "";
	if(request.getParameter("beginDate")!=null) {
		beginDate = request.getParameter("beginDate");
		// beginDate 디버깅 코드 
		System.out.println(beginDate+" // beginDate(rentalSearchAction.jsp)");
	}
	
	String endDate = "";
	if(request.getParameter("endDate")!= null) {
		endDate = request.getParameter("endDate");
		System.out.println(endDate+" // endDate(rentalSearchAction.jsp)");
	}
	

	int currentPage = 1;
	// 현재 페이지 1로 설정 
	if(request.getParameter("currentPage") != null) {
	// 이전, 다음을 통해 들어왔다면 
		currentPage = Integer.parseInt("currentPage");
	}
	System.out.println(currentPage+" // currentPage(rentalSearchAction.jsp)");
	// 디버깅 코드
	
	int rowPerPage = 10; 
	// 페이지당 보여줄 행의 갯수 
	
	int beginRow = (currentPage - 1) * rowPerPage;
	// 페이지마다 보여줄 시작행 
	System.out.println(beginRow+ "// beginRow(rentalSearchAction.jsp)");
	// 디버깅 코드 
	
	RentalDao rentalDao = new RentalDao();
	List<Map<String, Object>> list = rentalDao.selectRentalSearchList(storeId, customerName, beginDate, endDate, beginRow, rowPerPage);
 
	int totalRow = 0; 
	// 총 행의 갯수 0으로 초기화 
	
	totalRow = rentalDao.selectTotalRow(storeId, customerName, beginDate, endDate);
	
	int lastPage = 0;
	// 마지막 페이지 0으로 초기화 
	
	if(totalRow % rowPerPage == 0 ) {
		lastPage = totalRow / rowPerPage;
	} else {
		lastPage = ( totalRow / rowPerPage ) + 1;
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>rentalSearchAction</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>검색 결과 리스트</h1>
	<a href="<%=request.getContextPath() %>/index.jsp">index</a>
	<table class="table table-bordered">
		<thead>
			<tr>
				<td>rentalId</td>
				<td>rentalDate</td>
				<td>inventoryId</td>
				<td>customerId</td>
				<td>returnDate</td>
				<td>staffId</td>
				<td>lastUpdate</td>
				<td>customerName</td>
				<td>storeId</td>
				<td>filmId</td>
				<td>filmTitle</td>
			</tr>
		</thead>
		
		<tbody>
			<%
				for(Map<String, Object> m : list) {
			%>
					<tr>
						<td><%=m.get("rentalId")%></td>
						<td><%=m.get("rentalDate")%></td>
						<td><%=m.get("inventoryId")%></td>
						<td><%=m.get("customerId")%></td>
						<td><%=m.get("returnDate")%></td>
						<td><%=m.get("staffId")%></td>
						<td><%=m.get("lastUpdate")%></td>
						<td><%=m.get("cName")%></td>
						<td><%=m.get("storeId")%></td>
						<td><%=m.get("filmId")%></td>
						<td><%=m.get("filmTitle")%></td>
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
				<a href="<%=request.getContextPath()%>/rentalSearchAction.jsp?currentPage=<%=currentPage-1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&<%=endDate%>">이전</a>
		<%
			}
		%>
		
		<%
			if(currentPage < lastPage) {
		%>
				<a href="<%=request.getContextPath()%>/rentalSearchAction.jsp?currentPage=<%=currentPage+1%>&storeId=<%=storeId%>&customerName=<%=customerName%>&beginDate=<%=beginDate%>&<%=endDate%>">다음</a>
   
		<%
			}
		%>
	</div>
</body>
</html>