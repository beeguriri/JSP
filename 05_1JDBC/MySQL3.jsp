<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MySQL3</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
	<h2>영어를 공식언어로 사용하고 있는 나라</h2> <br/>
	<%
	
		//DB에 연결
		JDBConnect jdbc = new JDBConnect("world");
		
		//쿼리문 생성
		String sql = "select Continent, Name from country, countrylanguage where countrylanguage.CountryCode = country.Code and Language='English' and IsOfficial='T' limit 20";
		Statement stmt = jdbc.con.createStatement();

		//쿼리 수행
		ResultSet rs = stmt.executeQuery(sql);
		%>
		
		<table class="table table-striped">
		<tr>
		<th>Continent</th> <th>Name</th> 
		</tr>
		<%
		//결과 확인(웹페이지 출력)
		while (rs.next()) {
			String continent = rs.getString(1);
			String name = rs.getString(2);

		%>
			<tr>
				<td><%= continent %></td>
				<td><%= name %></td>
			</tr>
		<%	
		 } %>
		
		</table>
		 <%
		//연결 닫기
		jdbc.close();	
		%>

</body>
</html>