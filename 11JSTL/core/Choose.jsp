<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL - choose/when/otherwise</title>
</head>
<body>
	<!-- 변수선언 -->
	<c:set var="number" value="100" />
	
	<h4>choose 태그로 홀짝 판단하기</h4>
	<c:choose>
		<c:when test="${ number mod 2 eq 0 }">
			${ number }는 짝수입니다.
		</c:when>
		<c:otherwise>
			${ number }는 홀수입니다.
		</c:otherwise>
	</c:choose>
	
	<h4>국,영,수 점수를 입력하면 평균을 내어 학점 출력</h4>
	<!-- 점수 입력폼 -->
	<form>
		국어 : <input type="text" name="kor" /><br />
		영어 : <input type="text" name="eng" /><br />
		수학 : <input type="text" name="math" /><br />
		<input type="submit" value="학점 구하기" />	<!-- action이 없으면 자기자신 호출 -->	
	</form>
	
	<!-- 모든 과목의 점수가 입력되었는지 확인 -->
	<c:if test="${ not (empty param.kor or empty param.eng or empty param.math) }">
		
		<!-- 평균 계산 -->
		<c:set var ="avg" value="${ (param.kor + param.eng + param.math) /3 }"/>
		평균점수는 ${ avg }으로
		<!-- 학점 출력 -->
		<c:choose>
			<c:when test="${ avg>=90 }">A 학점</c:when>
			<c:when test="${ avg>=80 }">B 학점</c:when>
			<c:when test="${ avg>=70 }">C 학점</c:when>
			<c:when test="${ avg ge 60 }">D 학점</c:when>
			<c:otherwise>F 학점</c:otherwise>
		</c:choose>
		입니다.
	</c:if>
	<!-- else문처럼 사용하기 -->
	<c:if test="${ (empty param.kor or empty param.eng or empty param.math) }">
		모든 점수를 입력하면 학점을 계산합니다.
	</c:if>
</body>
</html>
