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
	// 파라미터 가져오기 num pageNum
	int num = Integer.parseInt(request.getAttribute("num").toString());
	String pageNum = (String)request.getAttribute("pageNum");
%>
	<h1>글삭제</h1>
	<h3>
		<a href="./BoardList.bo">글목록</a>
	</h3>
	<form action="./BoardDeleteAction.bo?pageNum=<%=pageNum%>" method="post">
	<input type="hidden" name="num" value="<%=num%>">
		<table border="1">
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="passwd">
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="글삭제">
					<input type="reset" value="다시쓰기">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>