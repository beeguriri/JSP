package edu.pnu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

// 브라우저에서 enctype이 multipart/form-data으로 설정되어 있어야 하고
// 서버에서는 Multipart 데이터를 위한 설정을 추가한다.
@MultipartConfig (maxFileSize = 1024*1024*50, maxRequestSize = 1024*1024*50*5)
// 전송할 수 있는 파일크기를 지정해 주어야 함
// maxFileSize : 보낼수 있는 파일 한개의 최대 크기 (50MB)
// maxRequestSize : 보낼수 있는 여러개 파일 총합의 최대 크기 (250MB)
@WebServlet("/fileupload")
public class FileUploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override 
	//doPost   : CREATE
	//doGet    : READ
	//doPut    : UPDATE
	//doDelete : DELETE
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 파일명의 Alias 출력
		String title = req.getParameter("title");
		System.out.println("title : " + title);

		// 업로드 대상 파일 정보
		Part part = req.getPart("upfile");	//fileUploadForm.jsp의 input type="file"에서 정보 가져옴
		String fname = part.getSubmittedFileName();
		System.out.println("fname : " + fname);

		// 실제 업로드할 경로 정보
		String realPath = req.getServletContext().getRealPath(File.separator + "upload");	//'upload' 폴더 생성
		System.out.println("realPath : " + realPath);
		
		// 경로가 없으면 생성
		Files.createDirectories(Paths.get(realPath));

		// 경로명과 파일명 연결
		String saveName = realPath + File.separator + fname;	//파일명 새로 생성
		System.out.println("saveName : " + saveName);

		// 파일 업로드
		try(InputStream is = part.getInputStream();
			FileOutputStream fos = new FileOutputStream(saveName);)
		{
			int rsize = 0;
			byte[] buffer = new byte[1024];
			while((rsize = is.read(buffer)) != -1) {
				fos.write(buffer, 0, rsize);
			}
		}
		System.out.println("End");
		
		req.getRequestDispatcher("/WEB-INF/JSP/EndUpload.jsp").forward(req,  resp);
	}
}
