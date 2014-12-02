<%@page import="net.order.db.OrderBean"%>
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
List orderlist=(ArrayList)request.getAttribute("goods_order_list");
%>
<h1>주문목록</h1>
<table border="1">
<tr><td>주문번호</td><td>결재방법</td><td>주문금액</td>
    <td>주문상태</td><td>주문일시</td></tr>
    <%
    for(int i=0;i<orderlist.size();i++){
    	OrderBean orderbean=(OrderBean)orderlist.get(i);
    	%>
<tr><td>
<a href="./OrderDetailList.or?num=<%=orderbean.getOrder_trade_num()%>"><%=orderbean.getOrder_trade_num() %></a></td>
<td><%=orderbean.getOrder_trade_type() %></td>
<td><%=orderbean.getOrder_sum_money() %></td>
<td><%
if(orderbean.getOrder_status()==0){
	%>대기중<%
}else if(orderbean.getOrder_status()==1){
	%>발송준비<%
}else if(orderbean.getOrder_status()==2){
	%>발송완료<%
}else if(orderbean.getOrder_status()==3){
	%>배송중<%
}else if(orderbean.getOrder_status()==4){
	%>배송완료<%
}else if(orderbean.getOrder_status()==5){
	%>주문취소<%
}
%></td>
<!-- 0 대기중 1 발송준비 2발송완료 3배송중 4배송완료 5주문취소 -->
<td><%=orderbean.getOrder_date() %></td></tr>    	
    	<%
    }
    %>
</table>
</body>
</html>






