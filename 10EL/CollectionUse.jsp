<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="common.Person" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현 언어(EL) - 컬렉션</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
	<h2>List 컬렉션</h2>
	<%
		List<Object> aList = new ArrayList<Object>();
		aList.add("청해진");
		aList.add(new Person("장보고", 28));
		pageContext.setAttribute("Ocean", aList);
	%>
	
	<ul>
		<li>0번째 요소 : ${ Ocean[0] }</li>
		<li>1번째 요소 : ${ Ocean[1].name }, ${ Ocean[1].age }</li>
		<li>2번째 요소 : ${ Ocean[2] }</li>	
	</ul>
	
	<h2>Map 컬렉션</h2>
	<%
		Map<String, String> map = new HashMap<String, String>();
		map.put("한글", "훈민정음");
		map.put("Eng", "English");
		pageContext.setAttribute("king", map);
	%>
	
	<ul>
		<li>영문 Key : ${ king["Eng"] }, ${ king['Eng'] }, ${ king.Eng }</li>
		<li>한글 key : ${ king["한글"] }, ${ king['한글'] }, \${ king.한글 }</li>
	</ul>
	
</body>
</html>
