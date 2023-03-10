<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function validateForm(form) {
		
		if(form.user_id.value== "") {
		alert("아이디를 입력하세요.");
		form.title.focus();
		return false;
		}
		
		//동일아이디 입력 -> 나중에 하자
		//if(form.user_id.value.equals()) {
		//	alert("아이디를 입력하세요.");
		//	form.title.focus();
		//	return false;
		//	}
		
		if (form.user_pw1.value =="" || form.user_pw2.value =="") {
			alert("비밀번호를 입력하세요.");
			form.content.focus();
			return false;
		}
		
		if (!form.user_pw1.value.equals(form.user_pw2.value)) {
			alert("비밀번호를 동일하게 입력하세요.");
			form.content.focus();
			return false;
		}
		
		if(form.user_name.value== "") {
			alert("이름을 입력하세요.");
			form.title.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	<h2>회원제 게시판 - 사용자등록</h2>
	<form name="regUser" method="post" action="RegistProcess.jsp" onsubmit="return validateForm(this);">
	
		아이디 입력 : <input type="text" name="user_id" /><br /><br />
		패스워드입력 : <input type="password" name ="user_pw1" /><br /><br />
		패스워드확인 : <input type="password" name ="user_pw2" /><br /><br />
		사용자 이름 : <input type="text" name="user_name" /><br /><br />
		
		<input type="submit" value="등록하기" />
		<button type="reset">다시 입력</button>	
	</form>
</body>
</html>