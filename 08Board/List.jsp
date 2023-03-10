<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="model1.board.BoardDAO" %>
<%@ page import="model1.board.BoardDTO" %>
<% 
	//DAO를 생성해 DB에 연결
	BoardDAO dao = new BoardDAO(application);

	//사용자 입력한 검색조건 MAP에 저장
	Map<String, Object> param = new HashMap<String, Object>();
	
	String searchField = request.getParameter("searchField");
	String searchWord = request.getParameter("searchWord");
	
	if (searchWord != null) {
		
		param.put("searchField", searchField);
		param.put("searchWord", searchWord);
	}
	
	int totalCount = dao.selectCount(param);	//게시물수 확인
	List<BoardDTO> boardLists = dao.selectList(param);	//게시물 목록 확인
	dao.close();
%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
	<jsp:include page="../Common/Link.jsp"></jsp:include>	<!-- 공통 링크 -->
	<h2>목록 보기(List)</h2>
	
	<!-- 검색폼 -->
	<form method="get">
		<table border="1" width="90%">
			<tr>
				<td align="center">
					<select name = "searchField">
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select>
					<input type="text" name="searchWord" />
					<input type="submit" value="검색하기" />
				</td>
			</tr>
		</table>
	</form>
	
	<!-- 게시물 목록 테이블(표) -->
	<table border="1" width="90%" class="table table-striped">
		<tr>
			<th width="10%">번호</th>
			<th width="50%">제목</th>
			<th width="15%">작성자</th>
			<th width="10%">조회수</th>
			<th width="15%">작성일</th>
		</tr>
		<!-- 목록 내용 -->
		<% 
			if(boardLists.isEmpty()) {
		%>	
				<tr>
					<td colspan="5" align="center">
						등록된 게시물이 없습니다!
					</td>
				</tr>
		<%
			} else {
				//게시물이 있을때
				int virtualNum = 0;
				for (BoardDTO dto : boardLists) {
					virtualNum = totalCount--;
		%>		
				<tr align="center">
					<td align="center"> <%= virtualNum %> </td>
					<td align="left"> 
						<a href="View.jsp?num=<%= dto.getNum() %>"><%= dto.getTitle() %></a>
					</td>
					<td align="center"> <%= dto.getId() %></td>
					<td align="center"> <%= dto.getVisitcount() %></td>
					<td align="center"> <%= dto.getPostdate() %></td>
				</tr>
		<%
			}
		}
		%>
	</table>
	
	<!-- 목록 하단의 [글쓰기] 버튼 -->
	<table border="1" width="90%">
		<tr align="right">
			<td>
				<button type="button" onclick="location.href='Write.jsp';">글쓰기</button>
			</td>
		</tr>
	</table>
</body>
</html>