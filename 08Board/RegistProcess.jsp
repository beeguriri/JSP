<%@page import="utils.JSFunction"%>
<%@page import="membership.MemberDAO"%>
<%@page import="membership.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	//폼값 받기
	String user_id = request.getParameter("user_id");
	String user_pw1 = request.getParameter("user_pw1");
	String user_name = request.getParameter("user_name");
	
	//폼값을 DTO 객체에 저장
	MemberDTO dto = new MemberDTO();
	dto.setId(user_id);
	dto.setPass(user_pw1);
	dto.setName(user_name);
	
	//DAO객체를 통해 DB에 DTO저장
	MemberDAO dao = new MemberDAO(application);
	int iResult = dao.regUser(dto);
	dao.close();

	//성공? 실패?
	if(iResult==1) {
		session.setAttribute("UserId", dto.getId());
		session.setAttribute("UserName", dto.getName());
		response.sendRedirect("List.jsp");

	} else {
		
		JSFunction.alertBack("등록에 실패하였습니다.", out);
	}
%>