<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function check() {
	if(document.fr.name.value == "") {
		alert("이름을 입력하세요");
		document.fr.name.focus();
		return false;
	}
	if(document.fr.subject.value == "") {
		alert("제목을 입력하세요");
		document.fr.subject.focus();
		return false;
	}
	if(document.fr.content.value == "") {
		alert("내용을 입력하세요");
		document.fr.content.focus();
		return false;
	}
	if(document.fr.passwd.value == "") {
		alert("비밀번호를 입력하세요");
		document.fr.passwd.focus();
		return false;
	}
}
</script>
</head>
<body>
	<h1>글쓰기</h1>
	<h3>
		<a href="./BoardList.bo">글목록</a>
	</h3>
	<form action="./BoardAddAction.bo" method="post" name="fr" onsubmit="return check()">
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="subject"></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea name="content" rows="13" cols="40"></textarea></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="passwd"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="글쓰기"> <input
					type="reset" value="다시쓰기"></td>
			</tr>
		</table>
	</form>
</body>
</html>