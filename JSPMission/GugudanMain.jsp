<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gugudan main</title>
</head>
<body>
<%
	String sel = request.getParameter("gugudan");
	out.print("sel :" + sel);	//sel값에 type1, type2 넘어오는거 확인
	
	String col = request.getParameter("input");
	out.print("col :" + col);	//text에서 입력한 값이 넘어오는거 확인

	if (sel.equals("type1")) response.sendRedirect("Gugudan1.jsp?dan="+col);
	else response.sendRedirect("Gugudan2.jsp?col="+col);

%>
</body>
</html>