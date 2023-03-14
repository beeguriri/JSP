<%@ page import="el.MyELClass" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	//객체를 page영역에 저장
	//MyELClass myClass = new MyELClass();
	//pageContext.setAttribute("myClass", myClass);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>표현언어(EL) - 메서드 호출</title>
</head>
<body>
	<!-- 
	<h3>영역에 저장 후 메서드 호출하기</h3>
	001225-3000000 => ${ myClass.getGender("001225-3000000") } <br />
	001225-2000000 => ${ myClass.getGender("001225-2000000") } <br />
	 -->
	
	<!-- 별도의 객체 생성 및 page영역 저장 없이 클래스명으로 바로 함수 호출해서사용 -->
	<h3>클래스명을 통해 정적메서드 호출하기</h3>
	${ MyELClass.showGugudan(7) }
	
	<!-- 숫자 테스트 -->
	<h3>숫자 테스트 하기</h3>
	33 : ${ MyELClass.isNumber("33") }
	이십 : ${ MyELClass.isNumber("이십") }
	
</body>
</html>