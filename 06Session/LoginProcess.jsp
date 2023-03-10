<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="membership.MemberDTO"%>
<%@ page import="membership.MemberDAO"%>

<% 
	String userId = request.getParameter("user_id");
	String userPwd = request.getParameter("user_pw");
	
	//web.xml에서 가져온 데이터베이스 연결정보
	String MySQLDriver = application.getInitParameter("MySQLDriver"); 
	String MySQLURL = application.getInitParameter("MySQLURL");
	String MySQLId = application.getInitParameter("MySQLId");
	String MySQLPwd = application.getInitParameter("MySQLPwd");
		
	// 회원 테이블 DAO를 통해 회원정보 DTO 획득
	MemberDAO dao = new MemberDAO(MySQLDriver, MySQLURL, MySQLId, MySQLPwd);
	MemberDTO memberDTO = dao.getMemberDTO(userId, userPwd);
	dao.close();
	
	
	//if(userId.equals("musthave") && userPwd.equals("1234")) {
	//	session.setAttribute("UserId", "musthave");
	//	session.setAttribute("UserName", "must");
	//	response.sendRedirect("LoginForm.jsp");
	if(memberDTO.getId()!=null) {
		session.setAttribute("UserId", memberDTO.getId());
		session.setAttribute("UserName", memberDTO.getName());
		response.sendRedirect("LoginForm.jsp");
		
	} else {
		request.setAttribute("LoginErrMsg", "로그인오류입니다.");
		request.getRequestDispatcher("LoginForm.jsp").forward(request, response);
	}
%>