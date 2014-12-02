<%@page import="net.goods.db.GoodsBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function isBasket(){
		//사이즈 색상 선택 제어
		if(document.fr.size.value==""){
			alert("사이즈를 선택해주세요");
			document.fr.size.focus();
			return;
		}
		if(document.fr.color.value==""){
			alert("색상을 선택해주세요");
			document.fr.color.focus();
			return;
		}
		var isbuy=confirm("장바구니에 저장하시겠습니까?");
		if(isbuy==true){
			document.fr.action="./BasketAdd.ba";
			document.fr.submit();
		}else{
			return;
		}
	}
</script>
</head>
<body>
<%
String item=(String)request.getAttribute("item");
GoodsBean goodsbean=(GoodsBean)request.getAttribute("goodsbean");
%>
<h1>상품상세보기</h1>
<h3><a href="./GoodsList.go?item=<%=item%>">상품목록</a></h3>
<form action="#" method="post" name="fr">
<input type="hidden" name="num" value="<%=goodsbean.getNum()%>">
<table>
<tr><td><img src="./upload/<%=goodsbean.getImage().split(",")[0] %>" width="300" height="300"></td>
<td>상품명 : <%=goodsbean.getName() %><br>
판매가격 : <%=goodsbean.getPrice() %>원<br>
수량 : <input type="text" name="amount" value="1">개<br>
남은수량(<%=goodsbean.getAmount() %>)개<br>
크기 : <select name="size">
<option value="">크기를 선택하세요</option>
<option value="<%=goodsbean.getSize()%>"><%=goodsbean.getSize() %></option>
</select> <br>
색깔: <select name="color">
<option value="">색깔을 선택하세요</option>
<option value="<%=goodsbean.getColor()%>"><%=goodsbean.getColor() %></option>
</select><br>
<a href="javascript:isBasket()">[장바구니담기]</a><br></td></tr>
</table>
</form>
</body>
</html>

