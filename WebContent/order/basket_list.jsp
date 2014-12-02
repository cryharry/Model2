<%@page import="net.goods.db.GoodsBean"%>
<%@page import="net.basket.db.BasketBean"%>
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
//세션값 제어하기
String id=(String)session.getAttribute("id");
if(id==null){
	response.sendRedirect("./MemberLogin.me");
}
List basketlist=(ArrayList)request.getAttribute("basketlist");
List goodslist=(ArrayList)request.getAttribute("goodslist");
%>
<h1>장바구니</h1>
<table>
<tr><td>번호</td><td>사진</td><td>제품명</td>
    <td>수량</td><td>가격</td><td>취소</td></tr>
    <%
    if(basketlist!=null && basketlist.size()!=0){
    	for(int i=0;i<basketlist.size();i++){
    	BasketBean basketbean=(BasketBean)basketlist.get(i);
    	GoodsBean goodsbean=(GoodsBean)goodslist.get(i);
    	%>
<tr><td><%=basketbean.getBasket_num() %></td>
<td><img src="./upload/<%=goodsbean.getImage().split(",")[0]%>" width="50" height="50"></td>
    <td><%=goodsbean.getName() %></td>
    <td><%=basketbean.getBasket_goods_amount() %></td>
    <td><%=goodsbean.getPrice() %></td>
    <td><a href="./BasketDelete.ba?num=<%=basketbean.getBasket_num()%>">취소</a></td></tr>    	
    	<%
       	}
    }else{
    %>
<tr><td colspan="6">장바구니에 담긴 상품이 없습니다.</td></tr>    
    <%	
    }
    %>
<tr><td colspan="6">
<a href="./OrderStar.or">[구매하기]</a>
<a href="./GoodsList.go">[계속 쇼핑하기]</a></td></tr>
</table>
</body>
</html>






