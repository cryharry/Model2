<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>우편번호 검색창</title>
</head>
<body>
<h1>우편번호 검색</h1>
<form action="./ZipcodeAction.me" method="post" name="wfr">
지역명:<input type="text" name="dong">
<input type="submit" value="검색"><br>
동을 입력하세요(예:화명,덕천 2글자 이상 입력)<br>
</form>
<h1>검색결과</h1>
<table border="1">
	<tr>
		<td>우편번호</td>
		<td>주소</td>
	</tr>
	<tr>
		<td><%= %></td>
		<td><%= %></td>
	</tr>
</table>
</body>
</html>