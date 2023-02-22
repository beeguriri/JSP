<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>gugudan2</title>
</head>
<body>
	<%
	try{
		int col = Integer.parseInt(request.getParameter("col"));
		for (int i=2; i<=9; i+=col) {
			for(int j=1; j<=9; j++) {
				for(int k=0; k<col; k++) {
					if((i+k)>9) break;
				
	%>			
	<%= i+k %> * <%= j %> = <%= (i+k) * j %>&nbsp;&nbsp;&nbsp;&nbsp;			
	<%
				}
	%>			
	<br/>
	<%		}
	%>
	<br />
	<%		
		}
	}
	catch(Exception e) {
		out.println("예외발생 : col을 입력하세요.");
	}
	 %>
</body>
</html>
