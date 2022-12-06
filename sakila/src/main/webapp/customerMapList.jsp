<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="vo.*"%>
<%@ page import="dao.*"%>
<%@ page import="java.util.*"%>
<%

	//페이징 구하기
	int currentPage = 1;
	if(request.getParameter("currentPage") != null) {
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}
	int rowPerPage = 10;
	int beginRow = (currentPage-1) * rowPerPage;
	
	
	// Model 
	CustomerDao customerDao = new CustomerDao();
	
	// lastPage
	int count = customerDao.customerCount();
	int lastPage = count / rowPerPage;
	if(count % rowPerPage != 0) {
		lastPage += 1;
	}

	ArrayList<HashMap<String, Object>> list = customerDao.selectCustomerMapList(beginRow, rowPerPage);

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Map 타입 사용 -->
	<table border="1">
		<tr>
			<th>firstName</th>
			<th>lastName</th>
			<th>address</th>
			<th>district</th>
			<th>city</th>
			<th>country</th>
		</tr>
		<%
			for(HashMap<String, Object> h : list) {
		%>
				<tr>
					<td><%=h.get("firstName")%></td>
					<td><%=h.get("lastName")%></td>
					<td><%=h.get("address")%></td>
					<td><%=h.get("district")%></td>
					<td><%=h.get("city")%></td>
					<td><%=h.get("country")%></td>
				</tr>
		<%
			}
		%>
	</table>
	
	<!-- 페이징 -->
	<a href="<%=request.getContextPath()%>/customerMapList.jsp?currentPage=1"><%="처음"%></a>
	<%
		if(currentPage > 1) {
	%>
			<a href="<%=request.getContextPath()%>/customerMapList.jsp?currentPage=<%=currentPage-1%>"><%="<"%></a>
	<%
		} else {
	%>
			<a href="<%=request.getContextPath()%>/customerMapList.jsp?currentPage=1"><%="<"%></a>
	<%
		}
	%>
		[<%=currentPage%>/<%=lastPage%>]
	<%
		if(currentPage < lastPage) {
	%>
			<a href="<%=request.getContextPath()%>/customerMapList.jsp?currentPage=<%=currentPage+1%>"><%=">"%></a>
	<%
		} else {
	%>
			<a href="<%=request.getContextPath()%>/customerMapList.jsp?currentPage=<%=lastPage%>"><%=">"%></a>
	<%
		}
	%>
	<a href="<%=request.getContextPath()%>/customerMapList.jsp?currentPage=<%=lastPage%>"><%="마지막"%></a>
	
</body>
</html>