<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gugudan1</title>
</head>
<body>
	<%
	try {
		int dan = Integer.parseInt(request.getParameter("dan"));
		for(int i=1; i<=9; i++){
			    
	%> 		    
	<%= dan %> * <%= i %> = <%= dan*i %><br/>
	<%
		}
	}
	catch(Exception e) {
		out.println("예외발생 : dan을 입력하세요.");
	}
	%>
</body>
</html>