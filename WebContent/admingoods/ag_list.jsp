<%@page import="net.admin.goods.db.GoodsBean"%>
<%@page import="java.util.ArrayList"%>
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
//request.setAttribute("list", list);
ArrayList list=(ArrayList)request.getAttribute("list");
%>
<h1>상품목록</h1>
<h3><a href="./GoodsAddForm.ag">상품등록</a></h3>
<table border="1">
<tr><td>번호</td><td>카테고리</td><td>사진</td>
<td>상품명</td><td>단가</td><td>수량</td>
<td>등록일자</td><td>수정/삭제</td></tr>
<%
if(list==null || list.size()==0){
%>
<tr><td colspan="8">상품없음</td></tr>
<%
}else{
	for(int i=0;i<list.size();i++){
		GoodsBean gb=(GoodsBean)list.get(i);
		%>
<tr><td><%=gb.getG_num() %></td>
<td><%=gb.getG_category() %></td>
<td>
<img src="./upload/<%=gb.getG_image().split(",")[0] %>" width="50" height="50">
<%=gb.getG_image() %></td><td><%=gb.getG_name() %></td>
<td><%=gb.getG_price() %></td><td><%=gb.getG_amount() %></td>
<td><%=gb.getG_date() %></td><td>수정/삭제</td></tr>		
		<%
	}	
}
%>

</table>
</body>
</html>