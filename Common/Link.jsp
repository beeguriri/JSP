<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<table class="table table-striped" width="90%">
	<tr>
		<td align="center">
			<% if(session.getAttribute("UserId")==null) {%>
				<a href = "../06Session/LoginForm.jsp">로그인</a>
			<% } else { %>
				<a href = "../06Session/Logout.jsp">로그아웃</a>
			<% } %>
			
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="../08Board/List.jsp">게시판(페이징X)</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="../09PagingBoard/List.jsp">게시판(페이징O)</a>
		</td>
	</tr>
</table>