<%@page import="net.goods.db.GoodsBean"%>
<%@page import="net.basket.db.BasketBean"%>
<%@page import="net.member.db.MemberBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
//세션처리
String id=(String)session.getAttribute("id");
if(id==null){
	response.sendRedirect("./MemberLogin.me");
}
List basketlist=(ArrayList)request.getAttribute("basketlist");
List goodslist=(ArrayList)request.getAttribute("goodslist");
MemberBean memberbean=(MemberBean)request.getAttribute("memberbean");
%>
<h1>주문 상세내역</h1>
<table>
<tr><td>사진</td><td>상품명</td><td>수량</td>
    <td>색깔</td><td>사이즈</td><td>가격</td></tr>
    <%
    for(int i=0;i<basketlist.size();i++){
    	BasketBean basketbean=(BasketBean)basketlist.get(i);
    	GoodsBean goodsbean=(GoodsBean)goodslist.get(i);
    	%>
<tr><td><img src="./upload/<%=goodsbean.getImage().split(",")[0]%>" width="50" height="50"></td>
<td><%=goodsbean.getName() %></td>
<td><%=basketbean.getBasket_goods_amount() %></td>
<td><%=basketbean.getBasket_goods_color() %></td>
<td><%=basketbean.getBasket_goods_size() %></td>
<td><%=goodsbean.getPrice() %></td></tr>    	
    	<%
    }
    %>
</table>
<h1>주문자 정보</h1>
이름:<%=memberbean.getName() %><br>
<form action="./OrderAdd.or" method="post">
<h1>배송지 정보</h1>
받는 사람 :<input type="text" name="order_receive_name" value="<%=memberbean.getName()%>"><br>
기타 요구사항 :<textarea rows="12" cols="60" name="order_memo"></textarea><br>
<h1>결제정보</h1>
입금자명:<input type="text" name="order_trade_payer" value="<%=memberbean.getName()%>">
<input type="submit" value="주문">
<input type="reset" value="취소">
</form>
</body>
</html>



