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
	request.setCharacterEncoding("utf8");
	
	BoardBean boardBean = (BoardBean)request.getAttribute("boardBean");
	String pageNum = (String)request.getAttribute("pageNum");
%>
	<h1>글수정</h1>
	<h3>
		<a href="./BoardList.bo">글목록</a>
	</h3>
	<form action="./BoardModifyAction.bo?pageNum=<%=pageNum%>" method="post">
	<input type="hidden" name="num" value="<%=boardBean.getNum()%>">
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value="<%=boardBean.getName() %>"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject" value="<%=boardBean.getSubject() %>"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" rows="13" cols="40"><%=boardBean.getContent() %></textarea></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="passwd"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="글수정"> <input
					type="reset" value="다시쓰기"></td>
			</tr>
		</table>
	</form>
</body>
</html>