<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="membership.MemberDAO" %>
<%@ page import="membership.MemberDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<%

	//DAO를 생성해 DB에 연결
	MemberDAO dao = new MemberDAO(application);

	//데이터 List에 저장
	List<MemberDTO> memberLists = dao.disMem();
	dao.close();
	
	request.setAttribute("list", memberLists);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
	<h2>Member Data 가져오기</h2>
	<table border="1" class="table table-striped">
		<tr>
			<th> id </th>
			<th> pass </th>
			<th> name </th>
			<th> regidate </th>
		</tr>	
		<%
			//EL은 4가지 저장영역(application, session, request, page)에 있는 변수만 읽어올수 있음
			//코드블럭안에 있는 변수는 사용할수 없으므로
			//setAttribute를 통해서 값 전달
			for(int i=0; i<memberLists.size(); i++) {
				pageContext.setAttribute("i", i);
		%>
	
		<tr>
			<td> ${ list[i].id }</td>
			<td> ${ list[i].pass } </td> 
			<td> ${ list[i].name } </td>
			<td> ${ list[i].regidate } </td>	
		</tr>			
		<%		
			}
		%>
	
	</table> 
</body>
</html>