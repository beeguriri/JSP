<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>File Download</title>
</head>
<body>
	<h2>File Download Test</h2>
	<ul>
		<!-- FileUpload : 프로젝트명 -->
		<!-- filedownload : @WebServlet("/filedownload") -->
		<li><a href="http://localhost:8080/FileUpload/filedownload?name=test.txt">test.txt</a></li>
		<li><a href="http://localhost:8080/FileUpload/filedownload?name=PV3.txt">PV3.txt</a></li>
		<li><a href="http://localhost:8080/FileUpload/filedownload?name=dog.png">dog.png</a></li>
	</ul>
</body>
</html>