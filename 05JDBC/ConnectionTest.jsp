<%@ page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<html>
<head><title>JDBC</title></head>
<body>
	<h2>JDBC 테스트 1</h2>
	<%
		JDBConnect jdbc1 = new JDBConnect();
		jdbc1.close();
	%>
	
	<h2>JDBC 테스트 2</h2>
	<%
		String driver = application.getInitParameter("MySQLDriver");
		String url = application.getInitParameter("MySQLURL");
		String id = application.getInitParameter("MySQLId");
		String pwd = application.getInitParameter("MySQLPwd");
	
		JDBConnect jdbc2 = new JDBConnect(driver, url, id, pwd);
		jdbc2.close();
	%>
	
	<h2>JDBC 테스트 3</h2>
	<%
		JDBConnect jdbc3 = new JDBConnect(application);
		jdbc3.close();
	%>
</body>
</html>