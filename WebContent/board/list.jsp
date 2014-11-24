<%@page import="net.board.db.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// board관련 list
	List<BoardBean> boardList = (List)request.getAttribute("boardList");
	// page관련 list
	List pageList = (List)request.getAttribute("pageList");
	int count = Integer.parseInt(pageList.get(0).toString());
	// int count = ((Integer)pageList.get(0)).intValue();
	int currentPage = Integer.parseInt(pageList.get(1).toString());
	int pageCount = Integer.parseInt(pageList.get(2).toString());
	int startPage = Integer.parseInt(pageList.get(3).toString());
	int endPage = Integer.parseInt(pageList.get(4).toString());
	int number = Integer.parseInt(pageList.get(5).toString());
	int pageBlock = Integer.parseInt(pageList.get(6).toString());
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
%>
	<h1>글목록(전체 글:<%=count%>)</h1>
	<h3><a href="./BoardWrite.bo">글쓰기</a></h3>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>작성일</td>
			<td>조회</td>
			<td>IP</td>
		</tr>
	<%for(int i=0; i<boardList.size(); i++) {
	    BoardBean boardBean = boardList.get(i); %>
		<tr>
			<td><%=number-- %></td>
			<td>
			<%
				int wid = 0;
				if(boardBean.getRe_lev()>0) { //답변글
				    wid = 10*boardBean.getRe_lev();
				%>
					<img src="./board/level.gif" width="<%=wid%>">
					<img src="./board/re.gif">
				<%
				}
			%>
				<a href="./BoardDetailAction.bo?num=<%=boardBean.getNum()%>&pageNum=<%=currentPage%>"><%=boardBean.getSubject()%></a>
			</td>
			<td><%=boardBean.getName() %></td>
			<td><%=sdf.format(boardBean.getDate()) %></td>
			<td><%=boardBean.getReadcount() %></td>
			<td><%=boardBean.getIp() %></td>
		</tr>
	<%} %>
	</table>
	<% if(count > 0) {
		   // [이전]
		   if(startPage > pageBlock) {
		       %><a href="./BoardList.bo?pageNum=<%=startPage-pageBlock%>">[이전]</a><%
		   }
		   // [1] ~ [10]
		   for(int i=startPage; i<=endPage; i++) {
		       %><a href="./BoardList.bo?pageNum=<%=i%>">[<%=i %>]</a><%
		   }
		   // [다음]
		   if(endPage < pageCount) {
		       %><a href="./BoardList.bo?pageNum=<%=startPage+pageBlock%>">[다음]</a><%
		   }
		   %>
	<% } %>
 </body>
</html>


