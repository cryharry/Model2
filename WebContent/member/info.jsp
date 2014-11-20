<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="net.member.db.MemberBean"%>
<%
	String id = (String)session.getAttribute("id");
	if(id == null) {
		response.sendRedirect("./MemberLogin.me");
	}
	MemberBean memberbean = (MemberBean)request.getAttribute("memberBean");
%>
<html>
<body>
	아이디:<%=memberbean.getId() %><br>
	비밀번호:<%=memberbean.getPasswd() %><br>
	이름:<%=memberbean.getName() %><br>
	가입날짜:<%=memberbean.getReg_date() %><br>
	나이:<%=memberbean.getAge() %><br>
	성별:<%=memberbean.getGender() %><br>
	이메일:<%=memberbean.getEmail() %><br>
	<a href="./MemberMain.me">메인화면으로</a>
</body>
</html>