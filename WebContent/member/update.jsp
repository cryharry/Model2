<%@page import="net.member.db.MemberBean"%>
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
	// 세션값 가져오기
	String id = (String)session.getAttribute("id");
	// 세션값 없으면 loginForm.jsp
	if(id == null) response.sendRedirect("./MemberLogin.me");
	// MemberBean memberBean = 메서드호출 getMember(id)
	MemberBean memberBean = (MemberBean)request.getAttribute("memberBean");
	String gender = memberBean.getGender();
	if(gender == null) {
	    gender = "남";
	}
%>
<h1>회원정보수정</h1>
<form action="./MemberUpdateAction.me" method="post">
아이디:<input type="text" name="id" value="<%=memberBean.getId()%>" readonly><br>
비밀번호:<input type="password" name="passwd"><br>
이름:<input type="text" name="name" value="<%=memberBean.getName()%>"><br>
나이:<input type="text" name="age" value="<%=memberBean.getAge()%>"><br>
성별:남<input type="radio" name="gender" value="남" <%if(gender.equals("남")){%>checked<%} %>>
     여<input type="radio" name="gender" value="여" <%if(gender.equals("여")){%>checked<%} %>><br>
이메일주소:<input type="text" name="email" value="<%=memberBean.getEmail()%>"><br>
<input type="submit" value="정보수정">
</form>
</body>
</html>