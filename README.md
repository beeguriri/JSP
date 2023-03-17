# JSP

## 00 Database_Java_Source
+ Lec : MySQL 접속을 위한 JDBC 드라이버 로드
+ Mission : 부모클래스 이용하여 MySQL, H2 드라이버 로드하기

## 01 DirectiveScript (JSP 기본)
+ `<%@ %>` : 지시어
+ `<%! %>` : 선언부(변수, 메서드)
+ `<% %>`  : 스크립틀릿(코드영역)
+ `<%= %>` : 표현식

## 02 ImplicitObject (내장객체)
+ request 객체
  + request.getParameter("parameter")
  + form method : get / post
+ response 객체
  + response.sendRedirect("~.jsp")

## 03 Scope (내장객체 영역)
  + page 영역 `pageContext.`
  + request 영역 `request.`
  + 공통메서드
    ```java
    setAttribute(String name, Object value)
    getAttribute(String name)
    removeAttribute(String name)
    ```
    
## 04 Cookie (쿠키)
  + 클라이언트 -> 서버 요청(처음방문)
  + 서버 -> 클라이언트 응답 (쿠키전송)
  + 클라이언트는 쿠키 저장
  + 클라이언트 -> 서버 요청 (재방문, 쿠키를 함께 전송)
  + 서버가 쿠키를 읽어 작업수행
  
## 05 JDBC (데이터베이스 연결)
```java
// JDBC 드라이버 로드
Class.forName("com.mysql.cj.jdbc.Driver") 

//연결객체 생성
Connection con = DriverManager.getConnection("url", "id", "pw");  

//질의객체 생성(변수X)
Statement st = con.createStatement();  
ResultSet rs = st.executeQuery("SQL쿼리문");

//질의객체 생성(변수O)
PreparedStatement st = con.prepareStatement("where code=?")); 
st.setString("?위치", "찾고자 하는 값");  //st.setInt, st.setDate...
ResultSet rs = st.executeQuery();

//질의결과 parsing
while(rs.next()) {		
  rs.getString(i)
}

//생성된 객체 연결을 모두 해제
rs.close();
st.close();
con.close();

```
## 06 Session (로그인페이지 만들기)

## 07 ActionTag (액션태그)
 +	`<jsp:useBean id="person" class="common.Person" scope="request" />`

## 08 Board (게시판만들기)
 + 로그인정보 확인 되면 게시판 읽기, 쓰기, 수정, 삭제
 + 추가mission : 신규사용자 등록하기
 
## 09 PagingBoard (게시판만들기-페이징)
  + 한 페이지에 보여지고싶은 목록수만큼 출력, 페이징블락 생성
  
## 10 EL
  + 코드영역에 값 설정 후 EL태그로 값 사용
```java
<%
	request.setAttribute("personObj", new Person("홍길동", 33));
%>
	<li>Person 객체 => 이름 : ${ personObj.name }, 나이 : ${ personObj.age }</li>
```

## JSPMission
  + 0222 : 구구단 출력
    + Gugudan1 : dan수 입력받아서 1단 출력
    + Gugudan2 : col수 입력받아서 가로로 col단 출력 (2~9단)
    + Gugudan3 : foam box
    + GugudanMain : Gugudan3에서 입력받는 값에 따라 Gugudan1/Gugudan2 호출
    
##
+ local : C:\Users\SW\eclipse-workspace\MustHaveJSP\src\main\webapp
