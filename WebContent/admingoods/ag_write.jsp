<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>상품등록</h1>
<form action="./GoodsAddAction.ag" method="post" enctype="multipart/form-data">
<input type="hidden" name="g_best" value="0">
<table border="1">
	<tr><td>카테고리</td>
	    <td><select name="g_category">
	    	<option value="outwear">아웃웨어</option>
	    	<option value="fulldress">정장/신사복</option>
	    	<option value="Tshirts">티셔츠</option>
	    	<option value="shirts">와이셔츠</option>
	    	<option value="pants">팬츠</option>
	    	<option value="shoes">슈즈</option>
	    </select></td></tr>
	<tr><td>상품이름</td><td><input type="text" name="g_name"></td></tr>
	<tr><td>판매가</td><td><input type="text" name="g_price"></td></tr>
	<tr><td>색깔</td><td><input type="text" name="g_color"></td></tr>
	<tr><td>수량</td><td><input type="text" name="g_amount"></td></tr>
	<tr><td>사이즈</td><td><input type="text" name="g_size"></td></tr>
	<tr><td>제품정보</td><td><textarea name="g_content" cols="50" rows="15"></textarea></td></tr>
	<tr><td>메인제품이미지</td><td><input type="file" name="file4"></td></tr>
	<tr><td>제품이미지1</td><td><input type="file" name="file3"></td></tr>
	<tr><td>제품이미지2</td><td><input type="file" name="file2"></td></tr>
	<tr><td>제품이미지3</td><td><input type="file" name="file1"></td></tr>
	<tr><td colspan="2">
	<input type="submit" value="등록">
	<input type="reset" value="다시쓰기"></td></tr>
</table>
</form>
</body>
</html>



