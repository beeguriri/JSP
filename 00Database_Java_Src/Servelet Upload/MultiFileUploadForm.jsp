<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Multi File Upload</title>
</head>
<body>
	<form method="post" action="/FileUpload/multifileupload" enctype="multipart/form-data">
		FileName :
		<input type="text" name="title"><br>
		<input type="file" name="upfile"><br>
		<input type="file" name="upfile"><br>
		<input type="submit" value="업로드">
	</form>
</body>
</html>