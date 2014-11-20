<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="net.member.db.MemberBean, java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// 세션값 사져오기
	String id = (String)session.getAttribute("id");
	// 세션값 없는 경우 admin아닌 경우 main.jsp이동
	if(id==null || !id.equals("admin")) {
	    response.sendRedirect("main.jsp");
	}
	List memberList = (List)request.getAttribute("memberList");
%>
<h1>회원목록</h1>
<table border="1">
	<tr>
		<td>아이디</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>가입날짜</td>
		<td>나이</td>
		<td>성별</td>
		<td>이메일</td>
	</tr>
	<%for(int i=0; i<memberList.size(); i++)  {
		MemberBean memberBean = (MemberBean)memberList.get(i);
	%>
	<tr>
		<td><%=memberBean.getId()%></td>
		<td><%=memberBean.getPasswd()%></td>
		<td><%=memberBean.getName()%></td>
		<td><%=memberBean.getReg_date()%></td>
		<td><%=memberBean.getAge()%></td>
		<td><%=memberBean.getGender()%></td>
		<td><%=memberBean.getEmail()%></td>
	</tr>
	<%} %>
</table>
</body>
</html>