<%@page import="net.order.db.OrderBean"%>
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
//request.setAttribute("goods_order_detail_list", goods_order_detail_list);
List order_detail_list=(List)request.getAttribute("goods_order_detail_list");
%>
<h1>주문 상세 페이지</h1>
<table border="1">
<tr><td>주문번호</td><td>주문상품</td><td>수량</td>
<td>주문금액</td></tr>
<%
for(int i=0;i<order_detail_list.size();i++){
OrderBean orderbean=(OrderBean)order_detail_list.get(i);
%>
<tr><td><%=orderbean.getOrder_trade_num() %></td>
<td><%=orderbean.getOrder_goods_name() %></td>
<td><%=orderbean.getOrder_goods_amount() %></td>
<td><%=orderbean.getOrder_sum_money() %></td></tr>
<%
}
%>

</table>
<a href="./OrderList.or">주문목록</a>
</body>
</html>