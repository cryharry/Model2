<%@page import="java.util.StringTokenizer"%>
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
	$('table tr').each(function(){
		$(this).click(function(){
			var s_text = $(this).children().text();
			opener.document.frm.zipcode1.value = s_text.substring(0,3);
			opener.document.frm.zipcode2.value = s_text.substring(4,7);
			opener.document.frm.addr1.value = s_text.substring(7,s_text.length);
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
			String addr = zipcodeBean.getSido()+" "+zipcodeBean.getGugun()
					+" "+zipcodeBean.getDong()+" "+zipcodeBean.getRi()+" "+
					zipcodeBean.getBunji();
			/*
				data의 내용을 ,로 분리
				StringTokenizer st = new StringTokenizer(data,",");
				String zipcode = st.nextToken(); <-,앞에꺼
				String addr = st.nextToken(); <-,뒤에꺼
				
				zip1 = zipcode.split("-")[0];
				zip2 = zipcode.split("-")[1];
			*/
			%>
			<tr>
				<td><a href="#" id="zipcode"><%=zipcodeBean.getZipcode() %></a></td>
				<td><a href="#" id="addr"><%=addr %></a></td>
			</tr>
			<%
		}
	} else {
%>
		<tr>
			<td colspan="2">검색 결과가 없습니다.</td>
		</tr>
<%	} %>
</table>
</body>
</html>