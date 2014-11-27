<%@page import="java.util.List"%>
<%@page import="net.member.db.ZipcodeBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>우편번호 검색창</title>
<script src="./js/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(function(){
	$('table tr').each(function(idx){
		$(this).click(function(){
			var s_text = $(this).children().text();
			opener.document.getElementById('zipcode1').value = s_text.substring(0,3);
			opener.document.getElementById('zipcode2').value = s_text.substring(4,7);
			opener.document.getElementById('addr1').value = s_text.substring(7,s_text.search('동')+1);
			opener.document.getElementById('addr2').value = s_text.substring(s_text.search('동')+1,$(this).text().length);
			window.close();
		});
	})
});
</script>
</head>
<body>
<%
	List zipcodeList = (List)request.getAttribute("zipcodeList");
%>
<h1>우편번호 검색</h1>
<form action="./ZipcodeAction.me" method="post" name="wfr">
지역명:<input type="text" name="dong">
<input type="submit" value="검색"><br>
동을 입력하세요(예:화명,덕천 2글자 이상 입력)<br>
</form>
<h1>검색결과</h1>
<table border="1">
	<tr>
		<td>우편번호</td>
		<td>주소</td>
	</tr>
	<%if(zipcodeList != null) {
		for(int i=0;i<zipcodeList.size();i++) {
			ZipcodeBean zipcodeBean = (ZipcodeBean)zipcodeList.get(i);
			String addr1 = zipcodeBean.getSido()+ " " + zipcodeBean.getGugun() + " "
					+ zipcodeBean.getDong();
			String addr2 = zipcodeBean.getRi() + " " + zipcodeBean.getBunji();
			%>
			<tr>
				<td><a href="#none" id="zipcode"><%=zipcodeBean.getZipcode() %></a></td>
				<td><a href="#none" id="addr"><%=addr1 %> <%=addr2 %></a></td>
			</tr>
			<%
		}
	} 
%>
</table>
</body>
</html>