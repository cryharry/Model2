<%@page import="net.admin.goods.db.GoodsBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 목록 페이지</title>
</head>
<body>
<%
	ArrayList list = (ArrayList)request.getAttribute("list");
%>
<table border="1">
	<tr>
		<td>상품번호</td>
		<td>카테고리</td>
		<td>사진</td>
		<td>상품명</td>
		<td>단가</td>
		<td>수량</td>
		<td>등록일자</td>
		<td></td>
	</tr>
	<%
	if(list!= null) {
		for(int i=0;i<list.size();i++){
			GoodsBean goodsBean = (GoodsBean)list.get(i);
			%>
			<tr>
				<td><%=goodsBean.getG_num() %></td>
				<td><%=goodsBean.getG_category() %></td>
				<td>
				<%  for(int j=0;j<goodsBean.getG_image().split(",").length;j++){ %>
						<img src="./upload/<%=goodsBean.getG_image().split(",")[j]%>" width="100" height="100">
				<%	} %>
				</td>
				<td><%=goodsBean.getG_name() %></td>
				<td><%=goodsBean.getG_price() %></td>
				<td><%=goodsBean.getG_amount() %></td>
				<td><%=goodsBean.getG_date() %></td>
				<td><a href="./">수정</a> <a href="./">삭제</a></td>
			</tr>
			<%
		}
	} else { %>
		<tr>
			<td colspan="8">상품없음</td>
		</tr>
<%	} %>
</table>
</body>
</html>