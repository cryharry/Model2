<%@page import="net.board.db.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	BoardBean boardBean = (BoardBean)request.getAttribute("boardBean");
	String pageNum = (String)request.getAttribute("pageNum");
	// 엔터키(\r\n) <br>바꾸기
	String content = boardBean.getContent().replace("\r\n","<br>");
%>
<h1>글내용보기</h1>
<table border="1">
	<tr>
		<td>글번호</td>
		<td><%=boardBean.getNum() %></td>
	</tr>
	<tr>
		<td>조회수</td>
		<td><%=boardBean.getReadcount() %></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td><%=boardBean.getName() %></td>
	</tr>
	<tr>
		<td>작성일</td>
		<td><%=boardBean.getDate() %></td>
	</tr>
	<tr>
		<td>글제목</td>
		<td colspan="3"><%=boardBean.getSubject() %></td>
	</tr>
	<tr>
		<td>글내용</td>
		<td colspan="3"><%=content %></td>
	</tr>
	<tr>
		<td colspan="4">
			<input type="button" value="글수정" onclick="location.href='./BoardModify.bo?num=<%=boardBean.getNum()%>&pageNum=<%=pageNum%>'">
			<input type="button" value="글삭제" onclick="location.href='./BoardDelete.bo?num=<%=boardBean.getNum()%>&pageNum=<%=pageNum%>'">
			<input type="button" value="답글쓰기" onclick="location.href='./BoardReply.bo?num=<%=boardBean.getNum()%>&re_ref=<%=boardBean.getRe_ref()%>&re_lev=<%=boardBean.getRe_lev()%>&re_seq=<%=boardBean.getRe_seq()%>'">
			<input type="button" value="글목록" onclick="location.href='./BoardList.bo?pageNum=<%=pageNum%>'">
		</td>
	</tr>
</table>
</body>
</html>