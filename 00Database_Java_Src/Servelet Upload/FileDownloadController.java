package edu.pnu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/filedownload")
public class FileDownloadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String realPath = req.getServletContext().getRealPath("/upload");
		String fname = req.getParameter("name");
		
		System.out.println("filename: " + realPath + File.separator + fname);

		File file = new File(realPath, fname);
		if (file != null) {
			try(FileInputStream is = new FileInputStream(file);)
			{
				resp.reset();
				resp.setContentType("appliction/octet-stream");
				resp.setHeader("content-Disposition",  "attachment; filename=\"" + fname + "\"");
				resp.setHeader("Content-Length",  String.valueOf(file.length()));

				OutputStream outStream = resp.getOutputStream();
				
				int rsize;
				byte[] buffer = new byte[1024];
				while((rsize = is.read(buffer)) != -1) {
					outStream.write(buffer, 0, rsize);
				}
				outStream.close();
			}
			System.out.println("End");
		}
	}
}
