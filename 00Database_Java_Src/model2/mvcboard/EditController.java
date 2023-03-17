package model2.mvcboard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

@WebServlet("/mvcboard/edit.do")
public class EditController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String idx = req.getParameter("idx");
		MVCBoardDAO dao = new MVCBoardDAO();
		MVCBoardDTO dto = dao.selectView(idx);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/14MVCBoard/Edit.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//파일 업로드 처리
		//1. 업로드 디렉터리의 물리적 경로 확인
		String saveDirectory = req.getServletContext().getRealPath("/Uploads");
		
		//2. 초기화 매개변수로 설정한 첨부 파일 최대 용량 확인
		ServletContext application = getServletContext();
		int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize"));
		
		//3. 파일 업로드
		MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
		
		if (mr==null) {
			
			JSFunction.alertBack(resp, "첨부 파일이 제한 용량을 초과합니다.");
			return;
		}
		
		//파일 업로드 외 처리
		//1. 수정내용을 매개변수에서 얻어옴
		String idx = mr.getParameter("idx");
		String prevOfile = mr.getParameter("prevOfile");
		String prevSfile = mr.getParameter("prevSfile");
		String name = mr.getParameter("name");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		
		//2. 비밀번호는 세션에서 가져옴
		HttpSession session = req.getSession();
		String pass = (String) session.getAttribute("pass");
		
		//3. DTO에 저장
		MVCBoardDTO dto = new MVCBoardDTO();
		dto.setIdx(idx);
		dto.setName(name);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setPass(pass);
		
		//4. 원본 파일명과 저장된 파일 이름 설정
		String fileName = mr.getFilesystemName("ofile");
		if (fileName != null) {
			
			//첨부파일이 있을 경우 파일명 변경
			// 새로운 파일명 생성
			String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
			String ext = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = now + ext;
			
			//파일명 변경
			File oldFile = new File(saveDirectory + File.separator + fileName);
			File newFile = new File(saveDirectory + File.separator + newFileName);
			oldFile.renameTo(newFile);
			
			//DTO에 저장
			dto.setOfile(fileName);
			dto.setSfile(newFileName);
			
			//기존파일 삭제
			FileUtil.deleteFile(req, "/Uploads", prevSfile);
		} else {
			dto.setOfile(prevOfile);
			dto.setSfile(prevSfile);
		}
		
		//5. DAO를 통해 DB에 내용 저장
		MVCBoardDAO dao = new MVCBoardDAO();
		int result = dao.updatePost(dto);
		dao.close();
		
		//성공실패
		if (result==1) {
			session.removeAttribute("pass");
			resp.sendRedirect("../mvcboard/view.do?idx=" + idx);
		} else {
			JSFunction.alertLocation(resp, "비밀번호 검증을 다시 진행해주세요", "../mvcboard/view.do?idx=" + idx);
		}
	}
}
