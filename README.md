# JSP

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
## JSPMission
  + 0222 : 구구단 출력
    + Gugudan1 : dan수 입력받아서 1단 출력
    + Gugudan2 : col수 입력받아서 가로로 col단 출력 (2~9단)
    + Gugudan3 : foam box
    + GugudanMain : Gugudan3에서 입력받는 값에 따라 Gugudan1/Gugudan2 호출
    
##
+ local : C:\Users\SW\eclipse-workspace\MustHaveJSP\src\main\webapp
