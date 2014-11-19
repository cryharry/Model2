<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="MemberJoinAction.me" method="post" name="frm">
아이디:<input type="text" name="id" onkeyup="show(this.value);" id="txtId"><span id="ajax"></span><br>
비밀번호:<input type="password" name="passwd" id="txtPasswd"><br>
이름:<input type="text" name="name" id="txtName"><br>
나이:<input type="text" name="age" id="txtAge" onkeypress="return onlyNum(event, 'numbers');" onkeydown="return onlyNum_han(this);"><br>
성별:남<input type="radio" name="gender" id="txtGender" value="남">
     여<input type="radio" name="gender" id="txtGender" value="여"><br>
이메일주소:<input type="text" name="email" id="txtEmail"><br>
<input type="submit" value="회원가입">
</form>
</body>
</html>