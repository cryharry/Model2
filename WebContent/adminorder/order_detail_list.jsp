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
int st = 0;
String trade_num;
%>
<h1>관리자 주문 상세 페이지</h1>
<table border="1">
<tr><td>주문번호</td><td>주문상품</td><td>수량</td>
<td>주문금액</td></tr>
<%
for(int i=0;i<order_detail_list.size();i++){
OrderBean orderbean=(OrderBean)order_detail_list.get(i);
st=orderbean.getOrder_status();//주문상태
trade_num=orderbean.getOrder_trade_num();//주문번호
%>
<tr><td><%=orderbean.getOrder_trade_num() %></td>
<td><%=orderbean.getOrder_goods_name() %></td>
<td><%=orderbean.getOrder_goods_amount() %></td>
<td><%=orderbean.getOrder_sum_money() %></td></tr>
<%
}
%>

</table>
<form action="./AdminOrderModify.ao" method="post">
<input type="hidden" name="order_trade_num" value="">
주문상태:<select name="order_status">
<option value="0" <%if(st==0){%>selected<%}%>>대기중</option>
<option value="1" <%if(st==1){%>selected<%}%>>발송준비</option>
<option value="2" <%if(st==2){%>selected<%}%>>발송완료</option>
<option value="3" <%if(st==3){%>selected<%}%>>배송완료</option>
<option value="4" <%if(st==4){%>selected<%}%>>배송완료</option>
<option value="5" <%if(st==5){%>selected<%}%>>배송완료</option>
</select>
<input type="submit" value="주문상태수정">
</form>
<a href="./AdminOrderList.or">주문목록</a>
</body>
</html>