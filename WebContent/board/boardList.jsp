<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<%
	int listCount = (Integer)(request.getAttribute("listCount"));
	int maxPage = (Integer)(request.getAttribute("maxPage"));
	int nowPage = (Integer)(request.getAttribute("page"));
	int startPage = (Integer)(request.getAttribute("startPage"));
	int endPage = (Integer)(request.getAttribute("endPage"));
	
%>

<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" type="text/css" href="css/shopping.css">
</head>
<body>
	<div id="wrap" align="center">
		<h1> 게시글 리스트</h1>
		<table class="list">
			<tr>
				<td colspan="5" style="border: white; text-align: right">
				<a href="BoardServlet?command=board_write_form">게시글 등록</a>
				</td>
			</tr>
			<tr><th>번호</th><th>제목</th><th>작성자</th><th>작성일</th><th>조회</th></tr>
			
			<c:forEach var="board" items="${boardList }">
				<tr class="record">
					<td>${board.num }</td>
					<td>
					<a href="BoardServlet?command=board_view&num=${board.num }">${board.title }</a>
					</td>
					<td>${board.name }</td>
					<td><fmt:formatDate value="${board.writedate }"/></td>
					<td>${board.readcount }</td>
				</tr>
			</c:forEach>
			<!-- 페이지 번호 시작 -->
			<tr align="center" height="20">
					<td colspan="5">
			<c:choose>
				<c:when test="${nowPage <= 1}">
					[이전]&nbsp;
				</c:when>
				<c:otherwise>
					<a href="BoardServlet?command=board_list&page=${nowPage -1}">[이전]</a>
				</c:otherwise>
			</c:choose>


			<c:forEach var="pageNum" begin="${startPage }" end="${endPage }">
				 <c:choose>
                     <c:when test="${1 == pageNum }">
                        ${pageNum }
                     </c:when>
                     <c:otherwise>
                        <a href="BoardServlet?command=board_list&page=${pageNum }">${pageNum }</a>
                     </c:otherwise>
                  </c:choose>
			</c:forEach>


			<c:choose>
				<c:when test="${nowPage <= 1}">
					
				</c:when>
				<c:otherwise>
					<a href="BoardServlet?command=board_list&page=${nowPage +1}">[다음]</a>
				</c:otherwise>
			</c:choose>
			
				</td>
				</tr> 
			<!-- 페이지 번호 끝 -->
			
		</table>
	</div>
	
	
	
		<%-- <tr align="center" height="20">
					<td colspan="5">
						<%if(nowPage <= 1){ %>
						[이전]&nbsp;
						<%}else{%>
						<a href="BoardServlet?command=board_list&page=<%=nowPage-1 %>">[이전]</a>
						<%} %>
						
						<%for(int i=startPage; i<=endPage; i++){ 
							if( i == nowPage){
						%>
							<%=i %>
							<%}else{ %>
							<a href="BoardServlet?command=board_list&page=<%=i%>">[<%=i %>]</a>
							<%} %>
						<%} %>
						
						
						<%if(nowPage >= maxPage){ %>
						<%}else{ %>
						<a href="BoardServlet?command=board_list&page=<%=nowPage+1 %>">[다음]</a>
						<%} %>
					</td>
				</tr>  --%>
	
	
	<<%-- c:if test="${result eq 1 }">
		<script type="text/javascript">
			alert("수정완료");
		</script>
	</c:if>	 --%>
	
</body>
</html>