<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gugudan3</title>
</head>
<body>
	<h2>구구단</h2>
	<form action="./GugudanMain.jsp" method="post">
		<input type="radio" name="gugudan" value="type1" /> Type1 <br />
		<input type="radio" name="gugudan" value="type2" /> Type2 <br />
		<input type="text" name="input" value="" /> 단수/열수 <br />
		<input type="submit" value="선택" />
	</form>
</body>
</html>