<%@ page import="fileupload.MyfileDAO" %>
<%@ page import="fileupload.MyfileDTO" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.io.File"%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String saveDirectory = application.getRealPath("/Uploads");
	int maxPostSize = 1024 * 1000; //파일 최대크기 1MB
	String encoding = "UTF-8";

	try{
		
		//1. MultipartRequest 객체 생성
		//파일 업로드, 다운로드 하기 위한 MultipartRequest 클래스 
		MultipartRequest mr = new MultipartRequest(request, saveDirectory, maxPostSize, encoding);
		
		//2. 새로운 파일명 생성
		String fileName = mr.getFilesystemName("attachedFile");
		String ext = fileName.substring(fileName.lastIndexOf("."));
		String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		String newFileName = now + ext;
		
		//3. 파일명 변경
		File oldFile = new File(saveDirectory + File.separator + fileName);
		File newFile = new File(saveDirectory + File.separator + newFileName);
		oldFile.renameTo(newFile);
		
		//4. 다른 폼값 받기
		String name = mr.getParameter("name");
		String title = mr.getParameter("title");
		String [] cateArray = mr.getParameterValues("cate");
		StringBuffer cateBuf = new StringBuffer();
		if(cateArray == null) {
			cateBuf.append("선택 없음");
		} else {
			for(String s : cateArray) {
				cateBuf.append(s + ", ");
			}
		}
		
		//5. DTO 생성
		MyfileDTO dto = new MyfileDTO();
		dto.setName(name);
		dto.setTitle(title);
		dto.setCate(cateBuf.toString());
		dto.setOfile(fileName);
		dto.setSfile(newFileName);
		
		//6. DAO를 통해 데이터베이스에 반영
		MyfileDAO dao = new MyfileDAO(application);
		dao.insertFile(dto);
		dao.close();
		
		//7. 파일 목록 JSP로 리다이렉션
		//클라이언트로 돌아갔다가 클라이언트에서 다시 요청 -> 리퀘스트를 공유하지 않음
		//포워드는 서버 안에서 공유하므로 리퀘스트 공유
		response.sendRedirect("FileList.jsp");
		
		
	} catch (Exception e) {
		e.printStackTrace();
		request.setAttribute("errorMessage", "파일 업로드 오류");
		request.getRequestDispatcher("FileUploadMain.jsp").forward(request, response);
	}

%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>