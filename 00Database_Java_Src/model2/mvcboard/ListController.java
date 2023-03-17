package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.BoardPage;

@WebServlet("/mvcboard/list.do")
public class ListController extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		//DAO 생성
		MVCBoardDAO dao = new MVCBoardDAO(); 
		
		//뷰에 전달 할 매개변수 저장용 맵
		Map<String, Object> map = new HashMap<String, Object>();
		
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("searchWord");
		if (searchWord != null) {
			map.put("searchField", searchField);
			map.put("searchWord", searchWord);
		}
		int totalCount = dao.selectCount(map);
		
		/* 페이지 처리 시작 */
		ServletContext application = getServletContext();
		
		int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
		int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
		
		//현재 페이지 확인
		int pageNum = 1;
		String pageTemp = req.getParameter("pageNum");
		if (pageTemp != null && !pageTemp.equals(""))
			pageNum = Integer.parseInt(pageTemp);
		
		//목록에 출력할 게시물 범위 계산
		int start = (pageNum-1)*pageSize + 1; //첫 게시물 번호
		//int end = pageNum * pageSize; //마지막 게시물 번호
		map.put("start", start);
		map.put("pageSize", pageSize);
		/* 페이지 처리 끝 */
		
		//게시물 목록 받기
		List<MVCBoardDTO> boardLists = dao.selectListPage(map);
		dao.close();
		
		//뷰에 전달할 매개변수 추가
		String pagingImg = BoardPage.pagingStr(totalCount, pageSize, blockPage, pageNum, "../mvcboard/list.do");
		map.put("pagingImg", pagingImg);
		map.put("totalCount", totalCount);
		map.put("pageSize", pageSize);
		map.put("pageNum", pageNum);
		
		//전달할 데이터를 request영역에 저장 후 List.jsp로 포워드
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		req.getRequestDispatcher("/14MVCBoard/List.jsp").forward(req, resp);
		
		
	}
}
