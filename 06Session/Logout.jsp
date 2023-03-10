<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//회원 인증정보 속성 삭제
	session.removeAttribute("UserId");
	session.removeAttribute("UserName");
	
	//모든 속성 한꺼번에 삭제
	session.invalidate();
	
	//삭제후 페이지 이동
	response.sendRedirect("LoginForm.jsp");
%>