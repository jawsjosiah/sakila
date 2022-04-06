<%@page import="dao.CustomerListDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.CustomerList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	int currentPage = 1;
	// 현재 페이지의 기본 값은 1페이지 
	if(request.getParameter("currentPage") != null) {
	// 이전, 다음 링크를 통해서 들어왔다면 
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		// 링크를 통해서 들어온 페이지수로 currenPage 변경 
	}
	System.out.println(currentPage+"//currentPage(CustomerList.jsp)");
	// 디버깅 코드 
	
	int rowPerPage = 10; 
	// 한 페이지당 보여줄 행의 갯수 
	
	int beginRow = (currentPage-1) * rowPerPage; 
	// 현재 페이지가 변경되면 beginRow도 변경되어야 한다. 
	System.out.println(beginRow+"//beginRow(customerList.jsp)");
	// 디버깅 코드 
	
	int totalRow = 0; 
	// 전체 행 갯수 
	
	List<CustomerList> list = new ArrayList<CustomerList>();
	// List타입 인스턴스 생성, DAO에 페이징 메서드 리턴 값으로 받을 타입이다. 
	CustomerListDao customerListDao = new CustomerListDao(); 
	// CustomerListDao 인스턴스 생성 
	list = customerListDao.selectCustomerListByPage(beginRow, rowPerPage);
	// 페이지 나누는 메서드 호출 
	totalRow = customerListDao.selectTotalRow();
	// 행의 총 갯수를 반환하는 메서드 호출 
	System.out.println(totalRow+"//totalRow(CustomerList.jsp)");
	// 디버깅 코드. 어디든 문제 있으면 추가할 생각 하자.
	
	int lastPage = 0; 
	// 마지막 페이지 
	if(totalRow % rowPerPage == 0) {
		// 나누어 떨어질 때 마지막 페이지 
	} else {
		lastPage = (totalRow / rowPerPage) + 1;
		// 나누어 떨어지지 않았을 때 마지막 페이지 
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CustomerList</title>
</head>
<body>
	<h1>CustomerList</h1>
	
	<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	
	<table class="table table-bordered">
		<thead>
			<tr>
				<td>id</td>
				<td>name</td>
				<td>address</td>
				<td>zipCode</td>
				<td>phone</td>
				<td>city</td>
				<td>country</td>
				<td>notes</td>
				<td>sid</td>
			</tr>
		</thead>
		
		<tbody>
				<%
					for(CustomerList cl : list) {
				%>
				
			<tr>
				<td><%=cl.getId()%></td>
				<td><%=cl.getName()%></td>
				<td><%=cl.getAddress()%></td>
				<td><%=cl.getZipCode()%></td>
				<td><%=cl.getPhone()%></td>
				<td><%=cl.getCity()%></td>
				<td><%=cl.getCountry()%></td>
				<td><%=cl.getNotes()%></td>
				<td><%=cl.getSid()%></td>
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
				<a href="<%=request.getContextPath()%>/CustomerList.jsp?currentPage=<%=currentPage-1%>">이전</a>
		<%
			}
		%>
		
		<%
			if(currentPage < lastPage) {
		%>
				<a href="<%=request.getContextPath()%>/CustomerList.jsp?currentPage=<%=currentPage+1%>">다음</a>
		<%		
			}
		%>
	</div>
</body>
</html>