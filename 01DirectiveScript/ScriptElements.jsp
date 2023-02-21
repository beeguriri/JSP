<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!	//선언부(메서드 선언) 
	public int add(int num1, int num2) {
	
	return num1 + num2;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립트 요소</title>
</head>
<body>
<% 	//선언부(스크립틀릿, 자바코드)
	int result = add(10,20);
%>

덧셈결과 1 : <%= result %> <br /> <!-- 표현식(변수) -->
덧셈결과 2 : <%= add(30 , 40) %> 	<!-- 표현식(메서드 호출) -->
</body>
</html>