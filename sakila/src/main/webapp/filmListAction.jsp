<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ page import="vo.*" %><%@ page import="dao.*" %><%@ page import="java.util.*" %>

<% 
	String word=request.getParameter("word");
	String type=request.getParameter("type");
	//1 controller 요청처리 모델호출
	int currentPage=1;
	
	int rowPerPage = 10;
	if(request.getParameter("currentPage")!=null){
		currentPage= Integer.parseInt(request.getParameter("currentPage"));
	}
	int beginPage= rowPerPage*(currentPage-1);
	
	FilmDao filmdao= new FilmDao();
	
	ArrayList<Film> list = filmdao.selectFilmListBySearch(word, type, beginPage, rowPerPage);
	int lastPage= filmdao.filmCount(type, word, rowPerPage);
	
	
	//2 view	
	
	
%>    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"></script>

<title>selectFilmListBySearch</title>
</head>
<body>
	<form method= "post" action="<%=request.getContextPath()%>/filmListAction.jsp">
		<br>
		<select name="type">
			<option value ="title">영화제목</option>
			<option value="description">영화설명</option>
			<option value="special_features">주석</option>
		</select>
		<input type="text" name="word" <%if(word==null||word.equals("")){ %>placeholder="검색"<%}else{ %>value="<%=word%>"<% }%>>
		<button type="submit">검색</button>
	</form>
	<h1>필름목록</h1>
	<table border="1">
		<tr>
			<th>일련번호</th>
			<th>영화제목</th>
			<th>영화설명</th>
			<th>출시년도</th>
			<th>자막언어</th>
			<th>원어</th>
			<th>대여기간</th>
			<th>대여료</th>
			<th>러닝타임</th>
			<th>파손시 비용</th>
			<th>영화등급</th>
			<th>주석</th>
			<th>갱신일자</th>
		</tr>
		<%for(Film f : list){ %>
			<tr>
				<td><%=f.getFilmId()%></td>
				<td><%=f.getTitle() %></td>
				<td><%=f.getDescription() %></td>
				<td><%=f.getReleaseYear() %></td>
				<td><%=f.getLanguageId() %></td>
				<td><%=f.getOriginalLanguageId() %></td>
				<td><%=f.getRentalDuration() %></td>
				<td><%=f.getRentalRate() %></td>
				<td><%=f.getLength() %></td>
				<td><%=f.getReplacementCost() %></td>
				<td><%=f.getRating() %></td>
				<td><%=f.getSpecialFeatures() %></td>
				<td><%=f.getLastUpdate() %></td>
			</tr>
		<%} %>
	</table>
	<div>
		<nav aria-label="Page navigation example">
  			<ul class="pagination justify-content-center pagination-lg">
  			<%if(word==null){ %>
   				
	   			<%if(currentPage > 1){%>	
	   				<li class="page-item">
	   				<% }else{ %>
	   				<li class="page-item disabled"><%} %>
	      				<a class="page-link" href="<%=request.getContextPath()%>/filmListAction.jsp?currentPage=<%=currentPage-1%>">Previous</a>
	    			</li>
	    		<%if(currentPage > 1){%>	
	    			<li class="page-item">
	    				<a class="page-link" href="<%=request.getContextPath()%>/filmListAction.jsp?currentPage=<%=currentPage-1%>"><%=currentPage-1%></a></li>
	    		<%} %>
	    			<li class="page-item active" aria-current="page">
	    				<span class="page-link"><%=currentPage%></span></li>
	    		<%if(currentPage < lastPage){%>		
	    			<li class="page-item">
	    				<a class="page-link" href="<%=request.getContextPath()%>/filmListAction.jsp?currentPage=<%=currentPage+1%>"><%=currentPage+1%></a></li>
	    		<%}
	    		  if(currentPage < lastPage){%>	
	    			<li class="page-item">
	    		<%}else{ %>
	    			<li class="page-item disabled"><%} %>
	      		   		<a class="page-link" href="<%=request.getContextPath()%>/filmListAction.jsp?currentPage=<%=currentPage+1%>">Next</a>
	    			</li>
	    	<%}else{ %>
	    		<%if(currentPage > 1){%>	
	   				<li class="page-item">
	   				<% }else{ %>
	   				<li class="page-item disabled"><%} %>
	      				<a class="page-link" href="<%=request.getContextPath()%>/filmListAction.jsp?currentPage=<%=currentPage-1%>&word=<%=word%>&type=<%=type%>">Previous</a>
	    			</li>
	    		<%if(currentPage > 1){%>	
	    			<li class="page-item">
	    				<a class="page-link" href="<%=request.getContextPath()%>/filmListAction.jsp?currentPage=<%=currentPage-1%>&word=<%=word%>&type=<%=type%>"><%=currentPage-1%></a></li>
	    		<%} %>
	    			<li class="page-item active" aria-current="page">
	    				<span class="page-link"><%=currentPage%></span></li>
	    		<%if(currentPage < lastPage){%>		
	    			<li class="page-item">
	    				<a class="page-link" href="<%=request.getContextPath()%>/filmListAction.jsp?currentPage=<%=currentPage+1%>&word=<%=word%>&type=<%=type%>"><%=currentPage+1%></a></li>
	    		<%}
	    		  if(currentPage < lastPage){%>	
	    			<li class="page-item">
	    		<%}else{ %>
	    			<li class="page-item disabled"><%} %>
	      		   		<a class="page-link" href="<%=request.getContextPath()%>/filmListAction.jsp?currentPage=<%=currentPage+1%>&word=<%=word%>&type=<%=type%>">Next</a>
	    			</li>
	    	<%} %>				
 	   		</ul>
	   </nav></div>
</body>
</html>