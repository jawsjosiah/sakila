<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.StaffListDao" %>
<%@ page import = "vo.StaffList" %>
<%@ page import = "java.util.*" %>
<%
	int currentPage = 1;
	// 현재 페이지의 기본 값을 1페이지로 설정 
	if(request.getParameter("currentPage") != null) {
	// 이전, 다음 링크를 통해서 들어왔다면 
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		// 새로 currentPage의 값을 바꿔준다. 
	}
	System.out.println(currentPage+"//currentPage(staffListList.jsp)");
	// 디버깅 코드 
	
	int rowPerPage = 10; 
	// 한 페이지당 행의 개수 
	int beginRow = (currentPage - 1) * rowPerPage; 
	// 현재 페이지가 변경되면 beginRow도 변경되어야 한다. 
	System.out.println(beginRow+"//beginRow(staffListList.jsp)"); 
	// 디버깅 코드 
	
	int totalRow = 0; 
	// 전체 행 갯수 
	
	List<StaffList> list = new ArrayList<StaffList>(); 
	// 페이지 나누는 메서드의 리턴 타입을 받기 위해 List 타입 인스턴스 생성 
	
	StaffListDao staffListDao = new StaffListDao();
	// DAO 인스턴스 생성. DAO 내 메서드 호출하기 위해서 선언 
	
	list = staffListDao.selectStaffListViewByPage(beginRow, rowPerPage);
	// 페이지 나누는 메서드 호출 
	
	totalRow = staffListDao.selectTotalRow();
	// 행의 총 갯수를 반환하는 메서드 호출
	System.out.println(totalRow+"//totalRow(StaffListList.jsp)");
	// 디버깅 코드 
	
	int lastPage = 0; 
	// 마지막 페이지 0으로 초기화 
	if(totalRow % rowPerPage == 0) {
		lastPage = totalRow / rowPerPage;
		// 딱 떨어지는 경우 마지막 페이지 
	} else {
		lastPage = (totalRow / rowPerPage) + 1;
		// 나누어 떨어지지 않는 경우 마지막 페이지 
	}
	
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>StaffListView</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>StaffListList</h1>
	<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	<table class="table table-bordered">
		<thead>
			<tr>
				<td>id</td>
				<td>name</td>
				<td>address</td>
				<!-- <td>zipCode</td>  -->
				<td>phone</td>
				<td>city</td>
				<td>country</td>
				<td>sid</td>
			</tr>
		</thead>
		
		<tbody>
				<%
					for(StaffList sl : list) {
				%>
			<tr>
				<td><%=sl.getId()%></td>
				<td><%=sl.getName()%></td>
				<td><%=sl.getAddress()%></td>
				<!-- <td><%=sl.getZipCode()%></td>  -->
				<td><%=sl.getPhone()%></td>
				<td><%=sl.getCity()%></td>
				<td><%=sl.getCountry()%></td>
				<td><%=sl.getSid()%></td>
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
				<a href="<%=request.getContextPath()%>/view/staffListList.jsp?currentPage=<%=currentPage-1%>">이전</a>
		<%
			}
		%>
		
		<%
			if(currentPage < lastPage) {
		%>
				<a href="<%=request.getContextPath()%>/view/staffListList.jsp?currentPage=<%=currentPage+1%>">다음</a>
		<%
			}
		%>
	</div>
</body>
</html>