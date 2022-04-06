<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "dao.ActorInfoDao" %>
<%@ page import = "vo.ActorInfo" %>
<%@ page import = "java.util.*" %>

<%
	int currentPage = 1;
	// 현재 페이지의 기본 값은 1페이지 
	if(request.getParameter("currentPage") != null) {
	// 이전, 다음 링크를 통해서 들어왔다면 
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		 
	}
	System.out.println(currentPage+ "<-- currentPage(ActorInfoList.jsp)");
	// 디버깅 코드 
	
	int rowPerPage = 10; 
	// 한 페이지당 행의 갯수  
	
	int beginRow = (currentPage-1) * rowPerPage; 
	// 현재 페이지가 변경되면 beginRow도 바뀌어야 한다. 
	System.out.println(beginRow+ "<-- beginRow(ActorInfoList.jsp)");
	// 디버깅 코드 
	
	int totalRow = 0;
	// 전체 행 갯수 
	List<ActorInfo> list = new ArrayList<ActorInfo>();
	// List타입 인스턴스 생성 
	ActorInfoDao actorInfoDao = new ActorInfoDao();
	// ActorInfoDao 인스턴스 생성 
	list = actorInfoDao.selectActorInfoListByPage(beginRow, rowPerPage);
	// 페이지 나누는 메서드 호출 
	totalRow = actorInfoDao.selectTotalRow(); 
	// 행의 총 갯수 반환하는 메서드 호출 
	System.out.println(totalRow+"//totalRow(ActorInfoList.jsp)");
	// 디버깅 코드 
	
	
	int lastPage = 0;
	// 마지막 페이지 0으로 초기화 
	if(totalRow % rowPerPage == 0) {
		lastPage = totalRow / rowPerPage;
		// 딱 떨어지는 경우 
	} else {
		lastPage = (totalRow / rowPerPage) + 1;
		// 나누어 떨어지지 않는 경우 
	}
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ActorInfoList</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
</head>
<body>
	<h1>ActorInfoList</h1>
	<a href="<%=request.getContextPath()%>/index.jsp">index</a>
	<table class="table table-bordered">
		<thead>
			<tr>
				<td>ActorId</td>
				<td>firstName</td>
				<td>lastName</td>
				<td>filmInfo</td>
			</tr>
		</thead>
		
		<tbody>
				<%
					for(ActorInfo ai : list) {
				%>
			
			<tr>
				<td><%=ai.getActorId()%></td>
				<td><%=ai.getFirstName()%></td>
				<td><%=ai.getLastName()%></td>
				<td><%=ai.getFilmInfo()%></td>
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
				<a href="<%=request.getContextPath()%>/ActorInfoList.jsp?currentPage=<%=currentPage-1%>">이전</a>
		<%
			}
		%>
		
		<%
			if(currentPage < lastPage) {
		%>
				<a href="<%=request.getContextPath()%>/ActorInfoList.jsp?currentPage=<%=currentPage+1%>">다음</a>
		<%
			}
		%>
	</div>
</body>
</html>